# 分布式服务

## 1、RPC基本原理

### 基础

- 远程过程调用Remote Procedure Call: 像调用本地方法一样调用远程方法
- 原理

	- 

		- 代理存根实现

			- 动态代理
			- AOP
			- 字节码编程

### RPC关键点

- 1 设计

	- RPC是基于接口的远程服务调用
	- 共享：POJO实体类定义，接口定义。

		- 注意是实体类定义、接口定义，而没有说实体类、接口，因为在REST/ProtoBuff/Thrift/WebService可能跨平台，非java的就没有实体类

- 2 代理

	- java下

		- 动态代理
		- AOP实现

	- C#直接有远程代理
	- Flex可以使用动态方法和属性

- 3 序列化

	- 序列化与反序列化的选择

		- 语言原生的序列化

			- 比如java默认的RMI只能在java里用，.NET里有.NET Remoting

		- 二进制平台无关

			- 省空间，数据量小
			- 优劣

				- 不可读

			- 常见

				- kyro
				- avro
				- Hession
				- fst

		- 文本

			- JSON

				- 没有schema，只有简单的几种类型

			- XML

				- 描述能力强

					- DTD
					- XSD

			- 优劣

				- 简单可读跨平台
				- 体积大

- 4 网络传输

	- 最常见的传输方式

		- TCP/SSL
		- HTTP/HTTPS
		- REST出现前，WebService一直算是事实标准,ESB

- 5 查找实现类

	- 通接口查找服务端的实现类
	- 一般是注册方式
	- 例如：dubbo 默认将接口和实现类配置到Spring

### 从RPC到分布式服务化

- 分布式业务场景

	- 需要考虑

		- 除了调用远程方法，还需要考虑？

			- 多个相同服务如何管理？
			- 服务的注册发现机智
			- 如何负载均衡、路由等集群功能？
			- 熔断、限流等治理能力
			- 重试等策略
			- 高可用、监控、性能等

### 示例

- @Bean相当于XML里配置了一个bean
- 注意接口里的参数尽量要写封装类，否则传RPC传参数的时候，接到的都是Integer, 没有int
- server返回给客户端时实际上是2次序列化，自己手工序列化，然后spring boot返回给调用端又序列化了一次——这个是偷懒做法，是为了复用spring boot以及response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));， 实际上一次就够了。
- 客户端可以复用client
- 换一种序列化
- ParserConfig 反序列化安全，所以addAccept那句

### 其他

- websocket是在http的去tcp的流？？？这个查一下
- RPC、自定义序列化时，需要判断循环嵌套，出现该情况需打断，否则会死人的。不过RPC一般是隐藏起来的、可能没法发现
- websocket需要在http头返回upgrade，表明升级成另一种协议，升级后收发数据就二进制的包、跟HTTP没关系，复用的是HTTP底层的TCP通道，但此时跟HTTP没啥关系了
- dubbo或是fastjson怎样避免反射？

	- 用字节码生成？？

- TCC分布式事务，如果cancel阶段，数据库挂掉，导致cancel执行失败，那只能手工介入，或者自己写个规则去检查
- TCC中，如果2个服务的分布式事务，结果1个confirm成功，1个confirm失败，那成功的那个confirm无法回滚，失败的那个需要记录、人工接入
- hmily TCC demo中，设计上有冻结金额，其实就是将undo redo log这种本来框架层面要做的事儿，拿到业务层面来做
- 柔性事务的本质是什么

	- 
XA是一个大事务 （一直都占用锁，是悲观锁）
SAGA 直接干、出了问题时回滚（补偿、冲正）
TCC 3个小事务
AT 
	- 柔性事务的本质是乐观锁

		- 柔性事务的假设前提：回滚是个小概率事件

- 怎么理解分布式事务的隔离级别

	- 分布式事务下，两个分支事务，一定不可能保证完全同事执行，那所有分布式事务最多都是read uncommit，有脏读问题。

XA里，需要所有分支事务都要弄成序列化，相当于加了锁

——解决：不同业务设计上尽量不要放到一个系统中，或者数据库表本身都放一起

## 2、RPC技术框架

### Corba

- 接口定义语言OMG Interface Definition Language就是现在的IDL，与语言无关

### 远古

### 常见RPC技术

