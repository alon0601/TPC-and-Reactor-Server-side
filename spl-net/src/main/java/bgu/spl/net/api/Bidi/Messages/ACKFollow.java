package bgu.spl.net.api.Bidi.Messages;

public class ACKFollow extends Ack{

    private String username;

    public ACKFollow(short otherOpcode,String username) {
        super(otherOpcode);
        this.username = username;
    }

    @Override
    public byte[] serialize() {
        byte[] bytes = super.serialize();
        byte[] userNameBytes = this.username.getBytes();
        return this.allBytes(bytes, userNameBytes);
    }
}
