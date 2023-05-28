package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class EspecificacaoMaquina {

    private Integer id;
    private String hostName;
    private Integer cpu;
    private Integer disco;
    private Integer ram;

    public EspecificacaoMaquina(Integer id, String hostName, Integer cpu, Integer disco, Integer ram) {
        this.id = id;
        this.hostName = hostName;
        this.cpu = cpu;
        this.disco = disco;
        this.ram = ram;
    }

    public EspecificacaoMaquina() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getDisco() {
        return disco;
    }

    public void setDisco(Integer disco) {
        this.disco = disco;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return String.format("""
                             Id: %d
                             Host Name: %s
                             Cpu: %d
                             Disco: %d
                             Ram: %d
                             """, id, hostName, cpu, disco, ram);
    }

}
