/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testplug;
import warningcomunity.*;
/**
 *
 * @author 626
 */
public class Testplug implements Plugins.Gestionnaire{
    private String name;
    public void Testplug()
    {
        name="Testplug";
        System.out.println("plug in construit ");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    }

    @Override
    public void plug() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   // représente le main représente le main
    public void start() {
        System.out.println("plung in demarrer ");
    }

    @Override
    public void unplug() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String getName() {
        return name;
    }
    
}
