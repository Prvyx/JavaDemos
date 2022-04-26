#### 18-JavaIO
1. File类
   - 目录列表器：[目录的列表器demo](src/main/java/fileutils/DirList.java)、[目录列表器2（使用内部类）](src/main/java/fileutils/DirList2.java)
   - 目录实用工具:[目录实用工具（local操作、递归操作）](src/main/java/fileutils/Directory.java)
   - 目录的检查及创建:File对象的一堆方法
2. 输入、输出（面向字节，字节流）
   - Java中”流“类库让人困惑的点：创建单一的流，却需要创建多个对象
   - 输入源：
     1. 字节数组：use ByteArrayInputStream
     2. String对象:use StringBufferInputStream
     3. 文件:use FileInputStream
     4. ”管道“:use PipedInputStream
     5. 其它流组成的序列:use SequenceInputStream
     6. FilterInputStream:装饰类接口，装饰类与其它InputStream类搭配，提供更有用的功能（比如一个塑料袋，并不是主要，但你买的物品可以放在里面，更加方便）
        - DataInputStream：读取”基本类型“数据
        - BufferedInputStream：指定缓冲区大小，与接口对象搭配
        - LineNumberInputStream:仅增加了行号，与接口对象搭配
        - PushbackInputStream:作为编译器的扫描器，几乎用不到
     7. 其它数据库，如Internet连接等
   - 输出地：
     1. 字节数组:use ByteArrayOutputStream
     2. 文件:use FileOutputStream
     3. 管道:use PipedOutputSteam
     4. 装饰器输出接口：FilterOutputStream，装饰类与其它OutputStream类搭配使用，提供更有用的功能
        - DataOutputStream：写入基本类型数据
        - PrintStream：不常用
        - BufferedOutputStream:指定缓冲区大小，与接口对象搭配
3. Reader、Writer（面向字符，Unicode字符流）
   - Reader、Writer的目的：
     1. 方便字符的读取、写入
     2. 老IO流仅支持8位字节流，不能很好地处理16位Unicode字符；而Reader、Writer支持Unicode
   - 适配器（类似于手机充电器）类（adapter类）：InputStreamReader：InputStream => Reader、OutputStream => Writer
