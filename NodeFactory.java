import java.util.Random;

public class NodeFactory {
    private int numIndepVars;
    private Node[] currentOps;
    NodeFactory(Binop[] b, int numVars) {
        //add
        numIndepVars = numVars;
        Node[] nodeArray = new Node[4];
        for (int i=0;i<b.length;i++) {
            nodeArray[i] = new Node(b[i]);
        }
        currentOps = nodeArray;
    }
    public Node getOperator(Random rand) {
        // add
        int index = rand.nextInt(currentOps.length);
        return (Node) currentOps[index].clone();
    }
    public int getNumOps() {
        {
            return currentOps.length;
        }
    }
    public Node getTerminal(Random rand) {
        // add
        int randNum = rand.nextInt(numIndepVars+1);
        Node n1;

        if (randNum == numIndepVars) {
            n1 = new Node(new Variable(randNum));
        }
        else {
            n1 = new Node(new Const(rand.nextDouble()));
        }

        return n1;
    }
    public int getNumIndepVars() {
        // add
        {
            return numIndepVars;
        }
    }
}