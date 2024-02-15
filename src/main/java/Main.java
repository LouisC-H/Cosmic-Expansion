import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        String filename = "src/main/resources/input.txt";
        System.out.println(runDay11Code(filename, 1000000));

    }

    public static long runDay11Code(String filename, int expansionCoefficient){

        File inputFile = new File(filename);

        SpaceMap spaceMap = initialiseSpaceMap(inputFile, expansionCoefficient);

        populateSpaceMap(inputFile, spaceMap);

        spaceMap.expandSpace();

        return spaceMap.getSumGalaxiesDistance();
    }

    public static SpaceMap initialiseSpaceMap(File inputFile, int expansionCoefficient){

        int rowCount = 0, charCount = 0;

        try {
            Scanner lineCounter = new Scanner(inputFile);

            charCount = lineCounter.nextLine().length();
            rowCount ++;

            while (lineCounter.hasNextLine()) {
                rowCount++;
                lineCounter.nextLine();
            }
            lineCounter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new SpaceMap (rowCount, charCount, expansionCoefficient);

    }

    private static void populateSpaceMap(File inputFile, SpaceMap spaceMap){
        try {
            Scanner myReader = new Scanner(inputFile);
            int rowCount = 0;

            while (myReader.hasNextLine()) {
                String charString = myReader.nextLine();
                spaceMap.populateSpace(charString, rowCount);
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
