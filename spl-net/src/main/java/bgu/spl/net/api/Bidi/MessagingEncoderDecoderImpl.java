package bgu.spl.net.api.Bidi;

import bgu.spl.net.api.Bidi.Messages.*;
import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MessagingEncoderDecoderImpl implements MessageEncoderDecoder {

    private int len = 0;
    private byte[] bytes = new byte[1 << 10];;

    public MessagingEncoderDecoderImpl(){}
    @Override
    public Object decodeNextByte(byte nextByte) {
        if (nextByte == '\n') {
            return popMessage();
        }

        pushByte(nextByte);
        return null; //not a line yet
    }

    @Override
    public byte[] encode(Object message) {
        return ((Message)(message)).serialize();
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    private List<String> makeArgs(){ //create args out of bytes
        List<String> args = new LinkedList<>();
        int idx = 2;
        int i = 0;
        while (i != -1 && idx < len && i < len){
            i = findNextZero(idx);
            args.add(new String(bytes, idx, i, StandardCharsets.UTF_8));
            idx = i + 1;
        }
        return args;
    }

    private Message popMessage() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        Message message = null;

        short opcode = (short)((bytes[0] & 0xff) << 8);
        opcode += (short)(bytes[1] & 0xff);
        List<String> args = makeArgs();

        if(opcode == 1){
            message = new RegisterMessage(args.get(0),args.get(1),args.get(2));
        }

        if (opcode == 2){
            message = new LoginMessages(args.get(0),args.get(1),bytes[len-1]);
        }

        if (opcode == 3){
            message = new LogoutMessage();
        }

        if (opcode == 4){
            String userName = new String(bytes, 3, len, StandardCharsets.UTF_8);
            message = new FollowUnfollowMessage(bytes[2],userName);
        }

        if (opcode == 5){
            message = new PostMessages(args.get(0));
        }

        if (opcode == 6){
            message = new PmMessage(args.get(0), args.get(1), args.get(2));
        }

        if (opcode == 7){
            message = new LogstatMessage();
        }

        if (opcode == 8){
            List<String> listUsers = new LinkedList<>();
            String users = args.get(0);
            String userName = "";

            for (int i = 0; i < users.length(); i++){ //make list of userNames for StatsRequestMessage
                if (users.indexOf(i) == '|'){
                    listUsers.add(userName);
                    userName = "";
                    i++;
                }
                else{
                    userName = userName + users.indexOf(i);
                }
            }

            message = new StatsRequestMessage(listUsers);
        }

        len = 0;
        return message;
    }

    private int findNextZero(int idx){ //find the next zero from the idx index
        int i = idx;
        while (i < len){
            if (bytes[i] == '\0'){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MessagingEncoderDecoderImpl m = new MessagingEncoderDecoderImpl();
        Integer age = 3;
        Integer numOfPosts = 5;
        Integer numFollowing = 7;
        Integer numFollowers = 1;
        Message msg = new AckUserInfo((short)9 , age.shortValue(),numFollowing.shortValue(),numFollowers.shortValue());
        byte[] ans = m.encode(msg);
        String ans1 ="";
        for(Byte b:ans) {
            System.out.print(b);
            ans1 = (String) m.decodeNextByte(b);
        }
        System.out.println(ans1);
    }
}
