import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Menu
{
	private List<String> options = new ArrayList<String>();
	
	/*
	 * options - menu options to display
	 */
	public Menu(String[] options)
	{
		for (String s : options)
			this.options.add(s);
	}
	
	private void CalculateFromFile()
	{
		String expression = new String();
		ExpressionInterpreter ie = new ExpressionInterpreter();
		BufferedReader bf;
		
		System.out.println("Podaj nazwê pliku: ");
		String filename = StaticScanner.scanner.nextLine();
		
		try
		{
			bf = new BufferedReader(new FileReader(filename));
			while ((expression = bf.readLine()) != null)
			{
				try
				{
					ie.Interpret(expression);
					double result = ie.getResult();
					
					System.out.println("Oto równanie: " + expression);
					System.out.println("Oto wynik: " + result + "\n\n");
				}
				catch (InvalidExpressionException e)
				{
					System.out.println(e.getMessage());
				}
				catch (DivideByZeroException e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Nie znaleziono pliku.");
		}
		catch (IOException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private void CalculateFromUser()
	{
		String expression = new String();
		ExpressionInterpreter ie = new ExpressionInterpreter();
		
		System.out.println("\nPodaj wyra¿enie do obliczenia, (\"koniec\" wychodzi do menu):");
		
		expression = StaticScanner.scanner.nextLine();
	
		while(!expression.matches("koniec"))
		{
			try
			{
				ie.Interpret(expression);
				double result = ie.getResult();
				
				System.out.println("Oto wynik: " + result);
			}
			catch (InvalidExpressionException e)
			{
				System.out.println(e.getMessage());
			}
			catch (DivideByZeroException e)
			{
				System.out.println(e.getMessage());
			}
			
			System.out.println("\nPodaj wyra¿enie do obliczenia, (\"koniec\" wychodzi do menu):");
			expression = StaticScanner.scanner.nextLine();
		}
	}
	
	public void Display()
	{
		int selection = 0;
		int n = 1;
		
		System.out.println("\nMenu: \n");
		
		for (String s : options)
			System.out.println(n++ + ". " + s);
		
		System.out.println("Wybierz opcjê: (\"koniec\" wychodzi do menu):");
		
		try
		{
			String input = StaticScanner.scanner.nextLine();

			if (!input.matches("koniec"))
				selection = Integer.parseInt(input);
		}
		catch(NumberFormatException e) {}
		
		switch (selection)
		{
			case 1:
				this.CalculateFromUser();
				this.Display();
				break;
			case 2:
				this.CalculateFromFile();
				this.Display();
				break;
			case 0:
				break;
				
			default: this.Display();
		}
	}
}