- Corba/RMI/.NET Remoting
- JSON RPC, XML RPC, WebService(Axis2, CXF)
- hession, Thrift, Protocol Buffer, gRPC

	- 二进制的协议

- 目前，thrift之前发展很好，但目前gRPC比thrift可能前途更好，跟go一起，云原生

## 3、如何自己设计一个RPC框架

### 从哪些方面考虑

- 基于共享接口还是IDL
- 动态代理 OR AOP？
- 序列化用什么？文本 or 二进制？
- 基于TCP还是HTTP?

### 实现

- 1、设计

	- 共享：POJO实体类定义，接口定义。

REST/PB下，真的不需要嘛？另一种选择：WSDL/WADL/IDL

远程->服务提供者，本地->服务消费者。

- 2、代理

	- RPC是基于接口的远程服务调用。

Java下，代理可以选择动态代理，或者AOP实现

- 3、序列化

	- 序列化和反序列化的选择：
1、语言原生的序列化，RMI，Remoting
2、二进制平台无关，Hessian，avro，kyro，fst等 
3、文本，JSON、XML等

- 4、网络传输

	- 最常见的传输方式：
	- - TCP/SSL
	- - HTTP/HTTPS

- 5、查找实现类

	- 通过接口查找具体的业务服务实现。

## 4、从RPC走向服务化->微服务架构

### 具体的分布式业务场景里，
除了能够调用远程方法，我们还需要考虑什么？

- 多个相同服务如何管理？ ==> 集群/分组/版本 => 分布式与集群
- 服务的注册发现机制？ ==> 注册中心/注册/发现
- 如何负载均衡，路由等集群功能？ ==> 路由/负载均衡
- 熔断，限流等治理能力。 ==> 过滤/流控
- 心跳，重试等策略。
- 高可用、监控、性能等等。

### 典型的分布式服务化架构

- 

### RPC与分布式服务化的区别

- RPC

	- 技术概念

- 分布式服务化

	- 差非功能性需求能力
	- 服务是业务语义，偏向于业务与系统的集成
	- 具体使用时，另一个重点是如何设计分布式的业务服务
	- 注意：服务 != 接口，服务可以用接口或接口文档之类的语言描述。

### 分布式服务化与SOA/ESB的区别

- ESB

	- 
	- 核心系统不敢动，于是在前置做，前置功能越来越多，变成综合前置。也就变成了ESB。
增强能力都做在ESB中

- SOA

	- 一种企业级的架构风格，不管系统还是老系统，
	- 我们把整个企业所有的业务能力，变成可以复用的，基于RPC的服务接口和实现，这就是SOA
	- SOAP和SOA

		- SOA -- 架构 -- ESB -- WebService -- WSDL -- SOAP

	- SOUP UI

		- 可以用来测试 webservice， restful， jms，，还可以做压测

	- 

- 分布式服务化

	- 
	- 增强能力根据特点

		- 有状态的部分：放XX中心
		- 无状态的：放服务侧（具体来说是框架和配置部分，尽量不影响业务代码）
		- 

### 配置/注册/元数据中心

- 概念

	- 配置中心（ConfigCenter）：管理系统需要的配置参数信息 
	- 注册中心（RegistryCenter）：管理系统的服务注册、提供发现和协调能力 
	- 元数据中心（MetadataCenter）：管理各个节点使用的元数据信息

- 异同

	- 相同点：都需要保存和读取数据/状态，变更通知
	- 不同点：配置是全局非业务参数，注册中心是运行期临时状态，元数据是业务模型

- 为何需要XX中心？

	- 配置中心

		- 1、大规模集群下，如何管理配置信息，特别是批量更新问题。
		- 2、大公司和金融行业，一般要求开发、测试、运维分离（物理隔离）。
		- 3、运行期的一些开关控制，总不能不断重启？？

	- 注册中心

		- 这些信息很重要，后续的集群管控，分布式服务治理，都要靠这个全局状态。
		- 让消费者能动态知道生产者集群的状态变化？

			- DNS?VIP?

				- DNS有缓存，会有延迟

			- 主动报告+心跳

	- 元数据中心

		- 一般情况下，没有问题也不大。 有了更好。
		- 元数据中心，定义了所有业务服务的模型

