package com.productos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
class GetProductsTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private ProductosServiceImpl productosService;

    @Test
    void testGetProducts() {
        // Configuración del repositorio simulado
        when(repository.findAll()).thenReturn(Arrays.asList(new Productos(), new Productos()));

        List<ProductosDTO> result = productosService.getProducts();

        // Verificar resultado
        assertEquals(2, result.size());

        // Verificar que se llamó al método findAll() del repositorio
        verify(repository, times(1)).findAll();
    }

   


    
}
