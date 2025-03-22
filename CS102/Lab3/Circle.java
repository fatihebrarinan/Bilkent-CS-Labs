public class Circle extends GeometricShape2D {
    private float radius;
    private final float pi = (float) Math.PI;

    public Circle(float radius) {
        this.radius = radius;
    }

    @Override
    public void printInfo() {
        System.out.printf("Type: Circle | Radius: %.2f\n", radius);

    }

    @Override
    public float calculateArea() {
        return pi * radius * radius;
    }
}
