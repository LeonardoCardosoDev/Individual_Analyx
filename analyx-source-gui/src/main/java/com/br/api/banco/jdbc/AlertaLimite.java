package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class AlertaLimite {

    private Integer id;
    private Double limiteVerde;
    private Double limiteVermelho;

    public AlertaLimite(Integer id, Double limiteVerde, Double limiteVermelho) {
        this.id = id;
        this.limiteVerde = limiteVerde;
        this.limiteVermelho = limiteVermelho;
    }

    public AlertaLimite() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLimiteVerde() {
        return limiteVerde;
    }

    public void setLimiteVerde(Double limiteVerde) {
        this.limiteVerde = limiteVerde;
    }

    public Double getLimiteVermelho() {
        return limiteVermelho;
    }

    public void setLimiteVermelho(Double limiteVermelho) {
        this.limiteVermelho = limiteVermelho;
    }

}
