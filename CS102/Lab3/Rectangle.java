public class Rectangle extends GeometricShape2D {
    protected float width;
    protected float height;

    public Rectangle(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void printInfo() {
        System.out.printf("Type: Rectangle | Width: %.2f | Height: %.2f\n", width, height);
    }

    @Override
    public float calculateArea() {
        return height * width;
    }
}