package socialmedia;

import java.io.IOException;
import java.io.Serializable;

public class Account implements Serializable {
    
    private int uID;

    private String handle;

    private String description;

    private int numEndorsements;

    public Account(int uID, String handle, String description){
        assert (description.length() < 100) : "Description is too long";

        this.uID = uID;
        this.handle = handle;
        this.description = description;
    }

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

}
