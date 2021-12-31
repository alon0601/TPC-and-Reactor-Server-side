package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class LogstatMessage implements Message{
    private short opcode = 7;

    public LogstatMessage(){
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
    public void act(BidiMessagingProtocolImp myProtocol) {
        myProtocol.logStat(opcode);
    }

}
