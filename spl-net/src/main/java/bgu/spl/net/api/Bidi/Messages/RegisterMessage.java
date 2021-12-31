package bgu.spl.net.api.Bidi.Messages;

import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;

public class RegisterMessage implements Message {

    private short opcode = 1;
    private String username;
    private String password;
    private String bday;

    public RegisterMessage(String _userName, String _password, String _bday){
        this.username = _userName;
        this.password = _password;
        this.bday = _bday;
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
        myProtocol.register(opcode, username, password, bday);
    }

}
