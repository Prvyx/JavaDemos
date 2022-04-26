package fileutils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @program: JavaDemos
 * @description:目录列表器
 * @author: Prvyx
 * @created: 2022/04/15 17:12
 */
class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex){
        pattern=Pattern.compile(regex);
    }
    public DirFilter(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
public class DirList {
    private String path;

    public DirList() {
    }

    public DirList(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public List<String> list(){

        File file=new File(path);
        String[] list = file.list(new DirFilter(".*\\.txt"));
        return Arrays.asList(list);
    }

    public static void main(String[] args) {
        DirList dirList=new DirList("C:\\Users\\呵\\Desktop");
        for(String s:dirList.list()){
            System.out.println(s);
        }
    }
}
