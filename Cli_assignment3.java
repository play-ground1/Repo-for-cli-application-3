import java.util.Scanner;

public class Cli_assignment3 {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = " Welcome to Smart Banking";
        final String CREATE_ACCOUNT = "Create New Account";
        final String DEPOSIT = "Deposit";
        final String WITHDRAWS = "Withdraws";
        final String TRANSFER = "Transfer";
        final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Delete Account";

        String[][] customers = new String[0][];
        String screen = DASHBOARD;
        String name;
        double balance;
        boolean foundCustomer = false;
        String id = "";

        do {
            final String APP_TITLE = String.format("%s%s%s",
                    COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("-".repeat(30));
            System.out.println(" ".repeat((30 - APP_TITLE.length() + 7) / 2).concat(APP_TITLE));
            System.out.println("-".repeat(30));

            switch (screen) {
                case DASHBOARD:
                
                    System.out.println("\n[1]. Create New Account ");
                    System.out.println("[2]. Deposit");
                    System.out.println("[3]. Withdraw");
                    System.out.println("[4]. Transfer");
                    System.out.println("[5]. Check Account Balance");
                    System.out.println("[6]. Delete");
                    System.out.println("[7]. Exit\n");
                    System.out.print("Enter an option to continue > ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option) {
                        case 1:
                            screen = CREATE_ACCOUNT;
                            break;
                        case 2:
                            screen = DEPOSIT;
                            break;
                        case 3:
                            screen = WITHDRAWS;
                            break;
                        case 4:
                            screen = TRANSFER;
                            break;
                        case 5:
                            screen = CHECK_ACCOUNT_BALANCE;
                            break;
                        case 6:
                            screen = DELETE_ACCOUNT;
                            break;
                        case 7:
                            System.exit(0);
                            break;
                        default:
                            continue;
                    }
                    break;

                case CREATE_ACCOUNT:
                    boolean valid=false;
                    id = "SDB-" + String.format("%05d", (customers.length + 1));
                    System.out.printf("New Customer ID: %s \n", id);
                    

                    name = nameValidation();
         
                    do {
                        System.out.print("Initial Deposit (Rs.): ");
                        balance = SCANNER.nextDouble();
                        SCANNER.nextLine();
                    
                        if (!(balance >= 5000.00)) {
                            valid = false;
                            System.out.printf("%sInsufficient Amount. Initial deposit should be more than Rs.5000.00%s \n", COLOR_RED_BOLD, RESET);
                        } else {
                            valid = true;
                        }
                    } while (!valid); 
                    

                    String[][] newCustomers = new String[customers.length + 1][3];

                    for (int i = 0; i < customers.length; i++) {
                         newCustomers[i] = customers[i];
                    }
                     newCustomers[newCustomers.length - 1][0] = id;
                     newCustomers[newCustomers.length - 1][1] = name;
                     newCustomers[newCustomers.length - 1][2] = Double.toString(balance);

                     customers = newCustomers;

                    System.out.println();
                    System.out.print(name + " added successfully. Do you want to add a new customer (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                default:
                    System.out.println("Invalid option");

            case DEPOSIT:

            valid=idValidation();
            if(!valid){
                System.out.println();
                  System.out.print("Account number dose not exist. Do you want to continue (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;  

            }

            for (int i = 0; i < customers.length; i++) {
                if(customers[i][0].equals(id)){
                    foundCustomer=true;
                do {
                valid = true;
                System.out.print("Deposit Amount (Rs.): ");
                double depositAmount = SCANNER.nextDouble();
                SCANNER.nextLine();
        
                if (depositAmount < 500.00) {
                    valid = false;
                    System.out.printf("%sInsufficient Amount. Deposit should be more than Rs.500.00%s \n", COLOR_RED_BOLD, RESET);
                } else {
                    balance = Double.valueOf(customers[i][2]).doubleValue();
                    balance += depositAmount;
                    customers[i][2] = Double.toString(balance);
                    System.out.println("Deposit was successful.");
                    System.out.println("New Balance: " + balance);
                }
            } while (!valid);break;         
        }
      }

      System.out.println();
      System.out.print("Do you want to continue (Y/n)? ");
      if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
      screen = DASHBOARD;
      break;  

      case WITHDRAWS:
            valid=idValidation();
               if(!valid){
                System.out.println();
                  System.out.print("Do you want to continue (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;  
            }

            for (int i = 0; i < customers.length; i++) {
                if(customers[i][0].equals(id)){
                    foundCustomer=true;
                do {
                valid = true;
                System.out.print("Withdraw Amount (Rs.): ");
                double withdrawAmount = SCANNER.nextDouble();
                SCANNER.nextLine();
        
                if (withdrawAmount < 100.00) {
                    valid = false;
                    System.out.printf("%sInsufficient Amount. Withdraw should be more than Rs.100.00%s \n", COLOR_RED_BOLD, RESET);
                } else {
                    balance = Double.valueOf(customers[i][2]).doubleValue();
                    balance -= withdrawAmount;
                    customers[i][2] = Double.toString(balance);
                    System.out.println("Withdraw was successful.");
                    System.out.println("New Balance: " + balance);
                }
            } while (!valid);         
        }
      }
      System.out.println();
      System.out.print("Do you want to continue (Y/n)? ");
      if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
      screen = DASHBOARD;
      break;  

      case TRANSFER:

      int i;
      double toBalance=0;
      double fromBalance=0;
      valid= idValidation();
      double  transferAmount;
         if(!valid){
                System.out.println();
                  System.out.print("Account number dose not exist. Do you want to continue (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;  
            }

      for (i = 0; i < customers.length; i++) {
        
        if(customers[i][0].equals(id)){
               foundCustomer=true;
        }
           valid = true;
            if(foundCustomer==true);
           System.out.print("Enter amount to trnsfer (Rs.): ");
           transferAmount = SCANNER.nextDouble();
           SCANNER.nextLine();
   
           if (transferAmount < 100.00) {
               valid = false;
               System.out.printf("%sInsufficient Amount. Transfer should be more than Rs.100.00%s \n", COLOR_RED_BOLD, RESET);
           } else {
               fromBalance = Double.valueOf(customers[i][2]).doubleValue();
               fromBalance -= transferAmount;
               balance=fromBalance;
               customers[i][2] = Double.toString(fromBalance);    
           }

       valid= idValidation();
         if(!valid){
                System.out.println();
                  System.out.print("Account number dose not exist. Do you want to continue (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;  
            }

      for ( i = 0; i < customers.length; i++) {
         
          if(customers[i][0].equals(id)){
                 foundCustomer=true;
    
              valid = true;
               toBalance = Double.valueOf(customers[i][2]).doubleValue();
               toBalance += transferAmount;
                customers[i][2] = Double.toString(toBalance);
       
   }
}
         System.out.println("From A/C Balance: " + fromBalance);
         System.out.println("To A/C Balance: " + toBalance);
      }
        System.out.println();
        System.out.print("Do you want to continue (Y/n)? ");
           if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) {
               continue;
           }
           screen = DASHBOARD;
           break;

          case CHECK_ACCOUNT_BALANCE:

           valid= idValidation();
              if(!valid){
                System.out.println();
                  System.out.print("Account number dose not exist.Do you want to continue (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;  

            }
   
           for ( i = 0; i < customers.length; i++) {
               if (customers[i][0].equals(id)) {
                   foundCustomer = true;
                   balance = Double.valueOf(customers[i][2]).doubleValue();
                    System.out.println();
                   System.out.println("name :"+customers[i][1]);
                   System.out.println("Current Account Balance  : Rs." + balance);
                   System.out.println("Available Account Balance: Rs." + (balance-500));
                   break;
               }
           }
       
           if (!foundCustomer) {
               System.out.println("Customer not found.");
           }
       
           System.out.println();
           System.out.print("Do you want to continue (Y/n)? ");
           if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) {
               continue;
           }
           screen = DASHBOARD;
           break;

           case DELETE_ACCOUNT:
           
           valid=idValidation();
              if(!valid){
                System.out.println();
                  System.out.print("Account number dose not exist. Do you want to continue (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;  
            }
        if(valid){
       
            int deleteIndex = 0;
            for ( i = 0; i < customers.length; i++) {
                if (customers[i][0].equals(id)) {
                    foundCustomer = true;
                    deleteIndex = i;
                    break;
                }
            }
        
            if (foundCustomer) {
                String[][] deletCustomers = new String[customers.length - 1][3];
                int newIndex = 0;
                for ( i = 0; i < customers.length; i++) {
                  if (i != deleteIndex) {
                        deletCustomers[newIndex] = customers[i];
                         newIndex++;
                    }
                }
                customers = deletCustomers;
                System.out.println("Account deleted successfully.");
    }
            
           }
          
           System.out.println();
           System.out.print("Do you want to continue (Y/n)? ");
           if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) {
               continue;
           }
           screen = DASHBOARD;
           break;
            }
        } while (true);
    }
         public static boolean idValidation() {
            System.out.print("Enter Customer Account Number: ");
            String id = SCANNER.nextLine().strip();

                 if (id.startsWith("SDB-") && id.length() == 9) {
                String numberPart = id.substring(5);
                    for (int i = 0; i < numberPart.length(); i++) {
                       if (!Character.isDigit(numberPart.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
        public static String nameValidation() { 
                    final String COLOR_RED_BOLD = "\033[31;1m";
                    final String RESET = "\033[0m";
                        boolean valid;
                        String name;
            do {
                        valid = true;
                        System.out.print("Name: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()) {
                            System.out.printf("%sName can't be empty%s\n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) ||
                                    Character.isSpaceChar(name.charAt(i)))) {
                                System.out.printf("%sInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);
                    return(name);
        } 
}
