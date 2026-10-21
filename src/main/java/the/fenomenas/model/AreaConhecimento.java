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
import jakarta.persistence.Table;
import java.io.Serializable;
/**
 *
 * @author aluno
 */
@Entity
@Table(name = "area")
public class AreaConhecimento implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", insertable = false, updatable = false)
    private Integer codigo;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "pesoMemoria", nullable = false)
    private Integer pesoMemoria;
    @Column(name = "pesoAnalise", nullable = false)
    private Integer pesoAnalise;
    @Column(name = "pesoCriatividade", nullable = false)
    private Integer pesoCriatividade;
    @Column(name = "pesoRigor", nullable = false)
    private Integer pesoRigor;
    @Column(name = "pesoRaciocinio", nullable = false)
    private Integer pesoRaciocinio;
    @Column(name = "pesoCalculo", nullable = false)
    private Integer pesoCalculo;

    public AreaConhecimento(Integer codigo, String nome, Integer pesoMemoria, Integer pesoAnalise, Integer pesoCriatividade, Integer pesoRigor, Integer pesoRaciocinio, Integer pesoCalculo) {
        this.codigo = codigo;
        this.nome = nome;
        this.pesoMemoria = pesoMemoria;
        this.pesoAnalise = pesoAnalise;
        this.pesoCriatividade = pesoCriatividade;
        this.pesoRigor = pesoRigor;
        this.pesoRaciocinio = pesoRaciocinio;
        this.pesoCalculo = pesoCalculo;
    }

    public AreaConhecimento() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
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

    public Integer getPesoMemoria() {
        return pesoMemoria;
    }

    public void setPesoMemoria(Integer pesoMemoria) {
        this.pesoMemoria = pesoMemoria;
    }

    public Integer getPesoAnalise() {
        return pesoAnalise;
    }

    public void setPesoAnalise(Integer pesoAnalise) {
        this.pesoAnalise = pesoAnalise;
    }

    public Integer getPesoCriatividade() {
        return pesoCriatividade;
    }

    public void setPesoCriatividade(Integer pesoCriatividade) {
        this.pesoCriatividade = pesoCriatividade;
    }

    public Integer getPesoRigor() {
        return pesoRigor;
    }

    public void setPesoRigor(Integer pesoRigor) {
        this.pesoRigor = pesoRigor;
    }

    public Integer getPesoRaciocinio() {
        return pesoRaciocinio;
    }

    public void setPesoRaciocinio(Integer pesoRaciocinio) {
        this.pesoRaciocinio = pesoRaciocinio;
    }

    public Integer getPesoCalculo() {
        return pesoCalculo;
    }

    public void setPesoCalculo(Integer pesoCalculo) {
        this.pesoCalculo = pesoCalculo;
    }

    
}
