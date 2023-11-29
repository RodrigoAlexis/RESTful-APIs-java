package com.productos.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductosDTO {

   private Long idProducto;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private int stock;
}
