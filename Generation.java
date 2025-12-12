import java.util.*;
import tabular.*;

public class Generation {
    private GPTree[] gpTree;
    private DataSet dataSet;
    private Random random;
    private NodeFactory nodeFactory;

    public Generation(int size, int maxDepth, String filename) {
        dataSet = new DataSet(filename);
        random = new Random();
        Binop[] binop = {new Plus(), new Minus(), new Mult(), new Divide()};
        int numVars = dataSet.getRows().get(0).getIndependentVariables().length;
        nodeFactory = new NodeFactory(binop, numVars);
        gpTree = new GPTree[size];
        for (int i=0; i<size; i++) {
            gpTree[i] = new GPTree(nodeFactory, maxDepth, random);
        }
    }

    public void evalAll() {
        for (GPTree tree: gpTree) {
            tree.evalFitness(dataSet);
        }
        Arrays.sort(gpTree);
    }
    public ArrayList<GPTree> getTopTen() {
        ArrayList<GPTree> topTen = new ArrayList<>();
        int limit = Math.min(gpTree.length, 10);
        for (int i=0;i<limit;i++) {
            topTen.add(gpTree[i]);
        }
        return topTen;
    }

    public void printBestFitness() {
        System.out.println(gpTree[0].getFitness());
    }

    public void printBestTree() {
        System.out.println(gpTree[0]);
    }

    public GPTree getBestTree() {
        return gpTree[0];
    }

    public void evolve() {
        GPTree[] newTrees = new GPTree[gpTree.length];
        evalAll();
        for (int i = 0; i< gpTree.length; i++) {
            GPTree parent1 = gpTree[random.nextInt(gpTree.length/2)];
            while (parent1 == null) {
                parent1 = gpTree[random.nextInt(gpTree.length/2)];
            }
            GPTree parent2 = gpTree[random.nextInt(gpTree.length/2)];
            while (parent2 == null) {
                parent2 = gpTree[random.nextInt(gpTree.length/2)];
            }
            GPTree child1 = (GPTree) parent1.clone();
            GPTree child2 = (GPTree) parent2.clone();
            child1.crossover(child2, random);
            newTrees[i] = child1;
            if (i + 1 < gpTree.length) {
                newTrees[i + 1] = child2;
            }
        }
        gpTree = newTrees;
    }
}
