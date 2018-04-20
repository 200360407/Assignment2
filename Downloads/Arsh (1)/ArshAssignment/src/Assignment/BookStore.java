/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BookStore {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        BookStore bs = new BookStore();
        bs.start();
    }

    /**
     * Method to Select Operation
     */
    public void start() {
        System.out.println("1. Login \n2. Create User\n3. Exit");
        int choice;
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                Login();
                break;
            }
            case 2: {
                CreateUser();
                break;
            }
            case 3: {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Enter Valid Choice");
                start();
            }
        }
    }

    /**
     * Method to access database for using login
     */
    private void Login() {
        sc.nextLine();
        System.out.print("Enter User ID:");
        String userID = sc.nextLine();
        try {
            /**
             * Database connectivity Classes
             */
            Connection conn = null;
            Statement statement = null;
            /**
             * Connection with database
             */
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Arshpreet", "root", "");
            ResultSet rs = null;
            statement = conn.createStatement();
            /**
             * Selecting data from Table
             */
            rs = statement.executeQuery("Select * from LibraryUsers");
            boolean flag = false;
            while (rs.next()) {
                /**
                 * checking if user exists
                 */
                if (rs.getString("username").equals(userID)) {
                    flag = true;

                    System.out.print("Enter Password:");
                    String password = sc.nextLine();
                    /**
                     * Getting salt in form of BLOB
                     */
                    Blob blob = rs.getBlob("salt");
                    //Total lemgth of blob

                    int blobLength = (int) blob.length();
                    // converting blob to byte[] 
                    byte[] salt = blob.getBytes(1, blobLength);
                    /**
                     * Check if hashed password is same as password inputed
                     */
                    if (PasswordGenerator.getSHA512Password(password, salt).equals(rs.getString("_password"))) {
                        System.out.println("Logged in");
                        // If user logs in successfully starting book shelf
                        AllBooks ab = new AllBooks();
                        // start Method call to allBooks 
                        ab.start();
                        break;
                    } else {
                        // If password doesn't match recall login method
                        System.out.println("Password Doesn't Match");
                        Login();
                    }
                }
            }
            if (!flag) {
                /**
                 * If no user exists again go back to start method
                 */
                System.out.println("User doesn't Exist");
                start();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Login();
        }
    }

    /**
     * Method to access and update database for using creating user
     */
    private void CreateUser() {
        sc.nextLine();
        System.out.print("Enter User ID:");
        String userID = sc.nextLine();
        System.out.print("Enter Password:");
        String password = sc.nextLine();
        try {
            // created new user object
            User newUser = new User(userID, password);
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Arshpreet", "root", "");
            // insert query for user 
            String sql = "Insert into LibraryUsers values(?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userID);
            // hashed password inserted
            preparedStatement.setString(2, PasswordGenerator.getSHA512Password(password, newUser.getSalt()));
            // inserted salt in database using blob 
            preparedStatement.setBlob(3, new javax.sql.rowset.serial.SerialBlob(newUser.getSalt()));
            preparedStatement.execute();
            System.out.println("User Created");
            start();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            CreateUser();
        }
    }
}