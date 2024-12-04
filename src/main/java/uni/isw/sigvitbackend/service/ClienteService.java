/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigvitbackend.dto.ClienteRequest;
import uni.isw.sigvitbackend.dto.ClienteResponse;
import uni.isw.sigvitbackend.model.Cliente;
import uni.isw.sigvitbackend.model.Persona;
import uni.isw.sigvitbackend.repository.ClienteRepository;
import uni.isw.sigvitbackend.repository.PersonaRepository;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PersonaRepository personaRepository;
    
    public List<ClienteResponse> listClientes(){
        return ClienteResponse.fromEntities(clienteRepository.findAll());
    }
    public ClienteResponse findCliente(Integer id){
        return ClienteResponse.fromEntity(clienteRepository.findById(id).get());
    }
    
    public ClienteResponse findCliente(String email){
        return ClienteResponse.fromEntity(clienteRepository.findByEmail(email).get());
    }
    public ClienteResponse insertCliente(ClienteRequest clienteRequest){
        Persona persona = null;
        if(clienteRequest.getIdPersona()==0){
            persona = new Persona(
                    clienteRequest.getIdPersona(),
                    clienteRequest.getNombre(),
                    clienteRequest.getApellidoPaterno(),
                    clienteRequest.getApellidoMaterno(),
                    clienteRequest.getFechaNacimiento(),
                    clienteRequest.getDireccion(),
                    clienteRequest.getTelefono()
            );
            personaRepository.save(persona);
        } else {
            persona = personaRepository.findById(clienteRequest.getIdPersona()).get();
        }
        if (persona == null)
            return new ClienteResponse();
        
        Cliente cliente = new Cliente(
                clienteRequest.getIdCliente(),
                clienteRequest.getPassword(),
                clienteRequest.getEmail(),
                persona
        );
        
        clienteRepository.save(cliente);
        return ClienteResponse.fromEntity(cliente);
    }
    public ClienteResponse updateCliente(ClienteRequest clienteRequest){
        Persona persona = new Persona(
                clienteRequest.getIdPersona(),
                clienteRequest.getNombre(),
                clienteRequest.getApellidoPaterno(),
                clienteRequest.getApellidoMaterno(),
                clienteRequest.getFechaNacimiento(),
                clienteRequest.getDireccion(),
                clienteRequest.getTelefono()
        );
        
        personaRepository.save(persona);
  
        Cliente cliente = new Cliente(
                clienteRequest.getIdCliente(),
                clienteRequest.getPassword(),
                clienteRequest.getEmail(),
                persona
        );
        
        clienteRepository.save(cliente);
        return ClienteResponse.fromEntity(cliente);
    }
    public void deleteCliente(int id){
        clienteRepository.deleteById(id);
    }
    public void deleteClienteByEmail(String email){
        Cliente c = clienteRepository.findByEmail(email).get();
        clienteRepository.deleteById(c.getIdCliente());
    }
}
