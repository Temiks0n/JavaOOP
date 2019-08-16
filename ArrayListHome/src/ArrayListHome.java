import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) {
        // 1 задание
        ArrayList<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/text.txt"))) {
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом");
        }

        // 2 задание
        ArrayList<Integer> integerNumbers = new ArrayList<>(Arrays.asList(2, 7, 8, 4, 1, 9, 23, 13, 4, 5, 15, 13, 23));

        for (int i = 0; i < integerNumbers.size(); i++) {
            if (integerNumbers.get(i) % 2 == 0) {
                integerNumbers.remove(i);
                i--;
            }
        }

        // 3 задание
        ArrayList<Integer> integersWithoutRepetition = new ArrayList<>();

        for (Integer e : integerNumbers) {
            if (!integersWithoutRepetition.contains(e)) {
                integersWithoutRepetition.add(e);
            }
        }

        System.out.println("Файл содержит строки: " + strings);
        System.out.println("Нечетные числа: " + integerNumbers);
        System.out.println("Числа без повторений: " + integersWithoutRepetition);
    }
}