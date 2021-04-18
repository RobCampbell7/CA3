package socialmedia;

import java.io.IOException;

public class Account {
    
    private int uID;

    private String handle;

    private String description;

    public Account(int uID, String handle, String description){
        assert (description.length() < 100) : "Description is too long";

        this.uID = uID;
        this.handle = handle;
        this.description = description;
    }

    public int uID(){
        return uID;
    }
    public String handle(){
        return handle;
    }
    public String description(){
        return description;
    }
}
