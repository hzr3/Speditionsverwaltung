package model;

public class Fuhrpark 
{
	private LKW24t lkw24t;
	private LKW60t lkw60t;
	
	// Platz [x][0] ist der 24-Tonner, [x][1] ist der 60-Tonner. 
	private int[][] jahresbitmaske;
	
	
	public Fuhrpark() 
	{
		jahresbitmaske = new int[365][2];
	}
	
	public boolean verfuegbar(int tag, int lkw) 
	{
		if (lkw == 0 && jahresbitmaske[tag][lkw] < 6)
		{
			return true;
		}
		else if (lkw == 1 && jahresbitmaske[tag][lkw] < 4)
		{
			return true;
		}
		return false;
	}
	
	
	public boolean lkwWaehlen(Transportgut tg, String datum) 
	{
		lkw24t = new LKW24t();
		lkw60t = new LKW60t();
		
		int tag = datumInTageszahl(datum);
		System.out.println(tag);
		
		if (lkw24t.ladungPasst(tg) && verfuegbar(tag, 0)) 
		{
			jahresbitmaske[tag][0]++;
			return true;
		}	
		else if (lkw60t.ladungPasst(tg) && verfuegbar(tag, 1))
		{
			jahresbitmaske[tag][1]++;
			
			return true;
		}
		return false;
	}
	
	public int datumInTageszahl(String datum) 
	{	
		String[] splitted = datum.split("/");
		
		int tag = Integer.valueOf(splitted[0]);
		int monat = Integer.valueOf(splitted[1]);
		
		int tagNr = 0;
		{
			if ( monat < 3)
			{
				tagNr =  tag + 31 * monat - 31;
			}
			else
			{
				tagNr = tag + (153 * monat - 162) / 5;
			}
		} 
		return tagNr; 
	}
	
	public String jahresbitmaskeAlsString()
	{
		String jbm = "";
		
		for (int tage = 0; tage < jahresbitmaske.length; tage++)
		{
			jbm = jbm + jahresbitmaske[tage][0] + "/" + jahresbitmaske[tage][1] + "  ";
			
			if (tage % 25 == 0 && tage != 0)
			{
				jbm = jbm + "\n";
			}
		}
		return jbm; 
	}
	
	
	
	
}