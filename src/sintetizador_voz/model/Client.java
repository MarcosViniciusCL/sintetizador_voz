/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizador_voz.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sintetizador_voz.control.Controller;
import sintetizador_voz.control.ServerController;

/**
 *
 * @author marcos
 */
public class Client implements Runnable{

    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final Controller sc;
    private boolean working;
    private final Cmd cmd;
    private final ServerController serverController;
    
    public Client (Socket socket, ServerController s) throws IOException{
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.sc = Controller.getInstance();
        this.serverController = s;
        this.cmd = new Cmd();
        working = true;
        System.out.println("New client connected");
    }
    
    
    @Override
    public void run() {
        String str;
        while (working) {            
            try {
                str = read().trim();
                if(str != null && str.toUpperCase().equals("STOP")){
                    System.exit(0);
                }
                if(str != null && !str.equals("")){
                    sc.newMessage(str);
                }
            } catch (IOException ex) {
                System.err.println("Connection closed");
                stop();
            }
        }
    }
    
    public String read() throws IOException{
        String str = "";
        int buffer = -1;
        while(buffer  != '\n'){
            buffer = reader.read();
            str += (char) buffer;
        }
        return str;
    }
    
    public void write(String text) throws IOException{
        writer.write(text);
        writer.flush();
    }

    public void stop() {
        working = false;
        serverController.killMe(this);
    }
   
    private class Cmd {
        public String START = ""; 
    }
}

