package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class Ack implements Message{

    short opcode = (short)10;
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
        System.out.println("sending ack");
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((opcode >> 8) & 0xFF);
        bytes[1] = (byte)(opcode & 0xFF);
        bytes[2] = (byte)((otherOpcode >> 8) & 0xFF);
        bytes[3] = (byte)(otherOpcode & 0xFF);
        return bytes;
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
