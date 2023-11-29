package com.productos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProductosDTOTest {

    ProductosDTO productosDTO = new ProductosDTO();

    @Test
    void buildProductosDTO() {
        Long idProducto = 1L;
        String nombre = "Producto de prueba";
        String descripcion = "Descripción del producto";
        BigDecimal precio = new BigDecimal("19.99");
        int stock = 10;

        
        productosDTO.setIdProducto(idProducto);
        productosDTO.setNombre(nombre);
        productosDTO.setDescripcion(descripcion);
        productosDTO.setPrecio(precio);
        productosDTO.setStock(stock);

        // Verificar que los valores se hayan establecido correctamente
        assertEquals(idProducto, productosDTO.getIdProducto());
        assertEquals(nombre, productosDTO.getNombre());
        assertEquals(descripcion, productosDTO.getDescripcion());
        assertEquals(precio, productosDTO.getPrecio());
        assertEquals(stock, productosDTO.getStock());
    } 

    @Test
    void compareEqualityProductsDTO() {
        ProductosDTO productosDTO1 = new ProductosDTO();
        productosDTO1.setIdProducto(1L);
        productosDTO1.setNombre("Producto A");
        
        ProductosDTO productosDTO2 = new ProductosDTO();
        productosDTO2.setIdProducto(1L);
        productosDTO2.setNombre("Producto A");
        
        assertEquals(productosDTO1, productosDTO2);
    }

    @Test
    void compareInequalityProductsDTO() {
        ProductosDTO productosDTO1 = new ProductosDTO();
        productosDTO1.setIdProducto(1L);
        productosDTO1.setNombre("Producto A");
        
        ProductosDTO productosDTO2 = new ProductosDTO();
        productosDTO2.setIdProducto(2L);
        productosDTO2.setNombre("Producto B");
        
        assertNotEquals(productosDTO1, productosDTO2);
    }
}
