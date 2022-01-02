package bgu.spl.net;

import bgu.spl.net.api.Bidi.Messages.Ack;
import bgu.spl.net.api.Bidi.Messages.ErrorMessage;
import bgu.spl.net.api.Bidi.Messages.Message;


public class testing123 {
    public static void main(String[] args) {
        Message ack = new ErrorMessage((short)2);
        byte[] by = ack.serialize();
        short result = (short)((by[0] & 0xff) << 8);
        result += (short)(by[1] & 0xff);
        System.out.println(result);

    }

}
