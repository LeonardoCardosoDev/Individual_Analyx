package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Monitoramento;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Carlos
 */
public class MonitoramentoController {

    public Monitoramento getMonitoramentoAzure(Integer fkMaquina) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        return conAzure.queryForObject("select top 1 id, "
                + "data, "
                + "hora, "
                + "fkMaquina as maquina "
                + "from monitoramento "
                + "where fkMaquina = ? order by id desc", new BeanPropertyRowMapper<Monitoramento>(Monitoramento.class), fkMaquina);
    }
    
    public Monitoramento getMonitoramentoLocal(Integer fkMaquina) {
        Conexao conexao = new Conexao();

        JdbcTemplate conAzure = conexao.getConexaoDoBanco();

        return conAzure.queryForObject("select id, "
                + "data, "
                + "hora, "
                + "fkMaquina as maquina "
                + "from monitoramento "
                + "where fkMaquina = ? order by id desc limit 1", new BeanPropertyRowMapper<Monitoramento>(Monitoramento.class), fkMaquina);
    }

    public void insertMonitoramentoAzure(String data, String hora, Integer fkMaquina) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        conAzure.update("insert into monitoramento values (?,?,?)", data, hora, fkMaquina);
    }

    public void insertMonitoramentoLocal(String data, String hora, Integer fkMaquina) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into monitoramento values (null, ?,?,?)", data, hora, fkMaquina);
    }

}
