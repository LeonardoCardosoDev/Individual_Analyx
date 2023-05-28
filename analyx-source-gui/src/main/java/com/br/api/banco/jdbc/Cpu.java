package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class Cpu {

    private Integer id;
    private String modelo;
    private Double emUso;

    public Cpu(Integer id, String modelo, Double emUso) {
        this.id = id;
        this.modelo = modelo;
        this.emUso = emUso;
    }

    public Cpu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getEmUso() {
        return emUso;
    }

    public void setEmUso(Double emUso) {
        this.emUso = emUso;
    }

    @Override
    public String toString() {
        return String.format("""
                             CPU
                             Id: %d
                             Modelo: %s
                             uso: %.2f
                             """, id, modelo, emUso);
    }

}
