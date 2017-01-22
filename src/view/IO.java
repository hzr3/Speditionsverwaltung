package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import model.Fuhrpark;
import model.Kunde;
import model.Ladungsart;
import model.Adresse;
import model.Auftrag;
import model.Zahlungsart;
import controller.Controller;
import model.Transportgut;

public class IO 
{
	private Controller controller;
	private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	private boolean nichtGueltig;
	private int inInt = 0;
	private String inString = "";
	private float inFloat = 0.0f;
	
	public IO(Controller controller)
	{
		this.controller = controller;
		controller.getModel().kundenlisteLaden();
		controller.getModel().auftragslisteLaden();
		hauptmenue();
	}
	
	public void hauptmenue() 
	{	
		System.out.println("Bitte wählen Sie eine Option aus \n" + "1. Kunden \n" + "2. Aufträge \n" + "3. Fuhrpark \n" + "4. Beenden \n");
		
		switch(inInt())
		{
			case 1 : 	kunden();
						break;
					
			case 2 : 	auftrag();
						break;
					
			case 3 : 	fuhrpark();
						break;
						
			case 4 : 	System.exit(0);
						break;
		}
	}
	
	public void kunden() 
	{
		
				System.out.println("Kundenmenü \n" + "1. Neuen Kunden anlegen \n" + "2. Bearbeiten eines Kunden \n" 
									+ "3. Ausgabe der Kundenliste \n" + "4. Löschen eines Kunden \n" + "5. Zurück zum Hauptmenü \n");				
				
				switch(inInt())
				{
					case 1 :	System.out.println("Neuen Kunden anlegen \n"); 	
								neuerKunde();
								break;
					
					case 2 : 	System.out.println("Verwaltungsnummer eingeben:");
								kundeBearbeiten();
								break;
					
					case 3 : 	System.out.println(controller.getModel().kundenlisteAlsString());
								hauptmenue();
								break;
					
					case 4 :	System.out.println("Kundennummer eingeben:");
								controller.getModel().kundeLoeschen(inInt());
								hauptmenue();
								break;			
								
					case 5 : 	hauptmenue();
								break;
				}
	}
	
	public void auftrag() 
	{
		
				System.out.println("Auftragsmenü \n" + "1. Neuen Auftrag anlegen \n" + "2. Auftrag bearbeiten \n" + "3. Liste der Aufträge \n" + "4. Löschen eines Auftrags \n" +"5. Zurück ins Hauptmenü");
				
				switch(inInt())
				{
					case 1 : 	neuerAuftrag();
								break;
					
					case 2 : 	auftragBearbeiten();
								break;		
								
					case 3 : 	System.out.println(controller.getModel().auftragslisteAlsString());
								break;
					
					case 4 : 	System.out.println("Auftragsnummer eingeben:");
								controller.getModel().auftragLoeschen(inInt());
								break;
					
					case 5 : 	hauptmenue();
								break;
				}
	}
	
	public void fuhrpark() 
	{
		System.out.println("Fuhrparkmenü \n" + "Jahresliste abfragen? [j/n]");
		String jaNein = inString().toLowerCase();
		
		if(jaNein.equals("j"))
		{
			String jbm = controller.getModel().getFuhrpark().jahresbitmaskeAlsString();
			System.out.println(jbm);
		}
		else
		{
			hauptmenue();
		}
	}
	
	public void neuerKunde() 
	{
		
		System.out.println("Firmenname:");
		String inName = inString();
		
		System.out.println("Ansprechpartner:");
		String inPartner = inString();		
		
		System.out.println("Straße:");
		String inStrasse = inString();
		
		System.out.println("Hausnummer:");
		int inHausNr = inInt();
				
		System.out.println("PLZ:");
		int inPlz = inInt();
					
		System.out.println("Stadt:");
		String inStadt = inString();
		
		// EuLaender inLand;
		
		// do{
		System.out.println("Land:");
		String inLand = inString();
		
		// inLand = controller.getModel().getAdresse().containsElement(inString);
		/*	
		if(!wahr)
			{
				nichtGueltig = true;
			}
		else
			{
				 nichtGueltig = false;
			}
		}
		while(nichtGueltig);
		*/
		
		System.out.println("Telefon Nr.:");
		int inTelNr = inInt();
		
		Kunde k = new Kunde(new Adresse(inName, inPartner, inStrasse, inHausNr, inPlz, inStadt, inLand, inTelNr));
		
		controller.getModel().getKundenliste().add(k);
		// System.out.println(controller.getModel().getKundenliste().get(0));
		controller.getModel().kundenlisteInDatei();
		
		hauptmenue();
	}
	
