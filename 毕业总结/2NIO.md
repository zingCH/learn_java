# 2NIO

## 1、java socket

### 

- 实现思路

	- 1、每次创建一个线程，由该线程处理新的socket
	- 2、进一步：线程池处理socket，复用线程

### 过程分析

- 主要有2种操作

	- - CPU 计算/业务处理
	- - IO 操作与等待/网络、磁盘、数据库

## 2、NIO模型与相关概念

### 通信模型

- 基本概念

	- 阻塞、非阻塞是线程处理模式。
	- 同步、异步是通信模式

- 常见5种IO模型

	- 
	- 只有一个异步（即AIO），其他都是同步

- 详解

	- 同步阻塞IO

		- 

			- 一般通过在 while(true) 循环中服务
端会调用 accept() 方法等待接收客户
端的连接的方式监听请求，请求一旦
接收到一个连接请求，就可以建立通
信套接字在这个通信套接字上进行读
写操作，此时不能再接收其他客户端
连接请求，只能等待同当前连接的客
户端的操作执行完成， 不过可以通过
多线程来支持多个客户端的连接

		- 
		- 典型：java socket编程

	- 同步非阻塞IO

		- 

			- 和阻塞 IO 类比，内核会立即返回，返
回后获得足够的 CPU 时间继续做其它
的事情。
用户进程第一个阶段不是阻塞的,需要
不断的主动询问 kernel 数据好了没有；
第二个阶段依然总是阻塞的。

		- 

	- IO多路复用

		- 同步、阻塞的
		- 

			- IO 多路复用(IO multiplexing)，也称事
件驱动 IO(event-driven IO)，就是在单
个线程里同时监控多个套接字，通过
select 或 poll 轮询所负责的所有
socket，当某个 socket 有数据到达了，
就通知用户进程。
IO 复用同非阻塞 IO 本质一样，不过利用
了新的 select 系统调用，由内核来负责
本来是请求进程该做的轮询操作。看似比
非阻塞 IO 还多了一个系统调用开销，不
过因为可以支持多路 IO，才算提高了效
率。
进程先是阻塞在 select/poll 上，再是阻
塞在读操作的第二个阶段上。

		- 
		- select/poll 的几大缺点

			- （1）每次调用 select，都需要把 fd 集合从用户态拷贝到内核态，这个开销在 fd 很多时会很大
			- （2）同时每次调用 select 都需要在内核遍历传递进来的
所有 fd，这个开销在 fd 很多时也很大
			- （3）select 支持的文件描述符数量太小了，默认是1024

		- epoll

			- Linux 2.5.44内核中引入,2.6内核正式引入,可被用于代替 POSIX select 和 poll 系统调用
			- 优点

				- （1）内核与用户空间共享一块内存
				- （2）通过回调解决遍历问题
				- （3）fd 没有限制，可以支撑10万连接

		- 典型：Reactor模式

			- 
			- 
			- 例如：Netty

	- 信号驱动IO(SIGIO)

		- 

			- 信号驱动 IO 与 BIO 和 NIO 最大的区
别就在于，在 IO 执行的数据准备阶段
，不会阻塞用户进程。
如图所示：当用户进程需要等待数据
的时候，会向内核发送一个信号，告
诉内核我要什么数据，然后用户进程
就继续做别的事情去了，而当内核中
的数据准备好之后，内核立马发给用
户进程一个信号，说”数据准备好了，
快来查收“，用户进程收到信号之后，
立马调用 recvfrom，去查收数据。

	- 异步IO（AIO）

		- 

			- 信号驱动 IO 与 BIO 和 NIO 最大的区
别就在于，在 IO 执行的数据准备阶段
，不会阻塞用户进程。
如图所示：当用户进程需要等待数据
的时候，会向内核发送一个信号，告
诉内核我要什么数据，然后用户进程
就继续做别的事情去了，而当内核中
的数据准备好之后，内核立马发给用
户进程一个信号，说”数据准备好了，
快来查收“，用户进程收到信号之后，
立马调用 recvfrom，去查收数据。

		- 比较

			- 

## 3、Netty

### 概述

- 
- 网络应用开发框架

	- 1. 异步
	- 2. 事件驱动
	- 3. 基于 NIO

- 适用于

	- 服务端
	- 客户端
	- TCP/UDP

### 特性

