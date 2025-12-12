
package tabular;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DataSet {
    private int numIndepVariables;
    private ArrayList<DataRow> data;
    public DataSet(String filename)
    {
        File file = new File(filename);
        try (Scanner in = new Scanner(file)) {
            String line = in.nextLine();
            String[] lineArray = line.split(",");
            int size = lineArray.length;
            numIndepVariables = size-1;
            data = new ArrayList<>();
            String string;
            String[] stringArray;
            while (in.hasNext()) {
                string = in.nextLine();
                stringArray = string.split(",");

                if (stringArray.length == size) {
                    double y = Double.parseDouble(stringArray[0]);
                    double[] x = new double[size-1];
                    for (int i=1;i<stringArray.length;i++) {
                        x[i-1] = Double.parseDouble(stringArray[i]);
                    }
                    data.add(new DataRow(y, x));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found");
        }
    }
    public ArrayList<DataRow> getRows() {
        return data;
    }
    public int getNumIndependentVariables() {
        return numIndepVariables;
    }
}
