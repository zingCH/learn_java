# JVM基础知识

## 字节码

### 单字节构成，理论上最多256个指令

### 四大类

- 栈操作指令
- 程序流程指令
- 对象操作指令

	- 包括方法调用指令

- 算术运算以及类型转换指令

### 方法调用的指令

- invokestatic

	- 调用静态方法

- invokespecial

	- 用来调用构造函数，但也可以用于调用同一个类中的 private 方法, 以及可见的超类方法

- invokevirtual

	- 如果是具体类型的目标对象，invokevirtual 用于调用公共，受保护和package 级的私有方法

- invokeinterface

	- 调用接口

- invokedynamic

	- JDK7 新增加的指令，是实现“动态类型语言”（Dynamically Typed Language）支持而进行的升级改进，同时也是 JDK8 以后支持 lambda 表达式的实现基础

## JVM类加载器

### 类的生命周期

- 加载
- 验证
- 准备
- 解析
- 初始化
- 使用
- 卸载

### 类的加载时机

- 

### 不会初始化（可能会加载）

- 

### 3类加载器

- BoostrapClassLoader
- ExtendClassLoader
- ApplicationClassLoader
- 自定义ClassLoader

### 技巧

- 添加引用类的几种方式

	- 放到 JDK 的 lib/ext 下，或者-Djava.ext.dirs
	- java –cp/classpath 或者 class 文件放到当前路径
	- 自定义 ClassLoader 加载
	- 拿到当前执行类的 ClassLoader，反射调用 addUrl 方法添加 Jar 或路径(JDK9 无效)。
	- 

## JVM内存模型（JMM）

### JVM栈内存整体结构

- 

	- 每启动一个线程，JVM 就会在栈空间栈分
配对应的 线程栈, 比如 1MB 的空间（-
Xss1m）。

### JVM栈内存结构

- 

### JVM堆内存结构

- 
- Non-Heap

	- 本质上还是 Heap，只是一般不归 GC管理，里面划分为 3 个内存池。

		- meta space

			- 原先的永久代

		- Compressed Class Space

			- 存放class信息，与metaspace有交叉

		- code cache

			- 存放JIT编译后的本地机器代码

### 常见问题

- volatile内存屏障

	- DCL为何要加volatile

- as-if-serial

	- 单线程，但CPU指令可能乱序执行，但要保证效果与顺序执行一致

- happen-before

### JMM规范：JSR133

- 

## JVM启动参数

### 参数分类

- 标准参数

	- -开头
	- 所有的 JVM 都要实现这些参数，并且向后兼容

- 非标参数

	- -X开头

- 非稳定参数

	- -XX开头
	- -XX：+-Flags 形式, +- 是对布尔值进行开关。
	- -XX：key=value 形式, 指定某个选项的值

- -D开头设置系统属性

### 常见参数配置

- 堆内存相关

	- -Xmx

		- 最大堆内存
		- 不包含栈内存，也不包含堆外使用的内存

	- -Xms

		- 堆初始大小

	- 
	- -Xss
	- -Xmn

		- 等价于 -XX:NewSize

- GC相关

	- 

- 分析诊断

	- 

- java agent

	- 

- 运行模式

	- 

- 系统属性

	- 

*XMind - Evaluation Version*