/**
 * 
 * Name: Patel, Vanit
 * Project: 2
 * Due: 10/18/24
 * Course: cs-2400-02-f24
 *
 * Description:
 * This program turns a infix expression to a postfix expression and evaluates it. 
 * The program also checks the balance and if the operand is integer
 */

public class ExpressionTest {
    public static void main(String[] args) {
        System.out.println("Expression by V. Patel");

        // Iterate through each argument provided as an infix expression
        for(String infixExpression : args) {
            try {

                // Split the infix expression into tokens (space-separated)
                String[] tokensInfix = infixExpression.split(" ");

                // Print the original infix expression
                System.out.println(infixExpression);

                // Convert infix to postfix
                String[] postfixExpression = Expression.convertToPostfix(tokensInfix);

                // Print the converted postfix expression
                System.out.print("\t");
                for (String infix: postfixExpression) {
                    System.out.print(infix + " ");
                }

                // Evaluate the postfix expression and print the result
                int result = Expression.evaluatePostfix(postfixExpression);
                System.out.print("= " + result);
                System.out.println();
                
            } catch (RuntimeException e) {

                // Print error message if any exception occurs
                System.out.println("Error: " + e.getMessage());
            }
        }         
    }
}
            
    

       
    


