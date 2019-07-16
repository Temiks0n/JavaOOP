package sukhov.vectro;

import java.util.Arrays;

public class Vector {
    private int size;
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.size = size;
        components = new double[size];
    }

    public Vector(Vector vector) {
        this.size = vector.size;
        this.components = vector.components;
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Незаполненные компоненты");
        }
        this.size = components.length;
        this.components = components;
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        if (size < components.length) {
            throw new IllegalArgumentException("Размерность меньше количества компонентов");
        }

        if (size > components.length) {
            this.size = size;
            this.components = new double[size];

            for (int i = 0; i < components.length; i++) {
                this.components[i] = components[i];
            }
        } else {
            this.components = components;
            this.size = size;
        }
    }

    public int getSize(Vector vector) {
        return size;
    }

    public double[] getComponents(Vector vector) {
        return components;
    }

    public double getComponent(int index) {
        if (index <= 0 || index >= components.length) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        return components[index];
    }

    public void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        if (size < components.length) {
            throw new IllegalArgumentException("Размерность меньше количества компонентов");
        }

        this.size = size;
    }

    public void setComponents(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Незаполненные компоненты");
        }

        if (size > components.length) {
            this.components = new double[components.length];

            for (int i = 0; i < components.length; i++) {
                this.components[i] = components[i];
            }
        } else {
            this.components = components;
            this.size = components.length;
        }
    }

    public void setComponent(double component, int index) {
        if (index <= 0 || index >= components.length) {
            throw new IllegalArgumentException("Неверный индекс");
        }

        this.components[index] = component;
    }

    public double[] addition(Vector vector) {
        double[] addition = new double[Math.max(size, vector.size)];

        for (int i = 0; i < Math.min(size, vector.size); i++) {
            addition[i] = components[i] + vector.components[i];
        }

        return addition;
    }

    public static Vector addition(Vector vector1, Vector vector2) {
        double[] addition = new double[Math.max(vector1.size, vector2.size)];

        for (int i = 0; i < Math.min(vector1.size, vector2.size); i++) {
            addition[i] = vector1.components[i] + vector2.components[i];
        }

        return new Vector(addition);
    }

    public double[] subtraction(Vector vector) {
        double[] subtraction = new double[Math.max(size, vector.size)];

        for (int i = 0; i < Math.min(size, vector.size); i++) {
            subtraction[i] = components[i] - vector.components[i];
        }

        return subtraction;
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        double[] subtraction = new double[Math.max(vector1.size, vector2.size)];

        for (int i = 0; i < Math.min(vector1.size, vector2.size); i++) {
            subtraction[i] = vector1.components[i] - vector2.components[i];
        }
        return new Vector(subtraction);
    }

    public double[] multiplication(double scalar) {
        double[] result = new double[size];

        for (int i = 0; i < size; i++) {
            result[i] = components[i] * scalar;
        }

        return result;
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        double sum = 0;

        for (int i = 0; i < Math.min(vector1.size, vector2.size); i++) {
            sum += vector1.components[i] * vector2.components[i];
        }

        return sum;
    }

    public void reverse() {
        for (int i = 0; i < size; i++) {
            components[i] *= -1;
        }
    }

    public double getLength() {
        double length = 0;

        for (int i = 0; i < size; i++) {
            length += Math.pow(components[i], 2);
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

        boolean isEquals = this.size == p.size;
        if (isEquals) {
            for (int i = 0; i < size; i++) {
                if (this.components[i] != p.components[i]) {
                    isEquals = false;
                    break;
                }
            }
        }

        return isEquals;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    @Override
    public String toString() {
        return "" + Arrays.toString(components);
    }
}
