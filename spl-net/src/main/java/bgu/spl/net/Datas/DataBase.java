package bgu.spl.net.Datas;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataBase {
    private Map<String,User> users;
    private Queue<User> loggedInUsers;

    public DataBase(){
        users = new ConcurrentHashMap<>();
        loggedInUsers = new ConcurrentLinkedQueue<>();
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
        if(user == null || user.getLog())
            return false;
        user.logIn();
        loggedInUsers.add(user);
        return true;
    }

    public boolean logOutRe(String userName){
        User user = users.get(userName);
        if(user == null || !user.getLog())
            return false;
        user.logOut();
        loggedInUsers.remove(userName);
        return true;
    }

    public boolean follow(boolean follow,String userMe,String otherUser){
        User meUser = users.get(userMe);
        User userOther = users.get(otherUser);
        if(meUser == null || userOther == null || !meUser.getLog())
            return false;
        if(follow){
            if(meUser.isFollowing(userOther)){
                return false;
            }
            meUser.addFollowing(userOther);
        }
        else{
            if(!meUser.isFollowing(userOther)){
                return false;
            }
            meUser.removeFollowing(userOther);
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

    public boolean addPM(String userName,String content){
        User user = this.users.get(userName);
        if(user == null)
            return false;
        user.addPMMsg(content);
        return true;
    }

    public Queue<User> connectedUsers(){
        return loggedInUsers;
    }

}
