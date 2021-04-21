package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

public class Post {

    protected String id;

    protected int nextCID = 0;

    protected int nextEID = 0;

    protected String uID;

    protected String content;

    protected boolean exists;

    protected ArrayList<Comment> comments = new ArrayList<>();

    protected ArrayList<Endorsement> endorsements = new ArrayList<>();

    protected int numEndorsements;

    protected int numComments;

    protected String parentID;

    public Post(String id, String uID, String content) {
        this.id = id;
        this.uID = uID;
        this.content = content;
    }

    public Post() {
    }

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

    public String uID() {
        return this.uID;
    }

    public String id() {
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

    public int getNextCID() {
        return this.nextCID;
    }

    public void setNextCID(int nextCID) {
        this.nextCID = nextCID;
    }

    public int getNextEID() {
        return this.nextEID;
    }

    public void setNextEID(int nextEID) {
        this.nextEID = nextEID;
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


    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

}