package com.productos.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProductosTest {

    Productos producto = new Productos();
    
    @Test
    void gettersAndSetters(){

        producto.setIdproducto(1L);
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripción del producto");
        producto.setPrecio(new BigDecimal("10.50"));
        producto.setStock(100);

        // Verificar que los valores se establecieron correctamente
        assertEquals(1L, producto.getIdproducto());
        assertEquals("Producto1", producto.getNombre());
        assertEquals("Descripción del producto", producto.getDescripcion());
        assertEquals(new BigDecimal("10.50"), producto.getPrecio());
        assertEquals(100, producto.getStock());

    }

    @Test
    void validateColumnConstraints() {
        producto.setIdproducto(1L);
        producto.setNombre("Nombre del producto");
        producto.setDescripcion(null);
        producto.setPrecio(new BigDecimal("10.50"));
        producto.setStock(100);

        // Verificar restricciones de columna
        assertNotNull(producto.getIdproducto());
        assertNotNull(producto.getNombre());
        assertNull(producto.getDescripcion());
        assertNotNull(producto.getPrecio());
        assertNotNull(producto.getStock());
        assertTrue(producto.getNombre().length() <= 100);
    }

    //Revisar valores nulos
    // @Test
    // void validateNonNullValues() {

    //     // Intentar establecer un nombre nulo debería arrojar una excepción
    //     assertThrows(IllegalArgumentException.class, () -> producto.setNombre(null));
    // }
}
