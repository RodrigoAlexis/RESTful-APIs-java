package com.productos.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import com.productos.dto.ProductosDTO;
import com.productos.entities.Productos;
import com.productos.mappers.ProductosMapper;
import com.productos.repositories.ProductosRepository;
import com.productos.services.ProductosService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(ProductosServiceImpl.class);
    private static final String MESSAGE_NOT_FOUND = "Producto no existente.";
    private static final String MESSAGE_BAD_REQUEST = "Nombre y precio son campos obligatorios.";



    @Override
    public List<ProductosDTO> getProducts() {
        logger.info("Entro metodo getProducts");
        
        return repository.findAll()
            .stream()
            .map(ProductosMapper::mapProductos)
            .collect(Collectors.toList());
    }

    @Override
    public ProductosDTO getProduct(Long id) {
        logger.info("Entro metodo getProduct");

        Productos productos = repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_NOT_FOUND));
        return ProductosMapper.mapProductos(productos);
    }

    @Override
    public ProductosDTO addProduct(ProductosDTO product) {
        logger.info("Entro metodo addProduct");

        if (product.getNombre() == null || product.getPrecio() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MESSAGE_BAD_REQUEST);
        }else{
            final Productos productos = ProductosMapper.mapProductosDTO(product);
            return ProductosMapper.mapProductos(this.repository.save(productos));
        }
    }

    @Override
    public ProductosDTO updateProduct(Long id, ProductosDTO product) {
        logger.info("Entro metodo updateProduct");

        return this.repository.findById(id)
            .map( productoActual -> {

                if (product.getNombre() == null || product.getPrecio() == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MESSAGE_BAD_REQUEST);
                }else{
                    BeanUtils.copyProperties(product, productoActual, "id");
                    Productos productoActualizado = this.repository.save(productoActual);
                    return ProductosMapper.mapProductos(productoActualizado);
                }
                
            })
            .orElseThrow(() -> {
                logger.error("Error: Producto no encontrado.");
                return new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_NOT_FOUND);
            });
        }

    @Override
    public ProductosDTO deleteProduct(Long id) {
        logger.info("Entro metodo deleteProduct");
        
        return repository.findById(id)
            .map(producto -> {
                repository.delete(producto);
                return ProductosMapper.mapProductos(producto);
            })
            .orElseThrow(() -> {
                logger.error("Error: Producto no encontrado.");
                return new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_NOT_FOUND);
            });
    }
}
