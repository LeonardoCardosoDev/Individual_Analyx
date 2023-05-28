package com.br.api.banco.jdbc;

public class Memoria {

    private Integer id;
    private Long total;
    private String emUso;

    public Memoria(Integer id, Long total, String emUso) {
        this.id = id;
        this.total = total;
        this.emUso = emUso;
    }

    public Memoria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getEmUso() {
        return emUso;
    }

    public void setEmUso(String emUso) {
        this.emUso = emUso;
    }

    @Override
    public String toString() {
        return String.format("Memória\n"
                + "Id: %d\n"
                + "Em Uso: %s\n"
                ,id, emUso);
    }
}
