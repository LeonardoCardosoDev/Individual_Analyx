/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.dados;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Leonardo
 */
public class Logger {

    private FileWriter arquivoLog;
    private SimpleDateFormat data;

    public Logger(String nomeArquivoLog) throws IOException {
        String caminhoArquivo = Paths.get("").toAbsolutePath().toString();
        String caminhoCompletoArquivoLog = caminhoArquivo + File.separator + nomeArquivoLog;
        arquivoLog = new FileWriter(nomeArquivoLog, true);
        data = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
    }

    public void logInfo(String message) {
        try {
            String logMessageInfo = "[" + getFormattedDate() + "] " + "[INFO] " + message + "\n";
            arquivoLog.write(logMessageInfo);
            arquivoLog.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        public void logErro(String message) {
        try {
            String logMessageInfo = "[" + getFormattedDate() + "] " + "[ERRO] " + message + "\n";
            arquivoLog.write(logMessageInfo);
            arquivoLog.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFormattedDate() {
        return data.format(new Date());
    }

    public void close() {
        try {
            arquivoLog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
