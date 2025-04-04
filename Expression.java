/**
 * Two methods: convertToPostfix and evaluatePostfix
 * convertToPostfix: converts the infix expression to postfix
 * evaluatePostfix: evaluate the postfix and returns the solution
 */
public class Expression{

    /**
     * Converts the infix to postfix
     * checks the balance of the infix 
     * checks if the operand is an interger or not
     * @param infixExpression methods takes in the input of the infix 
     * @return the postfix version of the infix
     */
    public static String[] convertToPostfix(String[] infixExpression) {
        StackInterface<String> operatorStack = new ArrayStack<>();
        StringBuilder postfix = new StringBuilder(); 
        int index = 0; 

        while(index < infixExpression.length) {
            String token = infixExpression[index];

            switch (token) {

                default:
                    if (isOperand(token)) {
                    
                        postfix.append(token).append(" ");
                    } else {
                        throw new RuntimeException("Invalid token: " + token);
                    }
                    break;

                case "^":
                operatorStack.push(token);
                break;

                case "+" : case "-" : case "*" : case "/" :
                while (!operatorStack.isEmpty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())){ 
                    postfix.append(operatorStack.peek()).append(" ");
                    operatorStack.pop();
                }
                operatorStack.push(token);
                break;

                case "(" : case "[": case "{":
               operatorStack.push(token);
               break;

                 case ")":
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                if (operatorStack.isEmpty() || !operatorStack.peek().equals("(")) {
                    throw new RuntimeException("Mismatched parentheses");
                }
                operatorStack.pop(); // Pop '('
                break;

            case "}":
                // Pop until '{' is found
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("{")) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                if (operatorStack.isEmpty() || !operatorStack.peek().equals("{")) {
                    throw new RuntimeException("Mismatched curly braces");
                }
                operatorStack.pop(); // Pop '{'
                break;

            case "]":
                // Pop until '[' is found
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("[")) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                if (operatorStack.isEmpty() || !operatorStack.peek().equals("[")) {
                    throw new RuntimeException("Mismatched square brackets");
                }
                operatorStack.pop(); // Pop '['
                break;  

            } 
            index++;
        }

         while(!operatorStack.isEmpty()) {
            String topOperator = operatorStack.pop();
            switch(topOperator) {
                 case "(":
                throw new RuntimeException("Mismatched parentheses");
                case "[":
                throw new RuntimeException("Mismatched square bracket");
                case "{":
                throw new RuntimeException("Mismatched curly brackets"); 

            }

            postfix.append(topOperator).append(" ");
        } 
        return postfix.toString().trim().split(" ");
    }     

    /**
     * shows the precedence of the operator 
     * @param operator takes in the operator of the infix expression
     * @return the precdence of the operator
     */
    private static int getPrecedence(String operator) {
        switch (operator) {

            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
                return 2;

            case "^":
                return 3;
            default:
            return -1;
        }
    } 

    /**
     * checks if the token is a integer or string
     * @param token takes in the token from the infix expression
     * @return true if token is the a integer and false if the token is not an integer
     */
    private static boolean isOperand(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * evlaute the postfix 
     * basically solves the expression 
     * @param postfixExpression takes in the postfix expression
     * @return the value of the evlautation 
     */
    public static int evaluatePostfix(String[] postfixExpression) {
        ArrayStack<Integer> valueStack = new ArrayStack<>();
    
        // Traverse through each token in the postfix expression
        for (String token : postfixExpression) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                    // Pop two operands from the stack
                    int operandTwo = valueStack.pop();
                    int operandOne = valueStack.pop();
                    
                    // Calculate result based on the operator
                    int result = 0;
                    switch (token) {
                        case "+":
                            result = operandOne + operandTwo;
                            break;

                        case "-":
                            result = operandOne - operandTwo;
                            break;

                        case "*":
                            result = operandOne * operandTwo;
                            break;

                        case "/":
                            if (operandTwo == 0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            result = operandOne / operandTwo;
                            break;

                        case "^":
                            result = (int) Math.pow(operandOne, operandTwo);
                            break;
                    }
    
                    // Push the result back to the stack
                    valueStack.push(result);
                    break;
    
                default:
                    // Assume the token is an operand (integer), parse it and push to the stack
                    try {
                        int operand = Integer.parseInt(token);
                        valueStack.push(operand);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Invalid token in postfix expression: " + token);
                    }
                    break;
            }
        }
    
        // The final result should be the only value remaining in the stack
        if (valueStack.isEmpty()) {
            throw new RuntimeException("Postfix expression is invalid.");
        }
    
        return valueStack.peek();
    }
}
