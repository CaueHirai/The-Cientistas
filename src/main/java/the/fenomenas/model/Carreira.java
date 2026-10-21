/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
/**
 *
 * @author aluno
 */
@Entity
@Table(name = "carreira")
public class Carreira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", insertable = false, updatable = false)
    private Integer codigo;
    @ManyToOne
    @JoinColumn(name = "codMulher", nullable = false) 
    private Mulher mulher;
    @ManyToOne
    @JoinColumn(name = "codCliente", nullable = false) 
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "codArea", nullable = false) 
    private AreaConhecimento area;

    public Carreira(Integer codigo, Mulher mulher, Cliente cliente, AreaConhecimento area) {
        this.codigo = codigo;
        this.mulher = mulher;
        this.cliente = cliente;
        this.area = area;
    }
    
    public Carreira(){
        
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Mulher getMulher() {
        return mulher;
    }

    public void setMulher(Mulher mulher) {
        this.mulher = mulher;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public AreaConhecimento getArea() {
        return area;
    }

    public void setArea(AreaConhecimento area) {
        this.area = area;
    }
    
    
}
