package niodemo;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

/**
 * @program: JavaDemos
 * @description:遍历java.nio.charset.Charset的availableCharsets以及每个字符集的别名
 * @author: Prvyx
 * @created: 2022/04/16 17:39
 */

public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Iterator<String> keyIterator=stringCharsetSortedMap.keySet().iterator();
        while (keyIterator.hasNext()){
            String key=keyIterator.next();
            System.out.print(key+"的aliases: ");
            Set<String> set=Charset.forName(key).aliases();
            Iterator<String> aliasesIterator=set.iterator();
            while (aliasesIterator.hasNext()){
                System.out.print(aliasesIterator.next()+",");
            }
            System.out.println();
        }
    }
}
