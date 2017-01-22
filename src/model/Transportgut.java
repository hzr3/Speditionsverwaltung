package model;

public class Transportgut
{
	private Ladungsart ladungsart;
	private float laenge;
	private float hoehe;
	private float gewicht;
	private float breite;
	
	public Transportgut(Ladungsart ladungsart, float laenge, float breite, float hoehe, float gewicht) 
	{        		
		this.laenge = laenge;
		this.breite = breite;
		this.hoehe = hoehe;
		this.gewicht = gewicht;
		this.ladungsart = ladungsart;
		
	}
	
	public Ladungsart getLadungsart()
	{
		return ladungsart;
	}

	
	public void setLaenge(float laenge)
	{
		this.laenge = laenge;
	}

	public void setHoehe(float hoehe)
	{
		this.hoehe = hoehe;
	}

	public void setGewicht(float gewicht)
	{
		this.gewicht = gewicht;
	}

	public void setBreite(float breite)
	{
		this.breite = breite;
	}

	public void setLadungsart(Ladungsart ladungsart)
	{
		this.ladungsart = ladungsart;
	}

	public float getLaenge()
	{
		return laenge;
	}

	public float getHoehe()
	{
		return hoehe;
	}

	public float getGewicht()
	{
		return gewicht;
	}

	public float getBreite()
	{
		return breite;
	}	
}
