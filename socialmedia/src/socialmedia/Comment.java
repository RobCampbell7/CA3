package socialmedia;

import java.io.IOException;

public class Comment extends Post{

    protected Post prevPost;
    
    public Comment(int pID, int uID, String content, Post prevPost){
        super(pID, uID, content);
        this.prevPost = prevPost;
    }
    
    public int uID(){
        return uID;
    }
    public int prevPID(){
        return prevPost.pID();
    }
    public String content(){
        return content;
    }
}
