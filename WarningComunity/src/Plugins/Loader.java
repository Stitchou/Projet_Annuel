/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Plugins;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;
import java.util.jar.JarFile;
/**
 *
 * @author 626
 */

public class Loader {
    public static ArrayList<Gestionnaire> list=new ArrayList<>();
    public static ArrayList<String> names = new ArrayList<String>();
    public static void load()
    {
        
        try
        {
            URLClassLoader search;
            // répertoire de plugins
            File fichier= new File(".\\src\\Plugins\\jar");
            // liste des plugins
            File [] listeFichier = fichier.listFiles();
            //classe loarder
            ArrayList<ClassLoader> cl =new ArrayList<ClassLoader>();
            // Liste denumeration 
            Enumeration enums;
            String name=null;
            Class nameClass=null;
            ArrayList<Class> tabClass = new ArrayList<Class>();
            //parcours de la lsite des pulgins
            int place=0;
            for(File f : listeFichier)
            {
                try
                {
                    URL [] listeUrl = {f.toURL()};
                    cl.add( new URLClassLoader (listeUrl));
                    JarFile ficjar = new JarFile(f.getAbsolutePath());
                    enums=ficjar.entries();
                    // parcours de l'énumération 
                    
                    while(enums.hasMoreElements())
                    {                       
                        name = enums.nextElement().toString();
                        if(name.length() > 6 && name.substring(name.length()-6).compareTo(".class") == 0) 
                        {                           
                            name = name.substring(0,name.length()-6);
                            name = name.replaceAll("/",".");
                            nameClass = Class.forName(name ,true,cl.get(place));
                            for(int i = 0 ; i < nameClass.getInterfaces().length; i ++ )
                            {
                                boolean b = nameClass.getInterfaces()[i].toString().equals("interface Plugins.Gestionnaire");
                                if(b) 
                                {
                                    tabClass.add(nameClass);
                                    break;
                                }
                            }
                        }
                    }               
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println();
                }
                place++;
            }
            place=0;
            for(Class c : tabClass)
            {
                names.add(c.getName().substring(c.getName().indexOf(".")+1));
                Gestionnaire myGestionnaire=(Gestionnaire) Class.forName(c.getName(),true,cl.get(place)).newInstance();
                list.add(myGestionnaire);
                place++;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("erreur lecture plugin");
        }
    }
}