4. RandomAccessFile类：自我独立：实现了DataInput、DataOutput（不是DataInputStream、DataOutputStream）接口，与InputStream、OutputStream树无关
   - 只能读写“文件”（只能 修饰的这个），包含一个记录指针（像C/C++的读取文件的方式） [关于RandomAccessFile类的demo](src/main/java/RandomAccessFileDemo.java)
   - 常用场景：网络请求中的多线程下载、断点续传
   - 资料：[RandomAccessFile](https://www.jianshu.com/p/360e37539266)
5. IO流的典型使用方式
   - 缓冲输入文件 [面向字符的缓冲输入文件](src/main/java/BufferedInputFile.java)
   - 从内存输入 [内存中的字符串作为输入源](src/main/java/MemoryInput.java)
   - 格式化的内存输入 [格式化的内存输入](src/main/java/FormattedMemoryInput.java)
   - 面向字符的文件输出 [面向字符的文件输出](src/main/java/BasicFileOutput.java)
   - 跨平台的存储和恢复数据（需要Java环境） 
     - [跨平台的存储和恢复数据（从windows资源管理器看DataOutputStream输出的文件是乱码的，需要使用DataInputStream读取）](src/main/java/StoreAndRecoverData.java)
     - 注意：对象序列化、XML可能是更容易地存储和读取复杂数据结构的方式
   - 读写随机访问文件 [关于RandomAccessFile类的demo](src/main/java/RandomAccessFileDemo.java)
   - 管道流：PipedInputStream、PipedOutputStream、PipedReader、PipedWriter。它们的价值在于“多线程的任务之间的通信”
6. 文件读写的实用工具
   - 引入：在[面向字符的文件输出](src/main/java/BasicFileOutput.java)中，创建“向文本文件写入“的流，官方提供了简化方式，不用人为地编写装饰器代码
   - So，编写一个文件读写的实用工具，就很有必要了，减少重复的代码量
7. 标准IO
   - [Java版的Unix的echo实现](src/main/java/Echo.java)
   - [用PrintWriter封装System.out](src/main/java/ChangeSystemOut.java)
   - [重定向默认的System.in、System.out](src/main/java/RedirectSystemINOUT.java)
8. 进程控制
9. 新IO
   - nio的目的：提供速度。实际上，旧io包已经使用nio重新实现过
   - 速度的提升在于所使用的结构：“通道”、“缓冲器”
   - （把它想象成一个煤矿，“通道”是一个包含煤层（数据）的矿藏，而缓冲器则是派送到矿藏的卡车，我们可以从卡车获得煤炭。我们并没有直接和通道交互，我们只是和缓冲器交互，并把缓冲器派送到通道。通道向缓冲器 获得数据或者发送数据）
   - 唯一直接与通道交互的缓冲器是“ByteBuffer”
   - [GetChannel的demo](src/main/java/niodemo/GetChannel.java)
   - [通过nio进行文件的复制](src/main/java/niodemo/FileCopyByNio.java)
   - [将两个通道直接相连，非常方便两个通道的复制](src/main/java/niodemo/TransferToFromDemo.java)
   - [ByteBuffer转换为文本](src/main/java/niodemo/BufferToText.java)
     - 复习编码与解码的知识：
       - 注意编码、解码，一般来说，用什么编码，就用什么解码（解码字符集>=编码字符集），且编码与解码过程中均不能出错，才能正确地编解码
       - eg1：中文字符=>字节（使用UTF-8），然后字节=>中文字符（使用UTF-8或者兼容UTF-8的字符集）
       - eg2：中文字符=>字节（使用ASCII），然后.... （编码过程已经出错）
       - 编码：字符（可视化数据）=>字节；解码：字节=>字符（可视化数据）
   - [遍历java.nio.charset.Charset的availableCharsets以及每个字符集的别名](src/main/java/niodemo/AvailableCharSets.java)
   - java.nio.ByteBuffer（缓冲器）详解：
     - [ByteBuffered的深入理解：flip()、clear()、rewind()的区别](src/main/java/niodemo/ByteBufferDeepUnderstand.java)
       - 通过这个demo，得出了如下结论：
         - flip():limit=position;position=0; 作用：方便后续的写入操作（读取ByteBuffer（position~limit-1），写至输出地），不用人为地编写fileChannel.write(bytes,0,len)的代码，而是直接fileChannel.write(bytes)即可
         - rewind():position=0; 作用：方便后续的”再次读取ByteBuffer，且无写入“的操作（读取position~limit-1）
         - clear():position=0;limit=capacity; 作用：将ByteBuffer写入文件等输出地后，需要使用clear()，重置position、limit为初始状态
         - 将ByteBuffer写入文件后，position停在与limit相等的位置
         - 在Java中，不讲字符集，说一个字符占多少字节都是耍流氓
         - byteBuffer.asCharBuffer()、byteBuffer.asCharBuffer().put("xxx")均是默认UTF_16BE字符集，一个英文字符占2个字节
     - 资料：[NIO之Buffer的clear()、rewind()、flip()方法的区别](https://www.cnblogs.com/cangqinglang/p/9379131.html)
   - 视图缓冲器
     - [IntBuffer的demo](src/main/java/niodemo/IntBufferDemo.java)。总结：向inputBuffer存入数据 或 从inputBuffer相对get()数据，会使position向后移动
     - [ByteBuffer的字节存放次序：big endian(高位优先)、little endian(低位优先)](src/main/java/niodemo/Endians.java)
   - 缓冲器的细节
     - ![img.png](img.png)
     - [交换相邻字符demo](src/main/java/niodemo/SwapAdjacentCharacters.java)
   - 内存映射文件：允许我们创建和修改那些因为太大而不能放入内存的文件。有了内存映射文件，可以假定整个文件都放在内存中，可以完全把它当作非常大的数组来访问
     - [内存映射文件demo](src/main/java/niodemo/LargeMappedFiles.java)
     - [测试旧IO与nio的mappedByteBuffer的速度差异](src/main/java/niodemo/MappedIOTester.java)。一些总结：当你想写入时，推荐RandomAccessFile；当你只想读取时，并不推荐RandomAccessFile，推荐加上BufferedInputStream的FileInputStream
   - 文件加锁：
     - 非阻塞：tryLock()、tryLock(long position,long size,boolean shared)（文件部分上锁）
     - 阻塞：lock()、lock(long position,long size,boolean shared)（文件部分上锁）
     - 无参的加锁是对整个文件加锁，会随着文件尺寸的变化而变化，有参的加锁不随文件尺寸的变化而变化
     - 对映射文件的部分加锁：[对映射文件进行部分加锁](src/main/java/niodemo/LockingMappedFiles.java)。注：对通道加锁，不能对缓冲器加锁
10. 压缩
    - JavaIO类库中支持读写压缩格式的数据流，它们从InputStream、OutputStream继承来的，因为压缩数据流是字节流
      - ![img_1.png](img_1.png)
    - GZIP
      - [使用GZIP压缩文本文件并读取的demo（我现在使用GZIP压缩不了图片。。。）](src/main/java/compress/GzipDemo.java)
      - 图片使用GZIP压缩不了，原因：java中没有一种官方的API可以去创建tar.gz文件
      - 资料：https://cloud.tencent.com/developer/article/1703421
    - Zip
      - [使用zip压缩单个图片](src/main/java/compress/ZipSimpleDemo.java)
      - [通过zip压缩目录，且压缩后读取](src/main/java/compress/ZipCompress.java)
    - JAR（Java ARchive，Java档案文件）
      - skip
11. 对象序列化
    - skip，不是Java核心内容，先不造轮子了
12. XML
    - skip，不是Java核心内容，先不造轮子了
13. 补充（来自官方文档）：
    - InputSteam及其导出类的无参read()返回 the next byte of data, or -1 if the end of the stream is reached.
    - InputStream及其导出类的有参read(xxx)返回 the total number of bytes read into the buffer, or -1 if there is no more data because the end of the stream has been reached.