package LW01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        try {
            File file = new File("D:\\Kuliah\\Semester 3\\Algoritma Struktur Data\\dsa-5026251017\\src\\LW01\\prelab\\jobs.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equals("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equals("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan!");
            return;
        }

        // Loop cetak summary
        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}