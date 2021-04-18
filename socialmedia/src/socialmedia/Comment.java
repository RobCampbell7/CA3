package socialmedia;

import java.io.IOException;

public class Comment extends Post{

    private String parentID;

    public String getparentID() {
        return parentID;
    }

    public void setparentID(String parentID) {
        this.parentID = parentID;
    }


    public Comment(String ID, String uID, String content){
        super(ID, uID, content);
    }

    public Comment() {}
    

}
