package com.productos.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.productos.dto.ProductosDTO;
import com.productos.entities.Productos;

class ProductosMapperTest {

    @Test
    void mapProductosDTO() {

        // Instancia de Productos
        Productos productos = new Productos();
        productos.setIdproducto(1L);
        productos.setNombre("Producto de prueba");
        productos.setDescripcion("Descripción de prueba");
        productos.setPrecio(new BigDecimal("19.99"));
        productos.setStock(10);

        // Productos a ProductosDTO
        ProductosDTO productosDTO = ProductosMapper.mapProductos(productos);

        // Verifica que sea lo esperado
        assertNotNull(productosDTO);
        assertEquals(productos.getIdproducto(), productosDTO.getIdProducto());
        assertEquals(productos.getNombre(), productosDTO.getNombre());
        assertEquals(productos.getDescripcion(), productosDTO.getDescripcion());
        assertEquals(productos.getPrecio(), productosDTO.getPrecio());
        assertEquals(productos.getStock(), productosDTO.getStock());
    }

    @Test
    void mapProductos() {

        ProductosDTO productosDTO = new ProductosDTO();
        productosDTO.setIdProducto(1L);
        productosDTO.setNombre("Producto de prueba");
        productosDTO.setDescripcion("Descripción de prueba");
        productosDTO.setPrecio(new BigDecimal("19.99"));
        productosDTO.setStock(10);

        // ProductosDTO a Productos
        Productos productos = ProductosMapper.mapProductosDTO(productosDTO);

        assertNotNull(productos);
        assertEquals(productosDTO.getIdProducto(), productos.getIdproducto());
        assertEquals(productosDTO.getNombre(), productos.getNombre());
        assertEquals(productosDTO.getDescripcion(), productos.getDescripcion());
        assertEquals(productosDTO.getPrecio(), productos.getPrecio());
        assertEquals(productosDTO.getStock(), productos.getStock());
    }
    
}
