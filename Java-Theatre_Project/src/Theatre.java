import java.io.*;
import java.util.*;

public class Theatre {

    static ArrayList<Integer> Row1 = new ArrayList<>();
    static {
        for (int a = 1; a <= 12; a++) {
            Row1.add(0);
        }
    }

    static ArrayList<Integer> Row2 = new ArrayList<>();
    static {
        for (int a = 1; a <= 16; a++) {
            Row2.add(0);
        }
    }

    static ArrayList<Integer> Row3 = new ArrayList<>();
    static {
        for (int a = 1; a <= 20; a++) {
            Row3.add(0);
        }
    }

    static ArrayList<Ticket> ticket_list = new ArrayList<>();


    static void buy_ticket() {

        Scanner input = new Scanner(System.in);

        String person_name;
        do {
            System.out.print("Enter the person name : ");
            person_name = input.nextLine().trim();
        } while (person_name.isEmpty());

        String person_surname;
        do {
            System.out.print("Enter the person surname : ");
            person_surname = input.nextLine().trim();
        } while (person_surname.isEmpty());

        String person_email;
        do {
            System.out.print("Enter the person email address : ");
            person_email = input.nextLine().trim();
        } while (person_email.isEmpty());

        int row = 0;   //only tack 1,2,3 as an input
        while (row != 1 && row != 2 && row != 3) {
            System.out.print("Enter the row number : ");
            while (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter integers as input.");
                System.out.print("Enter the row number : ");
                input.next();
            }
            row = input.nextInt();
            if (row != 1 && row != 2 && row != 3) {
                System.out.println("Invalid input. Row number does not exist. Please select 1-3.");
            }
        }

        int seat_n = 0;

        if (row == 1) {
            while (seat_n < 1 || seat_n > 12) {
                System.out.print("Enter the seat number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers as input.");
                    System.out.print("Enter the seat number : ");
                    input.next();
                }
                seat_n = input.nextInt();
                if (seat_n < 1 || seat_n > 12) {
                    System.out.println("Invalid input. Seat number does not exist. Please select 1-12.");
                }
            }
        }

