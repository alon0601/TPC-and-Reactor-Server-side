package bgu.spl.net.api.Bidi;

import bgu.spl.net.api.Bidi.Messages.*;
import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MessagingEncoderDecoderImpl implements MessageEncoderDecoder {

    private int size = 0;
    private int len = 0;
    private byte[] bytes = new byte[1 << 10];

    public MessagingEncoderDecoderImpl(){}
    @Override
    public Object decodeNextByte(byte nextByte) {
        if (nextByte == ';') {
            return popMessage();
        }
        size++;
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
        i = findNextZero(idx);
        while (i != -1 && idx <= size && i <= size){
            args.add(new String(bytes, idx, i - idx , StandardCharsets.UTF_8));
            idx = i + 1;
            i = findNextZero(idx);
        }
        for (int j = 0; j < args.size(); j++){
            System.out.println(args.get(j));
        }
        return args;
    }

    private Message popMessage() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        Message message = null;

        short opcode = (short)((bytes[0] & 0xff) << 8);
        opcode += (short)(bytes[1] & 0xff);
        System.out.println("making new message with opCode: " + opcode);
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
            String userName = new String(bytes, 3, size - 4, StandardCharsets.UTF_8);
            message = new FollowUnfollowMessage(bytes[2],userName);
        }

        if (opcode == 5){
            message = new PostMessages(args.get(0));
        }

        if (opcode == 6){
            message = new PmMessage(args.get(0), args.get(1), LocalDate.now().toString());
        }

        if (opcode == 7){
            message = new LogstatMessage();
        }

        if (opcode == 8){
            List<String> listUsers = new LinkedList<>();
            String users = args.get(0);
            String userName = "";

            for (int i = 0; i < users.length(); i++){ //make list of userNames for StatsRequestMessage
                if (users.charAt(i) == '|'){
                    listUsers.add(userName);
                    userName = "";
                }
                else{
                    userName = userName + users.charAt(i);
                }
            }

            message = new StatsRequestMessage(listUsers);
        }

        if (opcode == 12){
            message = new Block(args.get(0));
        }

        len = 0;
        size = 0;
        return message;
    }

    private int findNextZero(int idx){ //find the next zero from the idx index
        int i = idx;
        while (i <= len){
            if (bytes[i] == '\0'){
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
// Integer age = 3;
////        Integer numOfPosts = 5;
////        Integer numFollowing = 7;
////        Integer numFollowers = 1;
//////        Message msg = new AckUserInfo((short)9 , age.shortValue(),numFollowing.shortValue(),numFollowers.shortValue());
//////        byte[] ans = m.encode(msg);
////        String ans1 ="";
////        for(Byte b:ans) {
////            System.out.print(b);
////            ans1 = (String) m.decodeNextByte(b);
////        }
////        System.out.println(ans1);       MessagingEncoderDecoderImpl m = new MessagingEncoderDecoderImpl();
//
    }
}
