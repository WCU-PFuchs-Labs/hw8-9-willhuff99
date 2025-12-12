import java.util.*;
import tabular.*;

public class GPTree implements Collector, Comparable<GPTree>, Cloneable {
    private Node root;
    private ArrayList<Node> crossNodes;
    private double fitness;


    /**
     * @param - node The node to be collected.
     * TODO: implement this method
     */
    public void collect(Node node) {
        // add node to crossNodes if it is not a leaf node
        if (!node.isLeaf()) {
            crossNodes.add(node);
        }
    }

    public void evalFitness(DataSet dataSet) {  //note: implemented evalFitness as requested in hw 8 but seems to be no usages
        fitness = 0;
        for (DataRow row: dataSet.getRows()) {
            double temp = eval(row.getIndependentVariables());
            temp -= row.getDependentVariable();
            temp *= temp;
            fitness += temp;
        }
    }

    public double getFitness() {
        return fitness;
    }

    public int compareTo(GPTree t) {
        if (fitness < t.getFitness()) {
            return -1;
        }
        else if (fitness > t.getFitness()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public boolean equals(Object o) {
        if (o != null && o instanceof GPTree) {
            if (this.compareTo((GPTree) o) == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public Object clone() {
        Object o = null;
        try {
            o =  super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("Can't clone.");
        }
        GPTree b = (GPTree) o;
        if(root != null) {
            b.root = (Node) root.clone();
        }
        if(crossNodes != null) {
            b.crossNodes = crossNodes;
        }
        b.fitness = fitness;
        return o;
    }

    // DO NOT EDIT code below for Homework 8.
    // If you are doing the challenge mentioned in
    // the comments above the crossover method
    // then you should create a second crossover
    // method above this comment with a slightly
    // different name that handles all types
    // of crossover.

    /**
     * This initializes the crossNodes field and
     * calls the root Node's traverse method on this
     * so that this can collect the Binop Nodes.
     */
    public void traverse() {
        crossNodes = new ArrayList<Node>();
        root.traverse(this);
    }

    /**
     * This returns a String with all of the binop Strings
     * separated by semicolons
     */
    public String getCrossNodes() {
        StringBuilder string = new StringBuilder();
        int lastIndex = crossNodes.size() - 1;
        for(int i = 0; i < lastIndex; ++i) {
            Node node = crossNodes.get(i);
            string.append(node.toString());
            string.append(";");
        }
        string.append(crossNodes.get(lastIndex));
        return string.toString();
    }


    /**
     * this implements left child to left child
     * and right child to right child crossover.
     * Challenge: additionally implement left to
     * right child and right to left child crossover.
     */
    public void crossover(GPTree tree, Random rand) {
        // find the points for crossover
        this.traverse();
        tree.traverse();
        int thisPoint = rand.nextInt(this.crossNodes.size());
        int treePoint = rand.nextInt(tree.crossNodes.size());
        boolean left = rand.nextBoolean();
        // get the connection points
        Node thisTrunk = crossNodes.get(thisPoint);
        Node treeTrunk = tree.crossNodes.get(treePoint);


        if(left) {
            thisTrunk.swapLeft(treeTrunk);

        } else {
            thisTrunk.swapRight(treeTrunk);
        }

    }

    public GPTree() {
        root = null;
    }

    public GPTree(Node root) {
        this.root = root;
        fitness = 0;
    }

    public GPTree(NodeFactory n, int maxDepth, Random rand) {
        root = n.getOperator(rand);
        root.addRandomKids(n, maxDepth, rand);
    }

    public String toString() {
        return root.toString();
    }

    public double eval(double[] data) {
        return root.eval(data);
    }

}