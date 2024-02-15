import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SpaceMap {

    private final int rowCount;
    private final int colCount;
    private int expansionCoefficient;
    private final int[][] spaceMatrix;

    private ArrayList<Integer> emptyRowPositions = new ArrayList<>();
    private ArrayList<Integer> emptyColPositions = new ArrayList<>();

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
    }

    private void getGalaxyPositions(){
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (spaceMatrix[i][j] == 1){
                    this.galaxiesPositions.add(new int[]{i,j});
                }
            }
        }
    }

    public long getSumGalaxiesDistance(){
        getGalaxyPositions();
        long rollingSum = 0;
        for (int i = 0; i < galaxiesPositions.size(); i++) {
            for (int j = i + 1; j < galaxiesPositions.size(); j++) {
                rollingSum += getVerticalDistance(galaxiesPositions.get(i)[0], galaxiesPositions.get(j)[0]);
                rollingSum += getHorizontalDistance(galaxiesPositions.get(i)[1], galaxiesPositions.get(j)[1]);
            }
        }
        return rollingSum;
    }

    private long getVerticalDistance(int pos1, int pos2) {
        int originalDistance = Math.abs(pos1 - pos2);

        int numEmptyRows = 0;
        for (int rowNum :
                emptyRowPositions) {
            // if rowNum is bigger than one and smaller than the other, it is between the two
            if ( pos1 < rowNum ^ pos2 < rowNum ){
                numEmptyRows++;
            }
        }
        return originalDistance + (long) numEmptyRows * expansionCoefficient;
    }

    private long getHorizontalDistance(int pos1, int pos2) {
        int originalDistance = Math.abs(pos1 - pos2);

        int numEmptyCols = 0;
        for (int colNum :
                emptyColPositions) {
            // if colNum is bigger than one and smaller than the other, it is between the two
            if ( pos1 < colNum ^  pos2 < colNum ){
                numEmptyCols++;
            }
        }
        return originalDistance + (long) numEmptyCols * expansionCoefficient;
    }


    //Utils
    private boolean isRowEmpty(int[][] matrix, int rowNumber){
        return IntStream.of(matrix[rowNumber]).sum() == 0;
    }

    private boolean isColEmpty(int[][] spaceMatrix, int j) {
        int colSum = 0;
        for (int[] matrix : spaceMatrix) {
            colSum += matrix[j];
        }
        return colSum == 0;
    }
}
