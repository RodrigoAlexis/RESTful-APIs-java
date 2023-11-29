package com.productos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.productos.dto.ProductosDTO;
import com.productos.services.ProductosService;

@RestController
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);
    private static final String MESSAGE_BAD_REQUEST = "La información que envió no tiene el formato correcto.";

    @Autowired
    private ProductosService productosService;

    private boolean validaPeticion(ProductosDTO productosDTO){

        boolean valida = true;
        if(productosDTO.getNombre() == null || productosDTO.getPrecio() == null){
            valida = false;
        }
        return valida;
    }

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
        if(!validaPeticion(product)){
            logger.error("Producto invalido", new Exception("El objeto recibido no corresponde a un producto valido"));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_BAD_REQUEST);
        }else{
            return  ResponseEntity.ok(productosService.addProduct(product));
        }
    }

    @PutMapping(value = "productos/{id:[\\d]+}")
    public  ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductosDTO product){
        if (!validaPeticion(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MESSAGE_BAD_REQUEST);
        }else{
            return ResponseEntity.ok(productosService.updateProduct(id, product));
        }
    }

    @DeleteMapping(value = "productos/{id:[\\d]+}")
    public  ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productosService.deleteProduct(id));
    }





    
}
