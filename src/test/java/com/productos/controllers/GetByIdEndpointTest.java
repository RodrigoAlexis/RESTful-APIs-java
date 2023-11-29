package com.productos.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

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
class GetByIdEndpointTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private ProductosServiceImpl productosService;

    @Test
    void testGetProduct() {
        Productos productoSimulado = new Productos(1L, "Producto1", "Descripción1", BigDecimal.TEN, 5);
        when(repository.findById(1L)).thenReturn(Optional.of(productoSimulado));

        ProductosDTO result = productosService.getProduct(1L);

        assertEquals(productoSimulado.getIdproducto(), result.getIdProducto());
        assertEquals(productoSimulado.getNombre(), result.getNombre());
    }

    @Test
    void testGetProductNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> productosService.getProduct(1L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
    
}
