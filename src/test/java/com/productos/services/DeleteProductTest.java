package com.productos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
class DeleteProductTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private ProductosServiceImpl productosService;

    @Test
    void testDeleteProductSuccess() {
        Long id = 1L;

        Productos productoExistente = new Productos();
        when(repository.findById(id)).thenReturn(Optional.of(productoExistente));

        ProductosDTO result = productosService.deleteProduct(id);

        verify(repository, times(1)).delete(productoExistente);

        // Verifica que el resultado es la representación del producto eliminado
        assertNotNull(result);
    }

    @Test
    void testDeleteProductNotExist() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> productosService.deleteProduct(id));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(repository, never()).delete(any(Productos.class));
    }
    
}
