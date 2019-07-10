package sukhov.range.main;

import sukhov.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range rangeA = new Range(1, 12);
        Range rangeB = new Range(1, 12);

        int digit = 10;

        System.out.println("Длина интервала A = " + rangeA.getLength() + " Длина интервала B = " + rangeB.getLength());
        System.out.println("Число " + digit + " принадлежит диапазону А: " + rangeA.isInside(digit));

        System.out.println("Пересечения интервалов A и B: " + rangeA.getIntersectionInterval(rangeB));
        System.out.println("Объединения интервалов A и B: " + Arrays.toString(rangeA.getUnionInterval(rangeB)));
        System.out.println("Разность интервалов A и B: " + Arrays.toString(rangeA.getDifferenceInterval(rangeB)));
    }
}