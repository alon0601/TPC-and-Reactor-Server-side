package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

import java.util.List;

public class StatsRequestMessage implements Message{

    private short opcode = 8;
    private List<String> userNames;

    public StatsRequestMessage(List<String> userNames){
        this.userNames = userNames;
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
        myProtocol.stat(this.opcode,this.userNames);
    }
}
