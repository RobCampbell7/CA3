package socialmedia;

/**
 * The Endorsement class extends the superclass Post, this allows for a distinction
 * between endorsements, original posts and comments when programming using these
 * classes.
 * 
 * @author Graham Faiola
 * @author Robert Campbell
 */
public class Endorsement extends Post{

    /**
     * Constructs an instance of the Endorsement class and uses the setEnd method
     * of it's superclass to define itself as an Endorsement
     */
    public Endorsement() {
        this.setEnd(true);
    }

}