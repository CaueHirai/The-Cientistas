/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.service;

import the.fenomenas.dto.CarreiraDTO;
import the.fenomenas.model.Carreira;
import the.fenomenas.model.Cliente;
import the.fenomenas.model.Carreira;
import the.fenomenas.repository.CarreiraRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author aluno
 */
@Service
public class CarreiraDAO {
    private final CarreiraRepository repository;

    public CarreiraDAO(CarreiraRepository repository) {
        this.repository = repository;
    }
    
    public Carreira toCarreira(CarreiraDTO dto) {
        Carreira c = new Carreira();
        
        if(dto.codigo() != null)
            c.setCodigo(dto.codigo());
        c.setMulher(dto.mulher());
        c.setCliente(dto.cliente());
        c.setArea(dto.area());
        return c;
    }
    
    

}
