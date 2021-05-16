一、串行化GC
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseSerialGC GCLogAnalysis
日志结果：8次左右youngGC
串行 GC 对年轻代使用 mark-copy（标记-复制） 算法，对老年代使用 mark-sweep-compact（标记-清除-整理）算法。单线程的垃圾收集器，不能进行并行处理，所以都会触发全线暂停（STW），停止所有的应用线程。串行 GC 算法不能充分利用多核 CPU，CPU 利用率高，暂停时间长。只适合几百 MB 堆内存的 JVM，单核 CPU 时比较有用。

二、并行GC
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseParallelGC GCLogAnalysis
日志结果：20次左右youngGC、1-2次FullGC
年轻代和老年代的垃圾回收都会触发 STW 事件。在年轻代使用 标记-复制（mark-copy）算法，在老年代使用 标记-清除-整理算法。 -XX：ParallelGCThreads=N 来指定 GC 线程数， 其默认值为 CPU 核心数。并行垃圾收集器适用于多核服务器，主要目标是增加吞吐量。因为对系统资源的有效使用，能达到更高的吞吐量:  在 GC 期间，所有 CPU 内核都在并行清理垃圾，所以总暂停时间更短；在两次 GC 周期的间隔期，没有 GC 线程在运行，不会消耗任何系统资源.

三、CMS GC
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseConcMarkSweepGC GCLogAnalysis
日志结果：8次左右youngGC，一次分阶段CMSGC
其对年轻代采用并行 STW 方式的 mark-copy (标记-复制)算法，对老年代主要使用并发 mark-sweep (标记-清除)算法。CMS GC 的设计目标是避免在老年代垃圾收集时出现长时间的卡顿，主要通过两种手段来达成此目标：1. 不对老年代进行整理，而是使用空闲列表（free-lists）来管理内存空间的回收。2. 在 mark-and-sweep （标记-清除） 阶段的大部分工作和应用线程一起并发执行。也就是说，在这些阶段并没有明显的应用线程暂停。但值得注意的是，它仍然和应用线程争抢CPU 时。 默认情况下，CMS 使用的并发线程数等于 CPU 核心数的 1/4。如果服务器是多核 CPU，并且主要调优目标是降低 GC 停顿导致的系统延迟，那么使用 CMS 是个很明智的选择。进行老年代的并发回收时，可能会伴随着多次年轻代的 minor GC。

四、G1 GC
java -XX:+PrintGC -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseG1GC GCLogAnalysis
日志结果：相对比较复杂，youngGC、CMS分阶段GC
G1 的全称是 Garbage-First，意为垃圾优先，哪一块的垃圾最多就优先清理它。G1 GC 最主要的设计目标是：将 STW 停顿的时间和分布，变成可预期且可配置的。事实上，G1 GC 是一款软实时垃圾收集器，可以为其设置某项特定的性能指标。为了达成可预期停顿时间的指标，G1 GC 有一些独特的实现。首先，堆不再分成年轻代和老年代，而是划分为多个（通常是2048个）可以存放对象的小块堆区域(smaller heap regions)。每个小块，可能一会被定义成 Eden 区，一会被指定为 Survivor区或者Old 区。在逻辑上，所有的 Eden 区和 Survivor 区合起来就是年轻代，所有的 Old 区拼在一起那就是老年代。这样划分之后，使得 G1 不必每次都去收集整个堆空间，而是以增量的方式来进行处理: 每次只处理一部分内存块，称为此次 GC 的回收集(collection set)。每次 GC 暂停都会收集所有年轻代的内存块，但一般只包含部分老年代的内存块。G1 的另一项创新是，在并发阶段估算每个小堆块存活对象的总数。构建回收集的原则是： 垃圾最多的小块会被优先收集。这也是 G1 名称的由来。

五、如何选择
选择正确的 GC 算法，唯一可行的方式就是去尝试，一般性的指导原则：
1. 如果系统考虑吞吐优先，CPU 资源都用来最大程度处理业务，用 Parallel GC；
2. 如果系统考虑低延迟有限，每次 GC 时间尽量短，用 CMS GC；
3. 如果系统内存堆较大，同时希望整体来看平均 GC 时间可控，使用 G1 GC。
对于内存大小的考量：
六、总结
1. 一般 4G 以上，算是比较大，用 G1 的性价比较高。
2. 一般超过 8G，比如 16G-64G 内存，非常推荐使用 G1 GC。
1. 串行 GC（Serial GC）: 单线程执行，应用需要暂停；
2. 并行 GC（ParNew、Parallel Scavenge、Parallel Old）: 多线程并行地执行垃圾回收，
关注与高吞吐；
3. CMS（Concurrent Mark-Sweep）: 多线程并发标记和清除，关注与降低延迟；
4. G1（G First）: 通过划分多个内存区域做增量整理和回收，进一步降低延迟；