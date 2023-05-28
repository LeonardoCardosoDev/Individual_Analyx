package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class Monitoramento {

    private Integer id;
    private String data;
    private String hora;
    private Integer maquina;

    public Monitoramento(Integer id, String data, String hora, Integer maquina) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.maquina = maquina;
    }

    public Monitoramento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getMaquina() {
        return maquina;
    }

    public void setMaquina(Integer maquina) {
        this.maquina = maquina;
    }

    @Override
    public String toString() {
        return String.format("""
                             Monitoramento
                             Id: %d
                             Data: %s
                             Hora: %s
                             maquina: %d
                             """,
                id, data, hora, maquina);
    }

}
