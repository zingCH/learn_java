# 4java相关框架

## 1、Spring

### 框架设计

- framewor6大模块

	- 1. Core：Bean/Context/AOP
	- 2. Testing：Mock/TestContext
	- 3. DataAccess: Tx/JDBC/ORM
	- 4. Spring MVC/WebFlux: web
	- 5. Integration: remoting/JMS/WS
	- 6. Languages: Kotlin/Groovy

- 

### AOP

- 概念

	- 面向切面编程
	- 加一个中间层代理（字节码增强）来实现所有对象的托管。

- 实现方式

	- 动态代理

		- 接口类型

			- 默认使用JDK动态代理
			- 若proxyTargetClass配置为true，则采用CGLIB

		- 非接口类型

			- 默认使用CGLIB

	- 动态代理内部实现

		- JDK动态代理

			- Java反射

		- CGLIB动态代理

			- 字节码增强

	- 字节码增强工具

		- 

### IoC

- 概念

	- 控制反转
	- 又称DI（Dependency Injection）依赖注入

### 核心原理

- spring bean生命周期

	- 
	- 
	- 个人理解

		- 生命周期就4个环节：
1、实例化
2、属性赋值
3、初始化
4、销毁

只不过在上面这4个环节，添加扩展点：属性赋值之后、初始化之前，添加了Aware装配、BeanPostProcessor前置处理、InitializingBean接口，初始化之后添加BeanPostProcessor后置处理。

	- 

- Spring管理对象生命周期以后， 也就改变了编程和协作模式

	- 

### spring bean配置方式

- 

### 其他

- spring messaging

### 思考

- 什么类型的循环依赖Spring无法处理？
- 除了Spring，循环依赖还有哪些类似场景？
- 一个对象的代理有哪些种类？用在什么场景？

## 2、Spring Boot

### 基础

- 出发点

	- - 让开发变简单
	- - 让配置变简单
	- - 让运行变简单

- 实现思路

	- 约定优于配置

- 其他

	- 限定性框架和非限定性框架

		- 限定性框架

			- eureka/hystrix/xxx-starter......

		- 非限定性框架

			- spring framework/......

### 使用

- 官网：start.spring.io

### 核心原理

- 自动化配置：简化配置核心

	- 基于Configuration, EnableXXX, Condition
	- 
	- @SpringBootApplication

		- SpringBoot 应用标注在某个类上说明这个类是 SpringBoot 的主配置类，SpringBoot 就会运行这个类的 main 方法来启动 SpringBoot 项目。
		- •@SpringBootConfiguration
		- •@EnableAutoConfiguration
		- •@AutoConfigurationPackage
		- •@Import({AutoConfigurationImportSelector.class})
		- 加载所有 META-INF/spring.factories 中存在的配置类（类似 SpringMVC 中加载所有 converter）

	- 条件化自动配置：Condition相关注解

		- @ConditionalOnBean
		- @ConditionalOnClass
		- @ConditionalOnMissingBean
		- @ConditionalOnProperty
		- @ConditionalOnResource
		- @ConditionalOnSingleCandidate
		- @ConditionalOnWebApplication

- spring-boot-starter：脚手架核心

	- 整合第三方类库，协同工具
	- 自己手写一个starter

		- 1、spring.provides
		- 2、spring.factories
		- 3、additional--metadata
		- 4、自定义 Configuration 类

- 

## 3、JDBC与数据库连接池

### JDBC

- 定义了数据库交互接口

	- DriverManager
	- Connection
	- Statement
	- ResultSet

- Java 操作数据库的各种类库，
都可以看做是在 JDBC 上做的增强实现

	- 加上 XA 事务--XAConnection
	- 从连接池获取--PooledConnection
	- MySQL 驱动 JDBC 接口--Connection

- 

### 数据库连接池

- 常见

	- C3P0
	- DBCP
	- Druid
	- Hikari

## 4、ORM Hibernate/MyBatis

### Hibernate

- 操作方式

	- HQL
	- Criteria
	- native SQL

- 

### MyBatis

- 结构

	- 

### MyBatis与Hibernate比较

- Mybatis 优点：原生 SQL（XML 语法），直观，对 DBA 友好
- Hibernate 优点：简单场景不用写 SQL（HQL、Cretiria、SQL）
- Mybatis 缺点：繁琐，可以用 MyBatis-generator、MyBatis-Plus 之类的插件
- Hibernate 缺点：对 DBA 不友好

## 5、Spring 集成 ORM 与 JPA

### JPA

- 

### Spring管理事务

- 
- 隔离级别
- 传播性
- 常见问题

	- JDBC 层，数据库访问层，怎么操作事务？编程式事务管理
	- Spring 怎么做到无侵入实现事务？声明式事务管理：事务管理器+AOP
	- 

## 6、Spring Boot 集成 ORM 与 JPA

### 略

