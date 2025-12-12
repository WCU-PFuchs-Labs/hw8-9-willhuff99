
import java.util.*;

public class TestGeneration {
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
            generation.printBestTree();
            System.out.print(" = " + String.format("%.2f", fitness) + "\n");
            System.out.print("Fitness: " + String.format("%.2f", fitness) + "\n");
            System.out.print("Top Ten Fitness Values: ");
            ArrayList<GPTree> topTen = generation.getTopTen();
            for (int i = 0; i < topTen.size(); i++) {
                System.out.print(String.format("%.2f", topTen.get(i).getFitness()));
                if (i < topTen.size() - 1) System.out.print(", ");
            }
            System.out.print("\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
