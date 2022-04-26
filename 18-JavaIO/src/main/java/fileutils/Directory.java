package fileutils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: JavaDemos
 * @description:目录实用工具（local操作、递归操作）
 * @author: Prvyx
 * @created: 2022/04/15 18:22
 */

public class Directory {
    /**
     * @param dir：目录
     * @param regex：正则表达式
     * @return：目录中局部的（文件名与regex匹配）的文件对象数组
     */
    public static File[]
    local(File dir,String regex){
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(regex);
            }
        });
    }
    public static File[]
    local(String path,String regex){
        return local(new File(path),regex);
    }

    //----------------------------------------------------------------------

    public static class FileTreeInfo implements Iterable<File>{
        // FileTreeInfo自带
        private List<File> files=new ArrayList<>();//存放目录下的所有文件
        private List<File> dirs=new ArrayList<>();//存放目录下的所有目录

        public List<File> getFiles() {
            return files;
        }

        public List<File> getDirs() {
            return dirs;
        }

        public void addFile(File file) {
            this.files.add(file);
        }

        public void addDir(File dir) {
            this.dirs.add(dir);
        }

        // 添加 其它的文件树信息 到 此树
        public void addAll(FileTreeInfo otherFileTreeInfo){
            files.addAll(otherFileTreeInfo.files);
            dirs.addAll(otherFileTreeInfo.dirs);
        }

        /**
         *
         * @return：返回所有files
         */
        @Override
        public String toString() {
            return "FileTreeInfo{" +
                    "files=" + files +
                    '}';
        }

        // 重写
        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }
    }

    /**
     * 在start目录中walk，递归遍历
     * @param start:起始目录
     * @param regex：正则表达式
     * @return 在start目录中 与regex匹配的 文件信息树
     */
    public static FileTreeInfo walk(File start,String regex){
        return recurseDir(start,regex);
    }
    public static FileTreeInfo walk(String start,String regex){
        return walk(new File(start),regex);
    }
    public static FileTreeInfo walk(File start){
        return walk(start,".*");
    }
    public static FileTreeInfo walk(String start){
        return walk(new File(start));
    }

    /**
     *
     * @param startDir：开始的目录
     * @param regex：regex与文件名匹配
     * @return：文件信息树
     */
    private static FileTreeInfo
    recurseDir(File startDir,String regex){
        FileTreeInfo rs=new FileTreeInfo();
        if(startDir==null){
            return null;
        }
        for(File item:startDir.listFiles()){
            System.out.println("正在读取 "+item.getName()+" 下的文件名信息");
            if(item.isDirectory()){
                rs.addDir(item);
                rs.addAll(recurseDir(item,regex));
            }else {
                if(item.getName().matches(regex)){
                    rs.addFile(item);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        for (File file:local("C:\\Users\\呵\\Desktop\\Prvyx",".*\\.txt")){
            System.out.println(file);
        }
        Iterator<File> iterator=walk("C:\\Users\\呵\\Desktop\\Prvyx",".*\\.txt").iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

