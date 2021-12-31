package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;

public class AckUserInfo extends Ack{

    short age;
    short numPosts;
    short numFollowers;
    short numFollowing;

    public AckUserInfo(short age, short numFollowers ,short numFollowing) {
        super((short)(7));
        this.age = age;
        this.numFollowers = numFollowers;
        this.numFollowing = numFollowing;
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
