package sukhov.matrix;

import sukhov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixElements;

    public Matrix(int row, int column) {
        matrixElements = new Vector[row];

        for (int i = 0; i < matrixElements.length; i++) {
            matrixElements[i] = new Vector(column);
        }
    }

    public Matrix(Matrix matrix) {
        matrixElements = new Vector[matrix.getSizeRow()];

        for (int i = 0; i < matrix.getSizeRow(); i++) {
            matrixElements[i] = new Vector(matrix.getVectorRow(i));
        }
    }

    public Matrix(double[][] matrix) {
        matrixElements = new Vector[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            matrixElements[i] = new Vector(Arrays.copyOf(matrix[i], matrix[i].length));
        }
    }

    public Matrix(Vector... vector) {
        matrixElements = new Vector[vector.length];

        for (int i = 0; i < vector.length; i++) {
            matrixElements[i] = new Vector(vector[i]);
        }
    }

    public int getSizeColumn() {
        return matrixElements[0].getSize();
    }

    public int getSizeRow() {
        return matrixElements.length;
    }

    public Vector getVectorRow(int index) {
        if (index < 0 || index >= getSizeRow()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }

        Vector vectorRow = new Vector(getSizeColumn());

        for (int i = 0; i < vectorRow.getSize(); i++) {
            vectorRow.setComponent(i, matrixElements[index].getComponent(i));
        }

        return vectorRow;
    }

    public void setVectorRow(int index, Vector vectorRow) {
        if (index < 0 || index >= getSizeRow()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }

        matrixElements[index] = vectorRow;
    }

    public Vector getVectorColumn(int index) {
        if (index < 0 || index >= getSizeColumn()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        Vector vectorColumn = new Vector(getSizeRow());

        for (int i = 0; i < vectorColumn.getSize(); i++) {
            vectorColumn.setComponent(i, matrixElements[i].getComponent(index));
        }

        return vectorColumn;
    }

    public double getMatrixElement(int indexRow, int indexColumn) {
        if (indexRow < 0 || indexRow >= getSizeRow()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }
        if (indexColumn < 0 || indexColumn >= getSizeColumn()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        return matrixElements[indexRow].getComponent(indexColumn);
    }

    public void setMatrixElements(int indexRow, int indexColumn, double matrixElement) {
        if (indexRow < 0 || indexRow >= getSizeRow()) {
            throw new IndexOutOfBoundsException("Неверный индекс строки");
        }
        if (indexColumn < 0 || indexColumn >= getSizeColumn()) {
            throw new IndexOutOfBoundsException("Неверный индекс столбца");
        }

        matrixElements[indexRow].setComponent(indexColumn, matrixElement);
    }

    public Matrix transposeMatrix() {
        Matrix matrix = new Matrix(getSizeColumn(), getSizeRow());

        for (int i = 0; i < matrix.getSizeRow(); i++) {
            matrix.setVectorRow(i, getVectorColumn(i));
        }

        return matrix;
    }

    public void addition(Matrix matrix) {
        if (getSizeColumn() != matrix.getSizeColumn() || getSizeRow() != matrix.getSizeRow()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        for (int i = 0; i < matrixElements.length; i++) {
            matrixElements[i].addition(matrix.getVectorRow(i));
        }
    }

    public static Matrix addition(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1);

        result.addition(matrix2);

        return result;
    }

    public void subtraction(Matrix matrix) {
        if (getSizeColumn() != matrix.getSizeColumn() || getSizeRow() != matrix.getSizeRow()) {
            throw new IllegalArgumentException("Размерность строк и столбцов матриц должны быть одинаковые");
        }

        for (int i = 0; i < matrixElements.length; i++) {
            matrixElements[i].subtraction(matrix.getVectorRow(i));
        }
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1);

        result.subtraction(matrix2);

        return result;
    }

    public void scalarMultiplication(int scalar) {
        for (Vector matrixElement : matrixElements) {
            matrixElement.multiplication(scalar);
        }
    }

    public Vector vectorMultiplication(Vector vector) {
        if ((getSizeRow() != vector.getSize()) && (getSizeColumn() != vector.getSize())) {
            throw new IllegalArgumentException("Размерность строк или столбцов должны совподать с размерностью вектора");
        }

        double[] result = new double[vector.getSize()];

        if (getSizeColumn() == vector.getSize()) {
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < getSizeRow(); j++) {
                    result[i] += getMatrixElement(j, i) * vector.getComponent(i);
                }
            }
        }
        if (getSizeRow() == vector.getSize()) {
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < getSizeColumn(); j++) {
                    result[i] += getMatrixElement(i, j) * vector.getComponent(i);
                }
            }
        }

        return new Vector(result);
    }

    public static Matrix matrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSizeRow() != matrix2.getSizeColumn()) {
            throw new IllegalArgumentException("Размерность столбцов одной матрицы должны совподать с размерностью строк другой матрицы");
        }

        double[][] result = new double[matrix1.getSizeRow()][matrix2.getSizeColumn()];

        for (int k = 0; k < result.length; k++) {
            for (int i = 0; i < matrix1.getSizeRow(); i++) {
                for (int j = 0; j < matrix1.getSizeColumn(); j++) {
                    result[k][i] += matrix1.getMatrixElement(k, j) * matrix2.getMatrixElement(j, i);
                }
            }
        }

        return new Matrix(result);
    }

    public double getMatrixDeterminant() {
        if (getSizeRow() != getSizeColumn()) {
            throw new IllegalArgumentException("Размерность строк или столбцов должна быть одинаковая");
        }

        if (getSizeColumn() == 1) {
            return getMatrixElement(0, 0);
        }
        if (getSizeColumn() == 2) {
            return getMatrixElement(0, 0) * getMatrixElement(1, 1) - getMatrixElement(0, 1) * getMatrixElement(1, 0);
        }
        if (getSizeColumn() == 3) {
            double a1 = getMatrixElement(0, 0) * getMatrixElement(1, 1) * getMatrixElement(2, 2);
            double a2 = getMatrixElement(0, 1) * getMatrixElement(1, 2) * getMatrixElement(2, 0);
            double a3 = getMatrixElement(0, 2) * getMatrixElement(1, 0) * getMatrixElement(2, 1);
            double b1 = getMatrixElement(0, 2) * getMatrixElement(1, 1) * getMatrixElement(2, 0);
            double b2 = getMatrixElement(0, 1) * getMatrixElement(1, 0) * getMatrixElement(2, 2);
            double b3 = getMatrixElement(0, 0) * getMatrixElement(1, 2) * getMatrixElement(2, 1);

            return (a1 + a2 + a3) - (b1 + b2 + b3);        // ничего что разделил так? иначе слишком длинный код?
        }

        double[][] matrixDecomposition = new double[getSizeColumn() - 1][getSizeColumn() - 1];

        return getMatrixDeterminant(matrixDecomposition);
    }

    private double getMatrixDeterminant(double[][] matrixDecomposition) {
        double determinant = 0;

        for (int i = 0; i < getSizeColumn(); i++) {
            for (int column = 1; column < getSizeColumn(); column++) {
                for (int row = 0; row < getSizeColumn(); row++) {

                    if (row < i) {
                        matrixDecomposition[column - 1][row] = getMatrixElement(column, row);
                    } else if (row > i) {
                        matrixDecomposition[column - 1][row - 1] = getMatrixElement(column, row);
                    }
                }
            }
            determinant += getMatrixElement(0, i) * Math.pow(-1, i + 2) * getMatrixDeterminant(matrixDecomposition);
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        for (int i = 0; i < getSizeRow(); i++) {
            stringBuilder.append(matrixElements[i]).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
}