	public void kundeBearbeiten() 
	{
		int vnr = inInt();
		controller.getModel().vnrSuchen(vnr);
		
		//Als Integer abfragen, string geben
		System.out.println("Was möchten sie ändern?" + "\n" + "1. Firmenname \n" + "2. Ansprechpartner \n" 
							+ "3. Straße \n" + "4. Hausnummer \n" + "5. PLZ \n" + "6. Stadt \n" + "7. Land \n" + "8. Telefon Nr. \n" );
		
		String attribut = "";
		
		switch (inInt())
		{
		 case 1 : 	attribut = "Firmenname";
		 			break;
		
		 case 2 : 	attribut = "Ansprechpartner";
		 			break;
		 
		 case 3 : 	attribut = "Straße";
		 			break;
		 
		 case 4 : 	attribut = "Hausnummer";
		 			break;
		 
		 case 5 :	attribut = "PLZ";
		 			break;
		 
		 case 6 : 	attribut = "Stadt";
		 			break;
		 
		 case 7 :	attribut = "Land";
		 			break;
		 
		 case 8 : 	attribut = "Telefonnummer";
		 			break;
		}
		
	
		System.out.println("Geben Sie '" + attribut + "' ein.");
		if(inInt == 1 || inInt == 2 || inInt == 3 || inInt == 6 || inInt == 7 )
		{
			String neuerWert = inString();
			controller.getModel().kundenlisteAendern(vnr, attribut, neuerWert);

		}
		else if(inInt == 4 || inInt == 5 || inInt == 8)
		{
			int neuerWert = inInt();
			controller.getModel().kundenlisteAendern(vnr, attribut, neuerWert);
		}
		
		hauptmenue();
		
	}
	
