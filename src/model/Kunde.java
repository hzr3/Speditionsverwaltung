package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Kunde extends ObjektZaehler
{
	private int vnr;
	private Adresse adresse;
	
	public Kunde(Adresse adresse)
	{
		this.vnr = ObjektZaehler.zaehler++;
		this.adresse = adresse; 
	}
	
	
	public void inDatei()
	{
		try 	
		{
			String kunde = vnr + "\t" +	adresse.getName() + "\t" + adresse.getAnsprechpartner() + "\t" +	adresse.getStrasse()	+ "\t" + adresse.getHausnummer()	+ "\t" + adresse.getPlz() + "\t" + adresse.getStadt()	+ "\t" + adresse.getLand() + "\t" +	adresse.getTelefon();
			
			FileWriter fw = new FileWriter("/Users/hzr/Desktop/PROG1/Aufgabe_4/Kundenliste.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(kunde);
			bw.newLine();
			
			bw.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	public int getVnr()
	{
		return vnr;
	}
	
	public void setVnr(int vnr)
	{
		this.vnr = vnr;
	}

	public Adresse getAdresse()
	{
		return adresse;
	}


}
