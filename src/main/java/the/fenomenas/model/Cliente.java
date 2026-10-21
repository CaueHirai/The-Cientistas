/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the.fenomenas.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", insertable = false, updatable = false)
    private Integer codigo;
    @Column(name = "pergunta", nullable = false, length = 200)
    private String pergunta;
    @Column(name = "a", nullable = true, length = 100)
    private String a;
    @Column(name = "b", nullable = true, length = 100)
    private String b;
    @Column(name = "c", nullable = true, length = 100)
    private String c;
    @Column(name = "d", nullable = true, length = 100)
    private String d;
    @Column(name = "e", nullable = true, length = 100)
    private String e;
// Um mulher tem vários bens, deve haver na tabela bem um camplo chamado mulher
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Carreira> carreiras;

    public Cliente(Integer codigo, String pergunta, String a, String b, String c, String d, String e, List<Carreira> carreiras) {
        this.codigo = codigo;
        this.pergunta = pergunta;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.carreiras = carreiras;
    }

    public Cliente() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public List<Carreira> getCarreiras() {
        return carreiras;
    }

    public void setCarreiras(List<Carreira> carreiras) {
        this.carreiras = carreiras;
    }
    
    
}
