/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isw.sigvitbackend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    
}
