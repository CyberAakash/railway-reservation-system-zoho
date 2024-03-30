package net.chatGPT;

// Ticket.java
public class Ticket {
    private String name;
    private int age;
    private String gender;
    private String berthPreference;

    public Ticket(String name, int age, String gender, String berthPreference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBerthPreference() {
        return berthPreference;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Berth Preference: " + berthPreference;
    }
}

