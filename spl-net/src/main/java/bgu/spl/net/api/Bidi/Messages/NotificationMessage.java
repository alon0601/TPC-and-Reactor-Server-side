package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;

public class NotificationMessage implements Message{
    private short opcode = 9;
    private byte type;
    private String postingUser;
    private String content;

    NotificationMessage(byte _type,String _postingUser,String _content){
        this.type = _type;
        this.postingUser = _postingUser;
        this.content = _content;
    }

    @Override
    public short getOpcode() {
        return this.opcode;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    @Override
    public void act(BidiMessagingProtocol myProtocol) {

    }
}
