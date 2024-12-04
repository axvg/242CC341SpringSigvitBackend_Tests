package uni.isw.sigvitbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Empleado{
    private int idEmpleado;
    private double salario;
    private Date fechaContratacion;
    private String horario;
    private String password;
    private String rol;
    private String email;
    private Persona persona;
}
