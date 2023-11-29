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
class AddProductTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private ProductosServiceImpl productosService;

    @Test
    void testAddProductSuccess() {

        ProductosDTO productDTO = new ProductosDTO();
        productDTO.setNombre("Producto de prueba");
        productDTO.setPrecio(BigDecimal.valueOf(10.0));
        productDTO.setDescripcion(null);
        productDTO.setStock(23);

        when(repository.save(any(Productos.class))).thenAnswer(invocation -> {
            Productos productoGuardado = invocation.getArgument(0);
            productoGuardado.setIdproducto(1L); 
            return productoGuardado;
        });

        ProductosDTO result = productosService.addProduct(productDTO);

        // El ID no debería ser nulo después de guardar
        assertNotNull(result.getIdProducto());  

        verify(repository, times(1)).save(any(Productos.class));
    }

    @Test
    void testAddProductWithNullFields() {
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