- 如何实现XX中心？

	- 最核心的2个要素

		- 1、需要有存取数据的能力，特别是临时数据的能力
		- 2、需要有数据变化的实时通知机制，全量 或 增量

	- 主流的基座，一般都可以使用namespace的概念，用来在顶层隔离不同环境。

		- zk没有，但是我们一般用第一个根节点作为namespace。

	- 

### 服务的注册与发现

- 服务注册

	- 服务提供者启动时，将自己注册到注册中心（比如zk实现）的临时节点
	- 停止或者宕机时，临时节点消失。
	- 注册的数据格式

		- 节点key，代表当前服务（或者服务+版本）
		- 多个子节点，每一个为一个提供者的描述信息

- 服务发现

	- 服务消费者启动时

		- 从注册中心代表服务的主节点拿到多个代表提供者的临时节点列表，并本地缓存

			- 本地保存一份是为了在注册中心挂掉时仍能找到服务提供者

		- 根据router和loadbalance算法从其中的某一个执行调用
		- 如果可用的提供者集合发生变化时，注册中心通知消费者刷新本地缓存的列表

### 服务的集群与路由

- 服务集群

	- 对于完全相同能力的多个服务，我们希望他们能一切协同工作，分摊处理流量
	- 解决

		- 服务路由

			- service route
			- 跟网关的路由一样
			- 1、比如基于IP段的过滤，
			- 2、再比如服务都带上tag，用tag匹配这次调用范围
			- 大量变少量

		- 服务负载均衡

			- service loadbalance
			- 跟Nginx的负载均衡一样
			- 多个不同策略，原理不同，目的基本一致（尽量均匀）：
			- 常见策略

				- 1、Random（带权重） 
				- 2、RoundRobin（轮询） 
				- 3、LeastActive（快的多给） 
				- 4、ConsistentHashLoadBalance（同样参数请求到一个提供者）

			- 少量变一个

### 服务的过滤与流控

- 服务过滤

	- 所有的复杂处理，都可以抽象为管道+过滤器模式（Channel+Filter）

		- 这个机制是一个超级bug的存在，

	- 可以实现额外的增强机制（类似AOP），也可以中断当前处理流程，返回特定数据
	- 案例

		- serlvet filter
		- 自己写的NIO网关中的filter
		- spring cloud gateway主要写filter
		- soul网关也包含filter

- 服务流控（flow control）

	- 为何要流控？

		- 稳定性工程： 
1、我们逐渐意识到一个问题：系统会故障是正常现象，就像人会生病 
2、那么在系统出现问题时，直接不服务，还是保持部分服务能力呢？
		- 系统的容量有限。 保持部分服务能力是最佳选择，然后在问题解决后恢复正常状态
		- 响应式编程里，这就是所谓的回弹性（Resilient）。
		- 需要流控的本质原因是，输入请求大于处理能力

	- 流控3个级别

		- 1、限流（内部线程数，外部调用数或数据量）
		- 2、服务降级（去掉不必要的业务逻辑，只保留核心逻辑）
		- 3、过载保护（系统短时间不提供新的业务处理服务，积压处理完后再恢复输入请求）

## 5、dubbo

### 配置

- 端口可以配置-1，表示随机端口，这样可以是因为一般都会用注册中心，通过注册中心拿到ip和端口

### 框架介绍

- 产生于阿里B2B，后来淘系HSF
- 当当：dubbo-x
- 京东：JSF

### 主要功能

- 6大核心能力

	- 面试接口代理的高性能RPC调用
	- 智能负载均衡

		- 一般而言，http层面有h5、nginx可以做负载均衡

非http，dubbo支持，其他的暂时不知道

	- 服务自动注册与发现
	- 高度可扩展能力
	- 运行期流量调度

		- 内置条件、脚本等

	- 可视化的服务治理与运维

		- 比较简单，中等规模可能就不够用了

			- 开源项目多数在管控方面一般做的不太好，精力可能主要放在核心功能上了

- 基础功能

	- RPC调用

		- 多协议

			- 序列化
			- 传输
			- RPC

		- 服务注册发现
		- 配置、元数据管理

	- 
	- 框架分层设计，可任意组装和扩展。

- 扩展功能

	- 集群、高可用、管控
	- 细分

		- 集群，负载均衡
		- 治理，路由
		- 控制台，管理与监控

### 解读

