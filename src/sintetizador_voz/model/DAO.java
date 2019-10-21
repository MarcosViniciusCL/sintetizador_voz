/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizador_voz.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author marcos
 */
public class DAO {

    public void write(Room r) {
        JSONObject json = new JSONObject(r);
        System.out.println(json.toString());

        try {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("db.json"));
            buffWrite.append(json.toString());
            buffWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Room read(String sala) {
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader("db.json"));
            String str = "";
            String buffer = "";
            while ((buffer = buffRead.readLine()) != null) {
                str += buffer;
            }
            buffRead.close();
            JSONObject json = new JSONObject(str);
            String nome = json.getJSONObject(sala).getString("nome");
            String sobre = json.getJSONObject(sala).getString("sobre");
            return new Room(nome, sobre);
        } catch (FileNotFoundException ex) {
            System.err.println("db.json not found");
            return null;
        } catch (IOException ex) {
            System.err.println("JSON erro");
            return null;
        } catch (org.json.JSONException ex){
            //System.err.println("Room not found");
            return null;
        }
    }
}
