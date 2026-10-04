import java.util.*;

public class Main {

    static void problemOne() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> storedSongs = new ArrayList<>();
        int songCount = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            String operationType = parts[0];

            if (operationType.equals("ADD")) {
                String song = line.substring(4);
                storedSongs.add(song);
                songCount++;
            }

            if (operationType.equals("INSERT")) {
                int index = Integer.parseInt(parts[1]);
                String song = line.substring(7 + parts[1].length() + 1);
                storedSongs.add(index, song);
                songCount++;
            }

            if (operationType.equals("REMOVE")) {
                String song = line.substring(7);
                storedSongs.remove(song);
            }
        }

        System.out.println("===== Problem 1");
        System.out.println("Total songs: " + songCount);
        for (int i = 0; i < storedSongs.size(); i++) {
            System.out.println((i + 1) + ": " + storedSongs.get(i));
        }
    }

    static void problemTwo() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participantLists = new LinkedHashSet<>();
        int countDupeParticipant = 0;

        while (sc.hasNextLine()) {
            String nama = sc.nextLine();
            if (nama.isEmpty()) continue;

            if (participantLists.contains(nama)) {
                countDupeParticipant++;
            } else {
                participantLists.add(nama);
            }
        }

        System.out.println("Problem 2 =====");
        System.out.println("Unique participants: " + participantLists.size());
        int i = 1;
        for (String participant : participantLists) {
            System.out.println(i + ". " + participant);
            i++;
        }
        System.out.println("Duplicate registrations: " + countDupeParticipant);
    }

    static void problemThree() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventoryList = new LinkedHashMap<>();
        int failedOperation = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            String operationType = parts[0];
            String product = parts[1];
            int amount = Integer.parseInt(parts[2]);

            if (operationType.equals("ADD")) {
                if (inventoryList.containsKey(product)) {
                    int current = inventoryList.get(product);
                    inventoryList.put(product, current + amount);
                } else {
                    inventoryList.put(product, amount);
                }
            }

            if (operationType.equals("SELL")) {
                if (inventoryList.containsKey(product)) {
                    int current = inventoryList.get(product);
                    if (current >= amount) {
                        inventoryList.put(product, current - amount);
                    } else {
                        failedOperation++;
                    }
                } else {
                    failedOperation++;
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (String key : inventoryList.keySet()) {
            System.out.println(key + ": " + inventoryList.get(key));
        }
        System.out.println("Failed sales: " + failedOperation);
    }

    public static void main(String[] args) {
        problemOne();
        problemTwo();
        problemThree();
    }
}