- 重要概念

	- invoker：封装服务本身

		- cluster invoker
		- protool invoker
		- proxy invoker

	- protocol

		- 协议为中心

	- URL
	- filter

### 技术原理

- 重要的图

	- 框架设计

		- filter：设置mock就不用走网络
		- directory：拿到的是一个集合，从registry拿过来的
		- router会根据
		- loadbalancer会决定具体是哪一个

			- random
			- RR
			- 带权重
			- least active

	- 

- 重点：架构

	- 业务business

		- service

	- RPC

		- config

			- 配置层：对外配置接口，以 ServiceConfig, ReferenceConfig 为中心，可以直接初始 化配置类，也可以通过 spring 解析配置生成配置类

		- proxy

			- 服务代理层：服务接口透明代理，生成服务的客户端 Stub 和服务器端 Skeleton, 以 ServiceProxy 为中心，扩展接口为 ProxyFactory

		- registry

			- 注册中心层：封装服务地址的注册与发现，以服务 URL 为中心，扩展接口为 RegistryFactory, Registry, RegistryService

		- cluster

			- directory

				- 列出所有机器

			- router

				- 找出一个范围

			- load balancer
			- 路由层：封装多个提供者的路由及负载均衡，并桥接注册中心，以 Invoker 为中心， 扩展接口为 Cluster, Directory, Router, LoadBalance

		- monitor

			- 监控层：RPC 调用次数和调用时间监控，以 Statistics 为中心，扩展接口为 MonitorFactory, Monitor, MonitorService

		- protocol

			- 现在比较多
			- 远程调用层：封装 RPC 调用，以 Invocation, Result 为中心，扩展接口为 Protocol, Invoker, Exporter

	- 网络remoting

		- exchange

			- 信息交换层：封装请求响应模式，同步转异步，以 Request, Response 为中心， 扩展接口为 Exchanger, ExchangeChannel, ExchangeClient, ExchangeServer

		- transport

			- 走什么传输协议
			- 网络传输层：抽象 mina 和 netty 为统一接口，以 Message 为中心，扩展接口为 Channel, Transporter, Client, Server, Codec

		- serialize

			- 序列化成二进制、反序列化成对象
			- 数据序列化层：可复用的一些工具，扩展接口为 Serialization, ObjectInput, ObjectOutput, ThreadPool

- SPI的应用

	- SPI与API

		- API是给用户直接调用
		- 接口是空、实现需要自己来、再将自己实现融入到框架中，就是SPI——service provider interface
		- 典型案例：JDBC

	- 好处

		- 依赖反转，不需要知道具体实现，使用ServiceLoader调用就可以
		- 其他类似的依赖反转的方式

			- callback
			- event bus

- 服务如何暴露

	- 生成invoker
	- 重点：Exporter
	- Injvm
	- 复杂示例

		- 
		- dubbo对hession等协议做了增强，使用context模式

			- RpcContext attachment

				- 
				- 一般attachments 用来做链路跟踪，通用上下文透传

			- 在原协议基础上加报文头

	- 

- 服务如何引用

	- 

- 泛化调用

	- 实现方式

		- 

	- 不安全
	- 内部实现应该是反射，影响效率
	- 破坏OOP，不推荐？——回头看看录像确认下

- cluster

	- Directory

		- 所有的
		- return List<Invoker>

	- Router

		- 选取此次调用可以提供服务的invoker集合

			- 筛选出一小部分，
			- Condition，Script，Tag

	- LoadBalancer

		- 从上述集合选取一个作为最终调用者

			- 筛选出唯一的一个

		- 各种策略

			- 权重
			- 随机
			- RR
			- least active
			- 一致性hash

- 隐式传参

	- Context模式 

RpcContext.getContext().setAttachment("index", "1"); 

此参数可以传播到RPC调用的整个过程。
	- 大家觉得是如何实现的？

- mock

	- 

### dubbo应用场景

- 分布式服务化改造

	- 往往伴随着组织架构改造
	- 业务系统规模复杂，垂直拆分改造
	- - 数据相关改造 
- 服务设计 
- 不同团队的配合 
- 开发、测试运维

- 开放平台

	- 平台发展模式

		- 开放模式

			- 类似API的思想
			- 将公司的业务能力开放出来，形成开发平台，对外输出业务或技术能力

		- 容器模式

			- 类似SPI的思想

