package Collections;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class AddressBookUC8 {
	HashMap<String, LinkedList<Contacts2>> addressBooks = new HashMap<>();
	LinkedList<Contacts2> allContacts = new LinkedList<Contacts2>();
	Scanner scanner = new Scanner(System.in);

	/**
	 * Method to add contacts
	 */
	public Contacts2 addContact() {
		Contacts2 contact = new Contacts2();
		System.out.println("Enter First Name");
		contact.setFirstname(scanner.next());
		System.out.println("Enter Last Name");
		contact.setLastname(scanner.next());
		System.out.println("Enter City");
		contact.setCity(scanner.next());
		System.out.println("Enter State");
		contact.setState(scanner.next());
		System.out.println("Enter Pincode");
		contact.setZipcode(scanner.nextInt());
		System.out.println("Enter Phone Number");
		contact.setPhonenumber(scanner.next());
		System.out.println("Enter Email");
		contact.setEmail(scanner.next());
		System.out.println("Enter Book name to which you have to add contact");
		String bookName = scanner.next();

		// checking book already exist
		if (addressBooks.containsKey(bookName)) {
			// if exist then add contact to list
			LinkedList<Contacts2> contactList = addressBooks.get(bookName);
			contactList.add(contact);
			addressBooks.put(bookName, contactList);
			System.out.println("New Contact Added Sucessfully");
		} else {
			// creating a new book and list
			allContacts.add(contact);
			addressBooks.put(bookName, allContacts);
			System.out.println("New book created and Contact Added Sucessfully");
		}

		return contact;
	}

	/**
	 * Method to Edit contact using unique phoneNumber
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public boolean editContact(String phoneNumber) {
		for (Contacts2 contact : allContacts) {
			if (contact.getPhonenumber() == phoneNumber) {
				System.out.println("Enter First Name");
				String firstName = scanner.next();
				System.out.println("Enter last Name");
				String lastName = scanner.next();
				System.out.println("Enter City");
				String city = scanner.next();
				System.out.println("Enter State");
				String state = scanner.next();
				System.out.println("Enter zip");
				String zip = scanner.next();
				contact.setFirstname(firstName);
				contact.setLastname(lastName);
				contact.setCity(city);
				contact.setState(state);
				contact.setState(zip);
				return operationStatus(true);
			}
		}
		return operationStatus(false);
	}

	/**
	 * Method to Delete contact using unique phoneNumber
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public boolean deleteContact(String phoneNumber) {

		for (Contacts2 contact : allContacts) {
			if (contact.getPhonenumber() == phoneNumber) {
				allContacts.remove(contact);
				return operationStatus(true);
			}
		}
		return operationStatus(false);
	}

	/**
	 * Method to Display the Contact Details
	 */
	public void displayContacts() {
		for (Contacts2 contact : allContacts) {
			System.out.println(contact);
		}
	}

	/**
	 * Method to check the status of operation whether it is done properly or not
	 * 
	 * @param status
	 * @return
	 */
	private static boolean operationStatus(boolean status) {
		if (status) {
			System.out.println("Contact Updated Successfully");
		} else {
			System.out.println("Contact not found");
		}
		return status;
	}
	
	//check Duplicate using name
		private void addContactToExsistingBook(Contacts2 contact, String bookName, LinkedList<Contacts2> contactList)
		{
			boolean isAlreadyExsist = false;
			for (Contacts2 searchContact : contactList) 
			{
				if (searchContact.getFirstname().equals(contact.getFirstname()))
				{
					isAlreadyExsist = true;
					break;
				}
			}
			if( !(isAlreadyExsist) )
			{
				contactList.add(contact);				
				addressBooks.put(bookName, contactList);
				System.out.println("New Contact Added Sucessfully");
			}
			else
			{
				System.out.println("Contact already exsist");
			}
		}

		//method to search multiple person in city and state
		public void searchPerson(String searchKey)
		{
			for (String bookName : addressBooks.keySet())
			{
				LinkedList<Contacts2> contactList  =  addressBooks.get(bookName);
				for (Contacts2 contact : contactList) 
				{
					if (contact.getCity().equals(searchKey) ||  contact.getState().equals(searchKey) )
					{
						System.out.println(contact.getFirstname() + ""+ contact.getLastname());

					}
				}
			}
		}

}
