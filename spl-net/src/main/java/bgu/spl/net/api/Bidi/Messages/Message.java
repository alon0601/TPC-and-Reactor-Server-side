package bgu.spl.net.api.Bidi.Messages;
import bgu.spl.net.api.Bidi.BidiMessagingProtocol;
import bgu.spl.net.api.Bidi.BidiMessagingProtocolImp;
import bgu.spl.net.impl.rci.Command;
import bgu.spl.net.Datas.DataBase;
import java.util.List;

public interface Message{


    short getOpcode();

    byte[] serialize();

    void act(BidiMessagingProtocolImp myProtocol);


}
