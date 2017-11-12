package ie.lyit.testers;

import ie.lyit.serialize.BookSerializer;
import ie.lyit.book.Menu;

public class BookSerializerTester {

	public static void main(String[] args) {
		BookSerializer bookSerializer = new BookSerializer();

		// Read the ArrayList from the File in books
		// THIS DESERIALIZES THE ARRAYLIST
		bookSerializer.readRecordsFromFile();
		
	    // Create a Menu Object
		Menu menuObj = new Menu();
		
		do {
			// Call it's display() method
			menuObj.display();
			// Call it's readOption() method
			menuObj.readOption();
	
			// switch on the option and call the appropriate method
			switch(menuObj.getOption()){
			   case 1:bookSerializer.add();break;
			   case 2:bookSerializer.list();break;
			   case 3:bookSerializer.view();break;
			   case 4:bookSerializer.edit();break;
			   case 5:bookSerializer.delete();break;
			   case 6:break;				
			   default:System.out.println("INVALID OPTION...");
			}
		}while(menuObj.getOption() != 6);
		
		// Write the books ArrayList to File
		// THIS SERIALIZES THE ARRAYLIST		
		bookSerializer.writeRecordsToFile();
	}
}
