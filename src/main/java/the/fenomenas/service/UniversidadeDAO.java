/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.service;

import the.fenomenas.dto.UniversidadeDTO;
import the.fenomenas.model.Universidade;
import the.fenomenas.repository.UniversidadeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author aluno
 */
@Service
public class UniversidadeDAO {
    private final UniversidadeRepository repository;

    public UniversidadeDAO(UniversidadeRepository repository) {
        this.repository = repository;
    }
    
    public Universidade toUniversidade(UniversidadeDTO dto) {
        Universidade u = new Universidade();
        
        if(dto.codigo() != null)
            u.setCodigo(dto.codigo());
        u.setNome(dto.nome());
        u.setRanking(dto.ranking());
        return u;
    }
    
    public void gravar(UniversidadeDTO dto){
        this.repository.save(toUniversidade(dto));
    }
    
    public Universidade findByCodigo(Integer codigo){
        return this.repository.findById(codigo).orElseThrow();
    }
    
    public List<Universidade> listar() {
        return this.repository.findAll();
    }
}
