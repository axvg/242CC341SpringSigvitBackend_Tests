/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.isw.sigvitbackend.model.Producto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {
    private int idProducto;
    private String descripcion;
    private String nombre;
    private double precioVenta;
    private int stock;
    private String categoriaNombre;
    private String categoriaDescripcion;
    private String nombreProveedor;
    private String imagen;
    
    public static ProductoResponse fromEntity(Producto producto){
        return ProductoResponse.builder()
                .idProducto(producto.getIdProducto())
                .descripcion(producto.getDescripcion())
                .nombre(producto.getNombre())
                .precioVenta(producto.getPrecioVenta())
                .stock(producto.getStock())
                .categoriaNombre(producto.getCategoria().getNombre())
                .categoriaDescripcion(producto.getCategoria().getDescripcion())
                .nombreProveedor(producto.getProveedor().getNombreEmpresa())
                .imagen(producto.getImagen())
                .build();
    }
    
    public static List<ProductoResponse> fromEntities(List<Producto> productos){
        return productos.stream()
                .map(ProductoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
