package com.productos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
class UpdateProductTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private ProductosServiceImpl productosService;

    @Test
    void testUpdateProductSuccess() {
        Long id = 1L;
        ProductosDTO productDTO = new ProductosDTO();
        productDTO.setNombre("Nuevo Producto");
        productDTO.setPrecio(BigDecimal.valueOf(10.5));

        Productos productoExistente = new Productos();
        productoExistente.setIdproducto(id);

        when(repository.findById(id)).thenReturn(Optional.of(productoExistente));
        when(repository.save(any(Productos.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductosDTO result = productosService.updateProduct(id, productDTO);
        assertNotNull(result);
        assertEquals(id, result.getIdProducto());
        assertEquals(productDTO.getNombre(), result.getNombre());
        assertEquals(productDTO.getPrecio(), result.getPrecio());

        verify(repository, times(1)).save(any(Productos.class));
    }

    @Test
    void testUpdateProductNotExist() {
        Long id = 1L;
        ProductosDTO productDTO = new ProductosDTO();
        productDTO.setNombre("Nuevo Producto");
        productDTO.setPrecio(BigDecimal.valueOf(10.5));

        // Devolver Optional vacío
        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> productosService.updateProduct(id, productDTO));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(repository, never()).save(any(Productos.class));
    }
    
    @Test
    void testUpdateProductWithNullFields(){
        ProductosDTO productDTO = new ProductosDTO();
        productDTO.setNombre(null);
        productDTO.setPrecio(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> productosService.addProduct(productDTO));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());

        assertEquals("Nombre y precio son campos obligatorios.", exception.getReason());

        verify(repository, never()).save(any(Productos.class));
    }
}
