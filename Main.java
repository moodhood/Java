package com.example.challangeJava;

import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    private static ArrayList<Message> allMessages;
    private static ArrayList<Contact> contacts;
    private static int id = 0;

    public static void main(String[] args) {
        allMessages = new ArrayList<>();
        contacts = new ArrayList<>();
        System.out.println("Greetings User!");
        showInitialOptions();
    }
    private static void showInitialOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Please select one:\

                \t1. Manage Contacts\

                \t2. Messages\

                \t3. Quit""");

        switch(scanner.nextInt()){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }
    }
    private static void manageContacts(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Please select one:\

                \t1. Show all contacts\

                \t2. Add a new contact\

                \t3. Search for a contact\

                \t4. Delete a contact\

                \t5. Go back""");

        switch(scanner.nextInt()){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                    break;
            default:
                showInitialOptions();
        }
    }
    private static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the contact's name:");
        String name = scanner.nextLine();
        if(name.isEmpty()){
            System.out.println("Please enter a valid name:");
        }else{
            boolean doesExist = false;
            for(Contact contact: contacts){
                if(contact.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(contact);
                    break;
                }
            }
            if(!doesExist){
                System.out.println("Contact does not exist");
            }
            showInitialOptions();
        }
    }
    private static void searchForContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the contact name:");
        String name = scanner.nextLine();
        if(name.isEmpty()){
            System.out.println("Please enter a valid name:");
            searchForContact();
        }else{
            boolean doesExist = false;
            for (Contact contact : contacts) {
                if(contact.getName().equals(name)){
                    doesExist = true;
                    contact.getDetails();
                }
            }
            if(!doesExist){
                System.out.println("Contact does not exist");
            }
        }
        showInitialOptions();
    }
    private static void addNewContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding a new contact...");

        System.out.println("Please enter the contact's name:");
        String name = scanner.nextLine();

        System.out.println("Please enter the contact's phone number:");
        String number = scanner.nextLine();

        System.out.println("Please enter the contact's email:");
        String email = scanner.nextLine();

        if(name.isEmpty() || number.isEmpty() || email.isEmpty()){
            System.out.println("Please enter a valid information");
            addNewContact();
        }else{
            boolean doesExist = false;

            for (Contact contact : contacts) {
                if (contact.getName().equals(name)) {
                    doesExist = true;
                    break;
                }
            }

            if(doesExist){
                System.out.println("Contact " + name + "already exists, please pick another one!");
                addNewContact();
            } else {
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println("Contact " + name + " was successfully added");
            }
                showInitialOptions();
            }
        }
    private static void showAllContacts() {
        try {
            for (Contact contact : contacts) {
                contact.getDetails();
                System.out.println("**************");
            }
        }catch(Exception e){
            System.out.println("There are no contacts");
            manageContacts();
        }


        showInitialOptions();
    }
    private static void manageMessages(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Please select one:\

                \t1. Show all messages\

                \t2. Send a new message\

                \t3. Go Back""");

        switch(scanner.nextInt()){
            case 1:
                showAllMessage();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }
    private static void sendNewMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter recipient's name:");
        String name = scanner.nextLine();
        if(name.isEmpty()){
            System.out.println("Please enter a valid name:");
            sendNewMessage();
        }else{
            boolean doesExist = false;
            for (Contact contact : contacts) {
                if(contact.getName().equals(name)){
                    doesExist = true;
                    break;
                }
            }
            if(doesExist){
                System.out.println("Please enter your new message:");
                String text = scanner.nextLine();
                if(text.isEmpty()){
                    System.out.println("Please enter a valid message:");
                    sendNewMessage();
                }else{
                    id++;
                    Message message = new Message(text, name, id);
                    for (Contact contact : contacts) {
                        if(contact.getName().equals(name)){
                            ArrayList<Message> newMessages = contact.getMessages();
                            newMessages.add(message);
                            contact.setMessages(newMessages);
                        }
                    }
                }

            }else{
                System.out.println("Contact does not exist");
            }
        }
        showInitialOptions();
    }
    private static void showAllMessage() {
        for(Contact contact: contacts){
            allMessages.addAll(contact.getMessages());
        }

        if(!allMessages.isEmpty()){
            for (Message message : allMessages) {
                message.getDetails();
                System.out.println("**************");
            }
        }else{
            System.out.println("No messages found");
        }

        showInitialOptions();

    }

}





