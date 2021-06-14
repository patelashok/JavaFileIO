package addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AddressBookFileIOService {
    public static final String AddressBook_FILE_NAME = "AddressBook.txt";
    public void writeData(List<Contact> personList) {
        StringBuffer buffer = new StringBuffer();
        personList.forEach(emp -> {
            String empDataString = emp.toString().concat("\n");
            buffer.append(empDataString);
        });
        try {
            Files.write(Paths.get(AddressBook_FILE_NAME), buffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long countEntries(AddressBook.IOService ioService) {
        long entries = 0;
        try {
            entries = Files.lines(new File(AddressBook_FILE_NAME).toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
}
