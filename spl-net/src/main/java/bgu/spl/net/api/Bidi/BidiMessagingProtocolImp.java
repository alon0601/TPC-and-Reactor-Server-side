package bgu.spl.net.api.Bidi;

import bgu.spl.net.Datas.DataBase;
import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.Connections;

import java.util.Collection;

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

    }

    @Override
    public boolean shouldTerminate() {
        return this.shouldTerminate;
    }


}
