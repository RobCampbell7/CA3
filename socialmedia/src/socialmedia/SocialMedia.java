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

	public String finduid (String handle) {
		String id = "-1";
		for (Account a: accounts) {
			if (a.handle().equals(handle)) {
				id = a.uID();
				break;
			}
		}
		return id;
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
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		//maybe add error msg and repeat later if needed
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String newpostuid = finduid(handle);
		Post post = findPostFromID(id);
		if (message.length() > 100) {
			return "0";
		} else if (newpostuid.equals("-1")) {
			return "0";
		} else if (post.isNull()){
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
			Comment newcomment = new Comment();

			newcomment.setparentID(id);
			newcomment.setid(newCID);
			newcomment.setContent(message);

			post.addcomment(newcomment);

			post.nextCID += 1;
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
		return 0;
	}

	@Override
	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCommentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedPost() {
		// TODO Auto-generated method stub
		return 0;
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
