package com.mcsv.inventarioservice.controller;

import com.mcsv.inventarioservice.dto.InventarioResponse;

import com.mcsv.inventarioservice.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventarioResponse> isInStock(@RequestParam List<String> codigoSkuList){
        return inventarioService.isInStock(codigoSkuList);
    }
}
