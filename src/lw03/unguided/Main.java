import java.util.*;

public class Main {
    public static HashSet<String> problemOne() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        HashSet<String> registeredNames = new HashSet<>();
        int duplicateCount = 0;
        
        while (sc.hasNextLine()) {
            String id = sc.nextLine();
            if (id.isEmpty()) continue;

            if (registeredNames.contains(id)) {
                duplicateCount++;
            } else {
                registeredNames.add(id);
            }
        }

        return registeredNames;
    }

    public static int problemTwo() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        HashSet<String> checkedInNames = new HashSet<>();
        int duplicateCount = 0;
        int registeredCount = 0;

        while (sc.hasNextLine()) {
            String id = sc.nextLine();
            if (id.isEmpty()) continue;

            if (checkedInNames.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                duplicateCount++;
            } else if (!problemOne().contains(id)) {
                System.out.println(id + ": Rejected (not registered)");
                duplicateCount++;
            } else {
                System.out.println(id + ": Checked in");
                checkedInNames.add(id);
                registeredCount++;

            }
        }
        return registeredCount;
    }

    public static int problemThree() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        HashSet<String> checkedInNames = new HashSet<>();
        int duplicateCount = 0;
        int registeredCount = 0;
        int rejectedCount = 0;

        while (sc.hasNextLine()) {
            String id = sc.nextLine();
            if (id.isEmpty()) continue;

            if (checkedInNames.contains(id)) {
                rejectedCount++;
            } else if (!problemOne().contains(id)) {
                rejectedCount++;
            } else {
                checkedInNames.add(id);
                registeredCount++;

            }
        }
        return rejectedCount;
    }
    public static void main(String[] args) {
        System.out.println("===== Event Check-In Results =====");
        System.out.println("Checked-in participants: " + problemTwo());
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered participants: " + problemOne().size());
        System.out.println("Rejected attempts: " + problemThree());
        System.out.println("Absent participants: " + (problemOne().size() - problemThree()));

    }

}