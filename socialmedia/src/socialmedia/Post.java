package socialmedia;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Post is a class that encapsulates post functionality. An instance of the Post
 * class can be commented on, Endorsed. Both of which are available in the comments
 * and endorsements ArrayLists within the class.
 * The Post stores the ID of the Account that posted it to allow for the Account
 * records, e.g. numEndorsements, numCounts etc., can be kept consistent.
 * 
 * Post also implements serializable which allows for the Platform to be saved and
 * loaded within the SocialMedia class.
 * 
 * @author Graham Faiola
 * @author Robert Campbell
 */
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

    protected boolean isOrphan;

    protected Post genericEmptyPost;

    /**
     * Creates instance of the Post class using passed arguments.
     * 
     * @param id the id of the post
     * @param uID the id of the account responsible for the post
     * @param content the content of Post
     */
    public Post(int id, int uID, String content) {
        this.id = id;
        this.uID = uID;
        this.content = content;
    }

    /**
     * Creates blank instance of the Post class
     */
    public Post() {
    }

    public Post(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post(String content) {
        this.content = content;
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

    /**
     * Adds a comment to the ArrayList comments parameter of Post class
     * 
     * @param newcomment Comment object to add to list
     */
    public void addcomment(Comment newcomment) {
        this.comments.add(newcomment);
        int incrementcomment = this.getNumComments() + 1;
        this.setNumComments(incrementcomment);
    }

    /**
     * Removes a comment from the ArrayList comment parameter of Post class
     * 
     * @param oldcomment Comment object to remove from list of Comments
     */
    public void removecomment(Comment oldcomment) {
        this.comments.remove(oldcomment);
        int incrementcomment = this.getNumComments() - 1;
        this.setNumComments(incrementcomment);
    }

    /**
     * Adds a specified endorsement to the endorsements ArrayList of Post class
     * 
     * @param newendorsement Endorsement object to append to the list of Endorsements
     */
    public void addendorsement(Endorsement newendorsement) {
        this.endorsements.add(newendorsement);
        int incrementendorsement = this.getNumEndorsements() + 1;
        this.setNumEndorsements(incrementendorsement);
    }

    /**
     * Removes a specified endorsement to the endorsements ArrayList of Post class
     * 
     * @param oldendorsement Endorsement object to remove from the list of Endorsements
     */
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

    /**
     * Checks if the Post object is an Endorsement. As this means the post
     * would not be endorseable or commentable.
     * 
     * @return if this post is an Endorsement
     */
    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isOrphan() {
        return isOrphan;
    }

    public void setOrphan(boolean orphan) {
        isOrphan = orphan;
    }

    public Post getGenericEmptyPost() {
        return genericEmptyPost;
    }

    public void setGenericEmptyPost(Post genericEmptyPost) {
        this.genericEmptyPost = genericEmptyPost;
    }
}