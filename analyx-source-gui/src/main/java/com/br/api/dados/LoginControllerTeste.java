package com.br.api.dados;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Carlos
 */
public class LoginControllerTeste {

    public Usuario entrarMySql(String email, String senha) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.queryForObject("select u.id,\n"
                + "		u.email,"
                + "		u.senha,"
                + "		tu.tipoUsuario,"
                + "		f.nome as funcionario "
                + "                     from usuario as u"
                + "			join tipousuario as tu"
                + "			on tu.id = u.tipo"
                + "				join funcionario as f"
                + "				on f.id = u.funcionario "
                + "               where email = ? and senha = ?", new BeanPropertyRowMapper<Usuario>(Usuario.class), email, senha);
    }

    public Usuario entrarAzure(String email, String senha) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        return conAzure.queryForObject("select u.id,\n"
                + "		u.email,"
                + "		u.senha,"
                + "		tu.tipoUsuario,"
                + "		f.nome as funcionario "
                + "                     from usuario as u"
                + "			join tipousuario as tu"
                + "			on tu.id = u.tipo"
                + "				join funcionario as f"
                + "				on f.id = u.funcionario "
                + "               where email = ? and senha = ?", new BeanPropertyRowMapper<Usuario>(Usuario.class), email, senha);
    }
}