- 高性能的协议服务器

	- • 高吞吐
	- • 低延迟
	- • 低开销
	- • 零拷贝
	- • 可扩容
	- • 松耦合: 网络和业务逻辑分离
	- • 使用方便、可维护性好

- 兼容性

	- JDK 兼容性

		- • Netty 3.x: JDK5
		- • Netty 4.x: JDK6

	- 协议兼容性

		- • 兼容大部分通用协议
		- • 支持自定义协议

- 嵌入式

	- • HTTP Server
	- • HTTPS Server
	- • WebSocket Server
	- • TCP Server
	- • UDP Server
	- • In VM Pipe

### 基本概念

- Channel

	- 通道，Java NIO 中的基础概念,代表一个打开的连接,可执行读取/写入 IO 操作。
	- Netty 对 Channel 的所有 IO 操作都是非阻塞的

- ChannelFuture

	- Java 的 Future 接口，只能查询操作的完成情况, 或者阻塞当前线程等待操作完成。
	- Netty 封装一个 ChannelFuture 接口
	- 可以将回调方法传给 ChannelFuture，在操作完成时自动执行

- Event & Handler

	- Netty 基于事件驱动，事件和处理器可以关联到入站和出站数据流。
	- 入站事件与出站事件

		- 入站事件

			- • 通道激活和停用
			- • 读操作事件
			- • 异常事件
			- • 用户事件

		- 出站事件

			- • 打开连接
			- • 关闭连接
			- • 写入数据
			- • 刷新数据

	- 事件处理程序接口

		- • ChannelHandler
		- • ChannelOutboundHandler
		- • ChannelInboundHandler

	- 适配器（空实现，需要继承使用）

		- • ChannelInboundHandlerAdapter
		- • ChannelOutboundHandlerAdapter

- Encoder & Decoder

	- 处理网络 IO 时，需要进行序列化和反序列化, 转换 Java 对象与字节流。
	- 对入站数据进行解码, 基类是 ByteToMessageDecoder。
	- 对出站数据进行编码, 基类是 MessageToByteEncoder。

- ChannelPipeline

	- 数据处理管道就是事件处理器链。
	- 有顺序、同一 Channel 的出站处理器和入站处理器在同一个列表中。

### 典型的Netty应用组成

- • 网络事件
- • 应用程序逻辑事件
- • 事件处理程序

### 如何实现高性能

- 从事件处理机制到Reactor模型

	- 

- 不同的Reactor模型

	- Reactor单线程模型

		- 
		- 

	- Reactor多线程模型

		- 
		- 

	- Reactor主从模型

		- 
		- 

- Netty对于Reactor模型的支持

	- 

- Netty启动和处理流程

	- 

- Netty线程模型

	- 

- Netty运行原理

	- 

- 关键对象

	- Bootstrap: 启动线程，开启 socket 
	- EventLoopGroup 
	- EventLoop 
	- SocketChannel: 连接 
	- ChannelInitializer: 初始化 
	- ChannelPipeline: 处理器链 
	- ChannelHandler: 处理器

### 常见问题

- 粘包与拆包

	- 

- 网络拥堵与 Nagle 算法优化

### Netty优化

- 1、不要阻塞 EventLoop 
- 2、系统参数优化 
ulimit -a /proc/sys/net/ipv4/tcp_fin_timeout, TcpTimedWaitDelay 
- 3、缓冲区优化 
SO_RCVBUF/SO_SNDBUF/SO_BACKLOG/ REUSEXXX 
- 4、心跳周期优化 心跳机制与断线重连
- 5、内存与 ByteBuffer 优化 DirectBuffer与HeapBuffer
- 6、其他优化 - ioRatio - Watermark - TrafficShaping

## 4、聊聊高性能

### 什么是高性能

- 高并发Concurrency
- 高吞吐量Throughout
- 低延迟Latency

### 实现高性能的副作用

- 系统复杂度 x10以上
- 建设与维护成本++++
- 故障或 BUG 导致的破坏性 x10以上

### 应对措施

- 稳定性建设（混沌工程）

	- 1、容量
	- 2、爆炸半径
	- 3、工程方面积累与改进

*XMind - Evaluation Version*

IO实实在在是性能的瓶颈所在，关于同步异步、阻塞与非阻塞的认识增进异步，Netty框架是网络编程比较流行的。