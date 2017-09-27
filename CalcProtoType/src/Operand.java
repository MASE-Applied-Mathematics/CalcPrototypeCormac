import java.math.BigDecimal;

/**
 * Created by C.I.T on 24-Sep-17.
 */
public class Operand extends Token {

    //Operand = Numbers
    public Operand(String sRep) {
        super(sRep);
        super.setTypedToken(this);
    }

    public Operand(double num) {
        super(""+num);
        super.setTypedToken(this);
    }

    public double getDouble() {
        return Double.parseDouble(this.getStringRepresentation());
    }

    public double getInt() {
        return Integer.parseInt(this.getStringRepresentation());
    }

    public BigDecimal getBigDecimal() {
        return BigDecimal.valueOf(getDouble());
    }

}
