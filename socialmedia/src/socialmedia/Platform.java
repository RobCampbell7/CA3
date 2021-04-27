package socialmedia;

import java.io.Serializable;
import java.util.ArrayList;

public class Platform implements Serializable {

    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<Comment> orphanedComments = new ArrayList<>();
    private int nextid = 0;
    private int nextuid = 0;

    public Platform() {
    }


    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Comment> getOrphanedComments() {
        return orphanedComments;
    }

    public void setOrphanedComments(ArrayList<Comment> orphanedComments) {
        this.orphanedComments = orphanedComments;
    }

    public int getNextid() {
        return nextid;
    }

    public void setNextid(int nextpid) {
        this.nextid = nextpid;
    }

    public int getNextuid() {
        return nextuid;
    }

    public void setNextuid(int nextuid) {
        this.nextuid = nextuid;
    }
}
