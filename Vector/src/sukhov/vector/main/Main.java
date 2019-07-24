package sukhov.vector.main;

import sukhov.vector.Vector;

import static sukhov.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(2, new double[]{1, 3, 4});
        Vector vector2 = new Vector(new double[]{1, 5, 7, 3});
        Vector vector3 = new Vector(7);
        Vector vectorCopy = new Vector(vector1);

        vector1.setComponent(0, 10);
        vector1.reverse();
        vector2.multiplication(4);
        vector1.addition(vector3);
        vector3.subtraction(vector2);
        double scalarMultiplication = scalarMultiplication(vector1, vector2);
        Vector vector4 = addition(vector1, vector2);

        System.out.println("Векторы " + vector1.toString() + " и " + vectorCopy.toString() + " равны: " + vectorCopy.equals(vector1));
        System.out.println("Развернутый вектор 1 = " + vector1);
        System.out.println("Сумма векторов 1 и 2 = " + vector4);
        System.out.println("Умножение вектора 2 на скаляр 4 =  " + vector2);
        System.out.println("Разность векторов 3 и 2 = " + vector3);
        System.out.println("Скалярное произведение векторов 1 и 2 = " + scalarMultiplication);
        System.out.println("Компонент вектора 2 по индексу 2 = " + vector2.getComponent(2));
    }
}
