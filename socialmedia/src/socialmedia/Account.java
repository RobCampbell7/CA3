package socialmedia;

import java.io.Serializable;

/**
 * Account is a class that encapsulates user functionality for the
 * social media platform. Each instance of account has a unique id
 * and handle attributes, which is enforced within the social media
 * class's functionality.
 * Account extends serializable so that it can be serialised allowing
 * for the social media platform to be saved, loaded, etc.
 * 
 * @author Graham Faiola
 * @author Robert Campbell
 */
public class Account implements Serializable {
    
    private int uID;

    private String handle;

    private String description;

    private int numEndorsements;

    private int numPosts;

    /**
     * Creates an instance of the Account class using passed Arguments
     * 
     * @param uID ID of the user to create
     * @param handle hadnle of the user to create
     * @param description
     */
    public Account(int uID, String handle, String description){
        assert (description.length() < 100) : "Description is too long";

        this.uID = uID;
        this.handle = handle;
        this.description = description;
    }

    /**
     * Creates blank instance of Account class
     */
    public Account(){}

    public int getUID() {
        return this.uID;
    }

    public void setUID(int uID) {
        this.uID = uID;
    }

    public String getHandle() {
        return this.handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumEndorsements() {
        return numEndorsements;
    }

    public void addNumEndorsements() {
        this.numEndorsements += 1;
    }

    public void removeNumEndorsements() {
        this.numEndorsements -= 1;
    }
    public void setNumEndorsements(int numEndorsements) {
        this.numEndorsements = numEndorsements;
    }

    /**
     * Increments the number of posts assigned to this instance
     * of Account
     */
    public void addNumPosts() {
        this.numPosts += 1;
    }

    /**
     * Decrements the number of posts assigned to this instance
     * of Account
     */
    public void removeNumPosts() {
        this.numPosts -= 1;
    }

    public int getNumPosts() {
        return this.numPosts;
    }

}
