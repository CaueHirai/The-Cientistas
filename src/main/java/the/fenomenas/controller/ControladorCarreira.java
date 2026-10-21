/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.controller;

import the.fenomenas.dto.CarreiraDTO;
import the.fenomenas.model.Mulher;
import the.fenomenas.model.Universidade;
import the.fenomenas.service.MulherDAO;
import the.fenomenas.service.ClienteDAO;
import the.fenomenas.service.CarreiraDAO;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
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
@RequestMapping("/carreira")
public class ControladorCarreira {
    @Autowired
    private HttpSession sessao;
    
    private final MulherDAO mulherDAO;
    private final CarreiraDAO dao;
    private final ClienteDAO clienteDAO;
    public ControladorCarreira(CarreiraDAO dao, MulherDAO mulherDAO, ClienteDAO clienteDAO) {
        this.mulherDAO = mulherDAO;
        this.clienteDAO = clienteDAO;
        this.dao = dao;
    }
    

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
}
