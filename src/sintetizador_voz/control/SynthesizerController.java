/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizador_voz.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public class SynthesizerController {
    
    private static SynthesizerController controller;
    
    private SynthesizerController(){
        
    }
    
    public void execute(String text){
        new Thread(() -> {
            try {
                String[] exe =  new String[5];
                exe[0] = "espeak";
                exe[1] = "-v";
                exe[2] = "pt-br";
                exe[3] = "-s 100";
                exe[4] = "\"" + text + "\"";
                Process p = Runtime.getRuntime().exec(exe);
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String s1;
                while ( ( s1 = br.readLine() ) != null )
                    System.out.println(s1);
            } catch (IOException ex) {
                Logger.getLogger(SynthesizerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
    
    public static SynthesizerController getInstance(){
        controller = (controller == null) ? new SynthesizerController() : controller;
        return controller;
    }
    
}
