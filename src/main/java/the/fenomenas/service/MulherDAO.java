/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.service;

import the.fenomenas.dto.MulherDTO;
import the.fenomenas.model.Mulher;
import the.fenomenas.repository.MulherRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author aluno
 */
@Service
public class MulherDAO {
    private final MulherRepository repository;

    public MulherDAO(MulherRepository repository) {
        this.repository = repository;
    }
    
    public Mulher toMulher(MulherDTO dto) {
        Mulher m = new Mulher();
        
        if(dto.codigo() != null)
            m.setCodigo(dto.codigo());
        m.setNome(dto.nome());
        m.setMemoria(dto.memoria());
        m.setAnalise(dto.analise());
        m.setCriatividade(dto.criatividade());
        m.setRigor(dto.rigor());
        m.setRaciocinio(dto.raciocinio());
        m.setCalculo(dto.calculo());
        m.setInformacoes(dto.informacoes());
        return m;
    }
    
    public void gravar(MulherDTO dto){
        this.repository.save(toMulher(dto));
    }
    
    
    public Mulher findByCodigo(Integer codigo){
        return this.repository.findById(codigo).orElseThrow();
    }
    
    public List<Mulher> listar() {
        return this.repository.findAll();
    }
}
