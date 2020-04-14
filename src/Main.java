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
        Protocol protocolTest=new Protocol(path);
        String s=protocolTest.readCmd("011");
        //System.out.println(s);
        Protocol ServerTest=new Protocol("/home");
        s=ServerTest.readCmd(s);
        //System.out.println(s);
        ArrayList<File> files;
        files=Protocol.readDir(new File(path));
        files=Protocol.deleteDir(files);
        FileOutputStream fos=new FileOutputStream("1.zip");
        FileZipUtil.toZip(files,fos,path);
        fos.close();
        FileInputStream fis=new FileInputStream("1.zip");
        FileZipUtil.unZip(outPath,fis);
    }
}
