package Lab7;

public class Lab07_Q1
{
    public static void main(String[] args)
    {
        int[][] matrixOne =
        {
                { 1, 2, 3 },
                { 4, 5, 6 } };
        int[][] matrixTwo =
        {
                { 7, 8 },
                { 9, 10 },
                { 11, 12 } };
        int[][] matrixSquare =
        {
                { 1, 0, 0 },
                { 2, 1, 0 },
                { 3, 4, 1 } };
        int[][] childMatrix =
        {
                { 1, 5 },
                { 8, 9 } };
        int[][] parentMatrix =
        {
                { 1, 0, 2, 3 },
                { 4, 1, 5, 6 },
                { 7, 8, 9, 10 } };

        System.out.println("Matrix One:");
        printMatrix(matrixOne);
        System.out.println("Matrix Two:");
        printMatrix(matrixTwo);

        System.out.println("Multiplication Result:");
        int[][] result = multiplyMatrices(matrixOne, matrixTwo);
        printMatrix(result);

        System.out.println("Checking if matrix is rectangular:");
        printMatrix(matrixSquare);
        String rectangularResult = checkRectangular(matrixSquare);
        System.out.println("Result: " + rectangularResult);

        System.out.println("Checking if child matrix is a subset of parent matrix.");
        System.out.println("Parent:");
        printMatrix(parentMatrix);
        System.out.println("Child:");
        printMatrix(childMatrix);
        boolean isSubsetResult = isSubset(parentMatrix, childMatrix);
        System.out.println("Is child matrix a subset of parent matrix? " + isSubsetResult);

    }

    public static int[][] multiplyMatrices(int[][] matrixOne, int[][] matrixTwo)
    {
        int[][] result = new int[matrixOne.length][matrixTwo[0].length];
        if (matrixOne[0].length == matrixTwo.length)
        {
            for (int row = 0; row < matrixOne.length; row++)
            {
                for (int column = 0; column < matrixTwo[0].length; column++)
                {
                    int i = 0, addedValue = 0;
                    while (i < matrixOne[0].length)
                    {
                        addedValue += matrixOne[row][i] * matrixTwo[i][column];
                        i++;
                    }
                    result[row][column] = addedValue;
                }

            }

            return result;
        } else
        {
            System.out.println("Matrices are not suitable for multiplication.");
            return null;
        }

    }

    public static String checkRectangular(int[][] matrix)
    {
        boolean isLowerTriangle = true;
        boolean isUpperTriangle = true;
        // Checking if lower triangle
        int i = 1;
        for (int row = 0; row < matrix.length; row++)
        {
            i += row;
            while (i < matrix[row].length)
            {
                if (matrix[row][i] != 0)
                    isLowerTriangle = false;
                i++;
            }
            i = 1;
        }
        // Checking if upper triangle
        i = 1;
        for (int column = 0; column < matrix[0].length; column++)
        {
            i += column;
            while (i < matrix.length)
            {
                if (matrix[i][column] != 0)
                    isUpperTriangle = false;
                i++;
            }
            i = 1;
        }

        if (isLowerTriangle)
            return "Lower Triangular";
        else if (isUpperTriangle)
            return "Upper Triangular";
        else
            return "Neither";

    }

    public static boolean isSubset(int[][] parent, int[][] child)
    {
        // Check if the parent matrix includes child matrix.
        boolean isChild = false;
        for (int row = 0; row < parent.length - 1; row++)
        {
            for (int column = 0; column < parent[0].length - 1; column++)
            {
                // if the parent includes top left value of child
                if (parent[row][column] == child[0][0])
                {
                    // Check if the rest of the child is in parent.
                    isChild = true;
                    for (int childRow = 0; childRow < child.length; childRow++)
                    {
                        for (int childColumn = 0; childColumn < child.length; childColumn++)
                        {
                            if (child[childRow][childColumn] != parent[row + childRow][column + childColumn])
                            {
                                isChild = false;
                            }
                        }
                    }

                }
            }
        }

        return isChild;
    }

    public static void printMatrix(int[][] matrix)
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
