package socialmedia;

import java.io.IOException;
import java.io.Serializable;

public class Comment extends Post implements Serializable {


    public Comment(String ID, String uID, String content){
        super(ID, uID, content);
    }

    public Comment() {}
    

}
