package range;

public class Main {
    public static void main(String[] args) {
        Range rangeA = new Range(5, 10);
        Range rangeB = new Range(2, 8);

        int digit = 10;

        System.out.println("Длина интервала A = " + rangeA.getLength() + " Длина интервала B = " + rangeB.getLength());
        System.out.println("Число " + digit + " принадлежит диапазону А: " + rangeA.isInside(digit));

        RangeSet rangeSet = new RangeSet(rangeA.getFrom(), rangeA.getTo(), rangeB.getFrom(), rangeB.getTo());

        System.out.println("Пересечения интервалов A и B: " + rangeSet.getIntersectionInterval());
        System.out.println("Объединения интервалов A и B: " + rangeSet.getUnionInterval());
        System.out.println("Разность интервалов A и B: " + rangeSet.getDifferenceInterval());
    }
}
