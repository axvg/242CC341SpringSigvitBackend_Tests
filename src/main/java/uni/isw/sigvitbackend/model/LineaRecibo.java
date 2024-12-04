package uni.isw.sigvitbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "linea_recibo")
public class LineaRecibo {
    @EmbeddedId
    private LineaReciboId lineaReciboId;
    
    @Column(name = "cantidad",nullable = false)
    private int cantidad;
    @Column(name = "precio",nullable = false)
    private double precio;
    
    public double subtotal(){
        return cantidad * precio;
    }
}
