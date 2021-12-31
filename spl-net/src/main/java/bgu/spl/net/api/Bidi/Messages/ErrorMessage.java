package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;

public class ErrorMessage implements Message{
    private short opcode = 11;
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
        return new byte[0];
    }

    @Override
    public void act(BidiMessagingProtocol myProtocol) {

    }
}
