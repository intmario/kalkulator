
public class DivideByZeroException extends Exception
{
	@Override
	public String getMessage()
	{
		return "Nie wolno dzieliæ przez 0.";
	}
}
