package model;

public class Adresse
{
	private String 	name;
	private String  ansprechpartner;
	private String 	strasse;
	private int 	hausnummer;
	private int 	plz;
	private String 	stadt;
	private String land;
	private int 	telefon;
	
	
	public Adresse (String name, String ansprechpartner, String strasse, int hausnummer, int plz, String stadt, String land, int telefon)
	{
		this.name 				= name;
		this.ansprechpartner 	= ansprechpartner;
		this.strasse 			= strasse;
		this.hausnummer 		= hausnummer;
		this.plz 				= plz;
		this.stadt				= stadt;
		this.land 				= land;
		this.telefon			= telefon;	
	}
	
	public EuLaender containsElement(String land)
	{
		for(EuLaender l : EuLaender.values()) 
		{
			if(l.toString().toLowerCase().contains(land.toLowerCase()))
			{
				return l;
			}
		}
		return null;
	}
	
	public String getName()
	{
		return name;
	}


	public String getAnsprechpartner()
	{
		return ansprechpartner;
	}


	public String getStrasse()
	{
		return strasse;
	}


	public int getHausnummer()
	{
		return hausnummer;
	}


	public int getPlz()
	{
		return plz;
	}


	public String getStadt()
	{
		return stadt;
	}

	public String getLand()
	{
		return land;
	}

	public int getTelefon()
	{
		return telefon;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setAnsprechpartner(String ansprechpartner)
	{
		this.ansprechpartner = ansprechpartner;
	}

	public void setStrasse(String strasse)
	{
		this.strasse = strasse;
	}

	public void setHausnummer(int hausnummer)
	{
		this.hausnummer = hausnummer;
	}

	public void setPlz(int plz)
	{
		this.plz = plz;
	}

	public void setStadt(String stadt)
	{
		this.stadt = stadt;
	}

	public void setLand(String land)
	{
		this.land = land;
	}

	public void setTelefon(int telefon)
	{
		this.telefon = telefon;
	}
	
	

	
}