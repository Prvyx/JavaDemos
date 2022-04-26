#### 1两数之和
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

#### 个人理解：
使用Map持有对象，遍历nums，若Map中不存在（target-nums[i]）的key，则map.put(nums[i],i)；
若存在，则证明已找到这两个数，返回int[]{i,map.get(target-nums[i])}

#### 代码；
```java
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=0;i!=nums.length;i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }else {
                map.put(nums[i],i);
            }
        }
        return new int[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```

#### 2.两数相加
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

#### 思路：创建一个新链表、int carry=0，遍历两个链表，每一位都进行相加且加上carry，存至新链表，两个链表指针均next，一直以此循环，直至均到头

#### 代码：
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head,tail;
        head=tail=null;
        int carry=0;
        while (l1!=null||l2!=null){
            int n1=l1!=null?l1.val:0;
            int n2=l2!=null?l2.val:0;
            int sum=n1+n2+carry;
            if(head==null){
                head=tail=new ListNode(sum%10);
            }else {
                tail.next=new ListNode(sum%10);
                tail=tail.next;
            }
            carry=sum/10;
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
        }
        if(carry>0){
            tail.next=new ListNode(carry);
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```

#### 3.无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
#### 思路：eg：abcbbcab，双指针
- 思路1：固定每次的左指针，移动右指针，直至重复，记录长度，ans=Math.max(ans,右指针-左指针)。
即(abc)bbcab->a(bc)bbcab->ab(cb)bcab->abc(b)bcab->abcb(bca)b->abcbb(cab)->abcbbc(ab)->abcbbca(b)
- 思路2：在思路1的基础上进行优化，
在(abc)bbcab此处，之后的左指针指向b已经没意义，因为肯定比第一次短，因此可以，
先找出与s.charAt(j)（即'b'）重复的s.charAt(i->j-1)（i、j-1均包含）的下标deleteBoundary（必有重复的字符），
代码：int deleteBoundary=j<s.length()?map.get(s.charAt(j)):s.length()-1;
然后map.remove(s.charAt(i->deleteBoundary))（i、deleteBoundary均包含），最后i=deleteBoundary，继续循环，直至左指针遍历完

#### 代码：（思路2）：
```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map=new HashMap<>();
        int ans=0;
        int j=1;
        if(s.length()!=0){
            map.put(s.charAt(0),0);
        }
        for(int i=0;i<s.length();i++){
            while (j<s.length()&&!map.containsKey(s.charAt(j))){
                map.put(s.charAt(j),j);
                j++;
            }
            ans=Math.max(ans,j-i);
            int deleteBoundary=j<s.length()?map.get(s.charAt(j)):s.length()-1;
            // 清除map中的 s.charAt(i（包含）->deleteBoundary（包含）)
            for(int cleanUpMapValue=i;cleanUpMapValue<=deleteBoundary;cleanUpMapValue++){
                map.remove(s.charAt(cleanUpMapValue));
            }
            i=deleteBoundary;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```

#### 4.寻找两个正序数组的中位数
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
#### 思路：合并nums1、nums2后进行Arrays.sort()，根据数学求中位数即可。没有自己写轮子，而是用现有的轮子
#### 代码：
```java
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length,n=nums2.length;
        int[] nums=new int[m+n];
        for(int i=0;i!=m;i++){
            nums[i]=nums1[i];
        }
        for(int i=0;i!=n;i++){
            nums[i+m]=nums2[i];
        }
        Arrays.sort(nums);
        if((m+n)%2!=0){
            return nums[(m+n-1)/2];
        }else {
            return (nums[(m+n)/2]+nums[(m+n)/2-1])*1.0/2;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```

#### 5.最长回文子串
给你一个字符串 s，找到 s 中最长的回文子串。
#### 思路：
动态规划查找资料：[帅地的回答](https://zhuanlan.zhihu.com/p/91582909)

字符串s，可以从两个部分下手：1.从1个字符扩散、2.从2个字符扩散。这两个部分使用动态规划解决
1. 定义数组元素的含义
   - 在该题中，为dp[i][j]：表示s.charAt(i->j)是否为回文子串
2. 找出数组元素之间的关系式：dp[i][j]=dp[i+1][j-1]&&s.charAt(i)==s.charAt(j)
3. 找出初始值：两个部分：1.dp[k][k]为true 2.dp[k][k+1]=s.charAt(k)==s.charAt(k+1)
4. 写代码
#### 代码：
```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        if(s.length()<=1||(s.length()==2&&s.charAt(0)==s.charAt(1)))
            return s;
        Boolean[][] dp=new Boolean[s.length()][s.length()];
        int left,right,begin=0,maxLength=1;
//        初始化 单个字符必是回文子串，2个字符需要判断
        for(int k=0;k<s.length();k++){
            dp[k][k]=true;
        }
        for(int k=0;k<s.length()-1;k++){
            dp[k][k+1]=(s.charAt(k)==s.charAt(k+1));
            if(dp[k][k+1]){
                maxLength=2;
                begin=k;
            }
        }

//        Boolean dp[i][j]：表示s.charAt(i->j)是否为回文子串
//        推导公式：dp[i][j]=dp[i+1][j-1]&&s.charAt(i)==s.charAt(j)
        // 从单个字符开始
        for(int i=1;i<s.length()-1;i++){
            left=right=i;
            while (left>0&&right<s.length()-1&&dp[left][right]){
                left--;right++;
                dp[left][right]=dp[left+1][right-1]&&(s.charAt(left)==s.charAt(right));
                if(dp[left][right]&&right-left+1>maxLength){
                    maxLength=right-left+1;
                    begin=left;
                }
            }
        }
        // 从2个字符开始
        for(int i=1;i<s.length()-2;i++){
            left=i;right=i+1;
            while (left>0&&right<s.length()-1&&dp[left][right]){
                left--;right++;
                dp[left][right]=dp[left+1][right-1]&&(s.charAt(left)==s.charAt(right));
                if(dp[left][right]&&right-left+1>maxLength){
                    maxLength=right-left+1;
                    begin=left;
                }
            }
        }
        return s.substring(begin,begin+maxLength);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```

#### 6.字形变换
将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"
#### 思路：
![img.png](img.png)
（1）当s.length()或numRows为1时，return s;
（2）否则，就是比较正常的情况
   - （1）若i==0/n-1时，可能添加1个字符
   - （2）否则，可能需要添加2个字符，其中第二个字符，需要计算其下标
#### 代码：
```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
   public String convert(String s, int numRows) {
      if(s.length()==1||numRows==1){
         return s;
      }
      StringBuilder rstr=new StringBuilder(1000);
      int sLen=s.length(),n=numRows;
      // 第一个指针i，从上到下遍历0~n-1行
      for(int i=0;i<n;i++){
         if(i==0||i==n-1){//若为第1行或最后一行，可能添加一个字符
            // 第二个指针j，从左到右遍历
            for(int j=i;j<sLen;j+=2*(n-1)){
               rstr.append(s.charAt(j));

            }
         }else {
            // 第二个指针j，从左到右遍历，可能添加2个字符
            for(int j=i;j<sLen;j+=2*(n-1)){
               rstr.append(s.charAt(j));
               // 若有第二个字符，则其下标为2*(n-i-1)+j
               // 这个下标如何求出来的呢？根据中位数，(j+x)/2=n-1-i+j((n-1-i+j)的求法：j所在列最后一个的下标-j=(n-1)-i)
               if(2*(n-i-1)+j>=sLen){
                  break;
               }
               rstr.append(s.charAt(2*(n-i-1)+j));

            }
         }
      }
      return rstr.toString();
   }
}
//leetcode submit region end(Prohibit modification and deletion)
```