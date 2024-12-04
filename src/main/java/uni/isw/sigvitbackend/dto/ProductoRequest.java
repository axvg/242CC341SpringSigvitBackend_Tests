/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest {
    private int idProducto;
    private String descripcion;
    private String nombre;
    private double precioVenta;
    private double precioCompra;
    private int stock;
    private int idCategoria;
    private long rucProveedor;
    private String imagen;
}
