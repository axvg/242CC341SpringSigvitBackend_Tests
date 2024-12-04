package uni.isw.sigvitbackend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @Column(name = "ruc",nullable = false)
    private long ruc;
    @Column(name = "nombre_empresa",nullable = false)
    private String nombreEmpresa;
    @Column(name = "direccion",nullable = false)
    private String direccion;
    @Column(name = "email",nullable = false)
    private String email;
}
