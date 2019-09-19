package sukhov.matrix;

import sukhov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rows, int columns) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0");
        }

        this.rows = new Vector[rows];

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = new Vector(columns);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix.length <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0");
        }

        int maxSize = 0;
        for (double[] doubles : matrix) {
            if (doubles.length > maxSize) {
                maxSize = doubles.length;
            }
        }

        if (maxSize == 0) {
            throw new IllegalArgumentException("Размерность второго порядка хотя бы одного массива должна быть > 0");
        }

        rows = new Vector[matrix.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxSize, matrix[i]);
        }
    }

    public Matrix(Vector... rows) {
        if (rows.length <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0");
        }

        this.rows = new Vector[rows.length];

        int maxSize = 0;
        for (Vector row : rows) {
            if (row.getSize() > maxSize) {
                maxSize = row.getSize();
            }
        }

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = new Vector(maxSize);

            for (int j = 0; j < rows[i].getSize(); j++) {
                this.rows[i].setComponent(j, rows[i].getComponent(j));
            }
        }
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }

        if (getColumnsCount() != row.getSize()) {
            throw new IllegalArgumentException("Размерность вектора должна совпадать с размерностью столбцов");
        }

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        Vector column = new Vector(getRowsCount());

        for (int i = 0; i < column.getSize(); i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public double getElement(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }
        if (columnIndex < 0 || columnIndex >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        return rows[rowIndex].getComponent(columnIndex);
    }

    public void setElement(int rowIndex, int columnIndex, double matrixElement) {
        if (rowIndex < 0 || rowIndex >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }
        if (columnIndex < 0 || columnIndex >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        rows[rowIndex].setComponent(columnIndex, matrixElement);
    }

    public void transpose() {
        Vector[] result = new Vector[getColumnsCount()];

        for (int i = 0; i < result.length; i++) {
            result[i] = getColumn(i);
        }

        rows = result;
    }

    public void addition(Matrix matrix) {
        if (getColumnsCount() != matrix.getColumnsCount() || getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].addition(matrix.rows[i]);
        }
    }

    public static Matrix addition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        Matrix result = new Matrix(matrix1);

        result.addition(matrix2);

        return result;
    }

    public void subtraction(Matrix matrix) {
        if (getColumnsCount() != matrix.getColumnsCount() || getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtraction(matrix.rows[i]);
        }
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.getRowsCount() != matrix2.getRowsCount()) {
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
        if (getRowsCount() != column.getSize()) {
            throw new IllegalArgumentException("Размерность строк должна совпадать с размерностью вектора");
        }

        double[] result = new double[column.getSize()];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                result[i] += getElement(i, j) * column.getComponent(i);
            }
        }

        return new Vector(result);
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Размерность столбцов одной матрицы должны совпадать с размерностью строк другой матрицы");
        }

        double[][] result = new double[matrix1.getRowsCount()][matrix2.getColumnsCount()];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < matrix1.getColumnsCount(); j++) {
                for (int k = 0; k < matrix2.getRowsCount(); k++) {
                    result[i][j] += matrix1.getElement(i, k) * matrix2.getElement(k, j);
                }
            }
        }

        return new Matrix(result);
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Размерность строк или столбцов должна быть одинаковая");
        }

        if (getColumnsCount() == 1) {
            return getElement(0, 0);
        }
        if (getColumnsCount() == 2) {
            return getElement(0, 0) * getElement(1, 1) - getElement(0, 1) * getElement(1, 0);
        }

        return getDeterminant(rows);
    }

    private double getDeterminant(Vector[] matrix) {
        double determinant = 0;

        for (int i = 0; i < matrix.length; i++) {
            Matrix matrixDecomposition = new Matrix(matrix.length - 1, matrix.length - 1);

            for (int row = 1; row < matrix.length; row++) {
                for (int column = 0; column < matrix.length; column++) {
                    if (column < i) {
                        matrixDecomposition.rows[row - 1].setComponent(column, matrix[row].getComponent(column));
                    } else if (column > i) {
                        matrixDecomposition.rows[row - 1].setComponent(column - 1, matrix[row].getComponent(column));
                    }
                }
            }
            determinant += matrix[0].getComponent(i) * Math.pow(-1, i + 2) * matrixDecomposition.getDeterminant();
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
