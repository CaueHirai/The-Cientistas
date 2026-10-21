/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.service;

import the.fenomenas.dto.ClienteDTO;
import the.fenomenas.model.Cliente;
import the.fenomenas.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author aluno
 */
@Service
public class ClienteDAO {
    private final ClienteRepository repository;

    public ClienteDAO(ClienteRepository repository) {
        this.repository = repository;
    }
    
    public Cliente toCliente(ClienteDTO dto) {
        Cliente q = new Cliente();
        
        if(dto.codigo()>0)
            q.setCodigo(dto.codigo());
        q.setPergunta(dto.pergunta());
        q.setA(dto.a());
        q.setB(dto.b());
        q.setC(dto.c());
        q.setD(dto.d());
        q.setE(dto.e());
        q.setCarreiras(dto.carreiras());
        return q;
    }
    
    public void gravar(ClienteDTO dto){
        this.repository.save(toCliente(dto));
    }
    
    public List<Cliente> listar() {
        return this.repository.findAll();
    }
    
    public Cliente findByCodigo(int codigo){
        return this.repository.findById(codigo).orElseThrow();
    }
}
