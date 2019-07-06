package range.main;

import range.Range;

public class Main {
    public static void main(String[] args) {
        Range rangeA = new Range(1, 10);
        Range rangeB = new Range(-5, 5);

        int digit = 10;

        System.out.println("Длина интервала A = " + rangeA.getLength() + " Длина интервала B = " + rangeB.getLength());
        System.out.println("Число " + digit + " принадлежит диапазону А: " + rangeA.isInside(digit));

        double fromA = rangeA.getFrom();
        double toA = rangeA.getTo();
        double fromB = rangeB.getFrom();
        double toB = rangeB.getTo();

        Range intersection = rangeA.getIntersectionInterval(fromA, toA, fromB, toB);
        Range[] union = rangeA.getUnionInterval(fromA, toA, fromB, toB);
        Range[] difference = rangeA.getDifferenceInterval(fromA, toA, fromB, toB);

        StringBuilder stringBuilderIntersection = new StringBuilder();
        if (intersection == null) {
            stringBuilderIntersection = null;
        } else {
            stringBuilderIntersection.append("[").append(intersection.getFrom()).append("; ").append(intersection.getTo()).append("]");
        }

        StringBuilder stringBuilderUnion = new StringBuilder();
        for (int i = 0; i < union.length; i++) {
            stringBuilderUnion.append("[").append(union[i].getFrom()).append("; ").append(union[i].getTo()).append("]");
        }

        StringBuilder stringBuilderDifference = new StringBuilder();
        if (difference == null) {
            stringBuilderDifference = null;
        } else {
            for (int i = 0; i < difference.length; i++) {
                stringBuilderDifference.append("[").append(difference[i].getFrom()).append("; ").append(difference[i].getTo()).append("]");
            }
        }

        System.out.println("Пересечения интервалов A и B: " + stringBuilderIntersection);
        System.out.println("Объединения интервалов A и B: " + stringBuilderUnion);
        System.out.println("Разность интервалов A и B: " + stringBuilderDifference);
    }
}