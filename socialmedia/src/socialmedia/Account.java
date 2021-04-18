package socialmedia;

import java.io.IOException;

public class Account {
    
    private String uID;

    private String handle;

    private String description;

    public Account(String uID, String handle, String description){
        assert (description.length() < 100) : "Description is too long";

        this.uID = uID;
        this.handle = handle;
        this.description = description;
    }

    public String uID(){
        return uID;
    }
    public String handle(){
        return handle;
    }
    public String description(){
        return description;
    }
}
