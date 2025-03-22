import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    private static GeometricShape2D[] shapes2D = new GeometricShape2D[0];
    private static GeometricShape3D[] shapes3D = new GeometricShape3D[0];

    public static void main(String[] args)
    {
        boolean isOn = true;
        while (isOn)
        {
            printShapes();
            System.out.print("""
                    0 - Exit program
                    1 - Create and store new shapes
                    2 - Add existing shape to Multishape
                    3 - List all shapes
                    4 - Merge Multishapes
                    5 - Edit shape
                    Choose an option: """);
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0)
            {
                System.out.println("Exiting the program...");
                isOn = false;
            }

            switch (input)
            {
            case 1 -> makeShape();
            case 2 -> addToMultiShape2D();
            case 3 -> listShapes();
            case 4 -> mergeAllMultiShape2D();
            case 5 -> editShape();
            default -> System.out.println("Invalid input.");
            }
        }

        scanner.close();
    }

    private static void printShapes()
    {
        System.out.println("2D Shapes:");
        for (GeometricShape2D shape : shapes2D)
            shape.printInfo();

        System.out.println("3D Shapes:");
        for (GeometricShape3D shape : shapes3D)
            shape.printInfo();
    }

    private static void makeShape()
    {
        System.out.println(
                "Which shape do you want to make?\n0-Circle\n1-Cube\n2-Cuboid\n3-Cylinder\n4-Ellipse\n5-Equilateral Triangle\n6-MultiShape2D\n7-Pyramid\n8-Rectangle\n9-Sphere\n10-Square");
        int makeInput = scanner.nextInt();
        scanner.nextLine();
        switch (makeInput)
        {
        case 0:
            // Circle
            System.out.print("What is the radius? ");
            float circleRadius = scanner.nextFloat();
            scanner.nextLine();
            Circle circle = new Circle(circleRadius);
            shapes2D = (GeometricShape2D[]) addNewItemToArray(circle, shapes2D);
            break;
        case 1:
            // Cube
            System.out.print("What is the length of one edge? ");
            float cubeEdge = scanner.nextFloat();
            Cube cube = new Cube(cubeEdge);
            shapes3D = (GeometricShape3D[]) addNewItemToArray(cube, shapes3D);
            break;
        case 2:
            // Cuboid
            System.out.print("What are the dimensions? ");
            float cuboidL1 = scanner.nextFloat();
            scanner.nextLine();
            float cuboidL2 = scanner.nextFloat();
            scanner.nextLine();
            float cuboidL3 = scanner.nextFloat();
            scanner.nextLine();
            Cuboid cuboid = new Cuboid(cuboidL1, cuboidL2, cuboidL3);
            shapes3D = (GeometricShape3D[]) addNewItemToArray(cuboid, shapes3D);
            break;
        case 3:
            // Cylinder
            System.out.print("What is the radius? ");
            float cylinderR = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("What is the height? ");
            float cylinderH = scanner.nextFloat();
            scanner.nextLine();
            Cylinder cylinder = new Cylinder(cylinderR, cylinderH);
            shapes3D = (GeometricShape3D[]) addNewItemToArray(cylinder, shapes3D);
            break;
        case 4:
            // Ellipse
            System.out.print("What is the width radius? ");
            float ellipseR1 = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("What is the height radius? ");
            float ellipseR2 = scanner.nextFloat();
            scanner.nextLine();
            Ellipse ellipse = new Ellipse(ellipseR1, ellipseR2);
            shapes2D = (GeometricShape2D[]) addNewItemToArray(ellipse, shapes2D);
            break;
        case 5:
            // Equilateral Triangle
            System.out.print("What is the length of one edge? ");
            float triangleEdge = scanner.nextFloat();
            scanner.nextLine();
            EquilateralTriangle equilateralTriangle = new EquilateralTriangle(triangleEdge);
            shapes2D = (GeometricShape2D[]) addNewItemToArray(equilateralTriangle, shapes2D);
            break;
        case 6:
            // MultiShape2D
            MultiShape2D multiShape2D = new MultiShape2D();
            shapes2D = (GeometricShape2D[]) addNewItemToArray(multiShape2D, shapes2D);
            handleMultiShape2DMaking(multiShape2D);
            break;
        case 7:
            // Pyramid
            System.out.print("What is the length of one base edge?");
            float pyramidEdge = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("What is the height?");
            float pyramidHeight = scanner.nextFloat();
            scanner.nextLine();
            Pyramid pyramid = new Pyramid(pyramidEdge, pyramidHeight);
            shapes3D = (GeometricShape3D[]) addNewItemToArray(pyramid, shapes3D);
            break;
        case 8:
            // Rectangle
            System.out.print("What are the dimensions? ");
            float rectangleL1 = scanner.nextFloat();
            scanner.nextLine();
            float rectangleL2 = scanner.nextFloat();
            scanner.nextLine();
            Rectangle rectangle = new Rectangle(rectangleL2, rectangleL1);
            shapes2D = (GeometricShape2D[]) addNewItemToArray(rectangle, shapes2D);
            break;
        case 9:
            // Sphere
            System.out.print("What is the radius? ");
            float sphereRadius = scanner.nextFloat();
            scanner.nextLine();
            Sphere sphere = new Sphere(sphereRadius);
            shapes3D = (GeometricShape3D[]) addNewItemToArray(sphere, shapes3D);
            break;
        case 10:
            // Square
            System.out.print("What is the length of one edge? ");
            float squareEdge = scanner.nextFloat();
            scanner.nextLine();
            Square square = new Square(squareEdge);
            shapes2D = (GeometricShape2D[]) addNewItemToArray(square, shapes2D);
            break;

        default:
            System.out.println("Invalid input.");
            break;
        }

    }

    private static Object[] addNewItemToArray(Object item, Object[] array)
    {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = item;
        return array;
    }

    private static void handleMultiShape2DMaking(MultiShape2D multiShape2D)
    {
        boolean addMode = true;
        while (addMode)
        {
            System.out.println(
                    "Which shape do you want to add to the MultiShape2D?\n-1 - Exit\n0-Circle\n1-Ellipse\n2-Equilateral Triangle\n3-Rectangle\n4-Square");
            int multiShapeInput = scanner.nextInt();
            scanner.nextLine();
            switch (multiShapeInput)
            {
            case -1:
                System.out.println("Finished adding items to the MultiShape2D.");
                addMode = false;
                break;
            case 0:
                // Circle
                System.out.print("What is the radius? ");
                float circleRadius = scanner.nextFloat();
                scanner.nextLine();
                Circle circle = new Circle(circleRadius);
                multiShape2D.addShape(circle);
                break;
            case 1:
                // Ellipse
                System.out.print("What is the width radius? ");
                float ellipseR1 = scanner.nextFloat();
                scanner.nextLine();
                System.out.print("What is the height radius? ");
                float ellipseR2 = scanner.nextFloat();
                scanner.nextLine();
                Ellipse ellipse = new Ellipse(ellipseR1, ellipseR2);
                multiShape2D.addShape(ellipse);
                break;
            case 2:
                // Equilateral Triangle
                System.out.print("What is the length of one edge? ");
                float triangleEdge = scanner.nextFloat();
                scanner.nextLine();
                EquilateralTriangle equilateralTriangle = new EquilateralTriangle(triangleEdge);
                multiShape2D.addShape(equilateralTriangle);
                break;
            case 3:
                // Rectangle
                System.out.print("What are the dimensions? ");
                float rectangleL1 = scanner.nextFloat();
                scanner.nextLine();
                float rectangleL2 = scanner.nextFloat();
                scanner.nextLine();
                Rectangle rectangle = new Rectangle(rectangleL2, rectangleL1);
                multiShape2D.addShape(rectangle);
                break;
            case 4:
                // Square
                System.out.print("What is the length of one edge? ");
                float squareEdge = scanner.nextFloat();
                scanner.nextLine();
                Square square = new Square(squareEdge);
                multiShape2D.addShape(square);
                break;
            default:
                System.out.println("Invalid input.");
                break;
            }
        }
    }

    private static void addToMultiShape2D()
    {
        MultiShape2D[] multiShape2DArray = new MultiShape2D[0];
        for (int i = 0; i < shapes2D.length; i++)
        {
            if (shapes2D[i] instanceof MultiShape2D)
            {
                multiShape2DArray = (MultiShape2D[]) addNewItemToArray(shapes2D[i], multiShape2DArray);
            }
        }
        if (shapes2D.length >= 2 && multiShape2DArray.length > 0)
        {
            System.out.println("Which MultiShape2D do you want to add objects?");
            for (int i = 0; i < multiShape2DArray.length; i++)
            {
                System.out.print(i + ". ");
                multiShape2DArray[i].printInfo();
            }
            int multiShapeSelection = scanner.nextInt();
            scanner.nextLine();
            if (multiShapeSelection > multiShape2DArray.length - 1 || multiShapeSelection < 0)
            {
                System.out.println("Invalid input.");
            } else
            {
                MultiShape2D multiShape2D = multiShape2DArray[multiShapeSelection];

                System.out.println("Which 2D object do you want to add?");
                for (int i = 0; i < shapes2D.length; i++)
                {
                    System.out.print(i + ". ");
                    shapes2D[i].printInfo();
                }
                int addSelection = scanner.nextInt();
                scanner.nextLine();
                if (addSelection > shapes2D.length - 1 || addSelection < 0
                        || shapes2D[addSelection].equals(multiShape2D))
                {
                    System.out.println("Invalid input.");
                } else
                {
                    // Add it to the MultiShape2D
                    multiShape2D.addShape(shapes2D[addSelection]);
                    // Delete item from shapes2D
                    shapes2D = removeElementFromArray(shapes2D, addSelection);
                    System.out.println("Added shape to MultiShape2D!");

                }

            }

        }
    }

    private static GeometricShape2D[] removeElementFromArray(GeometricShape2D[] arr, int indexToRemove)
    {
        if (indexToRemove < 0 || indexToRemove >= arr.length)
        {
            throw new IllegalArgumentException("Invalid index");
        }

        GeometricShape2D[] newArr = new GeometricShape2D[arr.length - 1];

        // Copy elements before the index
        for (int i = 0; i < indexToRemove; i++)
        {
            newArr[i] = arr[i];
        }

        // Copy elements after the index
        for (int i = indexToRemove; i < newArr.length; i++)
        {
            newArr[i] = arr[i + 1];
        }

        return newArr;
    }

    private static void listShapes()
    {
        int counter = 0;
        System.out.println("2D Shapes");
        for (GeometricShape2D geometricShape2D : shapes2D)
        {
            System.out.print(counter + ". ");
            geometricShape2D.printInfo();
            counter++;
        }
        System.out.println("3D Shapes");
        for (GeometricShape3D geometricShape3D : shapes3D)
        {
            System.out.print(counter + ". ");
            geometricShape3D.printInfo();
            counter++;
        }
        System.out.println("Do you want details for a specific object? (-1 to return)");
        int infoInput = scanner.nextInt();
        scanner.nextLine();
        if (infoInput == -1)
        {
            System.out.println("Returning...");
        } else if (infoInput >= 0 && infoInput < shapes2D.length)
        {
            // Detail wanted for 2D object
            GeometricShape2D wanted2D = shapes2D[infoInput];
            if (wanted2D instanceof MultiShape2D)
            {
                // Detail wanted for MultiShape. We define a new printing technique.
                printMultiShape2DDetails((MultiShape2D) wanted2D, infoInput);
            } else
            {
                wanted2D.printInfo();
                System.out.printf(" | Area: %f\n", wanted2D.calculateArea());
            }

        } else if (infoInput >= shapes2D.length && infoInput < shapes2D.length + shapes3D.length)
        {
            // Detail wanted for 3D object
            GeometricShape3D wanted3D = shapes3D[infoInput - shapes2D.length];
            wanted3D.printInfo();
            System.out.printf(" | Area: %f | Volume: %f\n", wanted3D.calculateArea(), wanted3D.calculateVolume());
        } else
        {
            System.out.println("Invalid input.");
        }

    }

    private static void printMultiShape2DDetails(MultiShape2D multiShape2D, int depth)
    {
        printIndent(depth);
        System.out.printf("Multi-shape, area %.2f:\n", multiShape2D.calculateArea());

        // Iterate through contained shapes
        for (GeometricShape2D shape : multiShape2D.getShapes())
        {
            if (shape instanceof MultiShape2D)
            {
                printMultiShape2DDetails((MultiShape2D) shape, depth + 1);
            } else
            {
                printIndent(depth + 1);
                System.out.print("- ");
                shape.printInfo();
            }
        }
    }

    private static void printIndent(int depth)
    {
        for (int i = 0; i < depth; i++)
        {
            System.out.print("  "); // Two spaces per depth level
        }
    }

    private static void mergeAllMultiShape2D()
    {
        for (GeometricShape2D geometricShape2D : shapes2D)
        {
            if (geometricShape2D instanceof MultiShape2D)
            {
                ((MultiShape2D) geometricShape2D).mergeShapes();
            }
        }
        System.out.println("Merged all MultiShape2D's");
    }

    private static void editShape()
    {
        int counter = 0;
        for (GeometricShape2D geometricShape2D : shapes2D)
        {
            System.out.print(counter + ". ");
            geometricShape2D.printInfo();
            counter++;
        }
        for (GeometricShape3D geometricShape3D : shapes3D)
        {
            System.out.print(counter + ". ");
            geometricShape3D.printInfo();
            counter++;
        }
        System.out.println("Which object do you want to edit?");

        int editInput = scanner.nextInt();
        scanner.nextLine();
        if (editInput >= 0 && editInput < shapes2D.length)
        {
            GeometricShape2D object2D = shapes2D[editInput];
            if (object2D instanceof Circle)
            {
                System.out.println("Whats the new radius?");
                float newCircleRadius = scanner.nextFloat();
                scanner.nextLine();
                shapes2D[editInput] = new Circle(newCircleRadius);
            } else if (object2D instanceof Ellipse)
            {
                System.out.println("Whats the new width radius?");
                float newWidthRadius = scanner.nextFloat();
                scanner.nextLine();
                System.out.println("Whats the new height radius?");
                float newHeightRadius = scanner.nextFloat();
                scanner.nextLine();
                shapes2D[editInput] = new Ellipse(newWidthRadius, newHeightRadius);
            } else if (object2D instanceof EquilateralTriangle)
            {
                System.out.println("Whats the new edge lenght?");
                float newTriangleEdge = scanner.nextFloat();
                scanner.nextLine();
                shapes2D[editInput] = new EquilateralTriangle(newTriangleEdge);
            } else if (object2D instanceof Rectangle)
            {
                System.out.println("What are the new dimensions?");
                float newRectangleL1 = scanner.nextFloat();
                scanner.nextLine();
                float newRectangleL2 = scanner.nextFloat();
                scanner.nextLine();
                shapes2D[editInput] = new Rectangle(newRectangleL1, newRectangleL2);
            } else if (object2D instanceof Square)
            {
                System.out.println("What the new edge lenght?");
                float newSquareEdge = scanner.nextFloat();
                scanner.nextLine();
                shapes2D[editInput] = new Square(newSquareEdge);
            }

        } else if (editInput < shapes2D.length + shapes3D.length)
        {
            GeometricShape3D object3D = shapes3D[editInput - shapes2D.length];
            if (object3D instanceof Cube)
            {
                System.out.println("What is the new edge lenght>");
                float newCubeEdge = scanner.nextFloat();
                scanner.nextLine();
                shapes3D[editInput - shapes2D.length] = new Cube(newCubeEdge);
            } else if (object3D instanceof Cuboid)
            {
                System.out.println("What are the new dimensions?");
                float newCuboidL1 = scanner.nextFloat();
                scanner.nextLine();
                float newCuboidL2 = scanner.nextFloat();
                scanner.nextLine();
                float newCuboidL3 = scanner.nextFloat();
                scanner.nextLine();
                shapes3D[editInput - shapes2D.length] = new Cuboid(newCuboidL1, newCuboidL2, newCuboidL3);
            } else if (object3D instanceof Cylinder)
            {
                System.out.println("What is the new radius?");
                float newCylinderRadius = scanner.nextFloat();
                scanner.nextLine();
                System.out.println("What is the new height?");
                float newCylinderHeight = scanner.nextFloat();
                scanner.nextLine();
                shapes3D[editInput - shapes2D.length] = new Cylinder(newCylinderRadius, newCylinderHeight);
            } else if (object3D instanceof Pyramid)
            {
                System.out.println("What is the new base lenght?");
                float newPyramidBase = scanner.nextFloat();
                scanner.nextLine();
                System.out.println("What is the new height?");
                float newPyramidHeight = scanner.nextFloat();
                scanner.nextLine();
                shapes3D[editInput - shapes2D.length] = new Pyramid(newPyramidBase, newPyramidHeight);
            } else if (object3D instanceof Sphere)
            {
                System.out.println("What is the new radius?");
                float newSphereRadius = scanner.nextFloat();
                scanner.nextLine();
                shapes3D[editInput - shapes2D.length] = new Sphere(newSphereRadius);
            }
        } else
        {
            System.out.println("Invalid input.");
        }

    }

}
