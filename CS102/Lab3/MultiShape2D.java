import java.util.Arrays;

public class MultiShape2D extends GeometricShape2D {
    GeometricShape2D[] shapes = new GeometricShape2D[0];

    @Override
    public void printInfo() {
        System.out.println("Type: MultiShape2D | Contains: [");
        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] instanceof MultiShape2D) {
                System.out.printf("Type: Multishape2D | Area: %.2f\n", shapes[i].calculateArea());
            } else {
                shapes[i].printInfo();
            }
        }
        System.out.println("]");
    }

    @Override
    public float calculateArea() {
        float totalArea = 0;
        for (int i = 0; i < shapes.length; i++) {
            totalArea += shapes[i].calculateArea();
        }
        return totalArea;
    }

    public void addShape(GeometricShape2D addedShape) {
        shapes = Arrays.copyOf(shapes, shapes.length + 1);
        shapes[shapes.length - 1] = addedShape;
    }

    public void mergeShapes() {
        float area = calculateArea();
        if (shapes.length != 0) {
            shapes = new GeometricShape2D[1];
            shapes[0] = new Square((int) Math.sqrt(area));
        }
    }

    public GeometricShape2D[] getShapes() {
        return shapes;
    }

}
