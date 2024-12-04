package uni.isw.sigvitbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto",nullable = false)
    private int idProducto;
    @Column(name = "descripcion", nullable = true)
    private String descripcion;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "precio_venta", nullable = false)
    private double precioVenta;
    @Column(name = "precio_compra", nullable = false)
    private double precioCompra;
    @Column(name = "stock", nullable = false)
    private int stock;
    @Column(name = "imagen")
    private String imagen;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ruc_proveedor", referencedColumnName = "ruc")
    private Proveedor proveedor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;
}
