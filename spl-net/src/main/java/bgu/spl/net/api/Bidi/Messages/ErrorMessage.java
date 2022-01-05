package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class ErrorMessage implements Message{
    private short opcode = (short)11;
    private short msgOpcode;

    public ErrorMessage(short _msgOpcode){
        this.msgOpcode = _msgOpcode;
    }

    @Override
    public short getOpcode() {
        return this.opcode;
    }

    @Override
    public byte[] serialize() {
        System.out.println("sending error");
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((opcode >> 8) & 0xFF);
        bytes[1] = (byte)(opcode & 0xFF);
        bytes[2] = (byte)((msgOpcode >> 8) & 0xFF);
        bytes[3] = (byte)(msgOpcode & 0xFF);
        return bytes;
    }

    @Override
    public void act(BidiMessagingProtocolImp myProtocol) {

    }
}