	public void auftragBearbeiten() 
	{
		System.out.println("Auftragsnummer eingeben:");
		int anr = inInt();
		int index = controller.getModel().anrSuchen(anr);
		
		//Als Integer abfragen, string geben
		System.out.println("Was möchten sie ändern?" + "\n" + "1. Ladungsart \n" + "2. Länge \n" 
							+ "3. Breite \n" + "4. Höhe \n" + "5. Gewicht \n" + "6. Geliefert \n" + "7. Zahlungsart \n");
		
		String attribut = "";
		Ladungsart la = null;
		
		switch (inInt())
		{
		 case 1 : 	System.out.println("Transportgut wählen: \n" + "1. Baumstämme \n" + "2. Stahlstangen \n" + "3. Stahlplatten \n" + "4. Betonteile \n" + "5. Glasverkleidung \n");
			 		switch(inInt())
		 			{
						case 1 	: 	la = Ladungsart.Baumstaemme;
									controller.getModel().getAuftragsliste().get(index).getTransportgut().setLadungsart(la);
									break;
						case 2	: 	la = Ladungsart.Stahlstangen;
									controller.getModel().getAuftragsliste().get(index).getTransportgut().setLadungsart(la);
									break;
						case 3 	: 	la = Ladungsart.Stahlplatten;
									controller.getModel().getAuftragsliste().get(index).getTransportgut().setLadungsart(la);
									break;
						case 4	: 	la = Ladungsart.Betonteile;
									controller.getModel().getAuftragsliste().get(index).getTransportgut().setLadungsart(la);
									break;
						case 5 	: 	la = Ladungsart.Glasverkleidungen;
									controller.getModel().getAuftragsliste().get(index).getTransportgut().setLadungsart(la);
									break;
		 			};
		 			break;
		
		 case 2 : 	System.out.println("Länge eingeben:");
			 		controller.getModel().getAuftragsliste().get(index).getTransportgut().setLaenge(inFloat());
		 			break;
		 
		 case 3 : 	System.out.println("Breite eingeben:");
			 		controller.getModel().getAuftragsliste().get(index).getTransportgut().setBreite(inFloat());
		 			break;
		 
		 case 4 : 	System.out.println("Höhe eingeben:");
			 		controller.getModel().getAuftragsliste().get(index).getTransportgut().setHoehe(inFloat());
		 			break;
		 
		 case 5 :	System.out.println("Gewicht eingeben:");
			 		controller.getModel().getAuftragsliste().get(index).getTransportgut().setGewicht(inFloat());
		 			break;
		 
		 case 6 : 	System.out.println("Geliefert? \n" + "1. Ja \n" + "2. Nein \n");
					switch(inInt())
					{
							case 1 	: 	controller.getModel().getAuftragsliste().get(index).setGeliefert(true);
										break;
							case 2	: 	controller.getModel().getAuftragsliste().get(index).setGeliefert(false);
										break;
					};
		 			break;
		 
		 case 7 :	System.out.println("Zahlungsart: \n" + "1. Bar \n" + "2. Überweisung \n");
					switch(inInt())
					{
							case 1 	: 	controller.getModel().getAuftragsliste().get(index).setZahlungsart(Zahlungsart.Bar);
										break;
							case 2	: 	controller.getModel().getAuftragsliste().get(index).setZahlungsart(Zahlungsart.Ueberweisung);
										break;
					};
					break;
		}
		
		controller.getModel().auftragslisteInDatei();
		hauptmenue();
		
	}
	
	
	public void neuerAuftrag() 
	{
		System.out.println("Neuen Auftrag anlegen \n");
		
		System.out.println("Kundennummern eingeben: ");
		int vnr = inInt();
		
		System.out.println("Anlieferdatum wählen(TT/MM/JJJJ):");
		String datum = inString();
		System.out.println(datum);
		
		System.out.println("Transportgut wählen: \n" + "1. Baumstämme \n" + "2. Stahlstangen \n" + "3. Stahlplatten \n" + "4. Betonteile \n" + "5. Glasverkleidung \n");
		
		Ladungsart la = null;
		
		switch(inInt())
		{
			case 1 	: 	la = Ladungsart.Baumstaemme;
						break;
			case 2	: 	la = Ladungsart.Stahlstangen;
						break;
			case 3 	: 	la = Ladungsart.Stahlplatten;
						break;
			case 4	: 	la = Ladungsart.Betonteile;
						break;
			case 5 	: 	la = Ladungsart.Glasverkleidungen;
						break;
		}
		
		System.out.println("Länge des Transportgutes wählen:");
		float inLaenge = inFloat();
		
		System.out.println("Höhe des Transportgutes wählen:");
		float inHoehe = inFloat();
		
		System.out.println("Breite des Transportgutes wählen:");
		float inBreite = inFloat();
		
		System.out.println("Gewicht des Transportgutes wählen:");
		float inGewicht = inFloat();
		
		Transportgut tg = new Transportgut(la, inLaenge, inHoehe, inBreite, inGewicht);
		
		
		boolean gueltig = controller.getModel().getFuhrpark().lkwWaehlen(tg, datum);
		if (!gueltig) 
		{
			System.out.println("Nicht gültig!");
		}
			
		
		System.out.println("Die Kosten betragen 5000€ und Lieferung innerhalb eines Tages \n");
		System.out.println("Zahlungsmöglichkeiten auswählen: \n" + "1. Bar \n" + "2. Überweisung");
		
		Zahlungsart za = null;
		
		switch(inInt())
		{
			case 1 	: 	za = Zahlungsart.Bar;
						break;
			case 2	: 	za = Zahlungsart.Ueberweisung;
						break;
		}
		
		int index = controller.getModel().vnrSuchen(vnr);
		Kunde kunde = controller.getModel().getKundenliste().get(index);
		
		Auftrag auftrag = new Auftrag(tg, kunde, datum, za);
		
		controller.getModel().getAuftragsliste().add(auftrag);
		controller.getModel().auftragslisteInDatei();
		
		hauptmenue();
	}
	
	public String inString()
	{
		do
		{
			try
			{
				inString = buffer.readLine();
				nichtGueltig = false;
			}
			catch (Exception e)
			{	
				System.err.println("Fehler bei der Eingabe! \n" + "Keine Zahlen gültig!");
				nichtGueltig = true;
			}
		}
		while(nichtGueltig);
		return inString;
	}
	public Integer inInt()
	{
		do
		{
			try
			{
				inInt = Integer.parseInt(buffer.readLine());
				nichtGueltig = false;
			}
			catch(Exception e)
			{
				System.err.println("Fehler bei der Eingabe! \n" + "Nur Zahlen gültig!");
				nichtGueltig = true;
			}
		}
		while(nichtGueltig);
	return inInt;
	}
	
	public Float inFloat()
	{
		do
		{
			try
			{
				inFloat = Float.parseFloat(buffer.readLine());
				nichtGueltig = false;
			}
			catch(Exception e)
			{
				System.err.println("Fehler bei der Eingabe! \n" + "Nur Zahlen gültig!");
				nichtGueltig = true;
			}
		}
		while(nichtGueltig);
	return inFloat;
	}
}