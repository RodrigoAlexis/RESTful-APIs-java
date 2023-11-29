package com.productos.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.productos.dto.ProductosDTO;
import com.productos.entities.Productos;
import com.productos.repositories.ProductosRepository;
import com.productos.services.impl.ProductosServiceImpl;

@ExtendWith(MockitoExtension.class)
class GetEndpointTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private ProductosServiceImpl productosService;

    @Test
    void testGetProducts() {
        List<Productos> productosList = Arrays.asList(
                new Productos(1L, "Producto1", "Descripción1", BigDecimal.TEN, 5),
                new Productos(2L, "Producto2", "Descripción2", BigDecimal.valueOf(15.5), 10)
        );
        when(repository.findAll()).thenReturn(productosList);

        List<ProductosDTO> result = productosService.getProducts();

        // Verificar que el resultado tiene el mismo tamaño que la lista simulada
        assertEquals(productosList.size(), result.size());
    }
}
