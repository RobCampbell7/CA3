package socialmedia;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {

    protected int id;

    protected int uID;

    protected String content;

    protected boolean exists;

    protected ArrayList<Comment> comments = new ArrayList<>();

    protected ArrayList<Endorsement> endorsements = new ArrayList<>();

    protected int numEndorsements;

    protected int numComments;

    protected int parentID;

    protected boolean isEnd = false;

    public Post(int id, int uID, String content) {
        this.id = id;
        this.uID = uID;
        this.content = content;
    }

    public Post() {
    }

    public void setid(int id) {
        this.id = id;
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

    public int uID() {
        return this.uID;
    }

    public int id() {
        return this.id;
    }

    public String content() {
        return this.content;
    }

    public boolean exists() {
        return this.exists;
    }

    public void delete() {
        this.exists = false;
    }

    public void addcomment(Comment newcomment) {
        this.comments.add(newcomment);
        int incrementcomment = this.getNumComments() + 1;
        this.setNumComments(incrementcomment);
    }

    public void removecomment(Comment oldcomment) {
        this.comments.remove(oldcomment);
        int incrementcomment = this.getNumComments() - 1;
        this.setNumComments(incrementcomment);
    }

    public void addendorsement(Endorsement newendorsement) {
        this.endorsements.add(newendorsement);
        int incrementendorsement = this.getNumEndorsements() + 1;
        this.setNumEndorsements(incrementendorsement);
    }

    public void removeendorsement(Endorsement oldendorsement) {
        this.endorsements.remove(oldendorsement);
        int incrementendorsement = this.getNumEndorsements() -1;
        this.setNumEndorsements(incrementendorsement);
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Endorsement> getEndorsements() {
        return this.endorsements;
    }

    public int getNumEndorsements() {
        return this.numEndorsements;
    }

    public void setNumEndorsements(int numEndorsements) {
        this.numEndorsements = numEndorsements;
    }

    public int getNumComments() {
        return this.numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }


    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}