import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.math.NumberUtils;

class ExpressionInterpreter
{
	private static String numberMatcher = "\\d+\\.\\d+|\\d+|\\-\\d+\\.\\d+|\\-\\d+";
	private static String operatorMatcher = "\\+|\\-\\D|\\/|\\*";
	
	private List<Double> numbers = new ArrayList<Double>();
	private List<String> operators = new ArrayList<String>();
	private String expression = new String("");
	private double result = 0.0d;
	
	//Geters
	public double getResult() {return this.result;}
	
	//Seters
	public void setExpression(String newExpression) {this.expression = newExpression;}
	
	public ExpressionInterpreter()
	{
		
	}
	
	public ExpressionInterpreter(String expression)
	{
		try
		{
			this.Interpret(expression);
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
	
	public void Interpret(String expression) throws 
		InvalidExpressionException, DivideByZeroException
	{
		this.numbers.clear();
		this.operators.clear();
		this.result = 0.0d;
		this.expression = expression;
		
		Matcher numberMatcher = Pattern.compile(ExpressionInterpreter.numberMatcher).matcher(this.expression);
		Matcher operatorMatcher = Pattern.compile(ExpressionInterpreter.operatorMatcher).matcher(this.expression);
		
		//Tu u¿yta jest biblioteka
		while(numberMatcher.find())
			this.numbers.add(NumberUtils.createDouble(numberMatcher.group()));
		
		while(operatorMatcher.find())
			this.operators.add(operatorMatcher.group());
		
		if (this.numbers.size() - this.operators.size() != 1)
			throw new InvalidExpressionException();
		
		//Calculations
		for (int i = 0; i < this.numbers.size() - 1; ++i)
		{
			if (this.operators.get(i).equals("*"))
			{
				this.numbers.set(i, this.numbers.get(i) * this.numbers.get(i+1));
				this.numbers.remove(i+1);
				this.operators.remove(i);
				--i;
			}
			else if (this.operators.get(i).equals("/"))
			{
				if (this.numbers.get(i+1) == 0.0d)
					throw new DivideByZeroException();
				
				this.numbers.set(i, this.numbers.get(i) / this.numbers.get(i+1));
				this.numbers.remove(i+1);
				this.operators.remove(i);
				--i;
			}
		}
		
		this.result += this.numbers.get(0);
		
		for (int i = 1; i < this.numbers.size(); ++i)
		{
			if (this.operators.get(i-1).equals("+"))
			{
				this.result += this.numbers.get(i);
			}
			else if (this.operators.get(i-1).equals("-"))
			{
				this.result -= this.numbers.get(i);
			}
		}
	}
}
