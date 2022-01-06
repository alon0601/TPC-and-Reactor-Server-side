package bgu.spl.net.impl;

import bgu.spl.net.api.Bidi.Connections;
import bgu.spl.net.srv.ConnectionHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionsImp implements Connections {
    Map<Integer,ConnectionHandler> connectionMap;


    public ConnectionsImp(){
        this.connectionMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean send(int connId, Object msg) {
        if(this.connectionMap.get(connId) == null)
            return false;
        this.connectionMap.get(connId).send(msg);
        return true;
    }

    @Override
    public void broadcast(Object msg) {
        for(Integer integer: this.connectionMap.keySet()){
            this.connectionMap.get(integer).send(msg);
        }
    }

    @Override
    public void disconnect(int connId) {
        this.connectionMap.remove(connId);
    }

    public void connect(int id, ConnectionHandler con){
        this.connectionMap.put(id,con);
    }

}
