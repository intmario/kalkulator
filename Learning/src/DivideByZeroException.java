
public class DivideByZeroException extends Exception
{
	@Override
	public String getMessage()
	{
		return "Nie wolno dzieli� przez 0.";
	}
}
