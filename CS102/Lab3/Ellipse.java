public class Ellipse extends GeometricShape2D
{
    private float widthRadius;
    private float heightRadius;
    private final float pi = (float) Math.PI;

    public Ellipse(float widthRadius, float heightRadius)
    {
        this.heightRadius = heightRadius;
        this.widthRadius = widthRadius;
    }

    @Override
    public void printInfo()
    {
        System.out.printf("Type: Ellipse | Width Radius: %.2f | Height Radius: %.2f\n", widthRadius, heightRadius);

    }

    @Override
    public float calculateArea()
    {
        return pi * widthRadius * heightRadius;
    }
}
