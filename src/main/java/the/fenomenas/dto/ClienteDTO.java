/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.dto;
import java.util.List;
import the.fenomenas.model.Carreira;

/**
 *
 * @author aluno
 */
public record ClienteDTO (Integer codigo, String pergunta, String a, String b, String c, String d, String e, List<Carreira> carreiras) {
    
}
