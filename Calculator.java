/**
* Take in a simple mathematical expression with the operators +, -, *, /, and ^,
* where operands are either a number or an expression enclosed in parentheses.
* The entire expression must also be enclosed in parentheses. 
*
* @author Sophie von Coelln 
*/
public class Calculator {
	
	/**
	* Loop through the expression. Push numbers and operators to respective
	* stacks. When a right parenthesis is encountered, pop the most recent 
	* operator and the two most recent numbers, compute the resulting value,
	* and push it to the values stack. 
	* 
	* @param expression the expression to be solved
	* @return the result of the expression
	*/
    public static double evaluate(String expression) {		
		Stack<Character> operators = new Stack<Character>();
		Stack<Double> values = new Stack<Double>();
		
		for (int i = 0; i < (expression.length()); i++) {
			if (expression.charAt(i) == '(' || expression.charAt(i) == ' ') {
				//ignore 
			}
			else if (Character.isDigit(expression.charAt(i))) { //store numbers as a string to accomodate for two digit numbers
				String tempNum = ""; 
				while (Character.isDigit(expression.charAt(i))) { //check for two-digit numbers
					tempNum += (expression.charAt(i));
					i++;
				}
				if (expression.charAt(i) == '.') { //ensure decimal points are not skipped
					tempNum += (expression.charAt(i));
					i++;
					while (Character.isDigit(expression.charAt(i))) { //check for several digit decimal points
						tempNum += (expression.charAt(i));
						i++;
					}
					i--; //ensure that an iteration is not skipped
				}
				else {
					i--; //ensure that an iteration is not skipped
				}
				double num = Double.parseDouble(tempNum);
				values.push(num);
			}
			else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/' || expression.charAt(i) == '^') {
				operators.push(expression.charAt(i));
			}
			else if (expression.charAt(i) == ')') { //pop the most recent operator and two most recent numbers and push the result of the equation to the values stack
				double num2 = values.pop().doubleValue();
				double num1 = values.pop().doubleValue();
				char op = operators.pop();
				
				switch(op) {
					case '+':
						values.push(num1+num2);
						break;
					case '-':
						values.push(num1-num2);
						break;
					case '*':
						values.push(num1*num2);
						break;
					case '/':
						values.push(num1/num2);
						break;
					case '^':
						values.push(Math.pow(num1, num2));
						break;
					default:
						break;
				}
			}
		}
		return values.pop();
    }
}