import java.util.*;

public class Main {
    public static void main(String[] args){
        LinkedList<String[]> orderList = new LinkedList<String[]>();
        LinkedList<String[]> foodList = new LinkedList<String[]>();
        LinkedList<String[]> drinkList = new LinkedList<String[]>();
        LinkedList<String[]> failedOrders = new LinkedList<String[]>();

        Queue<String[]> queue = new LinkedList<String[]>();
        Stack<String[]> failedStack = new Stack<String[]>();
        
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("orders.txt"));
        while (scanner.hasNext()) {
            String[] transaction = new String[4];
            transaction[0] = scanner.next();
            transaction[1] = scanner.next();
            transaction[2] = scanner.next();
            transaction[3] = scanner.next();
            orderList.add(transaction);
        }

        foodList.add(new String[]{"Bakso", "2"});
        foodList.add(new String[]{"Sate", "1"});
        foodList.add(new String[]{"Soto", "2"});

        drinkList.add(new String[]{"Es Teh", "4"});
        drinkList.add(new String[]{"Es Jeruk", "2"});


        scanner.close();
        queue.addAll(orderList);
        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String name = order[0];
            String food = order[1];
            String drink = order[2];
            String table = order[3];
            
            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            if (!food.equals("-")) {
                foodAvailable = false;
                for (String[] item : foodList) {
                    if (item[0].equals(food) && Integer.parseInt(item[1]) > 0) {
                        foodAvailable = true;
                        break;
                    }
                }
            }

            if (!drink.equals("-")) {
                drinkAvailable = false;
                for (String[] item : drinkList) {
                    if (item[0].equals(drink) && Integer.parseInt(item[1]) > 0) {
                        drinkAvailable = true;
                        break;
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {
                if (!food.equals("-")) {
                    for (String[] item : foodList) {
                        if (item[0].equals(food)) {
                            int stock = Integer.parseInt(item[1]);
                            item[1] = String.valueOf(stock - 1);
                            break;
                        }
                    }
                }

                if (!drink.equals("-")) {
                    for (String[] item : drinkList) {
                        if (item[0].equals(drink)) {
                            int stock = Integer.parseInt(item[1]);
                            item[1] = String.valueOf(stock - 1);
                            break;
                        }
                    }
                }

                failedOrders.add(order);
            } else {
                failedOrders.push(order);
            }
        }

        System.out.println("Successful orders:");
        for (int i = 0; i < failedOrders.size(); i++) {
            String[] transaction = failedOrders.get(i);
            System.out.println(transaction[0] + " " + transaction[1] + " "
                    + transaction[2] + " " + transaction[3]);
        }

        System.out.println("\nRemaining food stock:");
        for (int i = 0; i < foodList.size(); i++) {
            System.out.println(foodList.get(i)[0] + ": "
                    + foodList.get(i)[1]);
        }

        System.out.println("\nRemaining drink stock:");
        for (int i = 0; i < drinkList.size(); i++) {
            System.out.println(drinkList.get(i)[0] + ": "
                    + drinkList.get(i)[1]);
        }

        System.out.println("\nFailed orders (LIFO):");
        while (!failedOrders.isEmpty()) {
            String[] transaction = failedOrders.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " "
                    + transaction[2] + " " + transaction[3]);
        }
    }
    
}