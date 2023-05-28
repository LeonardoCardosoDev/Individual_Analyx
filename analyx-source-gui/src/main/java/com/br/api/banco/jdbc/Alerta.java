package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class Alerta {
    private Integer id;
    private String nivelGravidade;
    private Integer fkTipoComponente;
    private Integer fkTipoCategoria;
    private Integer fkTipoAlertaLimite;
    private Integer fkMonitoramento;
    private Integer fkMaquina;

    public Alerta(Integer id, String nivelGravidade, Integer fkTipoComponente, Integer fkTipoCategoria, Integer fkTipoAlertaLimite, Integer fkMonitoramento, Integer fkMaquina) {
        this.id = id;
        this.nivelGravidade = nivelGravidade;
        this.fkTipoComponente = fkTipoComponente;
        this.fkTipoCategoria = fkTipoCategoria;
        this.fkTipoAlertaLimite = fkTipoAlertaLimite;
        this.fkMonitoramento = fkMonitoramento;
        this.fkMaquina = fkMaquina;
    }

    public Alerta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNivelGravidade() {
        return nivelGravidade;
    }

    public void setNivelGravidade(String nivelGravidade) {
        this.nivelGravidade = nivelGravidade;
    }

    public Integer getFkTipoComponente() {
        return fkTipoComponente;
    }

    public void setFkTipoComponente(Integer fkTipoComponente) {
        this.fkTipoComponente = fkTipoComponente;
    }

    public Integer getFkTipoCategoria() {
        return fkTipoCategoria;
    }

    public void setFkTipoCategoria(Integer fkTipoCategoria) {
        this.fkTipoCategoria = fkTipoCategoria;
    }

    public Integer getFkTipoAlertaLimite() {
        return fkTipoAlertaLimite;
    }

    public void setFkTipoAlertaLimite(Integer fkTipoAlertaLimite) {
        this.fkTipoAlertaLimite = fkTipoAlertaLimite;
    }

    public Integer getFkMonitoramento() {
        return fkMonitoramento;
    }

    public void setFkMonitoramento(Integer fkMonitoramento) {
        this.fkMonitoramento = fkMonitoramento;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }
    
}
