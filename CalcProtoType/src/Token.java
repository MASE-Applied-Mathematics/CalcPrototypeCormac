import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by C.I.T on 24-Sep-17.
 */
public abstract class Token {

    public String stringRepresentation;
    public static String numericalRegex = "[\\d\\.]+";
    public static String operandRegex = "[^\\.\\d\\s\\w]";
    public static String specialCharRegex = "(?:cos\\(*\\d+\\)*)|(?:tan\\(*\\d+\\)*)|(?:log\\(*\\d+\\)*)|\\π|\\!|(?:\\d+\\^\\d)";

    // Step 1: Allocate a Pattern object to compile a regexe
    public static Pattern numericalPattern = Pattern.compile(numericalRegex);
    public static Pattern operandPattern = Pattern.compile(operandRegex);

    private Token typedToken;

    public Token(String sRep){
        setStringRepresentation(sRep);
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    private void setStringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public Token getTypedToken() {
        return typedToken;
    }

    public void setTypedToken(Token typedToken) {
        this.typedToken = typedToken;
    }

    public String getTokenType() {
        if(typedToken!=null)
            return typedToken.getClass().getTypeName();
        else
            return "FATAL ERROR: Token has not been typed";
    }

    @Override
    public String toString(){
        return this.stringRepresentation;
    }

}
