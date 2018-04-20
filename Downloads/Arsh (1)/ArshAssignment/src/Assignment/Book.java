/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Book {

    String name, author;
    float price;
    //lambda expressions
    Predicate<String> StringCheck;
    Predicate<Float> priceCheck;
    Consumer<String> displayToConsole;
    Function<Float, Float> converter;

    /**
     * Constructor to initialize values of class
     */
    public Book(String name, String author, float price) {
        // Predicate lambda expression to check if string passed is empty or not
        StringCheck = (argument) -> !argument.isEmpty();
        // Consumer lambda expression to display value to console
        displayToConsole = (Argument) -> System.out.println(Argument.toUpperCase());
        // Predicate lambda expression to check if the value of price is greater than 0 or not
        priceCheck = (argument) -> argument > 0;
        // Function Lambda expression to round of value of price
        converter = (priceValue) -> {
            return Float.parseFloat(String.valueOf(Math.round(priceValue * 100) / 100));
        };
        setName(name);
        setAuthor(author);
        setPrice(price);
    }

    /**
     * Method to return name
     * @return 
     */
    public String getName() {
        return name;
    }

    
    /**
     * Method to set name if not empty
     * @param name 
     */
    public void setName(String name) {
        // Testting using lambda expression
        if (StringCheck.test(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name Field Cannot Be Empty");
        }
    }

    /**
     * Method to return Author
     * @return 
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Method to set author if not empty
     * @param name 
     */
    public void setAuthor(String author) {
        if (StringCheck.test(author)) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author Field Cannot Be Empty");
        }
    }

    /**
     * Method to return Price
     * @return 
     */
    public float getPrice() {
        return price;
    }

    /**
     * Method to set price if not less than or equal to 0
     * @param name 
     */
    public void setPrice(float price) {
        // Testing using lambda expression
        if (priceCheck.test(price)) {
            // set value by rounding of value
            this.price = converter.apply(price);
        } else {
            throw new IllegalArgumentException("Price cannot be zero or negative");
        }
    }

    /**
     * Method to use Consumer lambda expression
     */
    public void printInConsole() {
        displayToConsole.accept(this.toString());
    }

    /**
     * Method to return String of overridden toString Method
     * @return 
     */
    public String toString() {
        return "Name of Book : " + name + " Author of Book : " + author + " Price of Book : " + price;

    }

}
