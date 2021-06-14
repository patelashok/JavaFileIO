package addressbook;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AddressBookTest{
        static AddressBook addressBook;

        @BeforeClass
        public static void createAddressBookObj() {
            addressBook = new AddressBook(null);
        }
        @Test
        public void given2EmployeeDetails_whenWrittenToFile_ShouldMatchWithEntries() {
            Contact[] arrayOfEmps = { new Contact("Aishwarya", "Muthyala", "rmngr", "knr", "Tg", "505001", "9876543210", "aish@gmail.com"),
                    new Contact("Panu", "Muthyala", "algnr", "knr", "Tg", "505002", "9123456780", "panu@gmail.com")
            };
            addressBook = new AddressBook(Arrays.asList(arrayOfEmps));
            AddressBook.writeAddressBookData(AddressBook.IOService.FILE_IO);
            long entries = AddressBookFileIOService.countEntries(AddressBook.IOService.FILE_IO);
            Assert.assertEquals(2, entries);
        }
}
