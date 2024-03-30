package org.v1.railway;

import org.v1.person.Child;
import org.v1.person.Person;

import java.util.Scanner;

public class TicketHandler {

    public void bookTicket() {
        if (Railway.availableWaitingList == 0) {
            System.out.println("No Tickets Available!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Booking Process started");
        System.out.println("Enter your Name: ");
        String name = sc.next();
        System.out.println("Enter your Age: ");
        int age = sc.nextInt();
        System.out.println("Enter your Gender[M/F/O]: ");
        String gender = sc.next();
        System.out.println("Enter your Berth Preference[L/SL/U]: ");
        String berth = sc.next();

        if (age < 5) {
            Railway.childrens.add(new Child(name, age, gender));
            System.out.println("Childrens are not allowed to book ticket!");
            return;
        }

        Person person = new Person(name, age, gender, berth);
        String availableBerth = checkAvailability(person);
        if (availableBerth == null) {
            System.out.println("Booking Failed!");
            return;
        } else {
            System.out.println("Alloted Berth: " + availableBerth);
            person.setAlloted(availableBerth);
            Railway.passengers.add(person);
            Railway.passengerMap.put(person.getPid(), person);
            if (availableBerth.equals("RAC")) {
                Railway.racList.add(person.getPid());
            } else if (availableBerth.equals("WL")) {
                Railway.waitingList.add(person.getPid());
            } else {
                Railway.bookedList.add(person.getPid());
            }
            System.out.println("--------------------Booking Success!");
        }
    }

    private String checkAvailability(Person person) {
        if ((person.getBerthPreference().equals("L") && Railway.availableLowerBerths > 0) ||
                (person.getBerthPreference().equals("SL") && Railway.availableLowerBerths > 0) ||
                (person.getBerthPreference().equals("L") && Railway.availableLowerBerths > 0)
        ) {
//            System.out.println("Preferred Berth Available..");
            if (person.getBerthPreference().equals("L")) {
                if (person.getAge() < 60 && !person.getGender().equals("F")) {
                    if (Railway.availableSideLowerBerths > 0) {
                        Railway.availableSideLowerBerths--;
                        return "SL";
                    } else if (Railway.availableUpperBerths > 0) {
                        Railway.availableUpperBerths--;
                        return "U";
                    } else {
                        Railway.availableLowerBerths--;
                        return "L";
                    }
                }
                Railway.availableLowerBerths--;
                return "L";
            } else if (person.getBerthPreference().equals("SL")) {
                Railway.availableSideLowerBerths--;
                return "SL";
            } else {
                Railway.availableUpperBerths--;
                return "U";
            }
        } else if (Railway.availableLowerBerths > 0) {
//            if(person.getAge() > 60 || person.getGender().equals("F"))
            if (person.getAge() < 60 && !person.getGender().equals("F")) {
                if (Railway.availableSideLowerBerths > 0) {
                    Railway.availableSideLowerBerths--;
                    return "SL";
                } else if (Railway.availableUpperBerths > 0) {
                    Railway.availableUpperBerths--;
                    return "U";
                } else {
                    Railway.availableLowerBerths--;
                    return "L";
                }
            } else {
                Railway.availableLowerBerths--;
                return "L";
            }
        } else if (Railway.availableSideLowerBerths > 0) {
            Railway.availableSideLowerBerths--;
            return "SL";
        } else if (Railway.availableUpperBerths > 0) {
            Railway.availableUpperBerths--;
            return "U";
        } else if (Railway.availableRacTickets > 0) {
            Railway.availableRacTickets--;
            Railway.racList.add(person.getPid());
            return "RAC";
        } else if (Railway.availableWaitingList > 0) {
            Railway.availableWaitingList--;
            Railway.waitingList.add(person.getPid());
            return "WL";
        }
        return null;
    }

    public void cancelTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Passenger ID: ");
        int id = sc.nextInt();
        if (Railway.bookedList.contains(id) || Railway.racList.contains(id) || Railway.waitingList.contains(id)) {
            System.out.println("Cancelling ticket...");
        } else {
            System.out.println("Cancellation fails...");
            return;
        }
        Person person = Railway.passengerMap.get(id);
//        Railway.passengers.remove(person.getPid());
        System.out.println("1");
        Railway.bookedList.remove(Integer.valueOf(id));
        System.out.println("2");
        Railway.passengerMap.remove(Integer.valueOf(id));
        System.out.println("3");

        if (person.getAlloted().equals("L")) {
            Railway.availableLowerBerths++;
        } else if (person.getAlloted().equals("SL")) {
            Railway.availableSideLowerBerths++;
        } else if (person.getAlloted().equals("U")) {
            Railway.availableUpperBerths++;
        }

        if (Railway.racList.size() > 0) {
            int racId = Railway.racList.poll();
            Person racPerson = Railway.passengerMap.get(racId);
            Railway.racList.remove(racPerson.getPid());
            Railway.availableRacTickets++;
            if (person.getAlloted().equals("L")) {
                Railway.availableLowerBerths--;
            } else if (person.getAlloted().equals("SL")) {
                Railway.availableSideLowerBerths--;
            } else if (person.getAlloted().equals("U")) {
                Railway.availableUpperBerths--;
            }
            racPerson.setAlloted(person.getAlloted());
            Railway.bookedList.add(racId);

            if (Railway.waitingList.size() > 0) {
                int wlId = Railway.waitingList.poll();
                Person wlPerson = Railway.passengerMap.get(wlId);
                Railway.waitingList.remove(wlPerson.getPid());
                Railway.availableWaitingList++;
                Railway.availableRacTickets--;
                Railway.racList.add(wlId);
//                wlPerson.setAlloted();
            }
            System.out.println("-----------ticket cancellation success");
        }
    }
}
