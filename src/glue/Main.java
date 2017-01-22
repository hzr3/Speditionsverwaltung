/*package glue;

import model.*;

public class Starter
{
	
	public static void main(String[] args)
	{
		// String name, String ansprechpartner, String strasse, int hausnummer, int plz, String stadt, String land, int telefon
		// Adresse adresse = new Adresse("ClioTec", "Gerd Müller", "Bismarckstraße", 120, 28203, "Bremen", "Deutschland", 0452344);
		// Kunde kunde = new Kunde(adresse);
		
		Fuhrpark fp =  new Fuhrpark();
		System.out.println(fp.jahresbitmaskeAlsString());
		
		// kunde.inDatei();
		
	}
}
*/

package glue;
import controller.Controller;
import view.IO;
import model.Model;


public class Main
{
	public Main()
	{
		Model myModel = new Model();
		Controller myController = new Controller(myModel);	
		
		IO myView = new IO(myController);			
	}
	
	public static void main(String[] args) 
	{
		new Main();
	}
	
}
