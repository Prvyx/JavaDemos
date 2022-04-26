package fileutils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @program: JavaDemos
 * @description:目录列表器2（使用内部类）
 * @author: Prvyx
 * @created: 2022/04/15 18:01
 */

public class DirList2 {
    public static FilenameFilter filter(final String regex){
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(regex);
            }
        };
    }

    public static void main(String[] args) {
        File file=new File("C:\\Users\\呵\\Desktop");
        String[] list = file.list(filter(".*\\.txt"));
        if(list!=null){
            for(int i=0;i<list.length;i++){
                System.out.println(list[i]);
            }
        }
    }
}
