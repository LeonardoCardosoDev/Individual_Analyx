package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class FuncionarioController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();

    public void vincularMaquinaAzure(Integer fkMaquina, Integer idFuncionario) {

        conAzure.update("update funcionario set fkMaquina = ?"
                + " where id = ?", fkMaquina, idFuncionario);
    }

    public void vincularMaquinaLocal(Integer fkMaquina, Integer idFuncionario) {

        con.update("update funcionario set fkMaquina = ?"
                + " where id = ?", fkMaquina, idFuncionario);

    }

}
