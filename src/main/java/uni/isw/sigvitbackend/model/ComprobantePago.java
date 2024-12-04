package uni.isw.sigvitbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;
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
@Table(name = "comprobante_pago")
public class ComprobantePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_comprobante")
    private int nroComprobante;
    @Column(name = "fecha",nullable = true)
    private Timestamp fecha;
    @OneToMany(mappedBy = "lineaReciboId.comprobantePago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaRecibo> lineasRecibo;
    
    public double total(){
        double total = 0;
        for(LineaRecibo lr : lineasRecibo){
            total += lr.subtotal();
        }
        return total;
    }
}
