import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileZipUtil {
    public static void toZip(ArrayList<File> srcFiles, OutputStream out,String inPath)throws RuntimeException{
        ZipOutputStream zos=null;
        try{
            zos=new ZipOutputStream(out);
            for(File srcFile:srcFiles){
                byte[] buff=new byte[8192];
                String childPath=srcFile.getAbsolutePath().substring(inPath.length());
                zos.putNextEntry(new ZipEntry(childPath));
                int len;
                FileInputStream in=new FileInputStream(srcFile);
                while((len=in.read(buff))!=-1){
                    zos.write(buff,0,len);
                }
                zos.closeEntry();
                in.close();
            }
        }
        catch(Exception ex){
            throw new RuntimeException("zip error",ex);
        }
        finally {
            if(zos!=null){
                try{
                    zos.close();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void createDir(File file){
        File parentFile=file.getParentFile();
        if(parentFile!=null){
            parentFile.mkdirs();
        }
    }
    public static void unZip(String outPath, InputStream is) throws IOException{
        ZipInputStream zis=new ZipInputStream(is);
        ZipEntry nextEntry=zis.getNextEntry();
        while(nextEntry!=null){
            String name=nextEntry.getName();
            System.out.println(name);
            File file=new File(outPath+name);
            createDir(file);
            FileOutputStream fos=new FileOutputStream(file);
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            int len;
            byte []buff=new byte[8192];
            while((len=zis.read(buff))!=-1){
                bos.write(buff,0,len);
            }
            bos.close();
            fos.close();
            zis.closeEntry();
            nextEntry=zis.getNextEntry();
        }
    }
}
