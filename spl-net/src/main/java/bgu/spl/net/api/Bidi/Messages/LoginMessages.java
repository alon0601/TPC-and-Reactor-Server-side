package bgu.spl.net.api.Bidi.Messages;


import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class LoginMessages implements Message{

    private short opcode = 2;
    private String userName;
    private String password;
    private byte capcha;

    public LoginMessages(String userName, String password, byte capcha){
        this.userName = userName;
        this.password = password;
        this.capcha = capcha;
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
        myProtocol.logIn(opcode, userName,password ,capcha);
    }
}
