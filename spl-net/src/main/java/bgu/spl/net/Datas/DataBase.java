package bgu.spl.net.Datas;

import java.util.*;
import java.util.concurrent.*;

public class DataBase {
    private Map<String,User> users;
    private BlockingDeque<User> loggedInUsers;
    private Queue<String> filteredWords;
    private static DataBase dataBase = null;

    public static DataBase getInstance() {
        if(dataBase == null){
            dataBase = new DataBase();
        }
        return dataBase;
    }

    private DataBase(){
        users = new ConcurrentHashMap<>();
        loggedInUsers = new LinkedBlockingDeque<>();
        filteredWords = new ConcurrentLinkedQueue<>();
        addFilteredWords("FUCK");
        addFilteredWords("MOM");
    }


    public User getUser(String userName){
        return users.get(userName);
    }

    public boolean register(String userName,String password,String birthday){
        if(users.get(userName) != null)
            return false;
        User user = new User(userName,password,birthday);
        users.putIfAbsent(userName,user);
        return true;
    }

    public boolean logInRe(String userName,String password){
        User user = users.get(userName);
        if(user == null || user.getLog() || !user.confirmPassword(password))
            return false;
        user.logIn();
        loggedInUsers.add(user);
        return true;
    }

    public boolean logOutRe(String userName){
        User user = users.get(userName);
        synchronized (user) {
            if (user == null || !user.getLog())
                return false;
            user.logOut();
            loggedInUsers.remove(user);
            return true;
        }
    }

    public boolean follow(byte follow,String userMe,String otherUser){
        User meUser = users.get(userMe);
        User userOther = users.get(otherUser);
        if(meUser == null || userOther == null || !meUser.getLog())
            return false;
        if(follow - '0' == 0){
            if(meUser.isFollowing(userOther) || meUser.isBlocked(userOther) || userOther.isBlocked(meUser)){ //check if blocked him
                return false;
            }
            meUser.addFollowing(userOther);
            userOther.addFollower(meUser);
        }
        else{
            if(!meUser.isFollowing(userOther)){
                return false;
            }
            meUser.removeFollowing(userOther);
            userOther.removeFollower(meUser);
        }
        return true;
    }

    public boolean isRegister(String userName){
        return (this.users.get(userName) != null);
    }
    public boolean isLogged(String username){
        return this.users.get(username).getLog();
    }

    public boolean addPost(String userName,String content){
        User user = this.users.get(userName);
        if(user == null)
            return false;
        user.addPost(content);
        return true;
    }

    public boolean block(String userName,String blockedUser){
        User user = this.users.get(userName);
        User blockUser = this.users.get(blockedUser);
        if(user == null || blockUser == null)
            return false;
        user.addBlock(blockUser);
        user.block(blockUser);
        blockUser.block(user);
        return true;
    }

    public boolean addPM(String sendName, String receiveName, String content){
        User user = this.users.get(receiveName);
        User send = this.users.get(sendName);
        if(user == null || send == null || !send.isFollowing(user))
            return false;
        user.addPMMsg(content);
        return true;
    }

    public boolean isAllExist(List<String> userNames,String myUserName){
        for(String s:userNames){
            if (!this.users.containsKey(s) || this.users.get(s).isBlocked(this.users.get(myUserName)) || this.users.get(myUserName).isBlocked(this.users.get(s)))
                return false;
        }
        return true;
    }

    public BlockingDeque<User> connectedUsers(){
        return loggedInUsers;
    }

    public Queue<String> getFilteredWords(){
        return this.filteredWords;
    }

    public void addFilteredWords(String filter){
        this.filteredWords.add(filter);
    }
}
