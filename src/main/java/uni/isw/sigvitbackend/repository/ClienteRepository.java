/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uni.isw.sigvitbackend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import uni.isw.sigvitbackend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
    Optional<Cliente> findByEmail(String email);
}
