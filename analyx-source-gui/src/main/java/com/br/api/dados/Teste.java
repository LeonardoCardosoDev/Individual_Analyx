package com.br.api.dados;

import com.br.api.banco.jdbc.controller.CpuController;
import com.br.api.banco.jdbc.controller.DiscoController;
import com.br.api.banco.jdbc.controller.MemoriaController;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author carlo
 */
public class Teste {

    public static void main(String[] args) {
        Looca looca = new Looca();
        CpuController cpuDAO = new CpuController();
        DiscoController discoDAO = new DiscoController();
        MemoriaController memoriaDAO = new MemoriaController();

        List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();
        long total = 0;
        for (Volume volume : volumes) {
            total += volume.getTotal();
        }

        System.out.println("Teste sem formatação " + total);
        System.out.println("Tste com foratação " + total / 1000000000);

        Long totalRam = looca.getMemoria().getTotal();
        
        System.out.println("RAM ->>> " + totalRam / 1000000000);


//        System.out.println(looca.getGrupoDeDiscos().getDiscos());
//        System.out.println(looca.getGrupoDeDiscos().getQuantidadeDeDiscos());
//        System.out.println(looca.getGrupoDeDiscos().getQuantidadeDeVolumes());
//        System.out.println(looca.getGrupoDeDiscos().getTamanhoTotal());
//        System.out.println(looca.getGrupoDeDiscos().getVolumes());
//
//        long bytes1 = 1000202273280L;
//        long bytes = 240054796800L + 1000202273280L;
//        double gigabytes = (double) bytes / 1000000000.0;
//        gigabytes = Math.round(gigabytes * 100.0) / 100.0;
//        System.out.println("O número em gigabytes é: " + gigabytes);
//        double terabytes = (double) bytes / 1000000000000.0;
//        terabytes = Math.round(terabytes * 100.0) / 100.0;
//        System.out.println("O número em terabytes é: " + terabytes);
//        
//        List<Volume> volumeTotalUsado = looca.getGrupoDeDiscos().getVolumes();
//        long disponivel = 0;
//        long total = 0;
//        
//        for (Volume volume : volumeTotalUsado) {
//            disponivel += volume.getDisponivel();
//            total += volume.getTotal();
//        }
//        
//        System.out.println(disponivel);
//        System.out.println(total);
//        
//        
//        double testeTotal  = (double) total / 1000000000.0;
//        System.out.println("DISPONIVEL SEM MATH ROUND");
//        System.out.println(testeTotal);
//        System.out.println("DIPONIVEL COM MATH ROUND");
//        testeTotal = Math.round(testeTotal * 100.0) / 100.0;
//        System.out.println(testeTotal);
//        
//        double testeDisponivel  = (double) disponivel / 1000000000.0;
//        System.out.println("DISPONIVEL SEM MATH ROUND");
//        System.out.println(testeDisponivel);
//        System.out.println("DIPONIVEL COM MATH ROUND");
//        testeDisponivel = Math.round(testeDisponivel * 100.0) / 100.0;
//        System.out.println(testeDisponivel);
//        
//        Double espacoUtilizado = (double) (testeTotal - testeDisponivel);
//        Double porcentagemDeUso = (espacoUtilizado / testeTotal) * 100.0;
//        porcentagemDeUso = Math.round(porcentagemDeUso * 100.0) / 100.0;
//        System.out.println("PORCETAGEM DE USO ->>>>>>>   " + porcentagemDeUso);
//        
//        System.out.println("USO MEMORIA");
//        System.out.println(looca.getMemoria().getEmUso());
//        
//        Double memoriaUtilizada = (double) (looca.getMemoria().getTotal() - looca.getMemoria().getEmUso());
//        Double porcentagemUsoMemoria = (memoriaUtilizada / looca.getMemoria().getTotal()) * 100.0;
//        porcentagemUsoMemoria = Math.round(porcentagemUsoMemoria * 100.0) / 100.0;
//        System.out.println(porcentagemUsoMemoria);
//        
//        LocalDate data1 = LocalDate.now();
//        LocalDate data2 = LocalDate.of(200, 05, 22);
//        LocalDate data3 = LocalDate.parse("2000-05-22");
//        
//        System.out.println(looca.getProcessador().getNome());
//        System.out.println(looca.getProcessador().getIdentificador());
//        System.out.println(looca.getProcessador().getId());
        cpuDAO.insertCpuMaquinaAzure(looca.getProcessador().getNome());
    }
}
