package addressbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    static Scanner sc = new Scanner(System.in);

    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    public static List<Contact> list;

    public AddressBook(List<Contact> list) {
        super();
        this.list = list;
    }

    //Created method for adding contact
    public static void addContact() {
        System.out.println("Enter your firstName : ");
        String firstName = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFirstName().equalsIgnoreCase(firstName)) {
                System.out.println("Name already exists. Try another name");
                addPersons();
                break;
            }
        }

        System.out.println("Enter your lastName : ");
        String lastName = sc.nextLine();
        System.out.println("Enter your address : ");
        String address = sc.nextLine();
        System.out.println("Enter your city : ");
        String city = sc.nextLine();
        System.out.println("Enter your state : ");
        String state = sc.nextLine();
        System.out.println("Enter your zipCode : ");
        String zip = sc.nextLine();
        System.out.println("Enter your phoneNo : ");
        String phoneNo = sc.nextLine();
        System.out.println("Enter your emailId : ");
        String email = sc.nextLine();
        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNo, email);
        list.add(contact);

    }

    //Created method for editing contact details
    public static void editContact() {
        System.out.println("Enter first name: ");
        String firstName = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFirstName().equalsIgnoreCase(firstName)) {
                list.remove(i);
                addContact();
            } else {
                System.out.println("No data found in Address Book");
            }
        }
    }

    //Creating deleteContact() for deleting contact details using first name
    public static void deleteContact() {
        System.out.println("Enter first name : ");
        String firstName = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFirstName().equalsIgnoreCase(firstName)) {
                list.remove(i);
            } else {
                System.out.println("No data found");
            }
        }
    }

    //Adding Multiple persons i.e contacts in address book
    public static void addPersons() {
        System.out.println("Enter number of persons added to be in address book : ");
        int noOfPersons = sc.nextInt();
        int count  = 1;
        while (count <= noOfPersons) {
            addContact();
            count++;
        }
    }

    //Called method searchByCity for searching contact details by city name
    public void searchByCity() {
        System.out.println("Enter City Name : ");
        String city = sc.next();
        list.stream().filter(n -> n.getCity().equals(city))
                .forEach(i -> System.out.println("Result: "+i.getFirstName()));
    }

    /*Count number of contact persons based on city*/


    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new AddressBookFileIOService().countEntries(ioService);
        return 0;
    }

    //writing data from addressBook
    public static void writeAddressBookData(IOService ioService) {
        if (ioService.equals(addressbook.AddressBook.IOService.CONSOLE_IO))
            System.out.println("Employee Payroll to Details : " + list);
        if (ioService.equals(addressbook.AddressBook.IOService.FILE_IO))
            new AddressBookFileIOService().writeData(list);
    }

    //Reading data from addressBook
    public void readDataFromFile() {
        System.out.println("Enter address book name: ");
        String addressBookFile = sc.nextLine();
        Path filePath = Paths.get("C:\\Users\\Ashok\\IdeaProjects\\javafileIO\\src\\main\\java\\addressbook" + addressBookFile + ".txt");
        try {
            Files.lines(filePath).map(line -> line.trim()).forEach(line -> System.out.println(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}