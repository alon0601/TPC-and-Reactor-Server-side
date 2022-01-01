package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class Block implements Message{

    private short opcode = (short)12;
    private String userName;

    public Block(String userName){
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
        myProtocol.block(this.opcode,this.userName);
    }
}
