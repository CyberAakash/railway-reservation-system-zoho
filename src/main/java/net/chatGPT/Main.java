package net.chatGPT;

// MainApplication.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketHandler ticketHandler = new TicketHandler();

        while (true) {
            System.out.println("\nRailway Ticket Reservation System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Print Booked Tickets");
            System.out.println("4. Print Available Tickets");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter passenger name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter passenger age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter passenger gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter berth preference: ");
                    String berthPreference = scanner.nextLine();
                    Ticket ticket = new Ticket(name, age, gender, berthPreference);
                    ticketHandler.bookTicket(ticket);
                    break;
                case 2:
                    System.out.print("Enter passenger name to cancel ticket: ");
                    String cancelName = scanner.nextLine();
                    Ticket cancelTicket = null;
                    for (Ticket t : ticketHandler.confirmedTickets) {
                        if (t.getName().equalsIgnoreCase(cancelName)) {
                            cancelTicket = t;
                            break;
                        }
                    }
                    if (cancelTicket != null) {
                        ticketHandler.cancelTicket(cancelTicket);
                    } else {
                        System.out.println("Ticket not found.");
                    }
                    break;
                case 3:
                    ticketHandler.printBookedTickets();
                    break;
                case 4:
                    ticketHandler.printAvailableTickets();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}

