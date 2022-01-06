package bgu.spl.net.impl.BGSServer;

import bgu.spl.net.Datas.DataBase;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;
import bgu.spl.net.api.Bidi.MessagingEncoderDecoderImpl;
import bgu.spl.net.srv.Server;

public class TPCMain {
    public static void main(String[] args) {
        Server server = Server.threadPerClient(7777, () -> new BidiMessagingProtocolImp(DataBase.getInstance()), ()-> new MessagingEncoderDecoderImpl());
        server.serve();
    }
}
