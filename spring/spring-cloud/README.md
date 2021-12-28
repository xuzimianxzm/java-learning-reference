## Nacos Server

### Nacos start

本模块内使用的服务注册时 Alibaba Nacos，可以从 最新稳定版本 下载 nacos-server-$version.zip 包。

- https://github.com/alibaba/nacos/releases

```sh
 unzip nacos-server-$version.zip 或者 tar -xvf nacos-server-$version.tar.gz
  cd nacos/bin
```

启动命令(standalone 代表着单机模式运行，非集群模式):

```sh
sh startup.sh -m standalone
```

如果您使用的是 ubuntu 系统，或者运行脚本报错提示[[符号找不到，可尝试如下运行：

```sh
bash startup.sh -m standalone
```

- 访问 nacos 地址: http://localhost:8848/nacos ,默认登录名和密码都是 nacos

### Nacos Docker start

参考:
https://nacos.io/zh-cn/docs/quick-start-docker.html

- 单机模式 Derby

```sh
docker-compose -f nacos/standalone-derby.yaml up
```

- 如果希望使用 MySQL5.7

```sh
docker-compose -f nacos/standalone-mysql-5.7.yaml up
```

- 如果希望使用 MySQL8

```sh
docker-compose -f nacos/standalone-mysql-8.yaml up
```

#### 集群模式

```sh
docker-compose -f nacos/cluster-hostname.yaml up
```

- 服务注册

```sh
curl -X POST 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=nacos.naming.serviceName&ip=20.18.7.10&port=8080'
```

- 服务发现

```sh
curl -X GET 'http://127.0.0.1:8848/nacos/v1/ns/instance/list?serviceName=nacos.naming.serviceName'
```

- 发布配置

```sh
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos.cfg.dataId&group=test&content=helloWorld"
```

- 获取配置

```sh
  curl -X GET "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos.cfg.dataId&group=test"
```

## LoadBalance: Application Traffic Control

流量控制指的是根据一些流量特征，控制其流向下游的动作，流量控制可以应用在很多业务场景中，比如金丝雀发布,同机房发布，标签路由，全链路灰度发布。

- 金丝雀发布：其应用只有新和老两个版本，根据流量特征，将部分流量流入新版本进行版本验证，确定新版本稳定后再全量发布。
- 同机房优先路由：当公司规模扩大之后，应用会夸机房部署来达到高可用的目的。由于异地夸机房调用出现的网络延迟问题，需要确保服务消费方能优先调用相同机房的服务消费方。
- 全链路会读：当公司规模扩大之后，微服务数量会增多。微服务数量众多的情况下进行灰度发布会发现整个链路非常长。全链路会读解决的问题是保证特定流量能够路由到所有的特殊灰度版本。

根据以上场景总结出流量控制的前提是需要一下两个能力：

- 流量识别能力
- 实力打标能力

### Gray Publish Traffic Control Demo

本模块 Demo 简单模拟了灰度发布的流量控制场景，其包含：

- nacos: 服务发现,基础依赖服务，必须先启动(需自行前往下载)
- basic-servers/nacos-normal-provider-server ： 正常服务提供方
- basic-servers/nacos-gray-provider-server ： 灰度服务提供方
- load-balance/nacos-consumer-load-balance-enhance: 服务消费端，通过灰度标记，来决定是否调用对应的服务提供方

下面两个命令分别使用 RestTemplate 和 OpenFeign 发起服务调用，结果都会路由到会读实例上，如果没有"Gray:true"这个 Header,结果都会路由到正常实例上：

```sh
curl -s -H "Gray:true" http://localhost:8888/echoFeign
curl -s -H "Gray:true" http://localhost:8888/echo
```

## Configuration Management

### Gray Publish Dynamic Update Traffic Control Demo

本 Demo 基本上和上面的 Demo 差不多，都是全链路灰度流量控制，区别是本项目中会根据 nacos 配置中心配置的数据来动态识别流量是否切换到灰度，

- nacos: 服务发现,基础依赖服务，必须先启动(需自行前往下载)
- common/nacos-normal-provider-server ： 正常服务提供方
- common/nacos-gray-provider-server ： 灰度服务提供方
- configuration-management/nacos-consumer-load-balance-dynamic-update-enhance: 服务消费端，通过灰度标记，来决定是否调用对应的服务提供方

需要额外在 nacos 配置中心配置如下内容配置:

- Data ID: nacos-consumer-load-balance-dynamic-update-enhance.properties
  (注意: Data ID 必须和对应项目上的 spring.application.name 一致)

- 配置内容:
  ```properties
  traffic.rule.type=header
  traffic.rule.name=Gray
  traffic.rule.value=true
  ```
