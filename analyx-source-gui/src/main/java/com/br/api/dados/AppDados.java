/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.dados;

import com.br.api.banco.jdbc.AlertaLimite;
import com.br.api.banco.jdbc.EspecificacaoMaquina;
import com.br.api.banco.jdbc.Monitoramento;
import com.br.api.banco.jdbc.controller.AlertaController;
import com.br.api.banco.jdbc.controller.AlertaLimiteController;
import com.br.api.banco.jdbc.controller.CpuController;
import com.br.api.banco.jdbc.controller.DiscoController;
import com.br.api.banco.jdbc.controller.EspecificacaoMaquinaController;
import com.br.api.banco.jdbc.controller.MemoriaController;
import com.br.api.banco.jdbc.controller.MonitoramentoController;
import com.br.api.banco.jdbc.controller.PacoteController;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/**
 *
 * @author Leonardo
 */
public class AppDados {
   public void startApp() throws InterruptedException, IOException{
        MonitoramentoController monitoramentoDAO = new MonitoramentoController();
        CpuController cpuDAO = new CpuController();
        DiscoController discoDAO = new DiscoController();
        MemoriaController memoriaDAO = new MemoriaController();
        EspecificacaoMaquinaController emDAO = new EspecificacaoMaquinaController();
        PacoteController pacoteDAO = new PacoteController();
        AlertaLimiteController alertaLimiteDAO = new AlertaLimiteController();
        AlertaController alertaDAO  = new AlertaController();
        Looca looca = new Looca();
        JSONObject json = new JSONObject();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                
                Date dataHoraAtual = new Date();
                String dataAtual = new SimpleDateFormat("yyyy/MM/dd").format(dataHoraAtual);
                String horaAtual = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

                List<Volume> volumeTotalUsado = looca.getGrupoDeDiscos().getVolumes();
                long disponivel = 0;
                long total = 0;
                for (Volume volume : volumeTotalUsado) {
                    disponivel += volume.getDisponivel();
                    total += volume.getTotal();
                }
                Double espacoUtilizado = (double) (total - disponivel);
                Double usoDisco = (espacoUtilizado / total) * 100.0;
                usoDisco = Math.round(usoDisco * 100.0) / 100.0;

                Double memoriaUtilizada = (double) (looca.getMemoria().getTotal() - looca.getMemoria().getEmUso());
                Double usoRam = (memoriaUtilizada / looca.getMemoria().getTotal()) * 100.0;
                usoRam = Math.round(usoRam * 100.0) / 100.0;

                Double usoCpu = looca.getProcessador().getUso();
                
                Long bytesEnviados = 0L;
                Long bytesRecebidos = 0L;
                Long pacotesEnviados = 0L;
                Long pacotesRecebidos = 0L;

                List<RedeInterface> redes = new ArrayList<>(looca.getRede().getGrupoDeInterfaces().getInterfaces());
                for (RedeInterface rede : redes) {
                    if (rede.getBytesEnviados() > 0L || rede.getPacotesEnviados() > 0L) {
                        bytesEnviados += rede.getBytesEnviados();
                        bytesRecebidos += rede.getBytesRecebidos();
                        pacotesEnviados += rede.getPacotesEnviados();
                        pacotesRecebidos += rede.getPacotesRecebidos();
                    }
                }

                Double latencia = 0.0;
                try {
                    InetAddress address = InetAddress.getByName("www.google.com.br");
                    long start = System.nanoTime();
                    if (address.isReachable(5000)) {
                        long end = System.nanoTime();
                        latencia = (end - start) / 1000000.0;
                    } else {
                        System.out.println("Host inacessível");
                        latencia = null;
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    latencia = null;
                }

                EspecificacaoMaquina maquinaAtualLocal = emDAO.getEspecificacaoMaquinaPorHostNameLocal(
                        looca.getRede().getParametros().getHostName());
                monitoramentoDAO.insertMonitoramentoLocal(dataAtual, horaAtual, maquinaAtualLocal.getId());
                Monitoramento monitoramentoAtualLocal = monitoramentoDAO.getMonitoramentoLocal(maquinaAtualLocal.getId());

                pacoteDAO.insertPacotesLocal(latencia,
                        pacotesEnviados,
                        pacotesRecebidos,
                        bytesRecebidos,
                        bytesEnviados,
                        monitoramentoAtualLocal.getId());
                cpuDAO.insertUsoCpuLocal(usoCpu, monitoramentoAtualLocal.getId());
                discoDAO.insertUsoDiscoLocal(usoDisco, monitoramentoAtualLocal.getId());
                memoriaDAO.insertUsoRamLocal(usoRam, monitoramentoAtualLocal.getId());

                EspecificacaoMaquina maquinaAtualAzure = emDAO.getEspecificacaoMaquinaPorHostNameAzure(
                        looca.getRede().getParametros().getHostName());
                monitoramentoDAO.insertMonitoramentoAzure(dataAtual, horaAtual, maquinaAtualAzure.getId());
                Monitoramento monitoramentoAtualAzure = monitoramentoDAO.getMonitoramentoAzure(maquinaAtualAzure.getId());
                
                pacoteDAO.insertPacotesAzure(latencia,
                        pacotesEnviados,
                        pacotesRecebidos,
                        bytesRecebidos,
                        bytesEnviados,
                        monitoramentoAtualAzure.getId(),
                        maquinaAtualAzure.getId());
                cpuDAO.insertUsoCpuAzure(usoCpu, 
                        monitoramentoAtualAzure.getId(),
                        maquinaAtualAzure.getId());
                discoDAO.insertUsoDiscoAzure(usoDisco, 
                        monitoramentoAtualAzure.getId(),
                        maquinaAtualAzure.getId());
                memoriaDAO.insertUsoRamAzure(usoRam, 
                        monitoramentoAtualAzure.getId(),
                        maquinaAtualAzure.getId());
                
                AlertaLimite alertaLimite = alertaLimiteDAO.getAlertaLimiteAzure();
                Double verde = alertaLimite.getLimiteVerde();
                Double vermelho = alertaLimite.getLimiteVermelho();
                
                if (usoCpu <= verde) {
                    alertaDAO.insertAlertaAzure("Normal", 1, 1, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else if (usoCpu > verde && usoCpu < vermelho) {
                    alertaDAO.insertAlertaAzure("Alerta", 1, 2, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else {
                    alertaDAO.insertAlertaAzure("Crítico", 1, 3, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                    
//                    try {               
//                        String nome = maquinaAtualAzure.getHostName();
//                        json.put("text", 
//                                "Uma de suas máquinas está com uso crítico da CPU!"
//                                        + " Nome da Máquina: "+ nome);
//
//                        Slack.sendMessage(json);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
                
                if (usoDisco <= verde) {
                    alertaDAO.insertAlertaAzure("Normal", 2, 1, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else if (usoDisco > verde && usoDisco < vermelho) {
                    alertaDAO.insertAlertaAzure("Alerta", 2, 2, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else {
                    alertaDAO.insertAlertaAzure("Crítico", 2, 3, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                    
//                    try {               
//                        String nome = maquinaAtualAzure.getHostName();
//                        json.put("text", 
//                                "Uma de suas máquinas está com uso crítico do Disco!"
//                                        + " Nome da Máquina: "+ nome);
//
//                        Slack.sendMessage(json);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
                
                if (usoRam <= verde) {
                    alertaDAO.insertAlertaAzure("Normal", 3, 1, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else if (usoRam > verde && usoRam < vermelho) {
                    alertaDAO.insertAlertaAzure("Alerta", 3, 2, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else {
                    alertaDAO.insertAlertaAzure("Crítico", 3, 3, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                    
//                    try {               
//                        String nome = maquinaAtualAzure.getHostName();
//                        json.put("text", 
//                                "Uma de suas máquinas está com uso crítico do Disco!"
//                                        + " Nome da Máquina: "+ nome);
//
//                        Slack.sendMessage(json);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println("Monitoramento feito com sucesso, "
                        + "aguarde mais 1 minuto");
            }
        }, 0, 5000);//60000
    }  
}
