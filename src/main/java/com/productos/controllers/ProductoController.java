package com.productos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productos.dto.ProductosDTO;
import com.productos.services.ProductosService;

@RestController
public class ProductoController {

    @Autowired
    private ProductosService productosService;

    @GetMapping(value = "productos")
    public  ResponseEntity<Object> products() {
        return ResponseEntity.ok(productosService.getProducts());
    }

    @GetMapping(value = "productos/{id:[\\d]+}")
    public ResponseEntity<Object> productsById(@PathVariable Long id) {
        return ResponseEntity.ok(productosService.getProduct(id));
    }

    @PostMapping(value = "productos")
    public  ResponseEntity<Object> newProduct(@RequestBody ProductosDTO product) {
        return  ResponseEntity.ok(productosService.addProduct(product));
    }

    @PutMapping(value = "productos/{id:[\\d]+}")
    public  ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductosDTO product){
        return ResponseEntity.ok(productosService.updateProduct(id, product));
    }

    @DeleteMapping(value = "productos/{id:[\\d]+}")
    public  ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productosService.deleteProduct(id));
    }





    
}
