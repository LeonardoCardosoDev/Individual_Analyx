package com.br.api.banco.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author consultor
 */
public class ConexaoAzure {

    private JdbcTemplate conexaoDoBanco;

    public ConexaoAzure() {
        BasicDataSource dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        dataSource.setUrl("jdbc:sqlserver://analayx.database.windows.net:1433;database=analyx;user=analyx-admin@analayx;password=#Gfgrupo2;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"); 
        
        dataSource.setUsername("analyx-admin");
        
        dataSource.setPassword("#Gfgrupo2");

        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }

}
