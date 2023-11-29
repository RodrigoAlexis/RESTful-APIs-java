package com.productos.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.productos.dto.ProductosDTO;
import com.productos.repositories.ProductosRepository;
import com.productos.services.ProductosService;

@ExtendWith(MockitoExtension.class)
class UpdateProductEndpointTest {

    @Mock
    private ProductosService productosService;

    @Mock
    private ProductosRepository productosRepository;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void testUpdateProductSuccess() {
        when(productosService.updateProduct(Mockito.anyLong(), Mockito.any())).thenReturn(new ProductosDTO(1L, "ProductoActualizado", "Nueva descripción", BigDecimal.valueOf(30.00), 20));

        ResponseEntity<Object> response = productoController.updateProduct(1L, new ProductosDTO(1L, "ProductoActualizado2", "Nueva descripción2", BigDecimal.valueOf(30.00), 20));

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateProductWithNullFields() {

        ProductosDTO productDTO = new ProductosDTO(1L, null, null, null,0);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> productoController.updateProduct(1L, productDTO));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        
        assertEquals("La información que envió no tiene el formato correcto.", exception.getReason());
    }

}
