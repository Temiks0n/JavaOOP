package sukhov.shapes;

public class Circle implements Shapes {
    private double radius;

    public Circle(double radius) {
        if (radius < 0) {
            throw new RuntimeException("Значение должно быть больше нуля");
        }

        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius < 0) {
            throw new RuntimeException("Значение должно быть больше нуля");
        }

        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * PI * radius;
    }

    public String toString() {
        return "окружность: радиус = " + radius;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Circle p = (Circle) o;
        return radius == p.radius;
    }

    public int hashCode() {
        return 37 + Double.hashCode(radius);
    }
}
