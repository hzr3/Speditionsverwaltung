package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Model 
{
	private Adresse adresse;
	private Auftrag auftrag;
	private Fuhrpark fuhrpark;
	private Kunde kunde;
	private LKW lkw;
	private LKW24t lkw24t;
	private LKW60t lkw60t;
	private ObjektZaehler objektZaehler;
	private Transportgut transportgut;
	
	private String pathKundenliste = "/home/hzr/Kundenliste.txt";
	private String pathAuftragsliste = "/home/hzr/auftragsliste.txt";
	
	private ArrayList<Kunde> kundenliste = new ArrayList<Kunde>();
	private ArrayList<Auftrag> auftragsliste = new ArrayList<Auftrag>();
	
	public Model()
	{
		fuhrpark = new Fuhrpark();
	}
	
	public void auftragslisteLaden()
	{
		BufferedReader bufferedReader = null;
        //Der Pfad zur Textdatei
        String filePath = pathAuftragsliste;
        File file = new File(filePath);
        
        try 
        {
        	if (!file.exists()) 
            {
    			file.createNewFile();
            }
        	
        	bufferedReader = new BufferedReader(new FileReader(file));
        	String line;
          
        	//null wird bei EOF oder Fehler zurueckgegeben
        	while (null != (line = bufferedReader.readLine())) 
        	{  
        		String[] splitted = line.split(",");
        		
        		Transportgut tg = new Transportgut(	Ladungsart.valueOf(splitted[3].substring(1, splitted[3].length())), 
        											Float.parseFloat(splitted[4].substring(1, splitted[4].length())), 
        											Float.parseFloat(splitted[5].substring(1, splitted[5].length())),
        											Float.parseFloat(splitted[6].substring(1, splitted[6].length())), 
        											Float.parseFloat(splitted[7].substring(1, splitted[7].length())));
        		
        		
        		Kunde k = kundenliste.get(vnrSuchen(Integer.valueOf(splitted[2].substring(1))));
        		Auftrag a = new Auftrag(tg, k, splitted[8].substring(1, splitted[8].length()), Zahlungsart.valueOf(splitted[11].substring(1, splitted[11].length())));
        		
        		a.setPreis(Integer.valueOf(splitted[9].substring(1, splitted[9].length())));
        		a.setGeliefert(Boolean.parseBoolean(splitted[9].substring(1, splitted[9].length())));
        		
        		int anr = Integer.valueOf(splitted[0]);
        		a.setAnr(anr);
        		
        		auftragsliste.add(a);
        	}
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
        	if (null != bufferedReader) 
        	{
		        try 
		        {
		        	bufferedReader.close();
		        } 
		        catch (IOException e) 
		        {
		            e.printStackTrace();
		        }
        	}
        }
	}
	
	public void kundenlisteLaden()
	{
		BufferedReader bufferedReader = null;
        //Der Pfad zur Textdatei
        String filePath = pathKundenliste;
        File file = new File(filePath);
        
        try 
        {
        	if (!file.exists()) 
            {
    			file.createNewFile();
            }
        	
        	bufferedReader = new BufferedReader(new FileReader(file));
        	String line;
          
        	//null wird bei EOF oder Fehler zurueckgegeben
        	while (null != (line = bufferedReader.readLine())) 
        	{  
        		String[] splitted = line.split(",");
        		
        		Adresse ad = new Adresse(splitted[1].substring(1), splitted[2].substring(1), splitted[3].substring(1), Integer.valueOf(splitted[4].substring(1)), Integer.valueOf(splitted[5].substring(1)), splitted[6].substring(1), splitted[7].substring(1), Integer.valueOf(splitted[8].substring(1)));
        		
        		Kunde k = new Kunde(ad);
        		int vnr = Integer.valueOf(splitted[0]);
        		k.setVnr(vnr);
        		
        		kundenliste.add(k);
        	}
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
        	if (null != bufferedReader) 
        	{
		        try 
		        {
		        	bufferedReader.close();
		        } 
		        catch (IOException e) 
		        {
		            e.printStackTrace();
		        }
        	}
        }
	}
	
	public void allesInDateiLoeschen(String path)
	{
		try 
		{
			File file = new File(path);
			
			if (file.exists())
			{
				file.delete();
			}
			
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter bw = new BufferedWriter(fw); 
			
			bw.close(); 
		}
		catch (Exception e)
		{
			
		}
	}
	
	public void kundenlisteInDatei() 
	{
		String path = pathKundenliste;
		
		allesInDateiLoeschen(path);
		kundenSortieren();
		
		try 	
		{
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (Kunde k : kundenliste) 
			{
				String kunde = 	k.getVnr() + ",\t" +	
								k.getAdresse().getName() + ",\t" + 
								k.getAdresse().getAnsprechpartner() + ",\t" +	
								k.getAdresse().getStrasse()	+ ",\t" + 
								k.getAdresse().getHausnummer()	+ ",\t" + 
								k.getAdresse().getPlz() + ",\t" + 
								k.getAdresse().getStadt()	+ ",\t" + 
								k.getAdresse().getLand() + ",\t" +	
								k.getAdresse().getTelefon();
				
				bw.write(kunde);
				bw.newLine();
			}			
			bw.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	
	public void auftragslisteInDatei() 
	{
		String path = pathAuftragsliste;
		allesInDateiLoeschen(path);
		
		try 	
		{
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (Auftrag a : auftragsliste) 
			{
				String auftrag = 	a.getAnr() + ",\t" +	
									a.getKunde().getAdresse().getName() + ",\t" + 
									a.getKunde().getVnr() + ",\t" +	
									a.getTransportgut().getLadungsart()	+ ",\t" + 
									a.getTransportgut().getLaenge()	+ ",\t" + 
									a.getTransportgut().getBreite() + ",\t" + 
									a.getTransportgut().getHoehe()	+ ",\t" + 
									a.getTransportgut().getGewicht() + ",\t" +	
									a.getDatum() + ",\t" +
									a.getPreis() + ",\t" +
									a.isGeliefert() + ",\t" +
									a.getZahlungsart();
				
				bw.write(auftrag);
				bw.newLine();
			}			
			bw.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	
	public void kundenSortieren() 
	{
		Collections.sort(kundenliste, new Comparator<Kunde>() 
		{
        	public int compare(Kunde v1, Kunde v2)
        	{
        		return v1.getAdresse().getName().compareTo(v2.getAdresse().getName());
        	}
		});
	}
	
	public int vnrSuchen(int vnr)
	{
		for(Kunde k : kundenliste)
		{
			if (k.getVnr() == vnr) 
			{
				return kundenliste.indexOf(k);
			}
		}
		System.out.println("Nicht gefunden!");
		return -1;
	}
	
	public int anrSuchen(int anr)
	{
		for(Auftrag a : auftragsliste)
		{
			if (a.getAnr() == anr) 
			{
				return auftragsliste.indexOf(a);
			}
		}
		System.out.println("Nicht gefunden!");
		return -1;
	}
	
	public String kundenlisteAlsString()
	{
		String liste = "";
		
		for(Kunde k : kundenliste)
		{
			liste = liste + String.valueOf(k.getVnr()) + "\t";
			liste = liste + k.getAdresse().getName() + "\t";
			liste = liste + k.getAdresse().getAnsprechpartner() + "\t";
			liste = liste + k.getAdresse().getStrasse() + "\t";
			liste = liste + String.valueOf(k.getAdresse().getHausnummer()) + "\t";
			liste = liste + String.valueOf(k.getAdresse().getPlz()) + "\t";
			liste = liste + k.getAdresse().getStadt() + "\t";
			liste = liste + k.getAdresse().getLand() + "\t";
			liste = liste + String.valueOf(k.getAdresse().getTelefon()) + "\n";
		}
		return liste;
	}
	
	public String auftragslisteAlsString()
	{
		String liste = "";
		
		for(Auftrag a : auftragsliste)
		{
			liste = liste +	a.getAnr() + "\t";	
			liste = liste +	a.getKunde().getAdresse().getName() + "\t";
			liste = liste +	a.getKunde().getVnr() + "\t";
			liste = liste +	a.getTransportgut().getLadungsart() + "\t";
			liste = liste +	a.getTransportgut().getLaenge() + "\t";
			liste = liste +	a.getTransportgut().getBreite() + "\t";
			liste = liste +	a.getTransportgut().getHoehe() + "\t";
			liste = liste +	a.getTransportgut().getGewicht() + "\t";
			liste = liste +	a.getDatum() + "\t";
			liste = liste +	a.getPreis() + "\t";
			liste = liste +	a.isGeliefert() + "\t";
			liste = liste +	a.getZahlungsart() + "\t";
		}
		return liste;
	}
	
	public void kundenlisteAendern(int vnr, String attribut, String neuerWert)
	{
		int index = vnrSuchen(vnr);
		
		switch (attribut)
		{
			case "Firmenname" :			kundenliste.get(index).getAdresse().setName(neuerWert);
										break;
										
			case "Ansprechpartner" :	kundenliste.get(index).getAdresse().setAnsprechpartner(neuerWert);	
										break;
										
			case "Straße" :				kundenliste.get(index).getAdresse().setStrasse(neuerWert);
										break;
										
			case "Stadt" :				kundenliste.get(index).getAdresse().setStadt(neuerWert);
										break;
										
			case "Land" :				kundenliste.get(index).getAdresse().setLand(neuerWert);
										// Adresse adresse = kundenliste.get(index).getAdresse();
										// adresse.setLand(adresse.containsElement(neuerWert));
										break;									
		}
		kundenlisteInDatei();
	}
	
	public void kundenlisteAendern(int vnr, String attribut, int neuerWert)
	{
		int index = 0;
		
		for (Kunde k : kundenliste)
		{
			if (k.getVnr() == vnr)
			{
				index = kundenliste.indexOf(k);
			}
		}
		
		switch (attribut)
		{							
			case "Hausnummer" :		kundenliste.get(index).getAdresse().setHausnummer(neuerWert);
									break;
										
			case "PLZ" :			kundenliste.get(index).getAdresse().setPlz(neuerWert);
									break;
		}
	}
	
	public void kundeLoeschen(int vnr)
	{
		int index = vnrSuchen(vnr);
		kundenliste.remove(index);
		kundenlisteInDatei();
	}
	
	public void auftragLoeschen(int anr)
	{
		int index = anrSuchen(anr);
		auftragsliste.remove(index);
		auftragslisteInDatei();
	}
	
	public ArrayList<Kunde> getKundenliste()
	{
		return kundenliste;
	}

	public ArrayList<Auftrag> getAuftragsliste()
	{
		return auftragsliste;
	}

	public Adresse getAdresse()
	{
		return adresse;
	}
	
	public void setAdresse(Adresse adresse)
	{
		this.adresse = adresse;
	}

	public Auftrag getAuftrag()
	{
		return auftrag;
	}
	
	public void setAuftrag(Auftrag auftrag)
	{
		this.auftrag = auftrag;
	}
	
	public Fuhrpark getFuhrpark() 
	{
		return fuhrpark;
	}
	
	public void setFuhrpark(Fuhrpark fuhrpark) 
	{
		this.fuhrpark = fuhrpark;
	}
	
	public Kunde getKunde() 
	{
		return kunde;
	}
	
	public void setKunde(Kunde kunde) 
	{
		this.kunde = kunde;
	}
	
	public LKW getLkw() 
	{
		return lkw;
	}
	
	public void setLkw(LKW lkw)
	{
		this.lkw = lkw;
	}
	
	public LKW24t getLkw24t()
	{
		return lkw24t;
	}
	
	public void setLkw24t(LKW24t lkw24t)
	{
		this.lkw24t = lkw24t;
	}
	
	public LKW60t getLkw60t() 
	{
		return lkw60t;
	}
	public void setLkw60t(LKW60t lkw60t) {
		this.lkw60t = lkw60t;
	}
	
	public ObjektZaehler getObjektZaehler() 
	{
		return objektZaehler;
	}
	
	public void setObjektZaehler(ObjektZaehler objektZaehler)
	{
		this.objektZaehler = objektZaehler;
	}
	
	public Transportgut getTransportgut()
	{
		return transportgut;
	}
	
	public void setTransportgut(Transportgut transportgut)
	{
		this.transportgut = transportgut;
	}
	
	public void setKundenliste(ArrayList<Kunde> kundenliste)
	{
		this.kundenliste = kundenliste;
	}

	public void setAuftragsliste(ArrayList<Auftrag> auftragsliste)
	{
		this.auftragsliste = auftragsliste;
	}
	
}
