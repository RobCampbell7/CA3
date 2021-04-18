package socialmedia;

import java.io.IOException;

public class Post{

    protected int pID;

    protected int uID;

    protected String content;

    public Post(int pID, int uID, String content){
        this.pID = pID;
        this.uID = uID;
        this.content = content;
    }

    public int uID(){
        return uID;
    }
    public int pID(){
        return pID;
    }

    public String content(){
        return content;
    }
}