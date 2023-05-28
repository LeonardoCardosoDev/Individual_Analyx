package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Cpu;
import com.br.api.banco.jdbc.Disco;
import com.br.api.banco.jdbc.EspecificacaoMaquina;
import com.br.api.banco.jdbc.Memoria;
import com.br.api.dados.Logger;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import java.io.IOException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class EspecificacaoMaquinaController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();
    Logger log;
    Looca looca = new Looca();
    CpuController cpuDAO = new CpuController();
    DiscoController discoDAO = new DiscoController();
    MemoriaController memoriaDAO = new MemoriaController();
    FuncionarioController funcionarioDAO = new FuncionarioController();

    public void insertMaquinaAzure(String hostName, Integer fkCpu, Integer fkDisco, Integer fkRam) {

        conAzure.update("insert into especificacaoMaquina values (null, ?, ?, ?, ?)",
                hostName, fkCpu, fkDisco, fkRam);
    }

    public void insertMaquinaLocal(String hostName, Integer fkCpu, Integer fkDisco, Integer fkRam) {

        con.update("insert into especificacaoMaquina values (null, ?, ?, ?, ?)",
                hostName, fkCpu, fkDisco, fkRam);
    }

    public EspecificacaoMaquina cadastroDaMaquina(String hostName, Integer fkUsuario) {
        
        try {
            log = new Logger("logAnalyx.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<EspecificacaoMaquina> cadastro = conAzure.query("select id, "
                + "hostName, "
                + "fkCpu as cpu, "
                + "fkDisco as disco, "
                + "fkRam as ram "
                + "from especificacaoMaquina where hostName = ?",
                new BeanPropertyRowMapper(EspecificacaoMaquina.class),
                hostName);

        if (cadastro.isEmpty()) {
            Long totalRam = looca.getMemoria().getTotal();
            List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();
            long total = 0;
            for (Volume volume : volumes) {
                total += volume.getTotal();
            }
            totalRam = totalRam / 1000000000;
            total = total / 1000000000;
            cpuDAO.insertCpuMaquinaAzure(looca.getProcessador().getNome());
            discoDAO.insertDiscoMaquinaAzure(total);
            memoriaDAO.insertMemoriaMaquinaAzure(totalRam);
            
            System.out.println("A máquina foi cadastrada na Azure");

            Cpu cpu = conAzure.queryForObject("select id, modeloCPU from cpu "
                    + "where modeloCPU = ?",
                    new BeanPropertyRowMapper<Cpu>(Cpu.class),
                    looca.getProcessador().getNome());

            Disco disco = conAzure.queryForObject("select id, volume from disco "
                    + "where volume = ?",
                    new BeanPropertyRowMapper<Disco>(Disco.class),
                    total);

            Memoria ram = conAzure.queryForObject("select id, total from ram "
                    + "where total = ?",
                    new BeanPropertyRowMapper<Memoria>(Memoria.class),
                    totalRam);

            conAzure.update("insert into especificacaoMaquina values "
                    + "(?, ?, ?, ?)", hostName,
                    cpu.getId(),
                    disco.getId(),
                    ram.getId());

            EspecificacaoMaquina maquina = getEspecificacaoMaquinaPorHostNameAzure(hostName);

            funcionarioDAO.vincularMaquinaAzure(maquina.getId(), fkUsuario);
            log.logInfo("A máquina foi cadastrada na Azure. Maquina: "+ getEspecificacaoMaquinaPorHostNameAzure(hostName));
            return conAzure.queryForObject("select id, "
                    + "hostName, "
                    + "fkCpu as cpu, "
                    + "fkDisco as disco, "
                    + "fkRam as ram "
                    + "from especificacaoMaquina where hostName = ?",
                    new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                    hostName);
        } else {
            System.out.println("A máquina já está cadastrada na Azure");
            return conAzure.queryForObject("select id, "
                    + "hostName, "
                    + "fkCpu as cpu, "
                    + "fkDisco as disco, "
                    + "fkRam as ram "
                    + "from especificacaoMaquina where hostName = ?",
                    new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                    hostName);
        }
    }

    public EspecificacaoMaquina cadastroDaMaquinaLocal(String hostName) {

        List<EspecificacaoMaquina> cadastro = con.query("select id, "
                + "hostName, "
                + "fkCpu as cpu, "
                + "fkDisco as disco, "
                + "fkRam as ram "
                + "from especificacaoMaquina where hostName = ?",
                new BeanPropertyRowMapper(EspecificacaoMaquina.class),
                hostName);

        if (cadastro.isEmpty()) {
            Long totalRam = looca.getMemoria().getTotal();
            List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();
            long total = 0;
            for (Volume volume : volumes) {
                total += volume.getTotal();
            }
            totalRam = totalRam / 1000000000;
            total = total / 1000000000;
            cpuDAO.insertCpuMaquinaLocal(looca.getProcessador().getNome());
            discoDAO.insertDiscoMaquinaLocal(total);
            memoriaDAO.insertMemoriaMaquinaLocal(totalRam);
            System.out.println("A máquina foi cadastrada no Mysql");

            Cpu cpu = con.queryForObject("select id, modeloCPU from cpu "
                    + "where modeloCPU = ?",
                    new BeanPropertyRowMapper<Cpu>(Cpu.class),
                    looca.getProcessador().getNome());

            Disco disco = con.queryForObject("select id, volume from disco "
                    + "where volume = ?",
                    new BeanPropertyRowMapper<Disco>(Disco.class),
                    total);

            Memoria ram = con.queryForObject("select id, total from ram "
                    + "where total = ?",
                    new BeanPropertyRowMapper<Memoria>(Memoria.class),
                    totalRam);

            con.update("insert into especificacaoMaquina values "
                    + "(null,?, ?, ?, ?)", hostName,
                    cpu.getId(),
                    disco.getId(),
                    ram.getId());
            log.logInfo("A máquina foi cadastrada no Mysql local. Maquina: "+ getEspecificacaoMaquinaPorHostNameAzure(hostName));
            return con.queryForObject("select id, "
                    + "hostName, "
                    + "fkCpu as cpu, "
                    + "fkDisco as disco, "
                    + "fkRam as ram "
                    + "from especificacaoMaquina where hostName = ?",
                    new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                    hostName);
        } else {
            System.out.println("A máquina já está cadastrada na Azure");
            return con.queryForObject("select id, "
                    + "hostName, "
                    + "fkCpu as cpu, "
                    + "fkDisco as disco, "
                    + "fkRam as ram "
                    + "from especificacaoMaquina where hostName = ?",
                    new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                    hostName);
        }
    }

    public EspecificacaoMaquina getEspecificacaoMaquinaPorHostNameLocal(String hostName) {

        return con.queryForObject("select id, "
                + "hostName, "
                + "fkCpu as cpu, "
                + "fkDisco as disco, "
                + "fkRam as ram "
                + "from especificacaoMaquina where hostName = ?",
                new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                hostName);
    }

    public EspecificacaoMaquina getEspecificacaoMaquinaPorHostNameAzure(String hostName) {

        return conAzure.queryForObject("select id, "
                + "hostName, "
                + "fkCpu as cpu, "
                + "fkDisco as disco, "
                + "fkRam as ram "
                + "from especificacaoMaquina where hostName = ?",
                new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                hostName);
    }
}
