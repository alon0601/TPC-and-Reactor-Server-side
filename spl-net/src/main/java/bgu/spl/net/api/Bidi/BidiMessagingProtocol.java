package bgu.spl.net.api.Bidi;

public interface BidiMessagingProtocol<T> {
    void start(int connectionId, Connections connections);

    void process(T message);

    boolean shouldTerminate();
}
