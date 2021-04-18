package socialmedia;

import java.io.IOException;

public class Post{

    protected int pID;

    protected int uID;

    protected String content;

    protected boolean exists;

    private Comment[] comments;

    private Endorsement[] endorsements;

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
    public boolean exists(){
        return this.exists;
    }
    public void delete(){
        this.exists = false;
    }
}