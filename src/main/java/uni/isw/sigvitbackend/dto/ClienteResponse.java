/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import uni.isw.sigvitbackend.model.Cliente;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    private int idCliente;
    private int idPersona;
    private String email;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String telefono;
    
    public static ClienteResponse fromEntity(Cliente cliente){
        return ClienteResponse.builder()
                .idCliente(cliente.getIdCliente())
                .idPersona(cliente.getPersona().getIdPersona())
                .email(cliente.getEmail())
                .nombre(cliente.getPersona().getNombre())
                .apellidoMaterno(cliente.getPersona().getApellidoMaterno())
                .apellidoPaterno(cliente.getPersona().getApellidoPaterno())
                .direccion(cliente.getPersona().getDireccion())
                .telefono(cliente.getPersona().getTelefono())
                .build();
    }
    
    public static List<ClienteResponse> fromEntities(List<Cliente> clientes){
        return clientes.stream()
                .map(ClienteResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
