public class Main {

    public static void main(String[] args) {
        Protocol protocolTest=new Protocol();
        String s=protocolTest.readCmd("011/home/yuki/Code/");
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        System.out.println(s);
    }
}
