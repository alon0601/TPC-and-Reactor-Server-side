package bgu.spl.net.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;

public interface Message {
    public short getOpcode();

    public void act(BidiMessagingProtocol protocol);
}
