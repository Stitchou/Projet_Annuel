/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitment;

/**
 *
 * @author 626
 */
import java.io.FileReader ;
import java.io.FileWriter ;
import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.PrintWriter;

public class Logs {

        // fonction d'écriture
	public void ecrire(String path, String text) 
	{
		PrintWriter ecri ;
		try
		{
			ecri = new PrintWriter(new FileWriter(path,true));
			ecri.print("\r\n"+text);
			ecri.flush();
			ecri.close();
		}
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a)
		{
			System.out.println("Problème d'IO : "+ a.getMessage()+" "+path+" "+text);
		}
	}
        
        //fonction de lecture
        public String lire (String path) 
	{
		BufferedReader lect ;
		String tmp = "";
		try
		{
			lect = new BufferedReader(new FileReader(path)) ;
			while (lect.ready()==true) 
			{
				tmp += lect.readLine()+"\n"; 
			}
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a) 
		{
			System.out.println("Problème d'IO");
		}
		return tmp;
	} 
}
