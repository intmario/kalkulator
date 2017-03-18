
public class Calculator 
{	
	public static void main(String[] args)
	{
		Menu menu = new Menu(new String[]{
			"Obliczenia z klawiatury",
			"Obliczenia z pliku tekstowego"
		});
		
		menu.Display();
		
		StaticScanner.scanner.close();
	}
}
