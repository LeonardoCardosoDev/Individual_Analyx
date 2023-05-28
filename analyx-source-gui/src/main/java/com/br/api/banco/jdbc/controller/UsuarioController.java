package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author carlo
 */
public class UsuarioController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();

    public Usuario entrarMySql(String email, String senha) {

        return con.queryForObject("select id, "
                + "email,"
                + "senha, "
                + "fkTipoUsuario as tipoUsuario, "
                + "fkFuncionario as funcionario "
                + "from usuario where email = ? and senha = ?",
                new BeanPropertyRowMapper<Usuario>(Usuario.class), email, senha);
    }

    public Usuario entrarAzure(String email, String senha) {

        return conAzure.queryForObject("select id, "
                + "email,"
                + "senha, "
                + "fkTipoUsuario as tipoUsuario, "
                + "fkFuncionario as funcionario "
                + "from usuario where email = ? and senha = ?",
                new BeanPropertyRowMapper<Usuario>(Usuario.class), email, senha);
                
    }

    public Usuario getUsuarioAzure(String email) {

        return conAzure.queryForObject("select u.id,"
                + "		u.email,"
                + "		u.senha,"
                + "		tu.tipoUsuario,"
                + "		f.nome as funcionario, "
                + "             f.fkMaquina as maquina"
                + "                     from usuario as u"
                + "			join tipoUsuario as tu"
                + "			on tu.id = u.fkTipoUsuario"
                + "				join funcionario as f"
                + "				on f.id = u.fkFuncionario "
                + "                                    join especificacaoMaquina as em"
                + "                                    on f.fkMaquina = em.id"
                + "               where email = ?", new BeanPropertyRowMapper<Usuario>(Usuario.class), email);

    }

}
