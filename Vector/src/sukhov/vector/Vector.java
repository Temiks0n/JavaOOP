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

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.components = Arrays.copyOf(components, size);
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
            throw new IndexOutOfBoundsException("Неверный индекс");
        }

        this.components[index] = component;
    }

    public void addition(Vector vector) {
        if (components.length < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        int minSize = Math.min(vector.getSize(), components.length);

        for (int i = 0; i < minSize; i++) {
            components[i] += vector.components[i];
        }
    }

    public static Vector addition(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.addition(vector2);

        return vector;
    }

    public void subtraction(Vector vector) {
        if (components.length < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        int minSize = Math.min(vector.getSize(), components.length);

        for (int i = 0; i < minSize; i++) {
            components[i] -= vector.components[i];
        }
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtraction(vector2);

        return vector;
    }

    public void multiplication(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        double sum = 0;
        int minSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minSize; i++) {
            sum += vector1.components[i] * vector2.components[i];
        }

        return sum;
    }

    public void reverse() {
        multiplication(-1);
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
        StringBuilder stringBuilder = new StringBuilder();

        for (double component : components) {
            stringBuilder.append(component).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return "{" + stringBuilder + "}";
    }
}
