package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class Funcionario {

    private Integer id;
    private String nome;
    private String matricula;
    private Integer empresa;
    private Integer gestor;
    private Integer maquina;
    private Integer setor;

    public Funcionario(Integer id, String nome, String matricula, Integer empresa, Integer gestor, Integer maquina, Integer setor) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.empresa = empresa;
        this.gestor = gestor;
        this.maquina = maquina;
        this.setor = setor;
    }

    public Funcionario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public Integer getGestor() {
        return gestor;
    }

    public void setGestor(Integer gestor) {
        this.gestor = gestor;
    }

    public Integer getMaquina() {
        return maquina;
    }

    public void setMaquina(Integer maquina) {
        this.maquina = maquina;
    }

    public Integer getSetor() {
        return setor;
    }

    public void setSetor(Integer setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return String.format("""
                             Id: %d
                             Nome: %s
                             Matricula: %s
                             Empresa: %d
                             Gestor: %d
                             Maquina: %d
                             Setor: %d
                             """, id, nome, matricula, empresa, gestor, maquina, setor);
    }

}
