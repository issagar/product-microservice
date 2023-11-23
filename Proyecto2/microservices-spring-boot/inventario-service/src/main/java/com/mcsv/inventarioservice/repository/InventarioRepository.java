package com.mcsv.inventarioservice.repository;

import com.mcsv.inventarioservice.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List <Inventario> findByCodigoSku(List<String> codigoSku);
}
