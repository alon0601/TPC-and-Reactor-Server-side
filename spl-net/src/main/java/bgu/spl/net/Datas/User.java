package bgu.spl.net.Datas;

import bgu.spl.net.api.Bidi.Messages.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class User {
    private String userName;
    private String password;
    private String birthDay;
    private boolean loggedIn = false;
    private Queue<User> blocking;
    private Queue<User> following;
    private Queue<User> followers;
//    private Queue<String> outMsg;
    private Queue<String> PMMsg;
    private Queue<String> posts;
    private Queue<Message> waitingMessages;
    private int connectionId;


    public User(String userName, String password, String birthDay){
        this.userName = userName;
        this.password = password;
        this.birthDay = birthDay;
        this.blocking = new ConcurrentLinkedQueue<>();
        this.PMMsg = new ConcurrentLinkedQueue<>();
        this.posts = new ConcurrentLinkedQueue<>();
        this.following = new ConcurrentLinkedQueue<>();
        this.followers = new ConcurrentLinkedQueue<>();
        this.waitingMessages = new ConcurrentLinkedQueue<>();
    }
    public Queue<Message> getWaitingMessages() {
        return waitingMessages;
    }
    public String getUserName() {
        return userName;
    }

    public boolean confirmPassword(String password) {
        return this.password.equals(password);
    }

    public String getBirthDay() {
        return birthDay;
    }


    public Queue<String> getPosts() {
        return posts;
    }

    public Queue<String> getPMMsg() {
        return PMMsg;
    }

    public void addPost(String post){
        this.posts.add(post);
    }

    public void addBlock(User blocked){
        this.blocking.add(blocked);
    }

    public boolean isBlocked(User block){
        return this.blocking.contains(block);
    }

    public void block(User block){
        this.removeFollowing(block);
        this.removeFollower(block);
    }

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

    public int getConnectionId(){
        return this.connectionId;
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
        if (this.followers.contains(follower))
            this.followers.remove(follower);
    }

    public  void removeFollowing(User following){
        if (this.following.contains(following))
            this.following.remove(following);
    }

    public void removeWaitingMsg(Message message){
        this.waitingMessages.remove(message);
    }

    public boolean isFollowing(User user){return this.following.contains(user);}

    public boolean isFollowed(User user){return this.followers.contains(user);}

    public short getAge() {
        int d = Integer.parseInt(this.birthDay.substring(0,2));
        int m = Integer.parseInt(this.birthDay.substring(3,5));
        int y = Integer.parseInt(this.birthDay.substring(6));
        LocalDate birthDate = LocalDate.of(y, m, d);
        int actual = calculateAge(birthDate, LocalDate.now());
        return (short) actual;
    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
    public short getNumOfPosts() {return (short)(this.posts.size());}
    public short getNumOfFollowers(){
        return (short)(this.followers.size());
    }

    public short getNumOfFollowing(){
        return (short)this.following.size();
    }

    public void setConnectionId(int id){
        this.connectionId = id;
    }
}
