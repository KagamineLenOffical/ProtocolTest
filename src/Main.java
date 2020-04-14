import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        String path="/home/yuki/Code/hello world";
        String outPath="/home/yuki/Code/hello world2";
        FileOutputStream fos=new FileOutputStream("1.zip");
        Protocol protocolTest=new Protocol(path,fos),protocolTest2=new Protocol(outPath);

        String s=protocolTest.readCmd("011");
        //System.out.println(s);
        s=protocolTest2.readCmd(s);
        //System.out.println(s);
        System.out.println(s);
        s=protocolTest.readCmd(s);
        FileInputStream fis=new FileInputStream("1.zip");
        protocolTest2.setIs(fis);
        System.out.println(s);
        s=protocolTest2.readCmd(s);
        System.out.println(s);
        fos.close();


    }
}
