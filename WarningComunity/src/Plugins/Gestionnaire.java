/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Plugins;

/**
 *
 * @author 626
 */
public interface Gestionnaire {
    public void plug();
    public void start();
    public void unplug();
    public String getName();
}
