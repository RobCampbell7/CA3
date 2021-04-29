package socialmedia;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * BadSocialMedia is a minimally compiling, but non-functioning implementor of
 * the SocialMediaPlatform interface.
 *
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMedia implements SocialMediaPlatform {

    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private int nextID = 1;
    private int nextuid = 0;
    private String orphanMessage = "The original content was removed from the system and is no longer available.";
    private Post genericEmptyPost = new Post(orphanMessage);


    public int finduid(String handle) {
        int id = -1;
        for (Account a : accounts) {
            if (a != null && a.getHandle().equals(handle)) {
                id = a.getUID();
                break;
            }
        }
        return id;
    }

    public Account findAccountFromID(int id) {
        Account foundAccount = null;
        for (Account user : accounts) {
            if (user != null && user.getUID() == id) {
                foundAccount = user;
                break;
            }
        }
        return foundAccount;
    }

    public boolean uniqueHandle(String handle) {
        boolean unique = true;
        for (Account user : accounts) {
            if (user.getHandle() == handle) {
                unique = false;
                break;
            }
        }
        return unique;
    }

    public void orphanComment(Comment thisComment) {
        for (Comment comment : thisComment.getComments()) {
            comment.setOrphan(true);
            comment.setGenericEmptyPost(genericEmptyPost);
            comment.setParentID(0);
        }
    }

    public void deleteEndFromID(int id) {
        Endorsement thisEnd = (Endorsement) findFromID(id);
        posts.remove(thisEnd);
        for (Post post : posts) {
            if (post.id() == thisEnd.getParentID()) {
                post.removeendorsement(thisEnd);
                findAccountFromID(post.uID()).removeNumEndorsements();
            }
        }
    }

    public Post findFromID(int id) {
        for (Post post : posts) {
            if (post.id() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        if (handle.length() == 0 || handle.length() > 30 || handle.contains(" ")) {
            throw new InvalidHandleException("Handle does not match criteria");
        } else if (!uniqueHandle(handle)) {
            throw new IllegalHandleException("Handle is already in use");
        } else {
            Account newAccount = new Account();
            newAccount.setUID(nextuid);
            newAccount.setHandle(handle);

            nextuid += 1;
            accounts.add(newAccount);
            return newAccount.getUID();
        }
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        // TODO Auto-generated method stub
        if (handle.length() == 0 || handle.length() > 30 || handle.contains(" ")) {
            throw new InvalidHandleException("Handle does not match criteria");
        } else if (!uniqueHandle(handle)) {
            throw new IllegalHandleException("Handle is already in use");
        } else {
            Account newAccount = new Account();
            newAccount.setUID(nextuid);
            newAccount.setHandle(handle);
            newAccount.setDescription(description);

            nextuid += 1;
            accounts.add(newAccount);
            return newAccount.getUID();
        }
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        // TODO Auto-generated method stub
        Account oldAccount = null;
        for (Account user : accounts) {
            if (user != null && user.getUID() == id) {
                oldAccount = user;
                break;
            }
        }
        if (oldAccount == null) {
            throw new AccountIDNotRecognisedException("Account ID not recognised");
        } else {
            accounts.remove(oldAccount);
            for (Post post : posts) {
                if (id == post.uID()) {
                    try {
                        deletePost(post.id());
                    } catch (PostIDNotRecognisedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub
        int uid = finduid(handle);
        if (uid == -1) {
            throw new HandleNotRecognisedException("Handle not recognised");
            // Raises HandleNotRecognisedException
        } else {
            Account oldAccount = null;
            for (Account user : accounts) {
                if (user != null && user.getHandle() == handle) {
                    oldAccount = user;
                    break;
                }
            }
            accounts.remove(oldAccount);
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        int uid = finduid(oldHandle);
        if (uid == -1) {
            throw new HandleNotRecognisedException("Handle not recognised");
        } else if (newHandle.length() == 0 || newHandle.length() > 30 || newHandle.contains(" ")) {
            throw new InvalidHandleException("Handle does not match criteria");
        } else if (!uniqueHandle(newHandle)) {
            throw new IllegalHandleException("Handle is already in use");
        } else {
            Account userToAlter = findAccountFromID(uid);
            userToAlter.setHandle(newHandle);
        }
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        int uid = finduid(handle);
        if (uid == -1) {
            throw new HandleNotRecognisedException("Handle not recognised");
        } else {
            Account userToAlter = findAccountFromID(uid);
            userToAlter.setDescription(description);
        }
    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub
        int thisUID = finduid(handle);
        if (thisUID == -1) {
            throw new HandleNotRecognisedException("Handle not recognised");
        } else {
            Account thisaccount = new Account();
            for (Account account : accounts) {
                if (thisUID == account.getUID()) {
                    thisaccount = account;
                    break;
                }
            }
            String stringFormat = "<pre>\nID: " + thisaccount.getUID() + "\nHandle: " + thisaccount.getHandle()
                    + "\nDescription: " + thisaccount.getDescription() + "\nPost counts: " + thisaccount.getNumPosts()
                    + "\nEndorse count: " + thisaccount.getNumEndorsements() + "\n</pre>";
            return stringFormat;
        }
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        // maybe add error msg and repeat later if needed
        int newpostuid = finduid(handle);
        if (message.length() > 100) {
            throw new InvalidPostException("This post is more than 100 characters long");
        } else if (newpostuid == -1) {
            throw new HandleNotRecognisedException("Handle not recognised");
        } else {
            Post newpost = new Post();
            int newpostid = nextID;
            nextID += 1;
            newpost.setid(newpostid);
            newpost.setuID(newpostuid);
            findAccountFromID(newpostuid).addNumPosts();
            newpost.setContent(message);
            posts.add(newpost);
            return newpostid;
        }
    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        int newpostuid = finduid(handle);
        Post post = findFromID(id);
        Account endorsedAccount = findAccountFromID(post.uID());

        if (newpostuid == -1) {
            throw new HandleNotRecognisedException("Handle is not recognised");
        } else if (post == null) {
            throw new PostIDNotRecognisedException("Post ID is not recognised");
        } else if (post.isEnd()) {
            throw new NotActionablePostException("This post is an endorsement, not a comment or original post");
        } else {
            int newID = nextID;
            nextID += 1;

            Endorsement newEnd = new Endorsement();
            newEnd.setid(newID);
            newEnd.setParentID(id);
            newEnd.setuID(newpostuid);
            newEnd.setEnd(true);
            newEnd.setContent(post.content());
            post.addendorsement(newEnd);
            posts.add(newEnd);

            endorsedAccount.addNumEndorsements();
            return newID;
        }
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        // TODO Auto-generated method stub
        // should add comment to list within post object, as well as total post list.
        int newpostuid = finduid(handle);
        Post post = findFromID(id);
        if (newpostuid == -1) {
            throw new HandleNotRecognisedException("Handle is not recognised");
        } else if (post == null) {
            throw new PostIDNotRecognisedException("Post ID is not recognised");
        } else if (post.isEnd()) {
            throw new NotActionablePostException("This post cannot be commented on");
        } else if (message.length() > 100) {
            throw new InvalidPostException("Message length is greater than 100 characters");
        } else {
            int newID = nextID;
            nextID += 1;

            Comment newcomment = new Comment();
            newcomment.setParentID(id);
            newcomment.setid(newID);
            newcomment.setContent(message);
            newcomment.setuID(newpostuid);
            post.addcomment(newcomment);
            posts.add(newcomment);

            findAccountFromID(newpostuid).addNumPosts();
            return newID;
        }
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub
        Post thispost = findFromID(id);
        if (thispost == null) {
            throw new PostIDNotRecognisedException("Post ID is not recognised");
        } else {
            if (!thispost.isEnd()) {
                for (Endorsement end : thispost.getEndorsements()) {
                    deleteEndFromID(end.id());
                }
                posts.remove(thispost);
                findAccountFromID(thispost.uID()).removeNumPosts();
                orphanComment((Comment) thispost);
                for (Post post : posts) {
                    if (post.id() == thispost.getParentID() && thispost.getParentID() != 0) {
                        post.removecomment((Comment) thispost);
                    }
                }
            } else if (thispost.isEnd) {
                deleteEndFromID(thispost.id());
            }
        }
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub
        Post indivPost = findFromID(id);
        if (indivPost == null) {
            throw new PostIDNotRecognisedException("Post ID is not recognised");
        } else {
            Account postAccount = findAccountFromID(indivPost.uID());
            String PostDetails = "\n<pre>\nID: " + indivPost.id() + "\nAccount: " + postAccount.getHandle()
                    + "\nNo. endorsements: " + indivPost.getNumEndorsements() + " | No. comments: "
                    + indivPost.getNumComments() + "\n" + indivPost.content() + "\n</pre>";
            return PostDetails;
        }
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id)
            throws PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        Post parentPost = findFromID(id);
        if (parentPost == null) {
            throw new PostIDNotRecognisedException("Post ID is not recognised");
        } else if (parentPost.isEnd()) {
            throw new NotActionablePostException("This post is an endorsement, not a comment or original post");
        } else {
            String parentPostDetails = showIndividualPost(id).replaceAll("\n</pre>$", "");
            StringBuilder postChildrenDetails = new StringBuilder();
            postChildrenDetails.append(parentPostDetails);
            if (parentPost.getNumComments() != 0) {
                String strPostChildren = recursivePostChildren(1, parentPost.getComments()).toString();
                postChildrenDetails.append(strPostChildren);
            }
            postChildrenDetails.append("\n</pre>\n");
            return postChildrenDetails;
        }
    }

    public StringBuilder recursivePostChildren(int indentSize, ArrayList<Comment> postChildren)
            throws PostIDNotRecognisedException {
        StringBuilder indentString = new StringBuilder("\n");
        int indentIncrement = 0;
        while (indentIncrement < indentSize) {
            indentIncrement++;
            indentString.append("\t");
        }
        String strIndents = indentString.toString();
        String strFirstLineFormat = strIndents.replaceAll("\t$", "|");
        String strSecondLineFormat = strIndents.replaceAll("\t$", "| > ");
        String strFirstLinesFormat = strFirstLineFormat + strSecondLineFormat;
        StringBuilder postChildrenDetails = new StringBuilder();
        for (Comment comment : postChildren) {
            String parentPostDetails = showIndividualPost(comment.id()).replaceAll("\n</pre>$", "");
            parentPostDetails = parentPostDetails.replaceFirst("\n<pre>\n", strFirstLinesFormat);
            Scanner linescanner = new Scanner(parentPostDetails);
            int scannerlinetracker = 0;
            while (linescanner.hasNextLine()) {
                String thisline = linescanner.nextLine();
                if (scannerlinetracker <= 1) {
                    scannerlinetracker += 1;
                    linescanner.close();
                    continue;
                }
                scannerlinetracker += 1;
                String indentedthisline = strIndents.replaceAll("\n", "") + thisline;
                parentPostDetails = parentPostDetails.replaceFirst(thisline, indentedthisline);
            }
            postChildrenDetails.append(parentPostDetails);
            if (comment.getNumComments() != 0) {
                ArrayList<Comment> thisCommentChildren = comment.getComments();
                int newindentSize = indentSize + 1;
                postChildrenDetails.append(recursivePostChildren(newindentSize, thisCommentChildren));
            }
        }
        return postChildrenDetails;
    }

    @Override
    public int getNumberOfAccounts() {
        // TODO Auto-generated method stub
        int accountNo = 0;
        for (Account user : accounts) {
            if (user != null) {
                accountNo += 1;
            }
        }
        return accountNo;
    }

    @Override
    public int getTotalOriginalPosts() {
        // TODO Auto-generated method stub
        int postNo = 0;
        for (Post post : posts) {
            if (post.getParentID() == 0 && !post.isOrphan()) {
                postNo += 1;
            }
        }
        return postNo;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        // TODO Auto-generated method stub
        int endorsementNo = 0;

        for (Account account : accounts) {
            endorsementNo += account.getNumEndorsements();
        }
        return endorsementNo;
    }

    @Override
    public int getTotalCommentPosts() {
        // TODO Auto-generated method stub
        int commentNo = 0;

        for (Post post : posts) {
            commentNo += post.getNumComments();
            if (post.isOrphan()) {
                commentNo += 1;
            }
        }
        return commentNo;
    }

    @Override
    public int getMostEndorsedPost() {
        int highestEnd = 0;
        Post mostEnd = new Post();
        for (Post post : posts) {
            if (post.getNumEndorsements() > highestEnd) {
                mostEnd = post;
                highestEnd = post.getNumEndorsements();
            }
        }
        return mostEnd.id();
        // TODO Auto-generated method stub
    }

    @Override
    public int getMostEndorsedAccount() {
        // TODO Auto-generated method stub
        int highestEnd = 0;
        Account mostEnd = new Account();
        for (Account account : accounts) {
            if (account.getNumEndorsements() > highestEnd) {
                mostEnd = account;
                highestEnd = account.getNumEndorsements();
            }
        }
        return mostEnd.getUID();
    }

    @Override
    public void erasePlatform() {
        // TODO Auto-generated method stub
        posts = new ArrayList<>();
        accounts = new ArrayList<>();
        nextuid = 0;
        nextID = 0;

    }

    @Override
    public void savePlatform(String filename) throws IOException {
        // TODO Auto-generated method stub
        Platform platform = new Platform();
        platform.setAccounts(accounts);
        platform.setPosts(posts);
        platform.setNextid(nextID);
        platform.setNextuid(nextuid);
        platform.setGenericEmptyPost(genericEmptyPost);
        // creating file for the platform
        String platformfilename = filename + ".ser";
        FileOutputStream storeplatform = new FileOutputStream(platformfilename);
        // creating the file writer
        ObjectOutputStream serialiseObject = new ObjectOutputStream(storeplatform);
        // writing to file to store the platform
        serialiseObject.writeObject(platform);
        // close file
        serialiseObject.close();
        storeplatform.close();
    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        // accessing the file
        String filenameser = filename + ".ser";
        FileInputStream getPlatform = new FileInputStream(filenameser);
        // creating file reader
        ObjectInputStream readPlatform = new ObjectInputStream(getPlatform);
        // reading object from file
        Platform loadedPlatform = (Platform) readPlatform.readObject();
        posts.addAll(loadedPlatform.getPosts());
        accounts.addAll(loadedPlatform.getAccounts());
        nextID = loadedPlatform.getNextid();
        nextuid = loadedPlatform.getNextuid();
        genericEmptyPost = loadedPlatform.getGenericEmptyPost();
    }

}
