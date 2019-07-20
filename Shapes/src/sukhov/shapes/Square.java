package sukhov.shapes;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Значение должно быть больше нуля");
        }

        this.side = side;
    }

    public void setSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Значение должно быть больше нуля");
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

    @Override
    public String toString() {
        return "квадрат: сторона = " + side;
    }

    @Override
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

    @Override
    public int hashCode() {
        return Double.hashCode(side);
    }
}
