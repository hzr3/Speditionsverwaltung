package controller;

import view.IO;
import model.Adresse;
import model.Auftrag;
import model.Kunde;
import model.Model;
import model.Transportgut;


public class Controller 
{
	private IO view;
	private Model model;
	
	/*
	public void kundeErstellen(String name, String ansprechpartner, String straße, int hausnr, int plz, String stadt, String land, int telefon) 
	{
		Adresse adresse = new Adresse(name, ansprechpartner, straße, hausnr, plz, stadt, land, telefon);
		Kunde kunde = new Kunde(adresse);
	}
	
	public void auftragErstellen(Transportgut tg, Kunde kunde, String datum) 
	{
		Auftrag auftrag = new Auftrag(tg, kunde, datum);	
	}
	*/
	
	
	
	public void inAuftragsliste(Auftrag auftrag)
	{
		model.getAuftragsliste().add(auftrag);
	}
	
	public void inKundenliste(Kunde kunde)
	{
		// Muss noch sortiert werden
		model.getKundenliste().add(kunde);
	}
	
	public Controller(Model model)
	{
		this.model = model;
	}
	
	public void setView(IO view)
	{
		this.view = view;
	}
	
	public IO getView()
	{
		return this.view;
	}

	public Model getModel()
	{
		return model;
	}

	public void setModel(Model model)
	{
		this.model = model;
	}		
}