- 直接作为前端使用的后端（BFF）

	- 直接作为BFF给前端（Web或Mobile）提供服务
	- 一般不太建议这种用法

- 通过服务化建设中台

	- 将公司的所有业务服务能力，包装成API，形成所谓的业务中台

### dubbo最佳实践

- 开发分包

	- 建议将服务接口、服务模型、服务异常等均放在 API 包中，因为服务模型和异常也是 API 的一部分，这样做也符合分包原则：重用发布等价原则(REP)，共同重用原则 (CRP)。
	- 服务接口尽可能大粒度，每个服务方法应代表一个功能，而不是某功能的一个步骤，否 则将面临分布式事务问题，Dubbo 暂未提供分布式事务支持。 
	- 服务接口建议以业务场景为单位划分，并对相近业务做抽象，防止接口数量爆炸。 
	- 不建议使用过于抽象的通用接口，如：Map query(Map)，这样的接口没有明确语义， 会给后期维护带来不便。

- 环境隔离与分组

	- 怎么做多环境的隔离

		- 1、部署多套
		- 2、多注册中心机制
		- 3、group机制
		- 4、版本机制

	- 服务接口增加方法，或服务模型增加字段，可向后兼容，删除方法或删除字段，将不兼 容，枚举类型新增字段也不兼容，需通过变更版本号升级。

- 参数配置

	- 通用参数以 consumer 端为准，如果consumer端没有设置，使用provider数值
	- 建议在 Provider 端配置的 Consumer 端属性有： 
timeout：方法调用的超时时间 
retries：失败重试次数，缺省是 2 2 
loadbalance：负载均衡算法 3，缺省是随机 random。 
actives：消费者端的最大并发调用限制，即当 Consumer 对一个服务的并发调用到上限后，新 调用会阻塞直到超时，可以配置在方法或服务上。
	- 建议在 Provider 端配置的 Provider 端属性有： 
threads：服务线程池大小 
executes：一个服务提供者并行执行请求上限，即当 Provider 对一个服务的并发调用达到上限 后，新调用会阻塞，此时 Consumer 可能会超时。可以配置在方法或服务上。

- 容器化部署

	- 注册的IP问题，容器内提供者使用的IP，如果注册到zk，消费者无法访问。
	- 两个解决办法： 
1、docker使用宿主机网络 docker xxx -net xxxxx 

2、docker参数指定注册的IP和端口, -e DUBBO_IP_TO_REGISTRY — 注册到注册中心的IP地址 DUBBO_PORT_TO_REGISTRY — 注册到注册中心的端口 DUBBO_IP_TO_BIND — 监听IP地址 DUBBO_PORT_TO_BIND — 监听端口

- 运维和监控

	- 有元数据才能做测试
	- 可观测性

		- tracing

			- APM（skywalking, pinpoint, cat）

		- metrics

			- promethus+grafana

		- logging

			- ELK

	- Admin功能较简单，大规模使用需要定制开发，整合自己公司的运维监控系统。

- 分布式事务

	- 柔性事务，SAGA、TCC、AT 

- Seata 
- hmily + dubbo 

不支持 XA

- 重试与幂等

	- 服务调用失败默认重试2次，如果接口不是幂等的，会造成业务重复处理
	- 如何设计幂等接口？

		- 去重

			- bitmap 去重

		- 类似乐观锁机制
		- 前端需要去重

			- 前后端联动，前端从后端拿到一个token，新订单保存完成就消掉这个token

### 如何看dubbo源码

- 使用demo，加断点，看堆栈里

	- 
	- key

		- 
		- 不同方法是不同的接口

	- invocation就是自己实现时的request

		- 
		- 

- 核心重点模块

	- 核心重点模块： 
common 
config 
filter 
rpc/remoting/serialization 

集群与分布式： 
cluster 
registry/configcenter/metadata

- 学习源码的方法

	- 
	- 

- 关键

	- 调试dubbo代码：
	- 1、provider看 Protocol的export
	- 2、consumer看ReferenceConfig
	- 3、provider执行逻辑看Protocol 的handler

## 6、微服务发展历程

### 微服务发展架构

- 
- 相关知识

	- SOA相关

		- ESB

- 

### 常见架构案例

- 
- 

### 1、响应式微服务

- 来自于响应式编程

	- 响应式宣言

