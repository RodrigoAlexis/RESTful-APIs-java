package com.productos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productos.dto.ProductosDTO;

@Service
public interface ProductosService {
    
    public List<ProductosDTO> getProducts();
    public ProductosDTO getProduct(Long id);
    public ProductosDTO addProduct(ProductosDTO product);
    public ProductosDTO updateProduct(Long id, ProductosDTO product);
    public ProductosDTO deleteProduct(Long id);
}
