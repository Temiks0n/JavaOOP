package sukhov.shapes;

public class Square implements Shapes {
    private double side;

    public Square(double side) {
        if (side < 0) {
            throw new RuntimeException("Значение должно быть больше нуля");
        }

        this.side = side;
    }

    public void setSide(double side) {
        if (side < 0) {
            throw new RuntimeException("Значение должно быть больше нуля");
        }

        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    public String toString() {
        return "квадрат: сторона = " + side;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Square p = (Square) o;
        return side == p.side;
    }

    public int hashCode() {
        return 37 + Double.hashCode(side);
    }
}
