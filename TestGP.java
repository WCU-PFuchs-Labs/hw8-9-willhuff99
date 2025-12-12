
import java.util.*;

public class TestGP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println();
            String fileName = scanner.nextLine();
            int populationSize = 500;
            int maxDepth = 2;
            Generation generation = new Generation(populationSize, maxDepth, fileName);
            generation.evalAll();
            GPTree bestTree = generation.getBestTree();
            double fitness = bestTree.getFitness();
            for (int i = 0; i < 50; i++) {
                System.out.println("Generation " + (i + 1) + ": ");
                generation.evolve();
                generation.printBestTree();
                System.out.print(" = " + String.format("%.2f", fitness) + "\n");
                System.out.print("Fitness: " + String.format("%.2f", fitness) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}