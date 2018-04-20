/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AllBooks {

    ArrayList<Book> all;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor to initialize variables
     */
    
    public AllBooks() {
        all = new ArrayList<Book>();
        // Created three books objects
        Book b1 = new Book("Science", "Rich", 12.2F);
        Book b2 = new Book("Environment", "Freeman", 18.2F);
        Book b3 = new Book("Php", "RF", 14.2F);
        // added in books array list
        all.add(b1);
        all.add(b2);
        all.add(b3);

    }

    /**
     * Method to start Tasks on ALl Books
     */
    public void start() {
        System.out.println("All Books:");
        // Using stream to print list of all books
        all.stream().map(book -> book).forEach(book -> System.out.println(book.toString()));
        tasks();
    }

    /**
     * Method to perform Tasks
     */
    public void tasks() {
        System.out.println("Use of Streams");
        System.out.println("1.Filter with book names \n2.Total Price of all books\n3.Print all Books\n4.Add Book \n5.Sort Books By price \n6. Sort Books By name \n7. Sort Books By Author's Name \n8.Exit");
        int choice;
        choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1: {
                // To Filter Books using name
                System.out.println("Enter filter");
                String filter = sc.nextLine();
                // Using stream to filter list of books
                List<Book> filtered = all.stream().filter(book -> book.getName().startsWith(filter)).collect(Collectors.toList());

                // Using stream to print filtered list of books
                filtered.stream().map(book -> book).forEach(book -> System.out.println(book.toString()));
                tasks();
                break;
            }
            case 2: {

                // Using stream to get sum of price of all books
                double Sum = all.stream().mapToDouble(book -> book.getPrice()).sum();
                System.out.println("Total Value of all Books : " + Sum);
                tasks();
                break;
            }
            case 3: {
                System.out.println("All Books:");
                // Using stream to print list of all books
                all.stream().map(book -> book).forEach(book -> System.out.println(book.toString()));
                tasks();
                break;
            }
            case 4: {
                // To add new book in list
                System.out.println("Enter Book Name:");
                String bname = sc.nextLine();
                System.out.println("Enter Author Name:");
                String aname = sc.nextLine();
                System.out.println("Enter Book Price:");
                Float price = sc.nextFloat();
                // adding new book in list
                all.add(new Book(bname, aname, price));
                System.out.println("Book added");
                tasks();
                break;
            }
            case 5: {
                /*
                 Sorting books by price 
                 */
                List<Book> sortedList = all.stream().sorted(Comparator.comparing(Book::getPrice)).collect(Collectors.toList());
                sortedList.stream().map(product -> product).forEach(product -> System.out.println(product.toString()));
                tasks();
                break;
            }
            case 6: {

                /*
                 Sorting books by Name
                 */
                List<Book> sortedList = all.stream().sorted(Comparator.comparing(Book::getName)).collect(Collectors.toList());
                sortedList.stream().map(product -> product).forEach(product -> System.out.println(product.toString()));
                tasks();
                break;
            }
            case 7: {

                /*
                 Sorting books by author
                 */
                List<Book> sortedList = all.stream().sorted(Comparator.comparing(Book::getAuthor)).collect(Collectors.toList());
                sortedList.stream().map(product -> product).forEach(product -> System.out.println(product.toString()));
                tasks();
                break;
            }
            case 8: {
                break;
            }
            default: {
                System.out.println("Invalid Choice");
                tasks();

            }
        }

    }

}
