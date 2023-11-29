package com.productos.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.productos.dto.ProductosDTO;
import com.productos.repositories.ProductosRepository;
import com.productos.services.ProductosService;

@ExtendWith(MockitoExtension.class)
class DeleteProductEndpointTest {
    
    @Mock
    private ProductosService productosService;

    @Mock
    private ProductosRepository productosRepository;

    @InjectMocks
    private ProductoController productoController;

    private final Long existingProductId = 1L;

    @Test
void testDeleteProduct_Success() {
    when(productosService.deleteProduct(existingProductId)).thenReturn(new ProductosDTO());

    ResponseEntity<Object> response = productoController.deleteProduct(existingProductId);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    ProductosDTO deletedProductResponse = (ProductosDTO) response.getBody();
    assertNotNull(deletedProductResponse);
}
}
