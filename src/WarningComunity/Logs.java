package WarningComunity;

import java.io.FileReader ;
import java.io.FileWriter ;
import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.PrintWriter;

public class Logs {

	public static void ecrire(String path, String text) 
	{
		PrintWriter ecri ;
		try
		{
			ecri = new PrintWriter(new FileWriter(path),true);
			ecri.print(text);
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
}
