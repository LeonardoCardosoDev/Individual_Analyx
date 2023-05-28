package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class AlertaController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();
    
    public void insertAlertaAzure(String nivelGravidade,
            Integer fkTipoComponente,
            Integer fkTipoCategoria,
            Integer fkTipoAlertaLimite,
            Integer fkMonitoramento,
            Integer fkMaquina) {
        
        conAzure.update("insert into alerta values "
                + "(?, ?, ?, ?, ?, ?)",
                nivelGravidade,
                fkTipoComponente,
                fkTipoCategoria,
                fkTipoAlertaLimite,
                fkMonitoramento,
                fkMaquina);
        
    }
    
    public void insertAlertaLocal(String nivelGravidade,
            Integer fkTipoComponente,
            Integer fkTipoCategoria,
            Integer fkTipoAlertaLimite,
            Integer fkMonitoramento,
            Integer fkMaquina) {
        
        conAzure.update("insert into alerta values "
                + "(null, ?, ?, ?, ?, ?, ?)",
                nivelGravidade,
                fkTipoComponente,
                fkTipoCategoria,
                fkTipoAlertaLimite,
                fkMonitoramento,
                fkMaquina);
        
    }

}