## 思考

### java中两个不同的进程如何通信？

- guava event bus
- java spi机制
- callback机制
- 其他参考资料

	- https://blog.csdn.net/weixin_41835916/article/details/81436022
	- https://blog.csdn.net/u012232736/article/details/79878688

## 7、java 8 lambda

### 与匿名类实现方式的不同

- lambda是调用java 7的invokedynamic，没有生成子类
- 匿名类还是会生成一个子类

### 函数式接口
@FunctionalInterface

- Predicate<T> 有参数、条件判断 
- Function<T, R> 有参数、有返回值 
- Consumer<T> 无返回值 
- Supplier<T> 无参数、有返回值

### lambda 匿名实现接口时，只能有一个实现
——方法签名必须和接口完全一样。如果有多个，需要指明类型，保证方法签名不一样

### 进一步简化：方法引用

- 案例

## 8、java 8 stream

### 一个来自数据源的元素队列并支持聚合操作

### 流式风格fluent style

### 内部迭代

### 什么是流

- 

### 中间操作

- 选择与过滤

	- filter(Predicate p)
	- distinct()
	- limit(long maxSize)
	- skip(long n)

- 映射

	- map(Function f)
	- mapToDouble(ToDoubleFunction f)
	- mapToInt(ToIntFunction f)
	- mapToLong(ToLongFunction f) 
	- flatMap(Function f) 

- 排序

	- sorted()
	- sorted(Comparator comp)

- 终止操作

	- 查找
	- 规约

		- reduce

			- 注意需要初始化值，比如加法是0，乘法是1

	- 收集
	- 迭代
	- 

## 9、lombok

### 原理

- 基于JSR269
- 基于字节码增强，编译器处理

### 常用

- @Setter 
- @Getter 
- @Data 
- @XXXConstructor 
- @Builder 
- @ToString 
- @Slf4j

## 10、google guava

### 集合

- 不可变集合
- 新集合类型: multisets, multimaps, tables, bidirectional maps等
- 强大的集合工具类
- 扩展工具类

### 缓存

### 并发

- ListenableFuture：完成后触发回调的Future

### 字符串处理

### 事件总线

## 11、设计原则与设计模式

### 设计原则

- SOLID

	- 

### 规范

- 编码规范、checkstyle

	- 常见的编码规范：
 1、Google 编码规范：https://google.github.io/styleguide/javaguide.html 

2、Alibaba 编码规范：https://github.com/alibaba/p3c 

3、VIP 规范：https://vipshop.github.io/vjtools/#/standard/

- 其他规范

	- 其他规范： 架构设计规范，技术调研规范，数据库规范等等。

### 设计模式

- 
- 本质是一类特定场景下通用解决经验。
- GoF 23种设计模式

	- 关系
	- 分类

		- 创建型 
1. Factory Method（工厂方法） 
2. Abstract Factory（抽象工厂） 
3. Builder（建造者） 
4. Prototype（原型） 5. Singleton（单例）
		- 结构型 
6. Adapter（适配器） 
7. Bridge（桥接） 
8. Composite（组合） 
9. Decorator（装饰） 
10. Facade（外观） 
11. Flyweight（享元） 
12. Proxy（代理）
		- 行为型
13. Interpreter（解释器） 
14. Template Method（模板方法） 
15. Chain of Responsibility（责任链） 
16. Command（命令）
17. Iterator（迭代器） 
18. Mediator（中介者） 
19. Memento（备忘录） 
20. Observer（观察者） 
21. State（状态） 
22. Strategy（策略） 
23. Visitor（访问者）

- 设计模式与反模式

	- 

## 12、单元测试

### 如何做单元测试

- 1. 单元测试方法应该每个方法是一个case，断言充分，提示明确 
- 2. 单测要覆盖所有的corner case 
- 3. 充分使用mock（一切皆可mock） 
- 4. 如果发现不好测试，则说明业务代码设计存在问题，可以反向优化代码 
- 5. 批量测试用例使用参数化单元测试 
- 6. 注意测试是单线程执行 
- 7. 合理使用before, after, setup准备环境 
- 8. 合理使用通用测试基类 
- 9. 配合checkstyle，coverage等工具 
- 10.制定单元测试覆盖率基线

### 工具

- JUnit -> TestCase, TestSuite, Runner 

SpringTest 

Mock技术 
- Mockito 
- easyMock

### 单元测试的常见陷阱与经验

- 1. 尽量不要访问外部数据库等外部资源 
- 2. 如果必须用数据库考虑用嵌入式DB+事务自动回滚 
- 3. 防止静态变量污染导致测试无效 
- 4. 小心测试方法的顺序导致的不同环境测试失败 
- 5. 单元测试总时间特别长的问题

*XMind - Evaluation Version*

Spring在项目中是绝对基石，十分重要却也总是视而不见，值得一再学习与深入理解。