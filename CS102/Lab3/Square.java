public class Square extends Rectangle {
    public Square(float side) {
        super(side, side);
    }

    @Override
    public void printInfo() {
        System.out.printf("Type: Square | Side Length: %.2f\n", width);
    }
}