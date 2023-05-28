package com.br.api.dados;

import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class TesteConexaoAzureDocker {

    public static void main(String[] args) {
        LoginControllerTeste loginDao = new LoginControllerTeste();
        try {
            System.out.println("Email: marcio@email.com");
            System.out.println("Senha: 1234");
            System.out.println("-----------------------");
            System.out.println("Email: teste@email.com");
            System.out.println("Senha: 1234");
            System.out.println("""
                               -------------------------------
                               LOGIN NA AZURE
                               -------------------------------
                               """+loginDao.entrarAzure("marcio@email"
                                       + ".com", "1234"));
        } catch (Exception e) {
            System.out.println("Erro na conexão/Login com a azure");
        }
        
        try {
            System.out.println("""
                               -------------------------------
                               LOGIN NO MYSQL
                               -------------------------------
                               """+loginDao.entrarMySql("teste@email.com", "1234"));
        } catch (Exception e) {
            System.out.println("Erro na conexão/Login com o Container");
        }
    }
}
