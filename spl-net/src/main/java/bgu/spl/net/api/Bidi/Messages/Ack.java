package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;

public class Ack implements Message{

    short opcode = 10;
    short otherOpcode;

    public Ack(short otherOpcode){
        this.otherOpcode = otherOpcode;
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
    public void act(BidiMessagingProtocol myProtocol) {

    }
}
