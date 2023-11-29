package com.productos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productos.entities.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
    
}
