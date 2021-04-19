package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

public class Post{

    protected String id;

    protected int nextCID = 0;

    protected int nextEID = 0;

    protected String uID;

    protected String content;

    protected boolean exists;

    protected ArrayList<Comment> comments = new ArrayList<>();

    protected ArrayList<Endorsement> endorsements = new ArrayList<>();

    public void setid(String id) {
        this.id = id;
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

    public Post(String id, String uID, String content){
        this.id = id;
        this.uID = uID;
        this.content = content;
    }

    public Post() {}

    public String uID(){
        return uID;
    }
    public String id(){
        return id;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }

    public int getNextCID() {
        return nextCID;
    }

    public void setNextCID(int nextCID) {
        this.nextCID = nextCID;
    }

    public int getNextEID() {
        return nextEID;
    }

    public void setNextEID(int nextEID) {
        this.nextEID = nextEID;
    }
}