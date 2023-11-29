package com.productos.mappers;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import com.productos.dto.ProductosDTO;
import com.productos.entities.Productos;

public class ProductosMapper {
    
    private ProductosMapper(){
        //Constructor
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    static {
        MAPPER.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    public static ProductosDTO mapProductos(Productos productos){
        return MAPPER.map(productos, ProductosDTO.class);
    }

    public static Productos mapProductosDTO(ProductosDTO productosDTO){
        return MAPPER.map(productosDTO,Productos.class);
    }
}
