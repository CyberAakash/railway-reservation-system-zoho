package org.v1;

import org.v1.railway.Railway;
import org.v1.railway.TicketHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printMenu();
    }

    public static void printMenu() {

        int choice;
        System.out.println("//////// RAILWAY RESERVATION SYSTEM ////////");
        do{
            TicketHandler handler = new TicketHandler();
            System.out.println("Choose option from the menu: ");
            System.out.println("1. Book Ticket\n2. Cancel Ticket\n3. Print Booked Tickets\n4. Print Available Tickets\n5. Display Passenger Details\n6. Exit\n");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
//                    Book Ticket
                    handler.bookTicket();
                    break;
                case 2:
//                    Cancel Ticket
                    handler.cancelTicket();
                    break;
                case 3:
//                    Print Booked Ticket
                    Railway.getInstance().displayBookedTickets();
                    break;
                case 4:
//                    Print Available Ticket
                    Railway.getInstance().displayAvailableTickets();
                    break;
                case 5:
//                    Show Passengers
                    Railway.getInstance().displayPassengers();
                    Railway.getInstance().displayChildrens();
                    break;
                case 6:
                    System.out.println("Bye! Bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Option!");
                    break;
            }
        }while(true);
    }
}
