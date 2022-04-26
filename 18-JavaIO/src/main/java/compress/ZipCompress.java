package compress;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @program: JavaDemos
 * @description:通过zip压缩目录，且压缩后读取
 * @author: Prvyx
 * @created: 2022/04/17 18:02
 */

public class ZipCompress {
    private File sourceDir;
    private File targetFile;

    public ZipCompress(File sourceDir) {
        this.sourceDir = sourceDir;
        targetFile=new File(sourceDir.getParent()+File.separator+sourceDir.getName()+".zip");
    }

    public ZipCompress(File sourceDir, File targetFile) {
        this.sourceDir = sourceDir;
        this.targetFile = targetFile;
    }

    public ZipCompress(String sourceDir,String targetFile){
        this.sourceDir=new File(sourceDir);
        this.targetFile=new File(targetFile);
    }

    public File getTargetFile() {
        return targetFile;
    }

    @Override
    public String toString() {
        return "ZipCompress{" +
                "sourceDir=" + sourceDir +
                ", targetFile=" + targetFile +
                '}';
    }

    public void zip()throws IOException {
        ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(targetFile));
        // eg：在test.zip创建Prvyx空目录
//        zos.putNextEntry(new ZipEntry(sourceDir.getName()));
        // 上一行代码根本不需要，zos,putNextEntry()自己会根据参数创建目录。创建毛的空目录
        recurseZip(sourceDir,zos,sourceDir.getName());

        // 关键一行，必须把zos关掉；否则打不开zip文件
        zos.close();
    }
    /**
     * @param source: 需要压缩的目录 eg：C:\Users\呵\Desktop\Prvyx
	 * @param zos: 指定的压缩输出位置的ZipOutputStream流 eg：C:\Users\呵\Desktop\test.zip的ZipOutputStream流
	 * @param targetCurDir: 记录 对于targetFile的相对路径
     * @return void
     * @author Tobe
     * @description TODO
     * @date 2022/4/17 18:15
     */
    private void recurseZip(File source,ZipOutputStream zos,String targetCurDir)throws IOException{
        if(source==null)
            return;
        // 遍历source的所有文件和目录
        for(File file:source.listFiles()){
            if(file.isDirectory()){//若file是目录
                // 向下递归
                recurseZip(file,zos,targetCurDir+File.separator+file.getName());
            }else {//若file是文件
                // 读取file，压缩后输出至 相对于target的targetCurDir路径下
                // 在zip文件中创建一个输出的目标文件
                zos.putNextEntry(new ZipEntry(targetCurDir+File.separator+file.getName()));

                BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream bos=new BufferedOutputStream(zos);
                byte[] bytes=new byte[1024*4];
                int len;
                while ((len=bis.read(bytes))!=-1){
                    bos.write(bytes,0,len);
                }
                // 关闭bis；
                // bos不能关：bos.close()会使zos关闭
                bis.close();
                bos.flush();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ZipCompress zipCompress=new ZipCompress("C:\\Users\\呵\\Desktop\\hello","C:\\Users\\呵\\Desktop\\test.zip");
            zipCompress.zip();

            // 通过ZipInputStream读取
            System.out.println("通过ZipInputStream读取zip文件");
            ZipInputStream zipInputStream=new ZipInputStream(new FileInputStream(zipCompress.getTargetFile()));
            ZipEntry zipEntry;
            while ((zipEntry= zipInputStream.getNextEntry())!=null){
                System.out.println("File: "+zipEntry.getName());
            }
            System.out.println("------");
            System.out.println("通过ZipFile读取zip文件");
            // 通过ZipFile读取
            ZipFile zipFile=new ZipFile(zipCompress.getTargetFile());
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()){
                System.out.println("File: "+(ZipEntry) entries.nextElement());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
