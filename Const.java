/**
 * Code Template Author: David G. Cooper
 * Purpose: An operation class representing a constant number
 */
public class Const extends Unop {
    /** the constant value */
    private double value;
    /**
     * @param d the value to set the constant to.
     */
    public Const(double d) {
        value = Math.round(d * 100.0) / 100.0;
    }
    /**
     * @return the value of the constant.
     */
    public double eval(double[] values) {
        return value;
    }
}
