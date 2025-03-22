public class Sphere extends GeometricShape3D {
    private float radius;
    private final float pi = (float) Math.PI;

    public Sphere(float radius) {
        this.radius = radius;
    }

    @Override
    public void printInfo() {
        System.out.printf("Type: Sphere | Radius: %.2f\n", radius);

    }

    @Override
    public float calculateArea() {
        return 4 * pi * radius * radius;
    }

    @Override
    public float calculateVolume() {
        return (float) (4 / 3) * pi * (float) Math.pow(radius, 3);
    }

}
