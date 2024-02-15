import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SpaceMap {

    private final int rowCount;
    private final int colCount;
    private int expansionCoefficient;
    private final int[][] spaceMatrix;

    private  int expandedRowCount;
    private  int expandedColCount;
    private ArrayList<Integer> emptyRowPositions = new ArrayList<>();
    private ArrayList<Integer> emptyColPositions = new ArrayList<>();
    private int[][] expandedMatrix;

    private ArrayList<int[]> galaxiesPositions = new ArrayList<>();

    public SpaceMap(int rowCount, int colCount, int expansionCoefficient) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.expansionCoefficient = expansionCoefficient - 1;
        this.spaceMatrix = new int[rowCount][colCount];
    }

    public void populateSpace(String charRow, int rowIndex){
        for (int i = 0; i < charRow.length(); i++) {
            char c = charRow.charAt(i);
            // In this line, we compare if the character is '#'. If it is, isGalaxy = 1. If not it is 0.
            int isGalaxy = c == '#' ? 1 : 0;
            spaceMatrix[rowIndex][i] = isGalaxy;
        }
    }

    public void expandSpace() {
        // As of P2, this only happens virtually because of memory constraints
        int numEmptyRows = 0;
        int numEmptyColumns = 0;
        for (int i = 0; i < rowCount; i++) {
            // stream turns int[] into a stream of numbers
            if (isRowEmpty(spaceMatrix, i)){
                emptyRowPositions.add(i);
            }
        }
        for (int j = 0; j < colCount; j++) {
            // stream turns int[] into a stream of numbers
            if (isColEmpty(spaceMatrix, j)){
                emptyColPositions.add(j);
            }
        }
        repopulateSpace();
    }

    private void repopulateSpace() {
        // If the row had been empty, all rows from now on are shifted one extra row down
        int rowOffset = 0;
        for (int i = 0; i < rowCount; i++) {
            if (emptyRowPositions.contains(i)){
                rowOffset+= expansionCoefficient;
            }
            // If the column had been empty, all column from now on are shifted one extra column right
                int colOffset = 0;
                for (int j = 0; j < colCount; j++) {
                    if (emptyColPositions.contains(j)){
                        colOffset+= expansionCoefficient;
                    }
                    expandedMatrix[i + rowOffset][j + colOffset] = spaceMatrix[i][j];
                }
        }
    }

    private void getGalaxyPositions(){
        for (int i = 0; i < expandedRowCount; i++) {
            for (int j = 0; j < expandedColCount; j++) {
                if (expandedMatrix[i][j] == 1){
                    this.galaxiesPositions.add(new int[]{i,j});
                }
            }
        }
    }

    public int getSumGalaxiesDistance(){
        getGalaxyPositions();
        int rollingSum = 0;
        for (int i = 0; i < galaxiesPositions.size(); i++) {
            for (int j = i; j < galaxiesPositions.size(); j++) {
                rollingSum += Math.abs(galaxiesPositions.get(i)[0]-galaxiesPositions.get(j)[0]);
                rollingSum += Math.abs(galaxiesPositions.get(i)[1]-galaxiesPositions.get(j)[1]);
            }
        }
        return rollingSum;
    }


    //Utils
    private boolean isRowEmpty(int[][] matrix, int rowNumber){
        return IntStream.of(matrix[rowNumber]).sum() == 0;
    }

    private boolean isColEmpty(int[][] spaceMatrix, int j) {
        int colSum = 0;
        for (int i = 0; i < spaceMatrix.length; i++) {
            colSum += spaceMatrix[i][j];
        }
        return colSum == 0;
    }

    // Getters and Setters
    public int[][] getSpaceMatrix() {
        return spaceMatrix;
    }
}
