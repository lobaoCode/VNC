/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lobão
 */
public final class PCcontroller {

    private Process p;
    
    public PCcontroller() {}

    public List<String> listaPC(List<String> lista) {
        try {
           
            BufferedReader br = new BufferedReader(new FileReader("lista.txt"));
           
            while (br.ready()) {
                lista.add(br.readLine());
            }
        } catch (IOException ex) { 
            Logger.getLogger(PCcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public void comando(String comando){
        try {
            setP(Runtime.getRuntime().exec(comando)); 
        } catch (IOException ex) {
            Logger.getLogger(PCcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * @return the p
     */
    public Process getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Process p) {
        this.p = p;
    }
    
}
