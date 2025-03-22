public class Cuboid extends GeometricShape3D
{
    protected float height;
    protected float width;
    protected float depth;

    public Cuboid(float height, float width, float depth)
    {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    @Override
    public void printInfo()
    {
        System.out.printf("Type: Cuboid | Height: %.2f | Width: %.2f | Depth: %.2f\n", height, width, depth);
    }

    @Override
    public float calculateArea()
    {
        return 2 * (depth * height + depth * width + height * width);
    }

    @Override
    public float calculateVolume()
    {
        return depth * height * width;
    }
}