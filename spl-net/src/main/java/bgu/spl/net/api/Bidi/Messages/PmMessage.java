package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

import java.sql.Time;

public class PmMessage implements Message{

    private short opcode = 6;
    private String content;
    private String userName;
    private String time;

    public PmMessage(String userName, String content, String time){
        this.content = content;
        this.time = time;
        this.userName = userName;
    }
    @Override
    public short getOpcode() {
        return opcode;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    @Override
    public void act(BidiMessagingProtocolImp myProtocol) {
        myProtocol.PM(this.opcode,this.userName,this.content);
    }
}
