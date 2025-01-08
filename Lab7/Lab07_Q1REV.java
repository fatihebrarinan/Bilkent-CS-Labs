package Lab7;

public class Lab07_Q1REV
{
    public static void main(String[] args)
    {
        int[][] squareMatrix =
        {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 } };
        System.out.println("Original Square Matrix:");
        printMatrix(squareMatrix);
        int[][] rotatedSquareMatrix = rotate90Degree(squareMatrix);
        System.out.println("\nRotated Square Matrix:");
        printMatrix(rotatedSquareMatrix);
        int[][] nonSquareMatrix =
        {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 } };
        System.out.println("\nOriginal Non-Square Matrix:");
        printMatrix(nonSquareMatrix);
        int[][] rotatedNonSquareMatrix = rotate90Degree(nonSquareMatrix);
        System.out.println("\nRotated Non-Square Matrix:");
        printMatrix(rotatedNonSquareMatrix);
    }

    private static int[][] rotate90Degree(int[][] matrix)
    {
        int[][] updatedMatrix = new int[matrix[0].length][matrix.length];
        for (int column = 0; column < matrix[0].length; column++)
        {
            for (int row = matrix.length - 1; row >= 0; row--)
            {
                updatedMatrix[column][matrix.length - row - 1] = matrix[row][column];
            }
        }
        return updatedMatrix;
    }

    private static void printMatrix(int[][] matrix)
    {
        for (int row = 0; row < matrix.length; row++)
        {
            for (int column = 0; column < matrix[row].length; column++)
            {
                System.out.print(matrix[row][column] + " ");
            }
            System.out.println();
        }
    }
}
