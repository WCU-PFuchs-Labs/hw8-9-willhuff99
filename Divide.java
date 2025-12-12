//Author: Will Huff

public class Divide extends Binop {
    public double eval(double left, double right) {  //finished divide class to return 1.0
        if (Math.abs(right) < 0.0001) {
            return 1.0;
        }
        else {
            return left/right;
        }
    }

    public String toString() {
        return "/";
    }
}


