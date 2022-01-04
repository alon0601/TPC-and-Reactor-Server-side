package bgu.spl.net.api.Bidi;

import bgu.spl.net.Datas.DataBase;
import bgu.spl.net.srv.Server;

public class StartServer {
    public static void main(String[] args) {
        DataBase data = new DataBase();
        Server server = Server.threadPerClient(7777, () -> new BidiMessagingProtocolImp(data), ()-> new MessagingEncoderDecoderImpl());
        server.serve();
    }
}
