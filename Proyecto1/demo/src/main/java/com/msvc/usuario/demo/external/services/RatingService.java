package com.msvc.usuario.demo.external.services;

import com.msvc.usuario.demo.entity.Calification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@FeignClient(name= "CALIFICATION-SERVICE")
public interface RatingService {

    @PostMapping
    public ResponseEntity<Calification> saveCalification(Calification calification);
    @PostMapping("/ratings/{calificationId}")
    public ResponseEntity<Calification> updateCalification(@PathVariable String calificationId, Calification calification);

    @PostMapping("/calification/{calificationId}")
    public void removeCalification(@PathVariable String calificationId);
}
