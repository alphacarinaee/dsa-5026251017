import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        LinkedList<String[]> transactionList = new LinkedList<String[]>();
        LinkedList<String[]> customerList = new LinkedList<String[]>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            
            String name = parts[0];
            String type = parts[1];
            String amount = parts[2];

            String[] trans = new String[3];
            trans[0] = name;
            trans[1] = type;
            trans[2] = amount;
            transactionList.add(trans);


            boolean ada = false;
            for (int i = 0; i < customerList.size(); i++) {
                String[] cust = customerList.get(i);
                if (cust[0].equals(name)) {
                    ada = true;
                    break;
                }
            }


            if (ada == false) {
                String[] newCust = new String[2];
                newCust[0] = name;
                newCust[1] = "0";
                customerList.add(newCust);
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<String[]>();
        for (int i = 0; i < transactionList.size(); i++) {
            transactionQueue.add(transactionList.get(i));
        }

        Stack<String[]> failedStack = new Stack<String[]>();


        while (transactionQueue.size() > 0) {
            String[] trans = transactionQueue.poll();
            String name = trans[0];
            String type = trans[1];
            int amount = Integer.parseInt(trans[2]);

            for (int i = 0; i < customerList.size(); i++) {
                String[] cust = customerList.get(i);
                
                if (cust[0].equals(name)) {
                    int saldo = Integer.parseInt(cust[1]);

                    if (type.equals("DEPOSIT")) {
                        saldo = saldo + amount;
                        cust[1] = String.valueOf(saldo);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > saldo) {
                            failedStack.push(trans);
                        } else {
                            saldo = saldo - amount;
                            cust[1] = String.valueOf(saldo);
                        }
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (int i = 0; i < customerList.size(); i++) {
            String[] cust = customerList.get(i);
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (failedStack.size() > 0) {
            String[] failed = failedStack.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}

//masukin transaction ke queue bisa pake queue.addAll(parameter) 