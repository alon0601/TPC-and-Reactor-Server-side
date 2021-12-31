package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class PostMessages implements Message{
    private short opcode = 5;
    private String content;

    public PostMessages(String _content){
        this.content = _content;
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
        myProtocol.post(opcode, content);
    }
}
