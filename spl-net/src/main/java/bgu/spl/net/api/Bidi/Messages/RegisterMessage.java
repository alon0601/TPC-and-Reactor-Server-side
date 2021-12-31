package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;

public class RegisterMessage implements Message {

    private short opcode = 1;
    private String username;
    private String password;

    public RegisterMessage(String _userName, String _password, String _bday){
        this.username = _userName;
        this.password = _password;
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
    public void act(BidiMessagingProtocol bidiMessagingProtocol) {
    }

}
