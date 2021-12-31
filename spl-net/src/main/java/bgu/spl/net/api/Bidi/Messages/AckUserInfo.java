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
        //there must be a better way to do it
        byte[] bytes = super.serialize();
        byte[] dataBytes = new byte[8];
        bytes[0] = (byte)((age >> 8) & 0xFF);
        bytes[1] = (byte)(age & 0xFF);
        bytes[2] = (byte)((numPosts >> 8) & 0xFF);
        bytes[3] = (byte)(numPosts & 0xFF);
        bytes[4] = (byte)((numFollowers >> 8) & 0xFF);
        bytes[5] = (byte)(numFollowers & 0xFF);
        bytes[6] = (byte)((numFollowing >> 8) & 0xFF);
        bytes[7] = (byte)(numFollowing & 0xFF);
        return this.allBytes(bytes, dataBytes);
    }

    @Override
    public void act(BidiMessagingProtocol myProtocol) {

    }
}
