package com.br.api.banco.jdbc;

/**
 *
 * @author Carlos
 */
public class Pacote {
    private Integer id;
    private Double latencia;
    private Long pacotesEnviados;
    private Long pacostesRecebidos;
    private Long bytesEnviados;
    private Long bytesRecebidos;
    private Integer monitoramento;

    public Pacote(Integer id, Double latencia, Long pacotesEnviados, Long pacostesRecebidos, Long bytesEnviados, Long bytesRecebidos, Integer monitoramento) {
        this.id = id;
        this.latencia = latencia;
        this.pacotesEnviados = pacotesEnviados;
        this.pacostesRecebidos = pacostesRecebidos;
        this.bytesEnviados = bytesEnviados;
        this.bytesRecebidos = bytesRecebidos;
        this.monitoramento = monitoramento;
    }

    public Pacote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatencia() {
        return latencia;
    }

    public void setLatencia(Double latencia) {
        this.latencia = latencia;
    }

    public Long getPacotesEnviados() {
        return pacotesEnviados;
    }

    public void setPacotesEnviados(Long pacotesEnviados) {
        this.pacotesEnviados = pacotesEnviados;
    }

    public Long getPacostesRecebidos() {
        return pacostesRecebidos;
    }

    public void setPacostesRecebidos(Long pacostesRecebidos) {
        this.pacostesRecebidos = pacostesRecebidos;
    }

    public Long getBytesEnviados() {
        return bytesEnviados;
    }

    public void setBytesEnviados(Long bytesEnviados) {
        this.bytesEnviados = bytesEnviados;
    }

    public Long getBytesRecebidos() {
        return bytesRecebidos;
    }

    public void setBytesRecebidos(Long bytesRecebidos) {
        this.bytesRecebidos = bytesRecebidos;
    }

    public Integer getMonitoramento() {
        return monitoramento;
    }

    public void setMonitoramento(Integer monitoramento) {
        this.monitoramento = monitoramento;
    }

}
