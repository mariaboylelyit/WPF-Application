package ie.lyit.serialize;

import java.util.ArrayList;
import java.util.Scanner;
import ie.lyit.book.Book;
import java.io.*;

public class BookSerializer {
	private ArrayList<Book> books;
	
	final String FILENAME = "books.ser";	
	
	// Default Constructor
	public BookSerializer(){
		// Construct bookList ArrayList
		books = new ArrayList<Book>();
	}	

	/////////////////////////////////////////////////////////
	// Method Name : add()								   //
	// Return Type : void								   //
	// Parameters : None								   //
	// Purpose : Reads one Book record from the user       //
	//           and adds it to the ArrayList called books //
	/////////////////////////////////////////////////////////
	public void add(){
		// Create a Book object
		Book book = new Book();
		// Read its details
		book.read();	
		// And add it to the books ArrayList
		books.add(book);
	}

	///////////////////////////////////////////////
	// Method Name : list()						 //
	// Return Type : void					   	 //
	// Parameters : None						 //
	// Purpose : Lists all Book records in books //
	///////////////////////////////////////////////		
	public void list(){
		// for every Book object in books
        for(Book tmpBook:books)
			// display it
			System.out.println(tmpBook);
	}	
	
	////////////////////////////////////////////////////////////
	// Method Name : view()									  //
	// Return Type : Book								      //
	// Parameters : None								      //
	// Purpose : Displays the required Book record on screen  //
	//         : And returns it, or null if not found         //   
	////////////////////////////////////////////////////////////	
	public Book view(){
		Scanner keyboard = new Scanner(System.in);		

		// Read the number of the book to be viewed from the user
		System.out.println("ENTER LIBRARY NUMBER OF BOOK : ");
		int bookToView=keyboard.nextInt();
		
		// for every Book object in books
        for(Book tmpBook:books) {
 		   // if it's number equals the number of the bookToView
 		   if(tmpBook.getLibraryNumber() == bookToView){
 		      // display it and...
 			  System.out.println(tmpBook);
 			  // return it
 			  return tmpBook;
 		   }
        }
	    // if we reach this code the book was not found so return null
	    return null;		
	}
	
	///////////////////////////////////////////////////////////
	// Method Name : delete()								 //
	// Return Type : void									 //
	// Parameters : None									 //
	// Purpose : Deletes the required Book record from books //
	///////////////////////////////////////////////////////////	
	public void delete(){	
		// Call view() to find, display, & return the book to delete
		Book tempBook = view();

		// If the book != null, i.e. it was found then...
	    if(tempBook != null)
		   // ...remove it from books
	       books.remove(tempBook);		
	}

	///////////////////////////////////////////////////////////
	// Method Name : edit()			  					     //
	// Return Type : void									 //
	// Parameters : None									 //
	// Purpose : Edits the required Book record in books     //
	///////////////////////////////////////////////////////////	
	public void edit(){	
		// Call view() to find, display, & return the book to edit
		Book tempBook = view();

		// If the book != null, i.e. it was found then...
	    if(tempBook != null){
		   // get it's index
		   int index=books.indexOf(tempBook);
		   // read in a new book and...
		   tempBook.read();
		   // reset the object in books
		   books.set(index, tempBook);
	    }
	}
	
	// This method will serialize the books ArrayList when called, 
	// i.e. it will write it to a file called books.ser
	public void writeRecordsToFile(){
		ObjectOutputStream os=null;
		try {
			// Serialize the ArrayList...
			FileOutputStream fileStream = new FileOutputStream(FILENAME);
		
			os = new ObjectOutputStream(fileStream);
				
			os.writeObject(books);
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store books.");
		}
		catch(IOException ioE){
			System.out.println(ioE.getMessage());
		}
		finally {
			try {
				os.close();
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
			}
		}
	}

	// This method will deserialize the books ArrayList when called, 
	// i.e. it will restore the ArrayList from the file books.ser
	public void readRecordsFromFile(){
		ObjectInputStream is=null;
		
		try {
			// Deserialize the ArrayList...
			FileInputStream fileStream = new FileInputStream(FILENAME);
		
			is = new ObjectInputStream(fileStream);
				
			books = (ArrayList<Book>)is.readObject();	
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store books.");
		}
		catch(IOException ioE){
			System.out.println(ioE.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally {
			try {
				is.close();
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
			}
		}
	}
}
