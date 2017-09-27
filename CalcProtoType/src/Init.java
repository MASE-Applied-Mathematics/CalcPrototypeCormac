import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by C.I.T on 24-Sep-17.
 */
public class Init {

    public static void main(String[] args){


        // Input for matching the regexe pattern
        String input = "239+(3.09*(01.08-4))";
        //String input = "1+2*3/4";
        //String input = "50*6-3";

        Tokenizer tokenizer = new Tokenizer(input);

        ArrayList<Token> tokenArrayList = tokenizer.getTokenArrayList();
        Stack<Token> tokenStack = tokenizer.toStack(tokenArrayList);
        try {
            Queue<Token> outputQueue = shuntingYard(tokenArrayList);
            double answer = calculateExpression(outputQueue);
            System.out.println(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //Paul's section

    private static double calculateExpression(Queue<Token> outputQueue) throws Exception {
        Stack<Token> stack = new Stack<Token>();
        for (Token token : outputQueue) {
            if(token instanceof Operand){
                stack.push(token);
            }else{
                if(stack.size() < ((Operator)token).getParameterCount())
                {
                    System.out.println("Stack Size:" + stack.size());
                    System.out.println("Param Count:" + ((Operator)token).getParameterCount());

                    throw new Exception("Insufficient values");
                }else{
                    ArrayList<Operand> operands = new ArrayList<Operand>();
                    for (int i = 0; i < ((Operator)token).getParameterCount(); i++) {
                        operands.add((Operand)stack.pop());
                    }
                    stack.push(evaluateOperator(token, operands));
                }
            }
        }
        if(stack.size()==1)
        {
            return ((Operand)stack.pop()).getDouble();
        }else{
            throw new Exception("Error, the user has input too many values");
        }
    }




    private static Queue<Token> shuntingYard(ArrayList<Token> tokens) throws Exception {
        Queue<Token> outputQueue = new LinkedList<Token>();
        Stack<Token> stack = new Stack<Token>();
        for (Token token : tokens) {
            if(token instanceof Operand){
                outputQueue.add(token);
            }else if(token instanceof Operator && ((Operator) token).getOperatorType() == Operator.OperatorType.OPERATOR){
                while(!stack.isEmpty()){
                    Token topOfStack = stack.peek();
                    if(((Operator) topOfStack).getOperatorType() == Operator.OperatorType.LEFTBRACE)
                    {
                        break;
                    }
                    if(((Operator) token).getAsoc() == Operator.Associativity.LEFT && ((Operator) token).getPrecedence() >= ((Operator)topOfStack).getPrecedence())
                    {
                        outputQueue.add(stack.pop());
                    }else
                    {
                        break;
                    }
                }
                stack.push(token);
            }else if(((Operator) token).getOperatorType() == Operator.OperatorType.LEFTBRACE){
                stack.push(token);
            }else if(((Operator) token).getOperatorType() == Operator.OperatorType.RIGHTBRACE){
                while(((Operator) stack.peek()).toChar() != '('){
                    outputQueue.add(stack.pop());
                    if(stack.isEmpty())
                    {
                        throw new Exception("Mismatched Braces");
                    }
                }
                stack.pop();
            }
        }
        while(!stack.isEmpty()){
            if(((Operator) stack.peek()).toChar() == '(' || ((Operator) stack.peek()).toChar() == ')')
            {
                throw new Exception("Mismatched Braces");
            }
            else{
                outputQueue.add(stack.pop());
            }
        }
        return outputQueue;
    }

    private static Token evaluateOperator(Token operator, ArrayList<Operand> operands) throws Exception {
        Token result;

        switch (((Operator)operator).toChar()) {
            case '+':
                result = new Operand(operands.get(1).getDouble() + operands.get(0).getDouble());
                //result.setValue(operands.get(1).getValue() + operands.get(0).getValue());
                break;
            case '-':
                result = new Operand(operands.get(1).getDouble() - operands.get(0).getDouble());
                //result.setValue(operands.get(1).getValue() - operands.get(0).getValue());
                break;
            case '*':
                result = new Operand(operands.get(1).getDouble() * operands.get(0).getDouble());
                //result.setValue(operands.get(1).getValue() * operands.get(0).getValue());
                break;
            case '/':
                result = new Operand(operands.get(1).getDouble() / operands.get(0).getDouble());
                //result.setValue(operands.get(1).getValue() / operands.get(0).getValue());
                break;
            case '^':
                result = new Operand(Math.pow(operands.get(1).getDouble(), operands.get(0).getDouble()));
                //result.setValue(Math.pow(operands.get(1).getValue(), operands.get(0).getValue()));
                break;
            case '_':
                result = new Operand(-operands.get(0).getDouble());
                //result.setValue(-operands.get(0).getValue());
                break;

            default:
                throw new Exception("Unknown Operator");
        }
        return result;
    }

}


