package sukhov.vectro.main;

import sukhov.vectro.Vector;

import java.util.Arrays;

import static sukhov.vectro.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5, new double[]{1, 3, 4});
        Vector vector2 = new Vector(new double[]{1, 5, 7, 3});
        Vector vector3 = new Vector(7);

        vector3.setComponents(new double[]{1, 3, 4, 8, 4, 3});
        vector3.setSize(6);
        Vector vectorCopy = new Vector(vector1);

        boolean isEqual = vector1.equals(vectorCopy);

        vector2.reverse();

        double scalarMultiplication = scalarMultiplication(vector1, vector2);
        Vector vector4 = addition(vector1, vector3);
        Vector vector5 = subtraction(vector1, vector2);
        double[] multiplication = vector4.multiplication(4);

        System.out.println("Векторы " + vector1.toString() + " и " + vectorCopy.toString() + " равны: " + isEqual);
        System.out.println("Развернутый вектор 2 = " + vector2.toString());
        System.out.println("Сумма векторов 1 и 3 = " + vector4.toString());
        System.out.println("Умножение вектора 4 на скаляр = " + Arrays.toString(multiplication));
        System.out.println("Разность векторов 1 и 2 = " + vector5.toString());
        System.out.println("Скалярное произведение векторов 1 и 2 = " + scalarMultiplication);
        System.out.println("Компонент вектора 2 по индексу 2 = " + vector2.getComponent(2));
    }
}
