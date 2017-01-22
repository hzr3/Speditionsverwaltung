                        package model;


import model.Zahlungsart;

public class Auftrag extends ObjektZaehler
{
	private int anr;
	private Transportgut transportgut;
	private Kunde kunde;
	private String datum;
	private int preis = 5000;
	private boolean geliefert;
	private Zahlungsart zahlungsart;
	
	public Auftrag (Transportgut transportgut, Kunde kunde, String datum, Zahlungsart za)
	{
		this.anr = ObjektZaehler.zaehler++;		
		this.transportgut = transportgut;
		this.kunde = kunde;
		this.datum = datum;
		this.zahlungsart = za;
	}
	
	/* public void inDatei()
	{
		try 	
		{
			String auftrag = 			this.anr + "\t" +
										this.kunde.getAdresse().getName() +"\t" + 
										this.kunde.getVnr() + "\t" +
										this.getTransportgut().getLadungsart()+ "\t" +
										this.getTransportgut().getLaenge()	+ "\t" +
										this.getTransportgut().getBreite() + "\t" + 
										this.getTransportgut().getHoehe()	+ "\t" + 
										this.getTransportgut().getGewicht() + "\t" + 
										this.datum + "\t" +
										this.preis + "\t" +
										this.geliefert + "\t" + "Zahlungsart";
										
			
			FileWriter fw = new FileWriter("Pfad", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(auftrag);
			bw.newLine();
			
			bw.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	} */

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis)
	{
		this.preis = preis;
	}

	public void setAnr(int anr)
	{
		this.anr = anr;
	}

	public String getDatum()
	{
		return datum;
	}

	public int getAnr()
	{
		return anr;
	}

	public Transportgut getTransportgut()
	{
		return transportgut;
	}

	public Kunde getKunde()
	{
		return kunde;
	}
	
	public boolean isGeliefert()
	{
		return geliefert;
	}

	public void setGeliefert(boolean geliefert)
	{
		this.geliefert = geliefert;
	}

	public Zahlungsart getZahlungsart()
	{
		return zahlungsart;
	}

	public void setZahlungsart(Zahlungsart zahlungsart)
	{
		this.zahlungsart = zahlungsart;
	}
}