import java.io.File;
import java.util.ArrayList;

enum Command{
    LINKREQ("000"),FILELISTREQ("011"),SENDFILELIST("002"), FILEREQ("013"),FILESEND("004"),SENDEND("015"); //第二位 1 表示 服务器端
    String cmd;
    Command(String cmd){
        this.cmd=cmd;
    }

    public String getCmd() {
        return cmd;
    }
}

class Protocol {
    int fileCount=0;
    String []paths;
    //可能需要再加上每个文件的校验码
    String readCmd(String stream){
        String cmd=stream.substring(0,3);
        //System.out.println(cmd);
        if(cmd.equals(Command.FILELISTREQ.getCmd())){
            ArrayList<String> fileNameList;
            String path=stream.substring(3);
            File file=new File(path);
            File[] files=file.listFiles();
            String[] names=file.list();
            if(names==null){
                throw new NullPointerException("该文件夹下没有文件");
            }
            else{
                StringBuffer retString=new StringBuffer(Command.SENDFILELIST.getCmd());
                for(String name:names){
                    retString.append(path+name+"\n");
                }
                return retString.toString();
            }
        }
        if(cmd.equals(Command.SENDFILELIST.getCmd())){
            //可能需要比较文件
            String info=stream.substring(3);
            paths=info.split("\n");
            //++这里进行文件比较 留下需要更新的文件
            if(fileCount<paths.length){
                return Command.FILEREQ.getCmd()+paths[fileCount++];
            }
            else return Command.SENDEND.getCmd();
            //暂时不需要回应 发送完毕后 结束程序即可
            //可能需要加入服务器端进行校验的信息 也可能需要中间进行校验
        }
        if(cmd.equals(Command.FILEREQ.getCmd())){
            String path=stream.substring(3);
            String retString=Command.FILESEND.getCmd();
            //需要在retString后面加入文件内容
            return retString;
        }
        if(cmd.equals(Command.FILESEND.getCmd())){
            String fileContent=stream.substring(3);
            //处理文件内容
            if(fileCount<paths.length){
                return Command.FILEREQ.getCmd()+paths[fileCount++];
            }
            else return Command.SENDEND.getCmd();
        }
        return null; //此处没有读到任何指令 传输一定出现了问题
    }
}