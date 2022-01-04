package bgu.spl.net.api.Bidi.Messages;

public class ACKFollow extends Ack{

    private String username;

    public ACKFollow(short otherOpcode,String username) {
        super(otherOpcode);
        this.username = username;
    }

    @Override
    public byte[] serialize() {
        byte[] zero = new byte[1];
        zero[0] = '\0';
        byte[] bytes = super.serialize();
        byte[] userNameBytes = this.username.getBytes();
        byte[] userWithZero = allBytes(userNameBytes,zero);
        return this.allBytes(bytes, userWithZero);
    }
}
