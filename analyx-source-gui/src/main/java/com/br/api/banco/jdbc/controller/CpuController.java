package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Cpu;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class CpuController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();

    public Cpu getCpuAzure(String modeloCpu) {

        return conAzure.queryForObject("select id, "
                + "modeloCPU as modelo "
                + "from cpu where modeloCPU = ?",
                new BeanPropertyRowMapper<Cpu>(Cpu.class), modeloCpu);
    }

    public void insertCpuMaquinaAzure(String modeloCpu) {

        List<Cpu> cpu = conAzure.query("select id, "
                + "modeloCPU as modelo "
                + "from cpu where modeloCPU = ?",
                new BeanPropertyRowMapper<Cpu>(Cpu.class), modeloCpu);

        if (cpu.isEmpty()) {
            conAzure.update("insert into cpu values (?)", modeloCpu);
            System.out.println("Cpu cadastrada");
        } else {
            System.out.println(cpu + " Cpu já cadastrada");
        }
    }

    public void insertCpuMaquinaLocal(String modeloCpu) {

        List<Cpu> cpu = con.query("select id, "
                + "modeloCPU as modelo "
                + "from cpu where modeloCPU = ?",
                new BeanPropertyRowMapper<Cpu>(Cpu.class), modeloCpu);

        if (cpu.isEmpty()) {
            con.update("insert into cpu values (null,?)", modeloCpu);
            System.out.println("Cpu cadastrada");
        } else {
            System.out.println(cpu + " Cpu já cadastrada");
        }
    }

    public void insertUsoCpuLocal(Double c, Integer fkMonitoramento) {

        con.update("insert into componente values "
                + "(null, ?,?,1)", c, fkMonitoramento);
    }

    public void insertUsoCpuAzure(Double c, Integer fkMonitoramento, Integer fkMaquina) {

        conAzure.update("insert into componente values "
                + "(?, ?, ?, 1)", c, fkMonitoramento, fkMaquina);
    }
}
