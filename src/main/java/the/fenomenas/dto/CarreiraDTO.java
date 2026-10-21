/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.dto;

import the.fenomenas.model.AreaConhecimento;
import the.fenomenas.model.Mulher;
import the.fenomenas.model.Cliente;

/**
 *
 * @author aluno
 */
public record CarreiraDTO (Integer codigo, Mulher mulher, Cliente cliente, AreaConhecimento area) {
    
}
