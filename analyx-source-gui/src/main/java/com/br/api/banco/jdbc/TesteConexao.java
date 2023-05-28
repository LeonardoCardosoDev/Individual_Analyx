package com.br.api.banco.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author consultor
 */
public class TesteConexao {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = null;

        try {
            con = conexao.getConexaoDoBanco();
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro desconhecido: " + e.getMessage());
        }

    }
}
