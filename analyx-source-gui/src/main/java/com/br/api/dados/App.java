/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.dados;

import com.br.api.banco.jdbc.Usuario;
import com.br.api.banco.jdbc.controller.EspecificacaoMaquinaController;
import com.br.api.banco.jdbc.controller.UsuarioController;
import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Leonardo
 */
public class App {

    public static void main(String[] args) throws IOException {
        UsuarioController usuarioDAO = new UsuarioController();
        EspecificacaoMaquinaController emDAO = new EspecificacaoMaquinaController();
        Looca looca = new Looca();
        Logger log = new Logger("logAnalyx.txt");
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        
        System.out.println("Email:");
        String email = scanner1.nextLine();
        System.out.println("Senha:");
        String senha = scanner2.nextLine();
        try {
            Usuario user = usuarioDAO.entrarAzure(email, senha);
            String hostName = looca.getRede().getParametros().getHostName();
            emDAO.cadastroDaMaquina(hostName, user.getFuncionario());
            emDAO.cadastroDaMaquinaLocal(hostName);
            log.logInfo("Login efetuado user: " + email + " Maquina: " + emDAO.getEspecificacaoMaquinaPorHostNameAzure(hostName));
            AppDados appDados = new AppDados();
            appDados.startApp();
        } catch (Exception e) {
            String hostName = looca.getRede().getParametros().getHostName();
            log.logErro("Informações de login incorretas, user: " + email + " Maquina: " + emDAO.getEspecificacaoMaquinaPorHostNameAzure(hostName));
            System.out.println("erro ->" + e.getMessage());
            e.printStackTrace();
        }
        log.close();
    }
}
