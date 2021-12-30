package bgu.spl.net.api.Bidi.Messages;


import bgu.spl.net.api.Bidi.BidiMessagingProtocol;

public class LoginMessages implements Message{

    private short opcode = 2;
    private String userName;
    private String password;

    public LoginMessages(String userName, String password){
        this.userName = userName;
        this.password = password;
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
    public void act(BidiMessagingProtocol myProtocol) {
    }
}
