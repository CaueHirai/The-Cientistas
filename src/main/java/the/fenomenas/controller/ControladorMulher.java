/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.controller;

import the.fenomenas.dto.MulherDTO;
import the.fenomenas.model.Mulher;
import the.fenomenas.model.Cliente;
import the.fenomenas.service.MulherDAO;
import the.fenomenas.service.ClienteDAO;
import the.fenomenas.service.CarreiraDAO;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author aluno
 */
@Controller
@RequestMapping("/mulher")
public class ControladorMulher {
    
    private final MulherDAO dao;
    private final ClienteDAO clienteDAO;
    private final CarreiraDAO carreiraDAO;
    public ControladorMulher(MulherDAO dao, ClienteDAO clienteDAO, CarreiraDAO carreiraDAO) {
        this.dao = dao;
        this.clienteDAO = clienteDAO;
        this.carreiraDAO = carreiraDAO;
    }
    
    
    @Autowired
    private HttpSession sessao;
    
     @PostMapping("/tab")
    public String exibirTabela(Model model, @RequestParam Map<String, String> inputs){
        
        try{
            return("");
        }
        catch(Exception ex){
            model.addAttribute("msg", "Erro ao exibir tabela:" + ex.getMessage());
            return("mensagem");
        }
        
    }
    
    @PostMapping("/cad")
    public String cadastrar(@ModelAttribute MulherDTO dto, Model model){
        try {
            this.dao.gravar(dto);
            model.addAttribute("msg", "Cadastrado com sucesso");
        }
        catch(Exception ex){
            model.addAttribute("msg", "Erro ao cadastrar:" + ex.getMessage());
        }
        
        return("mensagem");
    }
    
    @PostMapping("/getmulher")
    public String getUser(Model model, @RequestParam Integer codigo){
        Mulher mulher = dao.findByCodigo(codigo);
        
        model.addAttribute("mulher", mulher);
                
        return("mostrarUsuario");
    }
}
