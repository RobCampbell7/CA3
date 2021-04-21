package socialmedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    private int nextpid = 0;
    private int nextuid = 0;
    private ArrayList<ArrayList<Comment>> orphanedComments = new ArrayList<>();

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
            if (user != null && user.getUID()) {
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

    public Post findPostFromID(String id) {
        Post foundPost = new Post();
        String[] idArr = id.split("-");
        ArrayList<Post> postArr = posts;

        for (String s : idArr) {
            for (Post post : postArr) {
                //comparing each post id to the currently selected part of the parent id
                if (post.id().equals(s)) {
                    //if the ids match checks
                    if (s.equals(idArr[-1])) {
                        //if this is the last part of parent id then it collects the requested post
                        foundPost = post;
                        // then returns the post this also breaks all loops and exits the function
                        return foundPost;
                    } else {
                        //change the array of posts to the array of comments inside the found matching post
                        postArr = post.getComments();
                    }
                }
            }
        }
        return foundPost;
    }


    /**
     * below are alternative methods that allow for finding posts individually by id or a comment by id or an endorsement
     * i have made it so that we only need to call the universal fromID function and give it the right parameters to minimise code repitition in other functions
     */
    public Post fromID(String type, String id, String action) {
        String[] idArr = id.split("-");
        if (action.equals("FIND")) {
            if (type.equals("Post")) {
                return findObjectFromID(idArr, type);
            }
            else if (type.equals("Comment")) {
                return findObjectFromID(idArr, type);
            }
            else if (type.equals("Endorsement")) {
                return findObjectFromID(idArr, type);
            }
        } else if (action.equals("DELETE")) {
            if (type.equals("Post")) {
                Post removepost = findObjectFromID(idArr, type);
                orphanedComments.add(removepost.getComments());
                posts.remove(removepost);
            }
            else if (type.equals("Comment")) {
                orphanedComments.add(findObjectFromID(idArr, type).getComments());
                String[] idArrImmediateParent = Arrays.copyOfRange(idArr, 0, idArr.length-1);
                if (idArrImmediateParent.length == 1) {
                    findObjectFromID(idArrImmediateParent, "Post").removecomment((Comment) findObjectFromID(idArr, type));
                } else {
                    findObjectFromID(idArrImmediateParent, type).removecomment((Comment) findObjectFromID(idArr, type));
                }
            }
            else if (type.equals("Endorsement")) {
                String[] idArrImmediateParent = Arrays.copyOfRange(idArr, 0, idArr.length-1);
                if (idArrImmediateParent.length == 1) {
                    findObjectFromID(idArrImmediateParent, "Post").removeendorsement((Endorsement) findObjectFromID(idArr, type));
                } else {
                    findObjectFromID(idArrImmediateParent, type).removeendorsement((Endorsement) findObjectFromID(idArr, type));
                }
            }
        }
        return null;
    }

    public Post findPostFromID(String[] idArr) {
        for (Post post : posts) {
            if (post.id().equals(idArr[0])) {
                return post;
            }
        }
        return null;
    }

    public Post findObjectFromID(String[] idArr, String type) {
        if (type.equals("Post")) {
            return findPostFromID(idArr);
        } else if (type.equals("Endorsement") && idArr.length == 2) {
            return findEndorsementFromID(findPostFromID(idArr).getEndorsements(), idArr);
        } else {
            return findObject(idArr, findPostFromID(idArr).getComments(), type);
        }
    }

    public Post findObject(String[] idArr, ArrayList<Comment> commentArr, String type) {
        //remove the first member of the array so that we can search using the next member
        String[] commentidArr = Arrays.copyOfRange(idArr, 1, idArr.length);
        for (Comment commment : commentArr) {
            //comparing each post id to the currently selected part of the parent id
            if (commment.id().equals(commentidArr[0])) {
                //if the ids match checks
                if (commentidArr.length == 1) {
                    //if this is the last part of parent id then it returns the comment this also breaks the loop and exits the function(s)
                    return commment;
                } else  if (commentidArr.length == 2 && type.equals("Endorsement")) {
                    return findEndorsementFromID(commment.getEndorsements(), commentidArr);
                } else {
                    //recursively call the function to search the matching comment's array of comments
                    return findObject(commentidArr, commment.getComments(), type);
                }
            }
        }
        return null;
    }

    public Post findEndorsementFromID(ArrayList<Endorsement> endorseArr, String[] idArr) {
        //remove the first member of the array so that we can search using the next member
        String[] endorsementidArr = Arrays.copyOfRange(idArr, 1, idArr.length);
        for (Endorsement endorsement : endorseArr) {
            if (endorsement.id().equals(endorsementidArr[0])) {
                return endorsement;
            }
        }
        return null;
    }


    @Override
    public String createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        if (handle.length() == 0 || handle.length() > 30 || handle.contains(" ")) {
            // Will call InvalidHandleException
            return "0";
        } else if (!uniqueHandle(handle)) {
            // Will call IllegalHandleException
            return "0";
        } else {
            Account newAccount = new Account();
            newAccount.setUID(Integer.toString(nextuid));
            newAccount.setHandle(handle);

            nextuid += 1;
            accounts.add(newAccount);
            return newAccount.getUID();
        }
    }

    @Override
    public String createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        // TODO Auto-generated method stub
        if (handle.length() == 0 || handle.length() > 30 || handle.contains(" ")) {
            // Will call InvalidHandleException
            return "0";
        } else if (!uniqueHandle(handle)) {
            // Will call IllegalHandleException
            return "0";
        } else {
            Account newAccount = new Account();
            newAccount.setUID(Integer.toString(nextuid));
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
        accounts.remove(oldAccount);
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub
        int uid = finduid(handle);
        if (uid == -1) {
            // Raises HandleNotRecognisedException
        } else {
            removeAccount(uid);
        }
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        int uid = finduid(oldHandle);
        if (uid == -1) {
            // Will call HandleNotRecognisedException
        } else if (newHandle.length() == 0 || newHandle.length() > 30 || newHandle.contains(" ")) {
            // Will call InvalidHandleException
        } else if (!uniqueHandle(newHandle)) {
            // Will call IllegalHandleException
        } else {
            Account userToAlter = findAccountFromID(uid);
            userToAlter.setHandle(newHandle);
        }
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        int uid = finduid(handle);
        if (uid == -1) {
        } else {
            Account userToAlter = findAccountFromID(uid);
            userToAlter.setDescription(description);
        }
    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        //maybe add error msg and repeat later if needed
        String newpostuid = Integer.toString(finduid(handle));
        if (message.length() > 100) {
            return 0;
        } else if (newpostuid.equals("-1")) {
            return 0;
        } else {
            Post newpost = new Post();
            String newpostid = Integer.toString(nextpid);
            nextpid += 1;
            newpost.setid(newpostid);
            newpost.setuID(newpostuid);
            newpost.setContent(message);
            posts.add(newpost);
            return Integer.parseInt(newpostid);
        }
    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        String newpostuid = Integer.toString(finduid(handle));
        String parentid = Integer.toString(id);
        Post post = findPostFromID(parentid);
        if (newpostuid.equals("-1")) {
            // Will call HandleNotRecognisedException
            return 0;
        } else if (post.exists()) {
            // Will call PostIDNotRecognisedException
            return 0;
        } else {
            String newID = Integer.toString(post.getNextEID());
            int incrementEID = post.getNextEID() + 1;
            post.setNextEID(incrementEID);

            Endorsement newEnd = new Endorsement();
            newEnd.setid(newID);
            newEnd.setParentID(parentid);
            newEnd.setuID(newpostuid);
            post.addendorsement(newEnd);
            return Integer.parseInt(newID);
        }
    }

    @Override
    public String commentPost(String handle, String id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        // TODO Auto-generated method stub
        // should add comment to list within post object, as well as total post list.
        String newpostuid = Integer.toString(finduid(handle));
        Post post = findPostFromID(id);
        if (message.length() > 100) {
            return "0";
        } else if (newpostuid.equals("-1")) {
            return "0";
        } else if (post.exists()) {
            return "0";
        } else {
            String newCID = Integer.toString(post.getNextCID());
            int incrementCID = post.getNextCID() + 1;
            post.setNextCID(incrementCID);

            Comment newcomment = new Comment();
            newcomment.setParentID(id);
            newcomment.setid(newCID);
            newcomment.setContent(message);

            post.addcomment(newcomment);
            return newCID;
        }
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub
        String strID = Integer.toString(id);
        fromID("Post", strID, "DELETE");
        
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id)
            throws PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        return null;
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
            postNo += 1;
        }
        return postNo;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        // TODO Auto-generated method stub
        int endorsementNo = 0;

        for (Post post : posts) {
            if (post != null) {
                for (Endorsement e : post) {
                    if (e != null) {
                        endorsementNo += 1;
                    }
                }
            }
        }
        return endorsementNo;
    }

    public int countChildren(Post post) {
        int commentNo = 1;
        //changed this it now says if the post in question has comments
        if (post.getComments().size() != 0) {
            // then for each comment in that post's array of comments
            for (Comment c : post.getComments()) {
                if (c != null) {
                    //recursively add to the commentNo the number of comments to the current comment if that comment has no children this will just be 1
                    commentNo += countChildren(c);
                }
            }
        }
        return commentNo;
    }

    @Override
    public int getTotalCommentPosts() {
        // TODO Auto-generated method stub
        int commentNo = 0;

        for (Post post : posts) {
            if (post != null) {
                commentNo += countChildren(post);
            }
        }
        return commentNo;
    }

    @Override
    public int getMostEndorsedPost() {
        // TODO Auto-generated method stub
    }

    @Override
    public int getMostEndorsedAccount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void erasePlatform() {
        // TODO Auto-generated method stub

    }

    @Override
    public void savePlatform(String filename) throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub

    }

}
