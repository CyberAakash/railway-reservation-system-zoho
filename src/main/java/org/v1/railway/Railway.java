package org.v1.railway;

import org.v1.person.Child;
import org.v1.person.Person;

import java.util.*;

public class Railway {
//    static final int MAX_CONFIRMED_TICKETS = 63;
//    static final int MAX_RAC_TICKETS = 18;
//    static final int MAX_WAITING_LIST_TICKETS = 10;

    static int availableLowerBerths = 1;// normally 21
    static int availableSideLowerBerths = 1;// normally 21
    static int availableUpperBerths = 1;// normally 21
    static int availableRacTickets = 1;// normally 18
    static int availableWaitingList = 1;// normally 10

    static List<Person> passengers = new ArrayList<Person>();
    static List<Child> childrens = new ArrayList<Child>();
    static Map<Integer, Person> passengerMap = new HashMap<Integer, Person>();

    static List<Integer> bookedList = new ArrayList<>();
    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();

    static Railway railway = null;

    public static Railway getInstance() {
        if(railway == null) {
            railway = new Railway();
        }
        return railway;
    }

    public void displayPassengers() {
        System.out.println("Passengers List");
        for(Map.Entry<Integer, Person> entry:passengerMap.entrySet()) {
            System.out.println("Passenger ID : "+ entry.getKey());
            Person person = entry.getValue();
            System.out.println("Name : "+ person.getName());
            System.out.println("Age : "+ person.getAge());
            System.out.println("Gender : "+ person.getGender());
            System.out.println("Berth Preference : "+ person.getBerthPreference());
            System.out.println("Allotted Berth : "+ person.getAlloted());
            System.out.println();
        }
        System.out.println();
    }

    public void displayChildrens() {
        System.out.println("Childrens List");
        System.out.println(Arrays.toString(Railway.childrens.toArray()));
        System.out.println();
    }

    public void displayAvailableTickets() {
        System.out.println("Available Tickets");
        System.out.println("Lower Berth: "+ availableLowerBerths);
        System.out.println("Side Lower Berth: "+ availableSideLowerBerths);
        System.out.println("Upper Berth: "+ availableUpperBerths);
        System.out.println("RAC : "+ availableRacTickets);
        System.out.println("Waiting List: "+ availableWaitingList);
        System.out.println();
    }

    public void displayBookedTickets() {
        System.out.println("Booked Tickets");
        System.out.println("Total Booked Tickets: "+ bookedList.size());
        for(int i = 0; i < bookedList.size(); i++) {
            Person person = passengerMap.get(bookedList.get(i));
            int id = i+1;
            System.out.println("BID: "+ bookedList.get(i));
            System.out.println("Booking ID: "+person.getPid());
            System.out.println("Name : "+ person.getName());
            System.out.println("Age : "+ person.getAge());
            System.out.println("Gender : "+ person.getGender());
            System.out.println("Berth Preference : "+ person.getBerthPreference());
            System.out.println("Allotted Berth : "+ person.getAlloted());
            System.out.println();
        }
        System.out.println();
    }
}
