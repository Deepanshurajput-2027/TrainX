import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



class Transaction {

    static int transactionCounter = 1;

    int transactionId;
    String type;
    double amount;
    LocalDate date;

    Transaction(String type, double amount) {

        this.transactionId = transactionCounter++;
        this.type = type;
        this.amount = amount;
        this.date = LocalDate.now();
    }
}



class BankAccount {

    static int nextAccountNumber = 1001;

    int accountNumber;
    String name;
    double balance;
    boolean isActive;

    ArrayList<Transaction> transactions;


    // Constructor
    BankAccount(String name, double balance) {

        this.accountNumber = nextAccountNumber++;
        this.name = name;
        this.balance = balance;
        this.isActive = true;

        transactions = new ArrayList<>();

        // Initial deposit
        if (balance > 0) {

            Transaction transaction =
                    new Transaction("DEPOSIT", balance);

            transactions.add(transaction);
        }
    }


    void deposit(double amount) {

        if (!isActive) {

            System.out.println("Account is closed.");
            return;
        }

        if (amount <= 0) {

            System.out.println(
                    "Deposit amount must be greater than 0."
            );

            return;
        }

        balance = balance + amount;

        Transaction transaction =
                new Transaction("DEPOSIT", amount);

        transactions.add(transaction);

        System.out.println(
                "₹" + amount + " deposited successfully."
        );

        System.out.println(
                "Current Balance: ₹" + balance
        );
    }



    void withdraw(double amount) {

        if (!isActive) {

            System.out.println("Account is closed.");
            return;
        }

        if (amount <= 0) {

            System.out.println(
                    "Withdrawal amount must be greater than 0."
            );

            return;
        }

        if (amount > balance) {

            System.out.println("Insufficient balance.");
            return;
        }

        balance = balance - amount;

        Transaction transaction =
                new Transaction("WITHDRAW", amount);

        transactions.add(transaction);

        System.out.println(
                "₹" + amount + " withdrawn successfully."
        );

        System.out.println(
                "Current Balance: ₹" + balance
        );
    }



    void checkMinimumBalance() {

        if (!isActive) {

            System.out.println("Account is closed.");
            return;
        }

        if (balance < 5000) {

            double charge = balance * 0.10;

            balance = balance - charge;

            Transaction transaction =
                    new Transaction("ANNUAL CHARGE", charge);

            transactions.add(transaction);

            System.out.println(
                    "Balance is below minimum ₹5000."
            );

            System.out.println(
                    "10% annual charge: ₹" + charge
            );

            System.out.println(
                    "Remaining Balance: ₹" + balance
            );

        } else {

            System.out.println(
                    "Minimum balance requirement satisfied."
            );

            System.out.println(
                    "Current Balance: ₹" + balance
            );
        }
    }


    void closeAccount() {

        if (!isActive) {

            System.out.println("Account is already closed.");
            return;
        }

        isActive = false;

        System.out.println(
                "Account closed successfully."
        );
    }



    void totalTransactions() {

        System.out.println(
                "Total number of transactions: "
                        + transactions.size()
        );
    }


    

    void depositLastNDays(int days) {

        LocalDate today = LocalDate.now();

        LocalDate startDate =
                today.minusDays(days);

        double total = 0;

        for (Transaction transaction : transactions) {

            if (transaction.type.equals("DEPOSIT")
                    && !transaction.date.isBefore(startDate)
                    && !transaction.date.isAfter(today)) {

                total += transaction.amount;
            }
        }

        System.out.println(
                "Total deposit in last "
                        + days
                        + " days: ₹"
                        + total
        );
    }



    void withdrawalLastNDays(int days) {

        LocalDate today = LocalDate.now();

        LocalDate startDate =
                today.minusDays(days);

        double total = 0;

        for (Transaction transaction : transactions) {

            if (transaction.type.equals("WITHDRAW")
                    && !transaction.date.isBefore(startDate)
                    && !transaction.date.isAfter(today)) {

                total += transaction.amount;
            }
        }

        System.out.println(
                "Total withdrawal in last "
                        + days
                        + " days: ₹"
                        + total
        );
    }
}


