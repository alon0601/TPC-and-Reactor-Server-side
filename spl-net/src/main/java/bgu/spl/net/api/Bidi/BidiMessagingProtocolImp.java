package bgu.spl.net.api.Bidi;

import bgu.spl.net.Datas.DataBase;
import bgu.spl.net.Datas.User;
import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.Connections;
import bgu.spl.net.api.Bidi.Messages.*;
import sun.jvm.hotspot.types.JByteField;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BidiMessagingProtocolImp implements BidiMessagingProtocol {
    private boolean shouldTerminate = false;
    private DataBase dataBase;
    private int myId;
    private Connections connections;
    private String userName;

    public  BidiMessagingProtocolImp(DataBase data){
        this.dataBase = data;
    }

    @Override
    public void start(int connectionId, Connections connections) {
        this.connections = connections;
        this.myId = connectionId;
        userName = "";
    }

    @Override
    public void process(Object message) {
        ((Message)(message)).act(this);
    }

    public void register(short opcode, String userName, String password, String bday){

        Message ans;
        boolean work  = this.dataBase.register(userName,password,bday);
        if (work){
            ans = new Ack(opcode);
        }
        else{
            ans = new ErrorMessage(opcode);
        }
        this.connections.send(this.myId, ans);
    }

    public void logout(short opcode){
        Message ans;
        boolean work = this.dataBase.logOutRe(this.userName);
        if (work){
            ans = new Ack(opcode);
        }
        else{
            ans = new ErrorMessage(opcode);
        }

        this.connections.send(this.myId, ans);
    }

    public void LogStat(short opcode){
        while (!dataBase.connectedUsers().isEmpty()) { //if users getting loged all the time we will not get out of this loop!
            User user = dataBase.connectedUsers().poll();
            Message userDataAck = new AckUserInfo(user.getAge(), user.getNumOfFollowers(),user.getNumOfFollowing());
            connections.send(this.myId, userDataAck);
        }
    }

    public void post(short opcode, String content){

        if (dataBase.isRegister(userName) && dataBase.isLogged(userName)){
            User user = dataBase.getUser(userName);
            user.addPost(content);
            Message post = new NotificationMessage((byte)(1), userName, content);

            //sending to followers
            for (User follower: user.getFollowers()){
                if (follower.getLog()){
                    connections.send(follower.getConnectionId(),post);
                }
                else{
                    follower.addWaitingMsg(post);
                }
            }

            //sending to tagged
            Queue<String> tagged = tagged(content);
            for (String name : tagged){
                User tagUser = dataBase.getUser(name);
                if (tagUser != null && !user.getFollowers().contains(tagUser)){
                    if (tagUser.getLog()){
                        connections.send(tagUser.getConnectionId(),post);
                    }
                    else{
                        tagUser.addWaitingMsg(post);
                    }
                }
            }
        }
    }

    private Queue<String> tagged(String content){
        Queue<String> tag = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < content.length(); i++){
            if (content.indexOf(i) == '@'){
                String name = getTaggedName(content, i);
                tag.add(name);
                i = i + name.length();
            }
        }
        return tag;
    }

    private String getTaggedName(String content, int idx){
        String name = "";
        int i = idx + 1;
        while (i < content.length()){
            if (content.indexOf(i) == ' '){
                return name;
            }
            else{
                name = name + content.indexOf(i);
                i++;
            }
        }
        return name;
    }




    @Override
    public boolean shouldTerminate() {
        return this.shouldTerminate;
    }


}
