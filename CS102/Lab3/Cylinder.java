public class Cylinder extends GeometricShape3D
{
    private float radius;
    private float height;
    private final float pi = (float) Math.PI;

    public Cylinder(float radius, float height)
    {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public void printInfo()
    {
        System.out.printf("Type: Cylinder | Radius: %.2f | Height: %.2f\n", radius, height);
    }

    @Override
    public float calculateArea()
    {
        return 2 * pi * radius * height;
    }

    @Override
    public float calculateVolume()
    {
        return pi * radius * radius * height;
    }

}
