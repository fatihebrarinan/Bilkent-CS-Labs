public class Cube extends Cuboid {
    public Cube(float edge) {
        super(edge, edge, edge);
    }

    @Override
    public void printInfo() {
        System.out.printf("Type: Cube | Edge: %.2f\n", height);
    }
}
