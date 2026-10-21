/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.service;

import the.fenomenas.dto.AreaConhecimentoDTO;
import the.fenomenas.model.AreaConhecimento;
import the.fenomenas.repository.AreaConhecimentoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author aluno
 */
@Service
public class AreaConhecimentoDAO {
    private final AreaConhecimentoRepository repository;

    public AreaConhecimentoDAO(AreaConhecimentoRepository repository) {
        this.repository = repository;
    }
    
    public AreaConhecimento toAreaConhecimento(AreaConhecimentoDTO dto) {
        AreaConhecimento m = new AreaConhecimento();
        
        if(dto.codigo() != null)
            m.setCodigo(dto.codigo());
        m.setNome(dto.nome());
        m.setPesoMemoria(dto.pesoMemoria());
        m.setPesoAnalise(dto.pesoAnalise());
        m.setPesoCriatividade(dto.pesoCriatividade());
        m.setPesoRigor(dto.pesoRigor());
        m.setPesoRaciocinio(dto.pesoRaciocinio());
        m.setPesoCalculo(dto.pesoCalculo());
        return m;
    }
    
    public void gravar(AreaConhecimentoDTO dto){
        this.repository.save(toAreaConhecimento(dto));
    }
    
    public AreaConhecimento findByCodigo(Integer codigo){
        return this.repository.findById(codigo).orElseThrow();
    }
    
    public List<AreaConhecimento> listar() {
        return this.repository.findAll();
    }
}
