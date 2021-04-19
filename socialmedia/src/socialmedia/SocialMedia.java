package socialmedia;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

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

	public int finduid (String handle) {
		int id = -1;
		for (Account a: accounts) {
			if (a != null && a.getHandle().equals(handle)) {
				id = a.getUID();
				break;
			}
		}
		return id;
	}
	public Account findAccountFromID(int id){
		Account foundAccount = null;
		for (Account user : accounts){
			if (user != null && user.getUID()){
				foundAccount = user;
				break;
			}
		}
		return foundAccount;
	}
	public boolean uniqueHandle(String handle){
		boolean unique = true;
		for (Account user : accounts){
			if (user.getHandle() == handle){
				unique = false;
				break;
			}
		}
		return unique;
	}
	public Post findPostFromID(String id){
		Post foundPost;
		String[] idArr = id.split("-");
		Post[] postArr = posts.toArray();

		for (String s : idArr){
			for (Post post : postArr){
				if (post.id().equals(s)){
					if (s.equals(idArr[-1])){
						foundPost = post;
						postArr = null;
					}
				}else{
					postArr = post.comments.toArray();
				}
			}
		}
		return foundPost;
	}
	@Override
	public String createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		if (handle.length() == 0 || handle.length() > 30 || handle.contains(" ")){
			// Will call InvalidHandleException
			return "0";
		}else if (!uniqueHandle(handle)){
			// Will call IllegalHandleException
			return "0";
		}else{
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
		if (handle.length() == 0 || handle.length() > 30 || handle.contains(" ")){
			// Will call InvalidHandleException
			return "0";
		}else if (!uniqueHandle(handle)){
			// Will call IllegalHandleException
			return "0";
		}else{
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
		for (Account user : accounts){
			if (user != null && user.getUID() == id){
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
		if (uid == -1){
			// Raises HandleNotRecognisedException
		}else{
			removeAccount(uid);
		}
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		int uid = finduid(oldHandle);
		if (uid == -1){
			// Will call HandleNotRecognisedException
		}else if (newHandle.length() == 0 || newHandle.length() > 30 || newHandle.contains(" ")){
			// Will call InvalidHandleException
		}else if (!uniqueHandle(newHandle)){
			// Will call IllegalHandleException
		}else{
			Account userToAlter = findAccountFromID(uid);
			userToAlter.setHandle(newHandle);
		}
	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		int uid = finduid(handle);
		if (uid == -1){
		}else{
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
	public String createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		//maybe add error msg and repeat later if needed
		String newpostuid = finduid(handle);
		if (message.length() > 100) {
			return "0";
		} else if (newpostuid.equals("-1")) {
			return "0";
		} else {
			Post newpost = new Post();
			String newpostpid = Integer.toString(nextpid);
			nextpid += 1;
			newpost.setpID(newpostpid);
			newpost.setuID(newpostuid);
			newpost.setContent(message);
			posts.add(newpost);
			return newpostpid;
		}
	}

	@Override
	public String endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		int newpostuid = finduid(handle);
		Post post = findPostFromID(id);
		if (newpostuid == -1) {
			// Will call HandleNotRecognisedException
			return "0";
		} else if (post.isNull()){
			// Will call PostIDNotRecognisedException
			return "0";
		} else {
			String newID = Integer.toString(post.nextEID);
			post.nextEID += 1;
			
			Endorsement newEnd = new Endorsement();
			newEnd.setID(newID);
			newEnd.setparentID(id);
			newEnd.setuID(newpostuid);
			post.addendorsement(newEnd);
		}
	}

	@Override
	public String commentPost(String handle, String id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// TODO Auto-generated method stub
		// should add comment to list within post object, as well as total post list.
		String newpostuid = finduid(handle);
		Post post = findPostFromID(id);
		if (message.length() > 100) {
			return "0";
		} else if (newpostuid.equals("-1")) {
			return "0";
		} else if (post.isNull()){
			return "0";
		} else {
			String newCID = Integer.toString(post.nextCID);
			post.nextCID += 1;

			Comment newcomment = new Comment();
			newcomment.setparentID(id);
			newcomment.setid(newCID);
			newcomment.setContent(message);

			post.addcomment(newcomment);
			return newpostpid;
		}
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub

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
		for (Account user : accounts){
			if (user != null){
				accountNo += 1;
			}
		}
		return accountNo;
	}

	@Override
	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		int postNo = 0;
		for (Post post : posts){
			postNo += 1;
		}
		return postNo;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		int endorsementNo = 0;
		
		for (Post post : posts){
			if (post != null){
				for (Endorsement e : post){
					if (e != null){
						endorsementNo += 1;
					}
				}
			}
		}
		return endorsementNo;
	}

	public countChildren(Post post){
		int commentNo = 1;
		if (c.comments.length() != 0){
			for (Comment c : post.comments){
				if (c != null){
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

		for (Post post : posts){
			if (post != null){
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
