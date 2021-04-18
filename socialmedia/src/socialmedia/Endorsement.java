package socialmedia;

import java.io.IOException;

public class Endorsement extends Post{

    private String id;

    private String parentID;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public Endorsement() {}
}