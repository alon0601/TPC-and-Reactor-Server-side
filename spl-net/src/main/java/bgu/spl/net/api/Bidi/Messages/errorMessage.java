package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;

public class errorMessage implements Message{
    private short opcode = 11;
    private short msgOpcode;

    errorMessage(short _msgOpcode){
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
