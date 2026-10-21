/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package the.fenomenas.repository;

import the.fenomenas.model.Mulher;
import the.fenomenas.model.Cliente;
import the.fenomenas.model.Carreira;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreiraRepository extends JpaRepository<Carreira, Integer> {
    
    List<Carreira> findByMulher(Mulher c);
    Optional<Carreira> findByMulherAndCliente(Mulher mulher, Cliente cliente);
}