- 要求

	- 即时响应性

		- 可以降级，但不能没有响应

	- 可恢复性
	- 弹性
	- 消息驱动

### 2、服务网格与云原生

- 云原生

	- 可观测性

		- 日志
		- 跟踪
		- 度量

	- 组成

		- 微服务
		- 容器化
		- DevOps
		- 持续交付

	- 

- 服务网格

	- 将服务间的网络通信层及其控制策略下沉到基础设施，就形成了所谓的“服务网格”技术
	- 

### 3、数据库网格

- 

### 4、单元化架构

- 以单元为组织架构，以单元化部署为调度单位
- 以单元为组织架构，以单元化部署为调度单位。 每个单元，是一个五脏俱全的缩小版整站，它是全能的，因为部署了所有应用；但它不是全量的，因为只能操作 一部分数据。能够单元化的系统，很容易在多机房中部署，因为可以轻易地把几个单元部署在一个机房，而把另 外几个部署在其他机房。通过在业务入口处设置一个流量调配器，可以调整业务流量在单元之间的比例。
- 单元是自洽的

	- 可以从来做多活，比如同城双活，异地多活
	- 目前四大行、各种金融机构都在做

		- 一般用：pass层（技术中台）+ 业务中台层

	- 都在搞的原因

		- 把所有数据、业务都打散的方式，在打散过程中，去IOE：
中台 --> 基础软件的国产化
大机下移 --> 国产硬件

	- 对于一个用户而言，数据只能在一个单元cell中，不能跨单元，这样甚至可以用本地事务
	- 如果一个cell挂掉，换一个cell即可
	- 
	- 

### 发展历程小结

- 

### 其他

- 实施标准

	- istio -> service mesh
	- k8s  -> container orchestration

- DevOps与SRE区别

	- DevOps：业务谁开发的，谁负责后续维护，运维、测试、问题追踪等
	- SRE系统稳定性工程师，负责网络、硬件、底层中间件、如何处理高可用方案，对稳定性负责

## 7、微服务架构应用场景

### 什么时候用微服务

- 
- 在复杂度低的情况下，微服务应用生产力比单体架构低；
随着复杂度升高，单体架构的生产力快速下降，而微服务相对平稳
- 典型应用：大规模复杂业务系统的架构升级与中台建设

	- 

### 怎么应用微服务架构

- 套上面这张图，看自己要做什么
- 
- 类似于PMP中学过的PDCA戴明环

## 8、微服务架构最佳实践

### 最佳实践1：遗留系统改造

- 功能剥离，数据解耦

	- 数据、功能要同时拆分，否则只拆功能，数据还是会卡住。当然，数据是有状态的，拆起来比较难

- 自然演进，逐步拆分

	- 根据系统需求演进、拆分，否则提前做也没意义

- 小步快跑，快速迭代

	- 一小块一小块的拆，将一部分拿出来，与其他部分没关系，就方便维护、优化。原先1w行代码，拿出来后可能只需要6k

- 灰度发布，谨慎试错
- 提质量线，还技术债

### 最佳实践2：恰当粒度拆分

- 拆分原则

	- 高内聚低耦合
	- 不同阶段拆分要点不同

- 场景

	- 有经常出问题的，可以先拿这种模块开始拆分
	- 目前系统没啥问题，但有个模块天生游离在主要逻辑之外，也可以从这里开始拆

### 最佳实践3：扩展立方体

- 数据拆分

	- 数据分片
	- 根据业务，大客户单独存

- 实践

	- 特性开关

		- 老的保留，走新逻辑还是老逻辑用开关控制

	- 容错设计

- 
- 1. 水平复制：复制系统
- 2.功能解耦：拆分业务
- 3.数据分区：切分数据

### 最佳实践4：自动化管理

- 自动化测试
- 自动化部署
- 自动化运维

### 最佳实践5：分布式事务

- 冲正补偿！！！
- 注意点

	- 幂等
	- 去重
	- 补偿

- 常见模式

	- TCC
	- AT
	- XA
	- SAGA

		- 业务上，事务找做、定时任务检查、出错了反向冲正补回来 --> 实际上就是SAGA事务模式

### 最佳实践6：完善监控体系

- 1.业务监控 

	- 除了做技术指标，也要做业务指标监控

		- 订单、用户数、在线用户数等
		- 业务指标出了问题，那可能是技术上有问题、需要找
		- 如果没做业务指标，业务上说有问题，
