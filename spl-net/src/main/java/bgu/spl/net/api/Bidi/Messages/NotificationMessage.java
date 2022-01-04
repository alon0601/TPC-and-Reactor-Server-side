package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

import java.nio.charset.StandardCharsets;

public class NotificationMessage implements Message{
    private short opcode = 9;
    private byte type;
    private String postingUser;
    private String content;

    protected byte[] allBytes(byte[] b1, byte[] b2){ //adding 2 different byte[] to a single byte[]
        byte[] allBytes = new byte[b1.length + b2.length];
        for (int i = 0; i < b1.length; i++){
            allBytes[i] = b1[i];
        }
        for (int i = 0; i < b2.length; i++){
            allBytes[i + b1.length] = b2[i];
        }
        return allBytes;
    }

    public NotificationMessage(byte _type,String _postingUser,String _content){
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
        byte[] bytes = new byte[3];
        bytes[0] = (byte)((opcode >> 8) & 0xFF);
        bytes[1] = (byte)(opcode & 0xFF);
        bytes[2] = this.type;
        byte[] content = new byte[this.content.length()];
        content = this.content.getBytes();
        byte[] user = new byte[this.postingUser.length()];
        user = this.postingUser.getBytes();
        byte[] reg = allBytes(bytes,user);
        return allBytes(reg,content);
    }

    @Override
    public void act(BidiMessagingProtocolImp myProtocol) {

    }
    public byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
}
