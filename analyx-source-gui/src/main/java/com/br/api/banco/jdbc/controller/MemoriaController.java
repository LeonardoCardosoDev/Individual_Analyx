package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Memoria;
import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;

/**
 *
 * @author carlo
 */
public class MemoriaController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();

    public void insertMemoriaMaquinaAzure(Long total) {

        List<Memoria> memoria = conAzure.query("select id, "
                + "total as modelo "
                + "from ram where total = ?",
                new BeanPropertyRowMapper<Memoria>(Memoria.class), total);

        if (memoria.isEmpty()) {
            conAzure.update("insert into ram values (?)", total);
            System.out.println("Memoria cadastrada");
        } else {
            System.out.println(memoria + " Memória já cadastrada");
        }
    }

    public void insertMemoriaMaquinaLocal(Long total) {

        List<Memoria> memoria = con.query("select id, "
                + "total as modelo "
                + "from ram where total = ?",
                new BeanPropertyRowMapper<Memoria>(Memoria.class), total);

        if (memoria.isEmpty()) {
            con.update("insert into ram values (null,?)", total);
            System.out.println("Memoria cadastrada");
        } else {
            System.out.println(memoria + " Memória já cadastrada");
        }
    }

    public List<Memoria> showAll() {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.query("select * from ram", new BeanPropertyRowMapper(Memoria.class));
    }

    public void insertUsoRamLocal(Double r, Integer fkMonitoramento) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into componente values "
                + "(null, ?,?,3)", r, fkMonitoramento);
    }

    public void insertUsoRamAzure(Double r, Integer fkMonitoramento, Integer fkMaquina) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        conAzure.update("insert into componente values "
                + "(?,?,?,3)", r, fkMonitoramento, fkMaquina);
    }

}
