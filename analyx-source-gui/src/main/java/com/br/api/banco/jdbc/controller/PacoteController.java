package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Carlos
 */
public class PacoteController {

    public void insertPacotesAzure( Double latencia,
            Long pctEnviado,
            Long pctRecebido,
            Long byteRecebido,
            Long byteEnviado,
            Integer fkMonitoramento,
            Integer fkMaquina) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        conAzure.update("insert into pacote values (?, ?, ?, ?, ?, ?, ?)",
                latencia,
                pctEnviado,
                pctRecebido,
                byteEnviado,
                byteRecebido,
                fkMonitoramento,
                fkMaquina);
    }

    public void insertPacotesLocal( Double latencia,
            Long pctEnviado,
            Long pctRecebido,
            Long byteRecebido,
            Long byteEnviado,
            Integer fkMonitoramento) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into pacote values (null, ?, ?, ?, ?, ?, ?)",
                latencia,
                pctEnviado,
                pctRecebido,
                byteEnviado,
                byteRecebido,
                fkMonitoramento);
    }
}
