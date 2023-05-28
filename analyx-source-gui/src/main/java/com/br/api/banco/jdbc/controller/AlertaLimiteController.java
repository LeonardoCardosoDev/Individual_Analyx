package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.AlertaLimite;
import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class AlertaLimiteController {
    
    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();
    
    public AlertaLimite getAlertaLimiteAzure() {
        
        return conAzure.queryForObject("select * from tipoAlertaLimite",
                new BeanPropertyRowMapper<AlertaLimite>(AlertaLimite.class)
        );
                
    }
    
    public AlertaLimite getAlertaLimiteLocal() {
        
        return conAzure.queryForObject("select * from tipoAlertaLimite",
                new BeanPropertyRowMapper<AlertaLimite>(AlertaLimite.class)
        );
                
    }
            
            
}
