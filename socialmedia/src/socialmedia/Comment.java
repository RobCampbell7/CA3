package socialmedia;

import java.io.IOException;

public class Comment extends Post{

    private String cID;

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }


    public Comment(String pID, String uID, String content){
        super(pID, uID, content);
    }

    public Comment() {}
    

}