技术拿出技术监控说没问题，扯皮，不断积累矛盾

- 2.系统监控 
- 3.容量规划 
- 4.报警预警 

	- 报警
	- 预警

		- 减少线上出现问题，可以减少一个数量级
		- 自己主动告知用户，是故事，被动由客户提出，那就是事故

- 5.运维流程 

	- 上线时出问题，立即回滚，不能说等3-5min解决继续上线，这不现实，会引入风险
	- 标准操作流程SOP

- 6.故障处理

	- 根因分析 COE
	- 事故报告

### 其他

- 制度与流程

	- 制度管人，流程管事
	- 按制度和流程来，就可以免责~.~

## 9、Spring Cloud

### 基本情况

- spring, spring boot是框架，spring cloud是解决方案
- 目前业界

	- spring cloud alibaba

		- nacos
		- sentinel

	- 腾讯TSF

		- 使用现有的ribbon feign

	- 常见解决方案

		- 
		- 
		- 

			- https://www.processon.com/view/5b7d2f1fe4b075b9fe22d3d2

### 各个组件

- 注册中心
- 配置中心
- 网关

	- Zuul

		- BIO，性能有限

	- Zuul2

		- 比spring cloud gateway晚
		- 直接利用netty，封装了netty
		- 

	- Spring Cloud Gateway

		- 基于webflux（基于reactor netty）
		- 可以找秦老师的仓库找找例子

- 客户端

	- Feign

		- 作为HTTP Client访问REST服务接口。
		- 优势

			- 1、全都基于注解，简单方便
			- 2、跟XXTemplate一样，内置了简化操作，OOP
			- 3、跟其他组件，ribbon，hytrix联合使用

	- Ribbon

		- 强大在支持多协议
		- 用于云环境的一个客户端内部通信（IPC）库。
		- 特性

			- 1、负载均衡
			- 2、容错
			- 3、多协议支持(HTTP, TCP, UDP)，特别是异步和反应式下
			- 4、缓存和批处理

- 流量控制（限流/熔断）

	- Hystrix
	- Sentinel

		- 可以找秦老师的仓库找找例子
		- 

	- 其他主题

		- 所有接口都用一个超时时间，比如100ms是不科学
可以按权重计算
		- 流控策略

			- 每分钟可以访问20M的数据
			- VIP用户每分钟可以访问1w次，普通用户可以访问100次

		- 内网不建议走域名，因为域名肯定要走DNS，而DNS域名解析有缓存，可能导致本地没有感知到最新的数据

	- soul

		- 有管理端界面，可以动态改

	- 

- 

## 10、微服务相关框架与工具

### 常见APM

- Apache skywalking
- pinpoint
- zipkin
- twitter
- jaeger

### 监控

- ELK
- Promethus + Grafana + MQ + 时序数据库(InfluxDB/openTSDB等)

### 可观测性

- logging
- tracing
- metrics

### 权限控制

- 最核心的3A

	- Authc
	- Authz
	- Audit

- 其他

	- 统一认证中心CAS
	- 单点登录SSO
	- LDAP
	- ticket granted token
st
	- spring security, apache shiro

		- 秦老师：推荐shiro，结构与sentinel类似

### 数据处理

- 1、读写分离与高可用HA：
- 2、分库分表Sharding：
- 3、分布式事务DTX
- 4、数据迁移Migration
- 5、数据集群扩容Scaling
- 6、数据操作审计Audit
- 

### 网关与通信

- 流量网关与WAF

	- 基本都是nginx

		- 基于nginx：Kong/Apisix

- 业务网关

	- zuul
	- zuul2
	- spring cloud gateway
	- soul

- REST与其他协议之争

## 其他

### 二方库

- 从三方库来的概念
- 一方：自己开发的
- 三方：外面的库
- 二方：同一个公司、不同部门

### API与SPI

- API是给用户直接调用
- 接口是空、实现需要自己来、再将自己实现融入到框架中，就是SPI——service provider interface
- 典型案例：JDBC

*XMind - Evaluation Version*

微服务间通信可以采用通常的HTTP方式或者RPC方式，做了很多都是利用HTTP方式进行直接调用的，至于RPC方式的代理、序列化与协议真的好基础。