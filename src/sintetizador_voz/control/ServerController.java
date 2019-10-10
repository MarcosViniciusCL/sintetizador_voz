/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizador_voz.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sintetizador_voz.model.Client;

/**
 *
 * @author marcos
 */
public class ServerController {
    
    private ServerSocket server;
    private List<Client> clients;
    private boolean working;
    
    public ServerController(int port) throws IOException{
        server = new ServerSocket(port);
        clients = new ArrayList<>();
        this.working = false;
    }
    
    public void start(){
        working = true;
        new Thread(() -> {
            Socket socket;
            while(working){
                try {
                    socket = server.accept();
                    Client c = new Client(socket, this);
                    clients.add(c);
                    new Thread(c).start();
                } catch (IOException ex) {
                    Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
    public void stop(){
        clients.forEach((n) -> {
            n.stop();
        });
    }

    public void killMe(Client client) {
        clients.remove(client);
    }
    
}
