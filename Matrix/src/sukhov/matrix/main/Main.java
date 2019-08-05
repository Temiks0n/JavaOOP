package sukhov.matrix.main;

import sukhov.matrix.Matrix;
import sukhov.vector.Vector;

import static sukhov.matrix.Matrix.multiplication;
import static sukhov.matrix.Matrix.subtraction;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{3, 4, 4});
        Vector vector2 = new Vector(new double[]{2, 6, 7});
        Vector vector3 = new Vector(new double[]{-1, 0, 2});

        Matrix matrix1 = new Matrix(vector1, vector2);
        Matrix matrix2 = new Matrix(new double[][]{{1, 9}, {4, 7}, {3, 6}});
        Matrix matrix3 = new Matrix(new double[][]{{5, 9, 5, 7, 8}, {6, 2, 4, 7, 4}});
        Matrix matrix8 = new Matrix(new double[][]{{5, 9, 5}, {2, 5, 1}, {4, 7, 2}});
        Matrix matrix4 = new Matrix(1, 1);
        Matrix copyMatrix1 = new Matrix(matrix1);


        matrix1.addition(copyMatrix1);
        Matrix matrix6 = subtraction(matrix1, copyMatrix1);
        Matrix matrix5 = multiplication(matrix2, matrix1);
        matrix8.transpose();
        matrix2.multiplication(6);

        System.out.println("Сумма матрицы 1 и ее копия = " + matrix1);
        System.out.println("Разность матрица 2 и матрица 1 = " + matrix6);
        System.out.println("Транспонирование матрицы 3 = " +  matrix8);
        System.out.println("Умножение матрицы 2 и 1 = " + matrix5);
        System.out.println("Определителя матрицы 8  = " + matrix8.getDeterminant());
        System.out.println("Умножение матрицы 2 на скаляр = " + matrix2);
        System.out.println("Умножение матрицы 8 на вектор 3 = " + matrix8.multiplication(vector3));
    }
}

