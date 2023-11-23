package com.mcsv.inventarioservice.service;

import com.mcsv.inventarioservice.dto.InventarioResponse;
import com.mcsv.inventarioservice.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Transactional(readOnly = true)
    public List<InventarioResponse> isInStock(List<String> codSkuList){
        return inventarioRepository.findByCodigoSku(codSkuList).stream()
                .map(inventario ->
                        InventarioResponse.builder()
                                .codigoSku(inventario.getCodigoSku())
                                .inStock(inventario.getCantidad() > 0)
                                .build()
                ).collect(Collectors.toList());
    }
}