class Operations {

    static HashMap<Integer, BankAccount> bankAccounts =
            new HashMap<>();



    static void createAccount(Scanner sc) {

        sc.nextLine();

        System.out.println("Enter Account Holder Name:");

        String name = sc.nextLine();

        System.out.println("Enter Initial Balance:");

        double balance = sc.nextDouble();

        if (balance < 0) {

            System.out.println(
                    "Balance cannot be negative."
            );

            return;
        }

        BankAccount account =
                new BankAccount(name, balance);

        bankAccounts.put(
                account.accountNumber,
                account
        );

        System.out.println(
                "\nAccount created successfully!"
        );

        System.out.println(
                "Your Account Number is: "
                        + account.accountNumber
        );
    }

    static BankAccount findAccount(Scanner sc) {

        System.out.println("Enter Account Number:");

        int accountNumber = sc.nextInt();

        BankAccount account =
                bankAccounts.get(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found."
            );

            return null;
        }

        return account;
    }



    static void depositMoney(Scanner sc) {

        BankAccount account =
                findAccount(sc);

        if (account == null) {
            return;
        }

        System.out.println("Enter Deposit Amount:");

        double amount = sc.nextDouble();

        account.deposit(amount);
    }


    static void withdrawMoney(Scanner sc) {

        BankAccount account =
                findAccount(sc);

        if (account == null) {
            return;
        }

        System.out.println("Enter Withdrawal Amount:");

        double amount = sc.nextDouble();

        account.withdraw(amount);
    }



    static void minimumBalance(Scanner sc) {

        BankAccount account =
                findAccount(sc);

        if (account == null) {
            return;
        }

        account.checkMinimumBalance();
    }



    static void closeAccount(Scanner sc) {

        BankAccount account =
                findAccount(sc);

        if (account == null) {
            return;
        }

        account.closeAccount();
    }



    static void totalTransactions(Scanner sc) {

        BankAccount account =
                findAccount(sc);

        if (account == null) {
            return;
        }

        account.totalTransactions();
    }


    static void depositLastNDays(Scanner sc) {

        BankAccount account =
                findAccount(sc);

        if (account == null) {
            return;
        }

        System.out.println("Enter number of days:");

        int days = sc.nextInt();

        account.depositLastNDays(days);
    }


    static void withdrawalLastNDays(Scanner sc) {

        BankAccount account =
                findAccount(sc);

        if (account == null) {
            return;
        }

        System.out.println("Enter number of days:");

        int days = sc.nextInt();

        account.withdrawalLastNDays(days);
    }
}


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {


            System.out.println("1. Create Bank Account");
            System.out.println("2. Deposit Balance");
            System.out.println("3. Withdraw Balance");
            System.out.println("4. Minimum Balance Check");
            System.out.println("5. Close Bank Account");
            System.out.println("6. Total Number of Transactions");
            System.out.println("7. Deposit Amount in Last N Days");
            System.out.println("8. Withdrawal Amount in Last N Days");
            System.out.println("9. Exit");


            System.out.println("Enter your choice:");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    Operations.createAccount(sc);

                    break;


                case 2:

                    Operations.depositMoney(sc);

                    break;


                case 3:

                    Operations.withdrawMoney(sc);

                    break;


                case 4:

                    Operations.minimumBalance(sc);

                    break;


                case 5:

                    Operations.closeAccount(sc);

                    break;


                case 6:

                    Operations.totalTransactions(sc);

                    break;


                case 7:

                    Operations.depositLastNDays(sc);

                    break;


                case 8:

                    Operations.withdrawalLastNDays(sc);

                    break;


                case 9:

                    running = false;

                    System.out.println(
                            "Thank you for using Bank Management System."
                    );

                    break;


                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }

        sc.close();
    }
}
