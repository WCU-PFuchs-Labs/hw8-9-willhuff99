//Edits and additions by Will Huff

import java.util.*;
/**
 * Code Template Author: David G. Cooper
 * Purpose: A Binary Tree class for Arithmetic evaluation
 */
public class Node implements Cloneable{
    protected int depth;
    private Node left;
    private Node right;
    private Op operation;

    public Node(Unop operation) {
        depth = 0;
        this.operation = operation;
    }

    public Node(Binop operation,Node left, Node right) {

        this.left = left;
        this.left.depth = depth + 1;
        this.right = right;
        this.right.depth = depth + 1;
        this.operation = operation;
        depth = 0;
    }

    public Node(Binop operation) {
        depth = 0;
        this.operation = operation;
    }

    public double eval(double[] values) {
        if (operation instanceof Unop) {
            return ((Unop)operation).eval(values);
        } else if (operation instanceof Binop) {
            return ((Binop)operation).eval(left.eval(values),right.eval(values));
        } else {
            System.err.println("Error operation is not a Unop or a Binop!");
            return 0.0;
        }
    }

    public String toString() {
        if (operation instanceof Const) {
            double[] values = {0};
            return String.valueOf(((Const)operation).eval(values));
        }
        else if (operation instanceof Variable) {
            return operation.toString();
        }
        else if (left != null && right != null) {
            return "(" +left.toString()+ " " +operation.toString()+ " " +right.toString()+ ")";
        }
        else {
            return "";
        }

    }

    public Object clone() {
        Object o = null;
        try {
            o =  super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("Op can't clone.");
        }
        Node b = (Node) o;
        if(left != null) {
            b.left = (Node) left.clone();
        }
        if(right != null) {
            b.right = (Node) right.clone();
        }
        if(operation != null) {
            b.operation = (Op) operation.clone();
        }
        return o;
    }

    public void addRandomKids(NodeFactory factory, int maxDepth, Random rand) {
        while (true) {
            if (depth == maxDepth) {
                left = factory.getTerminal(rand);
                left.depth = depth + 1;
                right = factory.getTerminal(rand);
                right.depth = depth + 1;
                return;
            }
            else {
                int random = rand.nextInt(factory.getNumOps()+factory.getNumIndepVars()+1);
                if (random < factory.getNumOps()) {
                    left = factory.getOperator(rand);
                    left.depth = depth + 1;
                    left.addRandomKids(factory, maxDepth, rand);
                    right = factory.getOperator(rand);
                    right.depth = depth + 1;
                    right.addRandomKids(factory, maxDepth, rand);
                }
                else {
                    left = factory.getTerminal(rand);
                    left.depth = depth + 1;

                    right = factory.getTerminal(rand);
                    right.depth = depth + 1;
                }
                return;
            }
        }
    }

    /**
     * collect using preorder traversal.
     */
    public void traverse(Collector c) {
        // collect this

        // traverse lChild

        // traverse rChild
        c.collect(this);
        if (left != null) {
            left.traverse(c);
        }
        if (right != null) {
            right.traverse(c);
        }
    }

    /**
     * swap this left child node with trunk left child node
     */
    public void swapLeft(Node trunk) {
        Node temp = left;
        left = trunk.left;
        trunk.left = temp;
    }
    
    /**
     * swap this right child node with trunk right child node
     */
    public void swapRight(Node trunk) {
        Node temp = right;
        right = trunk.right;
        trunk.right = temp;
    }

    /**
     * return true if operation is s Unop
     */
    public boolean isLeaf() {
        // return true if operation is a Unop
        if (operation instanceof Unop) {
            return true;
        }
        else {
            return false;
        }
    }
}
