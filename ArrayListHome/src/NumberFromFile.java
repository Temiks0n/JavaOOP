import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NumberFromFile {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> listNumbers = new ArrayList<Integer>();

        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/text.txt"))) {
            while (scanner.hasNextInt()) {
                listNumbers.add(scanner.nextInt());
            }
        }

        System.out.println("Файл содержит числа: " + listNumbers);

        for (int i = 0; i < listNumbers.size(); i++) {
            if (listNumbers.get(i) % 2 == 0) {
                listNumbers.remove(i);
            }
        }

        System.out.println("Нечетные числа: " + listNumbers);

        for (int i = 0; i < listNumbers.size(); i++) {
            int temp = listNumbers.get(i);
            
            for (int j = i + 1; j < listNumbers.size(); j++) {
                if (listNumbers.get(j) == temp) {
                    listNumbers.remove(j);
                }
            }
        }

        System.out.println("Числа без повторений: " + listNumbers);
    }
}