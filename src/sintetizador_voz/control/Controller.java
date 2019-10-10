/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizador_voz.control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sintetizador_voz.model.DAO;
import sintetizador_voz.model.Room;
import sintetizador_voz.view.Main;

/**
 *
 * @author marcos
 */
public class Controller {
    
    private static Controller controller;
    private ServerController serverController;
    private Main frameMain;
    private DAO dao;
    
    private Controller(){
        this.dao = new DAO();
    }
    
    public void InitServices(Main frame){
        try {
            System.out.println("Starting services");
            serverController = new ServerController(3333);
            serverController.start();
            System.out.println("Server started");
            this.frameMain = frame;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Controller getInstance(){
        controller = (controller == null) ? new Controller() : controller;
        return controller;
    }

    public void newMessage(String str) {
        System.out.println("Message: "+ str);
        Room r = dao.read(str.trim().toUpperCase());
        if(r != null)
            frameMain.setUpdate(str + "\n" + r.getNome(), r.getSobre());
        else
            System.err.println("Room not found");
    }
    
}
