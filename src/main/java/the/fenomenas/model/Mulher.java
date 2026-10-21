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
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "mulher")
public class Mulher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", insertable = false, updatable = false)
    private Integer codigo;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "memoria", nullable = false)
    private Integer memoria;
    @Column(name = "analise", nullable = false)
    private Integer analise;
    @Column(name = "criatividade", nullable = false)
    private Integer criatividade;
    @Column(name = "rigor", nullable = false)
    private Integer rigor;
    @Column(name = "raciocinio", nullable = false)
    private Integer raciocinio;
    @Column(name = "calculo", nullable = false)
    private Integer calculo;
    @Column(name = "informacoes", length = 1000)
    private String informacoes;

// Um cientista tem vários bens, deve haver na tabela bem um camplo chamado cientista
    @OneToMany(mappedBy = "mulher", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Carreira> carreiras;

    public Mulher(Integer codigo, String nome, Integer memoria, Integer analise, Integer criatividade, Integer rigor, Integer raciocinio, Integer calculo) {
        this.codigo = codigo;
        this.nome = nome;
        this.memoria = memoria;
        this.analise = analise;
        this.criatividade = criatividade;
        this.rigor = rigor;
        this.raciocinio = raciocinio;
        this.calculo = calculo;
    }

    public Mulher() {
        
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMemoria() {
        return memoria;
    }

    public void setMemoria(Integer memoria) {
        this.memoria = memoria;
    }

    public Integer getAnalise() {
        return analise;
    }

    public void setAnalise(Integer analise) {
        this.analise = analise;
    }

    public Integer getCriatividade() {
        return criatividade;
    }

    public void setCriatividade(Integer criatividade) {
        this.criatividade = criatividade;
    }

    public Integer getRigor() {
        return rigor;
    }

    public void setRigor(Integer rigor) {
        this.rigor = rigor;
    }

    public Integer getRaciocinio() {
        return raciocinio;
    }

    public void setRaciocinio(Integer raciocinio) {
        this.raciocinio = raciocinio;
    }

    public Integer getCalculo() {
        return calculo;
    }

    public void setCalculo(Integer calculo) {
        this.calculo = calculo;
    }


    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public List<Carreira> getCarreiras() {
        return carreiras;
    }

    public void setCarreiras(List<Carreira> carreiras) {
        this.carreiras = carreiras;
    }
    
}
