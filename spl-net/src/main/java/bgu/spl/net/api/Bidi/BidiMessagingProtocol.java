package bgu.spl.net.api.Bidi;

public interface BidiMessagingProtocol<T> {
    void start(int connectionId, Connections connections);

    void process(T message);

    boolean shouldTerminate();

    void logIn(String userName,String password);

    void follow(byte follow, String userName);

    void PM();

    void stat();

    void block();
}
