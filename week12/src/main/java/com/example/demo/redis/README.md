# Redis 配置

## Redis 安装

```shell
// 手动安装
mkdir redis && cd redis
curl -O http://download.redis.io/redis-stable.tar.gz
tar xzvf redis-stable.tar.gz
cd redis-stable
make
make test
sudo make install

// Homebrew
brew install redis
```



## Redis 多节点配置

* 共配置 6 个节点：三主三从，`port: 6379~6384`
* 各节点配置文件如下：
  * 配置目录：conf  配置文件格式：redis-6379.conf
  * 数据目录：data  数据目录格式：redis-6379
  * 日志目录：logs  日志文件格式：redis-6379.log

* 修改 redis.conf 文件，配置多个节点的配置文件，以 redis-6379 节点为例，其配置为 `~/conf/redis-6379.conf`，详细配置在 conf 文件夹下：

  ```redis.conf
  // 后台启动
  daemonize yes
  
  // 端口号
  port 6379
  
  // 进程文件
  pidfile "/var/run/redis_6379.pid"
  
  // 数据目录
  dir "/Users/kai/data/redis/redis-6379"
  
  // 日志文件
  logfile "/Users/kai/logs/redis/redis-6379.log"
  ```

* 启动 6 个节点

  ```
  // 以 redis-6379 为例
  redis-server redis-6379.conf
  ```

* 检查 Redis 是否正常工作

  ``` 
  redis-cli -h 127.0.0.1 -p 6379
  
  $ redis-cli ping
  PONG
  ```



## Redis 主从配置

```
redis-cli -h 127.0.0.1 -p 6380

// 配置 6380 为 6379 的从节点
slaveof 127.0.0.1 6379

// 取消从节点配置
slaveof no one
```



## Redis 主从切换：Redis Sentinel 高可用

* 共配置 3 个 Sentinel，分别对应三个主节点，`port: 26379~26381`

* 修改 sentinel.conf 文件，配置多个节点的配置文件，以 sentinel-6379 节点为例，其配置为 `~/conf/sentinel-26379.conf`，详细配置在 conf 文件夹下：

  ```
  daemonize yes
  
  port 26379
  
  pidfile "/var/run/redis-sentinel-26379.pid"
  
  logfile "/Users/kai/logs/redis/sentinel-26379.log"
  ```

* 启动 Sentinel

  ``` 
  // 以 sentinel-26379 为例
  redis-sentinel sentinel-6379.conf
  ```

* 检查 Redis Sentinel 是否正常工作

  ```
  redis-cli -h 127.0.0.1 -p 26379
  
  sentinel masters
  ```



## Redis Cluster 配置

* 修改 Redis 6 个节点的配置文件

  ```
  // 后台启动
  daemonize yes
  
  // 端口号
  port 6379
  
  // 进程文件
  pidfile "/var/run/redis_6379.pid"
  
  // 数据目录
  dir "/Users/kai/data/redis/redis-6379"
  
  // 日志文件
  logfile "/Users/kai/logs/redis/redis-6379.log"
  
  // 开启集群模式
  cluster-enabled yes 
  
  // 集群配置文件
  cluster-config-file nodes-6379.conf 
  ```

* 启动 6 个节点

  ```
  // 以 redis-6379 为例
  redis-server redis-6379.conf
  ```

* 检查 Redis 是否正常工作

  ``` 
  redis-cli -h 127.0.0.1 -p 6379
  
  $ redis-cli ping
  PONG
  ```

* 节点握手

  ```
  // 由 redis-6379 发起节点握手
  redis-cli -h 127.0.0.1 -p 6379
  
  127.0.0.1:6379> cluster meet 127.0.0.1 6378
  
  127.0.0.1:6379> cluster meet 127.0.0.1 6379
  
  127.0.0.1:6379> cluster meet 127.0.0.1 6380
  
  127.0.0.1:6379> cluster meet 127.0.0.1 6381
  
  127.0.0.1:6379> cluster meet 127.0.0.1 6382
  
  127.0.0.1:6379> cluster meet 127.0.0.1 6383
  
  127.0.0.1:6379> cluster meet 127.0.0.1 6384
  
  // 查看集群节点
  127.0.0.1:6379> cluster nodes
  
  // 查看集群信息
  127.0.0.1:6379> cluster info
  ```

* 分配槽

  ```
  redis-cli -p 6379 cluster addslots {0..5461}
  
  redis-cli -p 6380 cluster addslots {5462..10922}
  
  redis-cli -p 6381 cluster addslots {10923..16383}
  ```

* 主从配置

  ```
  redis-cli -h 127.0.0.1 -p 6382
  127.0.0.1:6382> cluster replicate a6bc71377d91b810c9ca337574779e2d9e5dd364 (6379 id)
  
  redis-cli -h 127.0.0.1 -p 6383
  127.0.0.1:6383> cluster replicate c1c4e48af7f16d308197d1ccf1df6225d99f697c (6380 id)
  
  redis-cli -h 127.0.0.1 -p 6384
  127.0.0.1:6384> cluster replicate 8eccb7753e6ee20f30c40fd0adbd4ac5b95f4c0e (6381 id)
  ```

  