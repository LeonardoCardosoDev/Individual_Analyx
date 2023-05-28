package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.UrlSlack;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class UrlSlackController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    public UrlSlack getUrl() {
        return conAzure.queryForObject("select urlSlack as url from empresa where id = 3", 
                new BeanPropertyRowMapper<UrlSlack>(UrlSlack.class));
    } 

}
