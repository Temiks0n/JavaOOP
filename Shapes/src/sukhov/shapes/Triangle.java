package sukhov.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private final double epsilon = 1.0e-10;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (Math.abs(((x3 - x1) * (y2 - y1)) - ((y3 - y1) * (x2 - x1))) <= epsilon) {
            throw new IllegalArgumentException("Точки лежат на одной прямой");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public void setXY1(double x1, double y1) {
        if (Math.abs(((x3 - x1) * (y2 - y1)) - ((y3 - y1) * (x2 - x1))) <= epsilon) {
            throw new IllegalArgumentException("Точки лежат на одной прямой");
        }

        this.x1 = x1;
        this.y1 = y1;
    }

    public void setXY2(double x2, double y2) {
        if (Math.abs(((x3 - x1) * (y2 - y1)) - ((y3 - y1) * (x2 - x1))) <= epsilon) {
            throw new IllegalArgumentException("Точки лежат на одной прямой");
        }

        this.x2 = x2;
        this.y2 = y2;
    }

    public void setXY3(double x3, double y3) {
        if (Math.abs(((x3 - x1) * (y2 - y1)) - ((y3 - y1) * (x2 - x1))) <= epsilon) {
            throw new IllegalArgumentException("Точки лежат на одной прямой");
        }

        this.x3 = x3;
        this.y3 = y3;
    }

    private static double getSide(double xFrom, double xTo, double yFrom, double yTo) {
        return Math.sqrt(Math.pow((xTo - xFrom), 2) + Math.pow((yTo - yFrom), 2));
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - getSide(x1, x2, y1, y2)) * (halfPerimeter - getSide(x1, x3, y1, y3)) * (halfPerimeter - getSide(x2, x3, y2, y3)));
    }

    @Override
    public double getPerimeter() {
        return getSide(x1, x2, y1, y2) + getSide(x1, x3, y1, y3) + getSide(x2, x3, y2, y3);
    }

    @Override
    public String toString() {
        return "треугольник с координатами: x1 = " + x1 + " y1 = " + y1 + " x2 = " + x2 + " y2 = " + y2 + " x3 = " + x3 + " y3 = " + y3;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Triangle p = (Triangle) o;
        return x1 == p.x1 && y1 == p.y1 && x2 == p.x2 && y2 == p.y2 && x3 == p.x3 && y3 == p.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}
