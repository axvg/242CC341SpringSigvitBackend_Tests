package uni.isw.sigvitbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente",nullable = false)
    private int idCliente;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "email",nullable = false)
    private String email;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Persona persona;
}
