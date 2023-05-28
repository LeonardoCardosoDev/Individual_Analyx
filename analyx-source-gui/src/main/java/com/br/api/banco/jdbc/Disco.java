package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class Disco {

    private Integer id;
    private Long volume;
    private Double emUso;

    public Disco(Integer id, Long volume, Double emUso) {
        this.id = id;
        this.volume = volume;
        this.emUso = emUso;
    }

    public Disco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
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
                             Disco
                             Id: %d
                             Volume do disco: %d
                             Uso disco: %.2f
                             """,
                id, volume, emUso);
    }

}
