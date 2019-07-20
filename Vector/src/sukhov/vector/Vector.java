package sukhov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.getSize());
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Незаполненные компоненты");
        }

        this.components = components;
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.components = new double[size];

        System.arraycopy(components, 0, this.components, 0, size);
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Неверный индекс");
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Неверный индекс");
        }

        this.components[index] = component;
    }

    public void addition(Vector vector) {
        for (int i = 0; i < Math.min(vector.getSize(), components.length); i++) {
            components[i] += vector.components[i];
        }
    }

    public static Vector addition(Vector vector1, Vector vector2) {
        vector1.addition(vector2);
        return new Vector(Arrays.copyOf(vector1.components,vector1.getSize()));
    }

    public void subtraction(Vector vector) {
        for (int i = 0; i < Math.min(vector.getSize(), components.length); i++) {
            components[i] -= vector.components[i];
        }
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        vector1.subtraction(vector2);
        return new Vector(Arrays.copyOf(vector1.components,vector1.getSize()));
    }

    public void multiplication(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        double sum = 0;

        for (int i = 0; i < Math.min(vector1.getSize(), vector2.getSize()); i++) {
            sum += vector1.components[i] * vector2.components[i];
        }

        return sum;
    }

    public void reverse() {
        for (int i = 0; i < components.length; i++) {
            components[i] *= -1;
        }
    }

    public double getLength() {
        double length = 0;

        for (double e : components) {
            length += Math.pow(e, 2);
        }

        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Vector p = (Vector) o;

        if (components.length != p.getSize()) {
            return false;
        }

        for (int i = 0; i < components.length; i++) {
            if (this.components[i] != p.components[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    @Override
    public String toString() {
        return Arrays.toString(components);
    }
}
