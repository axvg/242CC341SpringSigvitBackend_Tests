package uni.isw.sigvitbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineaReciboId implements Serializable {
  @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nro_comprobante", referencedColumnName = "nro_comprobante")
    ComprobantePago comprobantePago;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;
}
