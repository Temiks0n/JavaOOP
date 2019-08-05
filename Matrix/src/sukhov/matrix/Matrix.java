package sukhov.matrix;

import sukhov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int row, int column) {
        if ((row & column) <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0");
        }

        rows = new Vector[row];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(column);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowCount()];

        for (int i = 0; i < matrix.getRowCount(); i++) {
            rows[i] = new Vector(matrix.getRow(i));
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0");
        }

        int temp = 0;
        for (double[] e : matrix) {
            if (matrix.length < e.length) {
                temp = e.length;
            }
        }
        rows = new Vector[Math.max(matrix.length, temp)];

        for (int i = 0; i < rows.length; i++) {
            if (matrix.length > i) {
                rows[i] = new Vector(Arrays.copyOf(matrix[i], rows.length));
            } else {
                rows[i] = new Vector(rows.length);
            }
        }
    }

    public Matrix(Vector... rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0");
        }

        int temp = 0;
        for (Vector e : rows) {
            if (rows.length < e.getSize()) {
                temp = e.getSize();
            }
        }

        this.rows = new Vector[Math.max(rows.length, temp)];

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = new Vector(this.rows.length);
            for (int j = 0; j < this.rows[i].getSize(); j++) {
                if (rows.length > i) {
                    this.rows[i].setComponent(j, rows[i].getComponent(j));
                } else {
                    this.rows[i].setComponent(j, 0);
                }
            }
        }
    }

    public int getColumnCount() {
        return rows[0].getSize();
    }

    public int getRowCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }

        return rows[index];
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= getRowCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        Vector column = new Vector(getRowCount());

        for (int i = 0; i < column.getSize(); i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public double getElement(int indexRow, int indexColumn) {
        if (indexRow < 0 || indexRow >= getRowCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }
        if (indexColumn < 0 || indexColumn >= getColumnCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        return rows[indexRow].getComponent(indexColumn);
    }

    public void setElement(int indexRow, int indexColumn, double matrixElement) {
        if (indexRow < 0 || indexRow >= getRowCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }
        if (indexColumn < 0 || indexColumn >= getColumnCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        rows[indexRow].setComponent(indexColumn, matrixElement);
    }

    public void transpose() {
        Matrix matrix = new Matrix(rows);

        for (int i = 0; i < getRowCount(); i++) {
            setRow(i, matrix.getColumn(i));
        }
    }

    public void addition(Matrix matrix) {
        if (getColumnCount() != matrix.getColumnCount() || getRowCount() != matrix.getRowCount()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].getSize(); j++) {
                rows[i].setComponent(j, getElement(i, j) + matrix.getElement(i, j));
            }
        }
    }

    public static Matrix addition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getColumnCount() || matrix1.getRowCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        Matrix result = new Matrix(matrix1);

        result.addition(matrix2);

        return result;
    }

    public void subtraction(Matrix matrix) {
        if (getColumnCount() != matrix.getColumnCount() || getRowCount() != matrix.getRowCount()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].getSize(); j++) {
                rows[i].setComponent(j, getElement(i, j) - matrix.getElement(i, j));
            }
        }
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getColumnCount() || matrix1.getRowCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        Matrix result = new Matrix(matrix1);

        result.subtraction(matrix2);

        return result;
    }

    public void multiplication(double scalar) {
        for (Vector matrixElement : rows) {
            matrixElement.multiplication(scalar);
        }
    }

    public Vector multiplication(Vector column) {
        if (getColumnCount() != column.getSize()) {
            throw new IllegalArgumentException("Размерность столбца должна совподать с размерностью вектора");
        }

        double[] result = new double[column.getSize()];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < getRowCount(); j++) {
                result[i] += getElement(j, i) * column.getComponent(i);
            }
        }

        return new Vector(result);
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Размерность столбцов одной матрицы должны совпадать с размерностью строк другой матрицы");
        }

        double[][] result = new double[matrix1.getRowCount()][matrix2.getColumnCount()];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < matrix1.getColumnCount(); j++) {
                for (int k = 0; k < matrix2.getRowCount(); k++) {
                    result[i][j] += matrix1.getElement(i, k) * matrix2.getElement(k, j);
                }
            }
        }

        return new Matrix(result);
    }

    public double getDeterminant() {
        if (getRowCount() != getColumnCount()) {
            throw new IllegalArgumentException("Размерность строк или столбцов должна быть одинаковая");
        }

        if (getColumnCount() == 1) {
            return getElement(0, 0);
        }
        if (getColumnCount() == 2) {
            return getElement(0, 0) * getElement(1, 1) - getElement(0, 1) * getElement(1, 0);
        }

        return getDeterminant(rows);
    }

    private double getDeterminant(Vector[] matrix) {
        if (getColumnCount() == 3) {
            double a1 = getElement(0, 0) * getElement(1, 1) * getElement(2, 2);
            double a2 = getElement(0, 1) * getElement(1, 2) * getElement(2, 0);
            double a3 = getElement(0, 2) * getElement(1, 0) * getElement(2, 1);
            double b1 = getElement(0, 2) * getElement(1, 1) * getElement(2, 0);
            double b2 = getElement(0, 1) * getElement(1, 0) * getElement(2, 2);
            double b3 = getElement(0, 0) * getElement(1, 2) * getElement(2, 1);

            return (a1 + a2 + a3) - (b1 + b2 + b3);        // ничего что разделил так? иначе слишком длинный код?
        }

        double determinant = 0;

        for (int i = 0; i < getColumnCount(); i++) {
            Vector[] matrixDecomposition = new Vector[getColumnCount() - 1];

            for (int row = 1; row < getRowCount(); row++) {
                for (int column = 0; column < getColumnCount(); column++) {
                    if (column < i) {
                        matrixDecomposition[row - 1].setComponent(column, getElement(row, column));
                    } else if (column > i) {
                        matrixDecomposition[row - 1].setComponent(column - 1, getElement(row, column));
                    }
                }
            }
            determinant += getElement(0, i) * Math.pow(-1, i + 2) * getDeterminant(matrixDecomposition);
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        for (Vector e : rows) {
            stringBuilder.append(e).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
}
