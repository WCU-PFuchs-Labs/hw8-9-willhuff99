//Author: Will Huff

public class Variable extends Unop {
    private int index;
    public Variable(int index) {
        this.index = index;
    }
    public double eval(double[] values) {
        return values[index-1];
    }

    public String toString() {
        return "X"+index;
    }
}
