package com.productos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.productos.dto.ProductosDTO;
import com.productos.entities.Productos;
import com.productos.repositories.ProductosRepository;
import com.productos.services.impl.ProductosServiceImpl;

@ExtendWith(MockitoExtension.class)
class GetProductTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private ProductosServiceImpl productosService;

     @Test
    void testGetProduct() {
        Long productId = 1L;
        Productos producto = new Productos();
        producto.setIdproducto(productId);

        when(repository.findById(productId)).thenReturn(Optional.of(producto));

        ProductosDTO result = productosService.getProduct(productId);

        assertEquals(productId, result.getIdProducto());

        verify(repository, times(1)).findById(productId);
    }

    @Test
    void testGetProductWhenProductDoesNotExist() {
        Long productId = 1L;

        when(repository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> productosService.getProduct(productId));

        verify(repository, times(1)).findById(productId);
    } 
}
