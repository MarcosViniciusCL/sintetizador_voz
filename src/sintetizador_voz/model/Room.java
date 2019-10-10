/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizador_voz.model;

/**
 *
 * @author marcos
 */
public class Room {
    
    private String nome;
    private String sobre;

    public Room(String nome, String sobre) {
        this.nome = nome;
        this.sobre = sobre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }
}
