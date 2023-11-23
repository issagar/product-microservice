package com.auth.jwt.authservice.security;


import com.auth.jwt.authservice.dto.RequestDto;
import com.auth.jwt.authservice.entity.AuthUser;
import com.netflix.discovery.converters.Auto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret; //signature

    @Autowired
    private RouteValidate routeValidate;

    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        System.out.println("secret" + secret);
    }

    public String createToken(AuthUser authUser){
        Map<String, Object> claims = new HashMap<>();
        claims = (Map<String, Object>) Jwts.claims().setSubject(authUser.getUserName());
        claims.put("id", authUser.getIdAdmin());
        claims.put("role", authUser.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);
        System.out.println("secret " + secret);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .compact();

    }

    public boolean validate(String token, RequestDto requestDto) {
        try {
            Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token);
        } catch (Exception exception) {
            return false;
        }
        if(!isAdmin(token) && routeValidate.isAdmin(requestDto)){
            return false;
        }
        return true;
    }
    private boolean isAdmin(String token){
        return Jwts.parser().setSigningKey(secret)
                .build().parseClaimsJws(token).getBody().get("role").equals("admin");
    }
    public String getUserNameFromToken(String token){
        try{

            return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
        }catch (Exception exception){
            return "Bad token";
        }

    }
}
