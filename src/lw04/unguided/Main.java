import java.util.*;

class SortBot {
    public static int[] sortAndGetAfter2Passes(int[] arr) {
        int n = arr.length;
        int[] after2Passes = new int[n];

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;

            if (i == 2) {
                for (int k = 0; k < n; k++) {
                    after2Passes[k] = arr[k];
                }
            }
        }

        return after2Passes;
    }
}

class BinarySearch {
    public static void search(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;
        int foundIndex = -1;

        while (low <= high) {
            steps++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                foundIndex = mid;
                break;
            } else if (arr[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (foundIndex != -1) {
            System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
        } else {
            System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }
        scanner.close();

        int[] after2Passes = SortBot.sortAndGetAfter2Passes(weights);

        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < n; i++) {
            System.out.print(after2Passes[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        for (int i = 0; i < n; i++) {
            System.out.print(weights[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.println("==== Search Results ====");
        for (int i = 0; i < m; i++) {
            BinarySearch.search(weights, targets[i]);
        }
    }
}