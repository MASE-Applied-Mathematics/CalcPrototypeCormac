import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by C.I.T on 24-Sep-17.
 */
public class Tokenizer{

    private ArrayList<Token> tokenArrayList = new ArrayList<>();
    public static String abstractRegex = "("+Token.numericalRegex+"|"+Token.operandRegex+")";
    Pattern numericalPattern = Pattern.compile(abstractRegex);

    public Tokenizer(String input){
        Matcher absMatcher = numericalPattern.matcher(input);

        while(absMatcher.find()){
            String s = null;
            try{
                s = absMatcher.group(0);
            }catch (IllegalStateException ils){

            }
            tokenArrayList.add(tokenize(s));
        }
    }

    public Token tokenize(String value){
        Matcher numMatcher = Token.numericalPattern.matcher(value);
        Matcher opMatcher = Token.operandPattern.matcher(value);

        if(numMatcher.find()){
            return new Operand(numMatcher.group(0));
        }else if(opMatcher.find()){
            return new Operator(opMatcher.group(0));
        }
        return null;
    }


    public ArrayList<Token> getTokenArrayList() {
        return tokenArrayList;
    }

    public ArrayList<Token> getOperandArrayList(){
        ArrayList<Token> newList = new ArrayList<>();
        for(Token t : this.getTokenArrayList()){
            if(t.getTokenType().equals("Operand")){
                newList.add(t);
            }
        }
        return newList;
    }

    public ArrayList<Token> getOperatorArrayList(){
        ArrayList<Token> newList = new ArrayList<>();
        for(Token t : this.getTokenArrayList()){
            if(t.getTokenType().equals("Operator")){
                newList.add(t);
            }
        }
        return newList;
    }

    public char[] getOperatorArray(){
        ArrayList<Token> opArrayList = getOperatorArrayList();
        char[] cList = new char[opArrayList.size()];
        for(int i = 0; i < cList.length; i++){
            if(opArrayList.get(i) instanceof Operator){
                cList[i] = opArrayList.get(i).getStringRepresentation().charAt(0);
                //System.out.println(opArrayList.get(i).getStringRepresentation().charAt(0));
            }
        }
        return cList;
    }

    public double[] getOperandArray(){
        ArrayList<Token> opArrayList = getOperandArrayList();
        double[] cList = new double[opArrayList.size()];
        for(int i = 0; i < cList.length; i++){
            if(opArrayList.get(i) instanceof Operand){
                cList[i] = ((Operand) opArrayList.get(i)).getDouble();
            }
        }
        return cList;
    }

    public Stack<Token> toStack(ArrayList<Token> arrayList){
        Stack<Token> stack = new Stack<>();
        for(Token t : arrayList){
            stack.add(t);
        }
        return stack;
    }

    public Queue<Token> toQueue(ArrayList<Token> arrayList){
        Queue<Token> queue = new LinkedList<Token>();
        for(Token t : arrayList){
            queue.add(t);
        }
        return queue;
    }

}
