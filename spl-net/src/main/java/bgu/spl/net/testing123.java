package bgu.spl.net;

import bgu.spl.net.api.Bidi.Messages.Ack;
import bgu.spl.net.api.Bidi.Messages.ErrorMessage;
import bgu.spl.net.api.Bidi.Messages.Message;


public class testing123 {
    public static void main(String[] args) {
        Message ack = new ErrorMessage((short)2);
        byte[] by = ack.serialize();
        for (byte b: by){
            System.out.println(b);
        }
    }

}
