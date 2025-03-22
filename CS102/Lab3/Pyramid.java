public class Pyramid extends GeometricShape3D {
    private float base;
    private float height;

    public Pyramid(float base, float height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void printInfo() {
        System.out.printf("Type: Pyramid | Base Length: %.2f | Height: %.2f\n", base, height);
    }

    @Override
    public float calculateArea() {
        float slantHeight = (float) Math.sqrt(0.25f * base * base + height * height);
        return 2 * base * slantHeight + base * base;
    }

    @Override
    public float calculateVolume() {
        return (float) (1 / 3) * height * base * base;
    }
}