package uni.isw.sigvitbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido",nullable = false)
    Integer idPedido;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    Cliente cliente;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nro_comprobante", referencedColumnName = "nro_comprobante")
    ComprobantePago comprobantePago;
    
    @Column(name = "direccion_entrega", nullable = true)
    String direccionEntrega;
}
