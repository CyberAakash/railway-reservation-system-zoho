package net.chatGPT;

// TicketHandler.java
import java.util.ArrayList;
import java.util.List;

public class TicketHandler {
    private static final int MAX_CONFIRMED_TICKETS = 63;
    private static final int MAX_RAC_TICKETS = 18;
    private static final int MAX_WAITING_LIST_TICKETS = 10;

    List<Ticket> confirmedTickets;
    private List<Ticket> racTickets;
    private List<Ticket> waitingListTickets;

    public TicketHandler() {
        confirmedTickets = new ArrayList<>();
        racTickets = new ArrayList<>();
        waitingListTickets = new ArrayList<>();
    }

    public void bookTicket(Ticket ticket) {
        if (ticket.getAge() < 5) {
            System.out.println("Children below 5 years are not allowed to book tickets.");
            return;
        }

        if (confirmedTickets.size() < MAX_CONFIRMED_TICKETS) {
            if (ticket.getAge() > 60 || (ticket.getGender().equalsIgnoreCase("female") && ticket.getAge() > 18)) {
                if (confirmedTickets.size() < MAX_CONFIRMED_TICKETS) {
                    // Allocate lower berth
                    confirmedTickets.add(ticket);
                    System.out.println("Ticket booked successfully!");
                } else if (racTickets.size() < MAX_RAC_TICKETS) {
                    // Allocate RAC
                    racTickets.add(ticket);
                    System.out.println("Ticket booked successfully! RAC allocated.");
                } else if (waitingListTickets.size() < MAX_WAITING_LIST_TICKETS) {
                    // Waiting List
                    waitingListTickets.add(ticket);
                    System.out.println("Ticket booked successfully! You are in the waiting list.");
                } else {
                    System.out.println("No tickets available.");
                }
            } else {
                // Allocate other berths
                if (confirmedTickets.size() < MAX_CONFIRMED_TICKETS) {
                    confirmedTickets.add(ticket);
                    System.out.println("Ticket booked successfully!");
                } else if (racTickets.size() < MAX_RAC_TICKETS) {
                    racTickets.add(ticket);
                    System.out.println("Ticket booked successfully! RAC allocated.");
                } else if (waitingListTickets.size() < MAX_WAITING_LIST_TICKETS) {
                    waitingListTickets.add(ticket);
                    System.out.println("Ticket booked successfully! You are in the waiting list.");
                } else {
                    System.out.println("No tickets available.");
                }
            }
        } else if (racTickets.size() < MAX_RAC_TICKETS) {
            // Allocate RAC
            racTickets.add(ticket);
            System.out.println("Ticket booked successfully! RAC allocated.");
        } else if (waitingListTickets.size() < MAX_WAITING_LIST_TICKETS) {
            // Waiting List
            waitingListTickets.add(ticket);
            System.out.println("Ticket booked successfully! You are in the waiting list.");
        } else {
            System.out.println("No tickets available.");
        }
    }

    public void cancelTicket(Ticket ticket) {
        if (confirmedTickets.contains(ticket)) {
            confirmedTickets.remove(ticket);
            if (!racTickets.isEmpty()) {
                confirmedTickets.add(racTickets.remove(0));
                if (!waitingListTickets.isEmpty()) {
                    racTickets.add(waitingListTickets.remove(0));
                }
            }
            System.out.println("Ticket canceled successfully.");
        } else if (racTickets.contains(ticket)) {
            racTickets.remove(ticket);
            if (!waitingListTickets.isEmpty()) {
                racTickets.add(waitingListTickets.remove(0));
            }
            System.out.println("Ticket canceled successfully.");
        } else if (waitingListTickets.contains(ticket)) {
            waitingListTickets.remove(ticket);
            System.out.println("Ticket canceled successfully.");
        } else {
            System.out.println("Ticket not found.");
        }
    }

    public void printBookedTickets() {
        System.out.println("Confirmed Tickets:");
        for (Ticket ticket : confirmedTickets) {
            System.out.println(ticket);
        }
        System.out.println("RAC Tickets:");
        for (Ticket ticket : racTickets) {
            System.out.println(ticket);
        }
        System.out.println("Total Booked Tickets: " + (confirmedTickets.size() + racTickets.size()));
    }

    public void printAvailableTickets() {
        System.out.println("Available Tickets:");
        System.out.println("Waiting List:");
        for (Ticket ticket : waitingListTickets) {
            System.out.println(ticket);
        }
        System.out.println("Total Available Tickets: " + (MAX_WAITING_LIST_TICKETS - waitingListTickets.size()));
    }
}

