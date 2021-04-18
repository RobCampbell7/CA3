package socialmedia;

import java.io.IOException;

public class Endorsement extends Comment{

    public Endorsement(int pID, int uID, String content, Post prevPost){
        super(pID, uID, content, prevPost);
    }
    public String endorsedContent(){
        return prevPost.content();
    }
}