        if (row == 2) {
            while (seat_n < 1 || seat_n > 16) {
                System.out.print("Enter the seat number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers as input.");
                    System.out.print("Enter the seat number : ");
                    input.next();
                }
                seat_n = input.nextInt();
                if (seat_n < 1 || seat_n > 16) {
                    System.out.println("Invalid input. Seat number does not exist. Please select 1-16.");
                }
            }
        }

        if (row == 3) {
            while (seat_n < 1 || seat_n > 20) {
                System.out.print("Enter the seat number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers as input.");
                    System.out.print("Enter the seat number : ");
                    input.next();
                }
                seat_n = input.nextInt();
                if (seat_n < 1 || seat_n > 20) {
                    System.out.println("Invalid input. Seat number does not exist. Please select 1-20.");
                }
            }
        }
        int seat = seat_n;

        double ticket_p = 0;
        while (ticket_p<1) {
            System.out.print("Enter the ticket price : ");
            while (!input.hasNextDouble()) {
                System.out.println("Invalid input. Please enter integers as input.");
                System.out.print("Enter the ticket price : ");
                input.next();
            }
            ticket_p = input.nextDouble();
            if (ticket_p<1) {
                System.out.println("Invalid input.");
            }
        }
        double ticket_price = ticket_p;


        Person person = new Person(person_name, person_surname, person_email);
        Ticket ticket = new Ticket(row, seat, ticket_price, person);

        switch (row) {
            case 1:
                int R1_seatnum = --seat;
                int R1_seat_number = Row1.get(R1_seatnum);
                if (R1_seat_number == 0) {
                    Row1.set(R1_seatnum, 1);
                    System.out.println("\nSeat reserved.");
                    ticket.print();           //print ticket
                    ticket_list.add(ticket);  //add ticket to the ticket list

                } else if (R1_seat_number == 1) {
                    System.out.println("\nSeat is not free to reserve.");
                }
                break;

            case 2:
                int R2_seatnum = --seat;
                int R2_seat_number = Row2.get(R2_seatnum);
                if (R2_seat_number == 0) {
                    Row2.set(R2_seatnum, 1);
                    System.out.println("\nSeat reserved.");
                    ticket.print();             //print ticket
                    ticket_list.add(ticket);    //add ticket to ticket list
                } else if (R2_seat_number == 1) {
                    System.out.println("\nSeat is not free to reserve.");
                }
                break;

            case 3:
                int R3_seatnum = --seat;
                int R3_seat_number = Row3.get(R3_seatnum);
                if (R3_seat_number == 0) {
                    Row3.set(R3_seatnum, 1);
                    System.out.println("\nSeat reserved.");
                    ticket.print();             //print ticket
                    ticket_list.add(ticket);    //add ticket to ticket list
                } else if (R3_seat_number == 1) {
                    System.out.println("\nSeat is not free to reserve.");
                }
                break;
        }

    }


    static void print_seating_area() {

        System.out.println("\t ***********");
        System.out.println("\t *  STAGE  *");
        System.out.println("\t ***********");

        System.out.print("    ");
        for (int R1_seatnumber = 0; R1_seatnumber < 12; R1_seatnumber++) {

            if (Row1.get(R1_seatnumber) == 0) {
                System.out.print("O");
            }
            else if (Row1.get(R1_seatnumber) == 1) {
                System.out.print("X");
            }
            if (R1_seatnumber == 5) {
                System.out.print(" ");
            }
        }
        System.out.print("\n");

        System.out.print("  ");
        for (int R2_seatnumber = 0; R2_seatnumber < 16; R2_seatnumber++) {
            if (Row2.get(R2_seatnumber) == 0) {
                System.out.print("O");
            } else if (Row2.get(R2_seatnumber) == 1) {
                System.out.print("X");
            }
            if (R2_seatnumber == 7) {
                System.out.print(" ");
            }
        }
        System.out.print("\n");

        for (int R3_seatnumber = 0; R3_seatnumber < 20; R3_seatnumber++) {
            if (Row3.get(R3_seatnumber) == 0) {
                System.out.print("O");
            } else if (Row3.get(R3_seatnumber) == 1) {
                System.out.print("X");
            }
            if (R3_seatnumber == 9) {
                System.out.print(" ");
            }
        }
        System.out.print("\n");
    }

    static void cancel_ticket() {
        Scanner input = new Scanner(System.in);

        int row = 0;
        while (row != 1 && row != 2 && row != 3) {
            System.out.print("Enter the row number : ");
            while (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter integers as input.");
                System.out.print("Enter the row number : ");
                input.next();
            }
            row = input.nextInt();
            if (row != 1 && row != 2 && row != 3) {
                System.out.println("Invalid input. Row number does not exist. Please select 1-3.");
            }
        }

        int seat_n = 0;

        if (row == 1) {
            while (seat_n < 1 || seat_n > 12) {
                System.out.print("Enter the seat number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers as input.");
                    System.out.print("Enter the seat number : ");
                    input.next();
                }
                seat_n = input.nextInt();
                if (seat_n < 1 || seat_n > 12) {
                    System.out.println("Invalid input. Seat number does not exist. Please select 1-12.");
                }
            }
        }

        if (row == 2) {
            while (seat_n < 1 || seat_n > 16) {
                System.out.print("Enter the seat number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers as input.");
                    System.out.print("Enter the seat number : ");
                    input.next();
                }
                seat_n = input.nextInt();
                if (seat_n < 1 || seat_n > 16) {
                    System.out.println("Invalid input. Seat number does not exist. Please select 1-16.");
                }
            }
        }

        if (row == 3) {
            while (seat_n < 1 || seat_n > 20) {
                System.out.print("Enter the seat number : ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers as input.");
                    System.out.print("Enter the seat number : ");
                    input.next();
                }
                seat_n = input.nextInt();
                if (seat_n < 1 || seat_n > 20) {
                    System.out.println("Invalid input. Seat number does not exist. Please select 1-20.");
                }
            }
        }
        int seat = seat_n;

        int index = -1;
        for (int i = 0; i < ticket_list.size(); i++) {
            Ticket t = ticket_list.get(i);
            if (t.getrow() == row && t.getseat() == seat) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            ticket_list.remove(index);          //remove tickets from ticket_list.
        }

        switch (row) {
            case 1:
                int R1_seatnum = --seat;
                int R1_seat_number = Row1.get(R1_seatnum);
                if (R1_seat_number == 1) {
                    Row1.set(R1_seatnum, 0);
                    System.out.println("\nSeat is free.");
                } else if (R1_seat_number == 0) {
                    System.out.println("\nSeat is not reserved.");
                }
                break;

            case 2:
                int R2_seatnum = --seat;
                int R2_seat_number = Row2.get(R2_seatnum);
                if (R2_seat_number == 1) {
                    Row2.set(R2_seatnum, 0);
                    System.out.println("\nSeat is free.");
                } else if (R2_seat_number == 0) {
                    System.out.println("\nSeat is not reserved.");
                }
                break;

            case 3:
                int R3_seatnum = --seat;
                int R3_seat_number = Row3.get(R3_seatnum);
                if (R3_seat_number == 1) {
                    Row3.set(R3_seatnum, 0);
                    System.out.println("\nSeat is free.");
                } else if (R3_seat_number == 0) {
                    System.out.println("\nSeat is not reserved.");
                }
                break;
        }
    }

    static void show_available() {
        int Row1_available = 1;
        ArrayList<Integer> Row1_ava_seats = new ArrayList<>();
        for (int seatnumber = 0; seatnumber < 12; ++seatnumber) {
            if (Row1.get(seatnumber) == 0) {
                Row1_ava_seats.add(Row1_available);
                Row1_available++;
            } else if (Row1.get(seatnumber) == 1) {
                Row1_available++;
            }
        }
        System.out.print("Seats available in row 1 : ");
        System.out.println(Row1_ava_seats.toString().replace("[", "").replace("]", ""));

        int Row2_available = 1;
        ArrayList<Integer> Row2_ava_seats = new ArrayList<>();
        for (int seatnumber = 0; seatnumber < 16; ++seatnumber) {
            if (Row2.get(seatnumber) == 0) {
                Row2_ava_seats.add(Row2_available);
                Row2_available++;
            } else if (Row2.get(seatnumber) == 1) {
                Row2_available++;
            }
        }
        System.out.print("Seats available in row 2 : ");
        System.out.println(Row2_ava_seats.toString().replace("[", "").replace("]", ""));

        int Row3_available = 1;
        ArrayList<Integer> Row3_ava_seats = new ArrayList<>();
        for (int seatnumber = 0; seatnumber < 20; ++seatnumber) {
            if (Row3.get(seatnumber) == 0) {
                Row3_ava_seats.add(Row3_available);
                Row3_available++;
            } else if (Row3.get(seatnumber) == 1) {
                Row3_available++;
            }
        }
        System.out.print("Seats available in row 3 : ");
        System.out.println(Row3_ava_seats.toString().replace("[", "").replace("]", ""));

    }

    static void save() {
        try {
            File file = new File("Row_data.txt");

            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter write = new PrintWriter(file);   //save 3 arrays to Row_data file
            write.println(Row1.toString().replace("[", "").replace("]", ""));
            write.println(Row2.toString().replace("[", "").replace("]", ""));
            write.println(Row3.toString().replace("[", "").replace("]", ""));

            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done, Row information saved.\n");

    }

    static void load() {
        Row1.clear(); //clear data in Row1
        Row2.clear(); //clear data in Row2
        Row3.clear(); //clear data in Row3

        try {
            FileReader file = new FileReader("Row_data.txt");
            BufferedReader Reader = new BufferedReader(file);
            for (int lineNo = 1; lineNo <= 3; ++lineNo) {
                if (lineNo == 1) {
                    String line = Reader.readLine();

                    String[] line_1 = line.split(", ");
                    for (int i = 0; i < 12; i++) {
                        Row1.add(Integer.valueOf(line_1[i]));  //add new data to Row1
                    }
                }
                else if (lineNo == 2) {
                    String line = Reader.readLine();

                    String[] line_2 = line.split(", ");
                    for (int i = 0; i < 16; i++) {
                        Row2.add(Integer.valueOf(line_2[i]));  //add new data to Row2
                    }
                }
                else if (lineNo == 3) {
                    String line = Reader.readLine();

                    String[] line_3 = line.split(", ");
                    for (int i = 0; i < 20; i++) {
                        Row3.add(Integer.valueOf(line_3[i]));  //add new data to Row3
                    }
                }
            }
            Reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done, Row information restored.\n");

    }

    static void show_tickets_info() {
        //print tickets in ticket list

        for (int num = 0; num < ticket_list.size(); num++) {
            Ticket T_list = ticket_list.get(num);
            System.out.println("\nName : " + T_list.getPerson().getname());
            System.out.println("Surname : " + T_list.getPerson().getsurname());
            System.out.println("G-mail : " + T_list.getPerson().getemail());
            System.out.println("Row number : " + T_list.getrow());
            System.out.println("Seat number : " + T_list.getseat());
            System.out.println("Ticket price : " + T_list.getprice());
        }

        double full_price = 0;
        for (int i = 0; i < ticket_list.size(); i++) {
            Ticket t = ticket_list.get(i);
            full_price = full_price + t.getprice();
        }
        System.out.println("\nTotal price of all tickets = " + full_price);
    }

    static void sort_tickets() {
        ArrayList<Ticket> tickets_sorted = new ArrayList<>();
        tickets_sorted.addAll(ticket_list);

        int N = tickets_sorted.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < N; i++) {
                if (tickets_sorted.get(i - 1).getprice() > (tickets_sorted.get(i).getprice())) {
                    Ticket temp = tickets_sorted.get(i - 1);            // Swap elements
                    tickets_sorted.set(i - 1, tickets_sorted.get(i));
                    tickets_sorted.set(i, temp);
                    swapped = true;
                }
            }
            N--;
        } while (swapped);

        for (int num = 0; num < tickets_sorted.size(); num++) {
            Ticket T_list = tickets_sorted.get(num);
            System.out.println("\nName : " + T_list.getPerson().getname());
            System.out.println("Surname : " + T_list.getPerson().getsurname());
            System.out.println("G-mail : " + T_list.getPerson().getemail());
            System.out.println("Row number : " + T_list.getrow());
            System.out.println("Seat number : " + T_list.getseat());
            System.out.println("Ticket price : " + T_list.getprice());

        }
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to the New Theatre");

        for (; ; ) {

            System.out.println("-------------------------------------------------");
            System.out.println("Please select an option:");
            System.out.println("1) Buy a ticket ");
            System.out.println("2) Print seating area ");
            System.out.println("3) Cancel ticket ");
            System.out.println("4) List available seats ");
            System.out.println("5) Save to file ");
            System.out.println("6) Load from file ");
            System.out.println("7) Print ticket information and total price ");
            System.out.println("8) Sort tickets by price ");
            System.out.println("\t0) Quit ");
            System.out.println("-------------------------------------------------");

            Scanner input = new Scanner(System.in);

            int option=-1;

            while (option < 0 || option >8  ) {     //only tack 0-8 as input
                System.out.print("Enter option : ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers as input.\n");
                    System.out.print("Enter option : ");
                    input.next();
                }
                option = input.nextInt();
                if (option < 0 || option >8 ) {
                    System.out.println("Invalid input.\n");
                }
            }
            System.out.print("\n");



            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    print_seating_area();
                    break;
                case 3:
                    cancel_ticket();
                    break;
                case 4:
                    show_available();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    show_tickets_info();
                    break;
                case 8:
                    sort_tickets();
                    break;

            }
        }

    }

}
