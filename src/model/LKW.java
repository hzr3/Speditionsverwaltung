package model;

abstract class LKW 
{
	private float laenge;
	private float breite;
	private float hoehe;
	private float zuladung;
	
	public boolean ladungPasst(Transportgut tg)
	{
		if (tg.getBreite() <= this.breite && tg.getLaenge() <= this.laenge && tg.getHoehe() <= this.hoehe && tg.getGewicht() <= this.zuladung) 
		{
			return true;
		}
		return false;
	}
}