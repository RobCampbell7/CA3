package socialmedia;

import java.io.IOException;

public class Endorsement extends Post{

    private String eID;

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }

    public Endorsement(String pID, String uID, String content){
        super(pID, uID, content);
    }

    public Endorsement() {}
}