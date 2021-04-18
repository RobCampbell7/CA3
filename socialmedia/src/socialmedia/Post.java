package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

public class Post{

    public void setpID(String pID) {
        this.pID = pID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    protected String pID;

    protected String uID;

    protected String content;

    protected boolean exists;

    private ArrayList<Comment> comments = new ArrayList<>();

    private ArrayList<Endorsement> endorsements = new ArrayList<>();

    public Post(String pID, String uID, String content){
        this.pID = pID;
        this.uID = uID;
        this.content = content;
    }

    public Post() {}

    public String uID(){
        return uID;
    }
    public String pID(){
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