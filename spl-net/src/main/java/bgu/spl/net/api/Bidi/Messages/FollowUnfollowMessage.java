package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class FollowUnfollowMessage implements Message{

    private short opcode = 4;
    private byte follow;
    private String userName;

    public FollowUnfollowMessage(byte follow, String userName){
        this.follow = follow;
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
        myProtocol.follow(opcode, follow,userName);
    }
}
