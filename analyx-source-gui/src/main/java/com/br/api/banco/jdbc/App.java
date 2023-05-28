
package com.br.api.banco.jdbc;

import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author gibas
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException{
        JSONObject json = new JSONObject();
        
        json.put("text", "4° teste realizado!");
        
        Slack.sendMessage(json);
    }
}
