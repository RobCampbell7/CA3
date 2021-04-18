package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

public class Post{

    public void setpID(int pID) {
        this.pID = pID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    protected int pID;

    protected int uID;

    protected String content;

    protected boolean exists;

    private ArrayList<Comment> comments = new ArrayList<>();

    private ArrayList<Endorsement> endorsements = new ArrayList<>();

    public Post(int pID, int uID, String content){
        this.pID = pID;
        this.uID = uID;
        this.content = content;
    }

    public Post() {}

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
    public void delete(){ this.exists = false; }

    public void addcomment(Comment newcomment) {
        this.comments.add(newcomment);
    }

    public void removecomment(Comment oldcomment) {
        this.comments.remove(oldcomment);
    }

    public void addendorsement(Endorsement newendorsement) {
        this.endorsements.add(newendorsement);
    }

    public void removeendorsement(Endorsement oldendorsement) {
        this.endorsements.remove(oldendorsement);
    }


}