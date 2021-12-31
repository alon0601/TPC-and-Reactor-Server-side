package bgu.spl.net.api.Bidi;

import bgu.spl.net.Datas.DataBase;
import bgu.spl.net.api.Bidi.Messages.Ack;
import bgu.spl.net.api.Bidi.Messages.Message;

public class BidiMessagingProtocolImp implements BidiMessagingProtocol {
    private boolean shouldTerminate = false;
    private DataBase dataBase;
    private int myId;
    private Connections connections;

    public  BidiMessagingProtocolImp(DataBase data){
        this.dataBase = data;
    }

    @Override
    public void start(int connectionId, Connections connections) {
        this.connections = connections;
        this.myId = connectionId;
    }

    @Override
    public void process(Object message) {
        Message msg = (Message) message;
        msg.act(this);
    }
    @Override
    public boolean shouldTerminate() {
        return this.shouldTerminate;
    }

    public void logIn(String userName,String password){
        boolean work = true;
        Integer opcode = new Integer(2);
        work = dataBase.logInRe(userName,password);
        if(work)
            connections.send(myId,new Ack(opcode.shortValue()));
        else
            connections.send(myId,new Error());
    }

    @Override
    public void follow(byte follow, String userName) {
        boolean work = true;
        Integer opcode = new Integer(2);
        work = dataBase.follow(follow,userName,"11");
        if(work)
            connections.send(myId,new Ack(opcode.shortValue()));
        else
            connections.send(myId,new Error());
    }

    @Override
    public void PM() {

    }

    @Override
    public void stat() {

    }

    @Override
    public void block() {

    }

}
