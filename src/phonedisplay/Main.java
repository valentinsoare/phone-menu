package phonedisplay;

import java.util.Scanner;
import static java.lang.System.*;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private static MobilePhone iPhone = new MobilePhone("0723138260");

    public static void intro(String msg) {
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        out.print("|");
        for (int i = 0; i < msg.length(); i++) {
            out.print("-");
        }
        out.print("|");
        out.println("\n" + "|" + RED + msg + RESET + "|");
        out.print("|");

        for (int j = 0; j < msg.length(); j++) {
            out.print("-");
        }
        out.print("|");
        out.println();
    }

    private static void startPhone() {
        out.println("Options to choose from....");
    }

    private static void printActions() {
        out.println();
        out.println("\t [0] available options.\n" +
                    "\t [1] add contact.\n" +
                    "\t [2] find contact.\n" +
                    "\t [3] remove contact.\n" +
                    "\t [4] update existing contact.\n" +
                    "\t [5] print list of contacts.\n" +
                    "\t [6] quit.");
        out.print("\nChoose your action: ");
    }

    private static void addNewContact() {
        out.print("Enter new contact name: ");
        String name = scan.nextLine().trim();
        out.print("Enter phone number: ");
        String phone = scan.nextLine().trim();
        Contacts newContact = Contacts.createContacts(name, phone);

        if (iPhone.addNewContacts(newContact)) {
            out.println("New Contact added: name " + name + ", phone = " + phone);
        } else {
            out.println("Cannot add, " + name + " already on file.");
        }
    }

    private static void updateContact() {
        out.print("Enter existing contact name: ");
        String name = scan.nextLine().trim();
        Contacts existingContactRecord = iPhone.queryContact(name);

        if (existingContactRecord == null) {
            out.println("Contact not found.");
        }

        out.print("Enter new contact name: ");
        String newName = scan.nextLine().trim();
        out.print("Enter new contact phone number: ");
        String newNumber = scan.nextLine().trim();
        Contacts newContact = Contacts.createContacts(newName, newNumber);

        if (iPhone.updateContact(existingContactRecord, newContact)) {
            out.println("Successfully updated record.");
        } else {
            out.println("Error updating record.");
        }
    }

    private static void removeContact() {
        out.print("Enter existing contact name: ");
        String name = scan.nextLine().trim();
        Contacts existingContactRecord = iPhone.queryContact(name);

        if (existingContactRecord == null) {
            out.println("Contact not found.");
        }

        if (iPhone.removeContact(existingContactRecord)) {
            out.println("Successfully deleted.");
        } else {
            out.println("Failure deleting contact.");
        }
    }

    private static void queryContact() {
        out.print("Enter existing contact name: ");
        String name = scan.nextLine().trim();

        Contacts existingContactRecord = iPhone.queryContact(name);

        if (existingContactRecord == null) {
            out.println("Contact not found.");
        }

        out.println("Name " + existingContactRecord.getName() + " phone number is " + existingContactRecord.getPhoneNumber());
    }

    public static void main(String[] args) {
        boolean quit = false;

        intro("**---iPHONE MENU**---");
        out.println();
        startPhone();
        printActions();
        while (!quit) {
            try {
                int input = Integer.parseInt(scan.nextLine().trim());
                    switch (input) {
                        case 6:
                            out.println("\nQuiting...");
                            quit = true;
                            break;
                        case 5:
                            iPhone.printContacts();
                            break;
                        case 1:
                            addNewContact();
                            break;
                        case 4:
                            updateContact();
                            break;
                        case 3:
                            removeContact();
                            break;
                        case 2:
                            queryContact();
                            break;
                        case 0:
                            printActions();
                            break;
                    }
                    if (input != 0 && input != 6) {
                        printActions();
                    }
            } catch (Exception e) {
                printActions();
            }

        }
    }
}
