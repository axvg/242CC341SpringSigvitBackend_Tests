package uni.isw.sigvitbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona",nullable = false)
    private int idPersona;
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "apellido_paterno",nullable = false)
    private String apellidoPaterno;
    @Column(name = "apellido_materno",nullable = false)
    private String apellidoMaterno;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
}