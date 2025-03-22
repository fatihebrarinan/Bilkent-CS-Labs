public class EquilateralTriangle extends GeometricShape2D
{
    private float edge;

    public EquilateralTriangle(float edge)
    {
        this.edge = edge;
    }

    @Override
    public void printInfo()
    {
        System.out.printf("Type: Equilateral Triangle | Edge: %.2f\n", edge);

    }

    @Override
    public float calculateArea()
    {
        return edge * edge * (float) Math.sqrt(3) * (1 / 4);
    }
}
