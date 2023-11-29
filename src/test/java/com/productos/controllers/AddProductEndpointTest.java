package com.productos.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.productos.dto.ProductosDTO;
import com.productos.entities.Productos;
import com.productos.repositories.ProductosRepository;
import com.productos.services.impl.ProductosServiceImpl;

@ExtendWith(MockitoExtension.class)
class AddProductEndpointTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private ProductosServiceImpl productosService;

    @Test
    void testAddProductSuccess() {
        ProductosDTO productDTO = new ProductosDTO(1L, "NuevoProducto1", "Descripción1", BigDecimal.valueOf(20.00), 15);
        Productos productoGuardado = new Productos(2L, "NuevoProducto2", "Descripción2", BigDecimal.valueOf(22.00), 10);
        when(repository.save(any(Productos.class))).thenReturn(productoGuardado);

        ProductosDTO result = productosService.addProduct(productDTO);

        assertEquals(productoGuardado.getIdproducto(), result.getIdProducto());
        assertEquals(productoGuardado.getNombre(), result.getNombre());
    }

    @Test
    void testAddProductWithNullFields() {
        ProductosDTO productDTO = new ProductosDTO(1L, null, null, null,0);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> productosService.addProduct(productDTO));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}
