package com.br.api.dados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.dispositivos.DispositivoUsb;
import com.github.britooo.looca.api.group.dispositivos.DispositivosUsbGrupo;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.Servico;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.text.DecimalFormat;
import java.util.List;

public class HardwareColeta {

    public static void main(String[] args) {

        Looca looca = new Looca();
        DecimalFormat df = new DecimalFormat("#.##");

        //EXIBINDO AS INFORMAÇÕES DO SISTEMA
        Sistema sistema = looca.getSistema();
        System.out.println(sistema);

        //EXIBINDO INFORMAÇÕES DO DISCO
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        for (Disco disco : discos) {
            System.out.println(disco);
        }

        //EXIBINDO AS INFORMAÇÕES DA MEMORIA RAM
        Memoria memoria = looca.getMemoria();
        System.out.println(memoria);

        //EXIBINDO AS INFORMAÇÕES DA CPU
        Processador processador = looca.getProcessador();
        System.out.println(processador);
        System.out.println("Mostrando a frequencia em gigahertz");
        System.out.println(looca.getProcessador().getFrequencia() / 1000000000);
        System.out.println("Mostrando o uso com duas casas decimais");
        System.out.println(df.format(looca.getProcessador().getUso()));
        //EXIBINDO A TEMPERATURA
        Temperatura temperatura = looca.getTemperatura();
        System.out.println(temperatura);



        //EXIBINDO OS PROCESSOS
//        ProcessoGrupo grupoDeProcessos = looca.getGrupoDeProcessos();
//        List<Processo> processos = grupoDeProcessos.getProcessos();
//        for (Processo processo : processos) {
//            System.out.println(processos);
//        }
        //EXIBINDO A LISTA DE SERVIÇOS
//        ServicoGrupo grupoDeServicos = looca.getGrupoDeServicos();
//        List<Servico> servicos = grupoDeServicos.getServicos();
//        for (Servico servico : servicos) {
//           System.out.println(servicos);
//        }
        //EXIBINDO AS JANELAS ABERTAS
//        JanelaGrupo grupoDeJanelas = looca.getGrupoDeJanelas();
//        List<Janela> janelas = grupoDeJanelas.getJanelas();
//        for (Janela janela : janelas) {
//            System.out.println(janela);
//        }
        //EXIBINDO OS DIPOSITIVOS USB CONECTADOS
//        DispositivosUsbGrupo grupoDeDispositivosUsb = looca.getDispositivosUsbGrupo();
//        List<DispositivoUsb> dispositivosUsb = grupoDeDispositivosUsb.getDispositivosUsb();
//        for (DispositivoUsb  dispositivoUsb : dispositivosUsb) {
//            System.out.println(dispositivoUsb);
//        }
    }
}
