/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uni.isw.sigvitbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isw.sigvitbackend.model.Proveedor;

/**
 *
 * @author user0
 */
public interface ProveedorRepository extends JpaRepository<Proveedor,Long>{
    
}
