import java.util.*;
import tabular.*;

public class Generation {
    private GPTree[] gpTree;
    private DataSet dataSet;
    private Random random;
    private NodeFactory nodeFactory;


    public Generation(int size, int maxDepth, String filename){
    random = new Random();
    dataSet = new DataSet(filename);
    }
    public void evalAll() {

    }
    public ArrayList<GPTree> getTopTen(){
        return null;
    }
    public void printBestFitness(){

    }
    public void printBestTree(){

    }
    public void evolve(){

    }
}
