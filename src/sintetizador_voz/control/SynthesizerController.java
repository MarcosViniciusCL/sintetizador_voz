/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizador_voz.control;

/**
 *
 * @author marcos
 */
public class SynthesizerController {
    
    private static SynthesizerController controller;
    
    private SynthesizerController(){
        
    }
    
    public static SynthesizerController getInstance(){
        controller = (controller == null) ? new SynthesizerController() : controller;
        return controller;
    }
    
}
