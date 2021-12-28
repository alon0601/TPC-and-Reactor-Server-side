package bgu.spl.net.Datas;

import bgu.spl.net.Messages.Message;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class User {
    private String userName;
    private String password;
    private String birthDay;
    private boolean loggedIn = false;
    private Queue<User> following;
    private Queue<User> followers;
//    private Queue<String> outMsg;
    private Queue<String> PMMsg;
    private Queue<String> posts;
    private Queue<Message> waitingMessages;

    public User(String userName, String password, String birthDay){
        this.userName = userName;
        this.password = password;
        this.birthDay = birthDay;
//        this.outMsg = new ConcurrentLinkedQueue<String>();
        this.PMMsg = new ConcurrentLinkedQueue<>();
        this.posts = new ConcurrentLinkedQueue<>();
        this.following = new ConcurrentLinkedQueue<>();
        this.followers = new ConcurrentLinkedQueue<>();
        this.waitingMessages = new ConcurrentLinkedQueue<>();
    }

    public String getUserName() {
        return userName;
    }

    public boolean confirmPassword(String password) {
        return this.password == password;
    }

    public String getBirthDay() {
        return birthDay;
    }

//    public Queue<String> getOutMsg() {
//        return outMsg;
//    }

    public Queue<String> getPosts() {
        return posts;
    }

    public Queue<String> getPMMsg() {
        return PMMsg;
    }

    public void addPost(String post){
        this.posts.add(post);
    }

//    public void addOutMsg(String outMsg){
//        this.outMsg.add(outMsg);
//    }

    public void addPMMsg(String inMsg){
        this.PMMsg.add(inMsg);
    }

    public void logIn(){
        this.loggedIn = true;
    }

    public void logOut(){
        this.loggedIn = false;
    }

    public Queue<User> getFollowing() {
        return following;
    }

    public Queue<User> getFollowers() {
        return followers;
    }

    public boolean getLog(){
        return this.loggedIn;
    }

    public void addFollower(User follower){
        this.followers.add(follower);
    }

    public void addFollowing(User following){
        this.following.add(following);
    }

    public void addWaitingMsg(Message message){
        this.waitingMessages.add(message);
    }

    public void removeFollower(User follower){
        this.followers.remove(follower);
    }

    public  void removeFollowing(User following){
        this.following.remove(following);
    }

    public void removeWaitingMsg(Message message){
        this.waitingMessages.remove(message);
    }

    public boolean isFollowing(User user){return this.following.contains(user);}

    public boolean isFollowed(User user){return this.followers.contains(user);}
}
