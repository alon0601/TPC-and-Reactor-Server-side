package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class NotificationMessage implements Message{
    private short opcode = 9;
    private byte type;
    private String postingUser;
    private String content;

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
        byte[] zero = new byte[1];
        zero[0] = '\0';

        byte[] opCodeBytes = new byte[3];
        opCodeBytes[0] = (byte)((opcode >> 8) & 0xFF);
        opCodeBytes[1] = (byte)(opcode & 0xFF);
        opCodeBytes[2] = this.type; //adding the type

        byte[] postingUserBytes = this.postingUser.getBytes();
        byte[] contentBytes = content.getBytes();

        byte[] arrToPostUser = allBytes(opCodeBytes,postingUserBytes); //arr to the PostUser
        byte[] zero1 = allBytes(arrToPostUser,zero); //arr until the first zero
        byte[] arrToContent = allBytes(zero1,contentBytes); //arr until the Content

        return allBytes(arrToContent,zero);

    }

    @Override
    public void act(BidiMessagingProtocolImp myProtocol) {

    }

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
}
