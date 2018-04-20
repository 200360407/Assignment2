/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.security.NoSuchAlgorithmException;

public class User {

    private String userID;
    private String Password;
    private byte[] salt;

    public User(String userID, String Password) throws NoSuchAlgorithmException {
        this.userID = userID;
        this.salt = PasswordGenerator.getSalt();
        this.Password = PasswordGenerator.getSHA512Password(Password, salt);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        if(userID.isEmpty())
        {
            throw new IllegalArgumentException("User id cannot be empty");
        }
        else
        {
        this.userID = userID;
        }
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        if(Password.length()<6)
        {
            throw new IllegalArgumentException("Password must be of 6 or more characters");
        }
        else
        {
        this.Password = Password;
        }
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
    
}
