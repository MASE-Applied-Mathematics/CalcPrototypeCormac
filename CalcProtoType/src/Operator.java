/**
 * Created by C.I.T on 24-Sep-17.
 */
public class Operator extends Token {

    public static char[][] precedenceList = {{'(', ')'}, {'^', ' '}, {'*', '/'}, {'+', '-'}};

    public enum Associativity {
        LEFT, RIGHT
    }

    public enum OperatorType {
        NOTHING, NUMBER, OPERATOR, LEFTBRACE, RIGHTBRACE
    }

    private Associativity asoc;
    private OperatorType operatorType = OperatorType.OPERATOR;

    private int precedence = 0;
    private int parameterCount;

    public Operator(String sRep) {
        super(sRep);
        super.setTypedToken(this);
        precedence = setPrecedence();
        setAssociativity();
    }

    public char toChar() {
        return stringRepresentation.charAt(0);
    }

    private int setPrecedence() {
        //System.out.println(precedenceList.length);
        for (int i = 0; i < precedenceList.length; i++) {
            for (int j = 0; j < precedenceList[0].length; j++) {
                if (toChar() == precedenceList[i][j]) {
                    if(i == 0 && j == 0)
                        this.operatorType = OperatorType.LEFTBRACE;
                    else if(i == 0 && j == 1)
                        this.operatorType = OperatorType.RIGHTBRACE;
                    return i;
                }
            }
        }
        return 0;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    private void setAssociativity() {
        switch(toChar()) {
            case '+':
                asoc = Associativity.LEFT;
                parameterCount = 2;
                break;
            case '-':
                asoc = Associativity.LEFT;
                parameterCount = 2;
                break;
            case '*':
                asoc = Associativity.LEFT;
                parameterCount = 2;
                break;
            case '/':
                asoc = Associativity.LEFT;
                parameterCount = 2;
                break;
            case '^':
                asoc = Associativity.LEFT;
                parameterCount = 2;
                break;
        }
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public Associativity getAsoc() {
        return asoc;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

}
