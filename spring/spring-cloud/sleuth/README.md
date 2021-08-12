## Introduction

Spring Cloud Sleuth 是 Spring Cloud 的一个组件，它的主要功能是在分布式系统中提供服务链路追踪的解决方案。

微服务架构是一个分布式架构，它按业务划分服务单元，一个分布式系统往往有很多个服务单元。
由于服务单元数量众多，业务的复杂性，如果出现了错误和异常，很难去定位。
主要体现在，一个请求可能需要调用很多个服务，而内部服务的调用复杂性，决定了问题难以定位。
所以微服务架构中，必须实现分布式链路追踪，去跟进一个请求到底有哪些服务参与，参与的顺序又是怎样的，从而达到每个请求的步骤清晰可见，出了问题，很快定位。

## Terminology

Spring Cloud Sleuth 借鉴了 Dapper 的术语。

Span:

- 基本的工作单元。例如，发送一个 RPC 是一个新的跨度，就象向 RPC 发送响应一样。跨度也包含其他数据，诸如描述时间戳事件，键值对标签，引起 ta 们的跨度 ID 和进程 ID(通常是 IP)。
- 跨度能启动和终止，并且他们跟踪它们的时间信息。一旦你创建一个跨度，你必须在将来某个时终止它间。

Trace:

- 一组跨度的集合形成树状结构。例如，如果你运行一个分布式的大数据存储，追踪可能由一个 Put 请求形成。

Annotation/Event:

- 用于及时记录事件的存在。
- cs: 客户端发送，客户端发起一个请求。这个注释指示了跨度的开始。
- sr: 服务端接收。服务端获取到请求并且开始处理它。从这个时间戳减去 cs 的时间戳可得到网络延迟。
- ss: 服务端发送。在请求处理完成时注释(响应被发送会客户端)。这个时间戳减去 sr 时间戳得到服务端处理请求的时间。
- cr:  客户端接收。标示跨度结束。和护短成功从服务端接收到响应。这个时间戳减去 cs 时间戳得到客户端从服务端接收到响应的全部时间。

## Span Lifecycle with Spring Cloud Sleuth’s API

Spring Cloud Sleuth Core 在他的 api 模块中包含所有必要的要有追踪者实现的接口。

最常用的接口有：

- org.springframework.cloud.sleuth.Tracer: 使用追踪者，你能创建一个根跨度去捕获关键路径的请求。
- org.springframework.cloud.sleuth.Span: 跨度是一个单独工作单元它碧玺有启动和结束。包含计时信息和事件和标签

Span 生命周期行为如下:

- start: 当你开始一个跨度，它的名字被分配并且记录开始的时间戳。
- end: 跨度被结束(跨度的结束时间被记录)，并且如果跨度被采样，它是有资格被收集。
- continue: 跨度被另一个线程继续。
- create with explicit parent: 你可以创建一个新的跨度并且为它设置一个明确的父级跨度。

## Context Propagation
Traces connect from service to service using header propagation. 

Traces从服务连接到服务使用header传播。默认格式是B3.
```
   Client Tracer                                                  Server Tracer     
┌───────────────────────┐                                       ┌───────────────────────┐
│                       │                                       │                       │
│   TraceContext        │          Http Request Headers         │   TraceContext        │
│ ┌───────────────────┐ │         ┌───────────────────┐         │ ┌───────────────────┐ │
│ │ TraceId           │ │         │ X-B3-TraceId      │         │ │ TraceId           │ │
│ │                   │ │         │                   │         │ │                   │ │
│ │ ParentSpanId      │ │ Inject  │ X-B3-ParentSpanId │ Extract │ │ ParentSpanId      │ │
│ │                   ├─┼────────>│                   ├─────────┼>│                   │ │
│ │ SpanId            │ │         │ X-B3-SpanId       │         │ │ SpanId            │ │
│ │                   │ │         │                   │         │ │                   │ │
│ │ Sampling decision │ │         │ X-B3-Sampled      │         │ │ Sampling decision │ │
│ └───────────────────┘ │         └───────────────────┘         │ └───────────────────┘ │
│                       │                                       │                       │
└───────────────────────┘                                       └───────────────────────┘
```

Similar to data formats, you can configure alternate header formats also,
与数据格式类似,也可以配置代替的header格式，提供的traceId 和 spanId和B3兼容。最值得注意的是，这表明traceId 和 spanId是小写的，不是uuid
除了trace标识符，其他属性也可以一起随着请求传递。

要使用提供的默认值你可以设置spring.sleuth.propagation.type属性。值可以是一个集合，这种情况下你将可以传播更多的追踪header。

## Zipkin

Zipkin 是 Twitter 的一个开源项目，它基于 Google 的 Dapper 实现。Zipkin 用于收集分布式系统的链路数据，提供了数据持久化策略，也提供
面向开发者的 API 接口用于查询数据。

Zipkin 提供了可插拔式的数据存储方式，目前支持的数据存储有 In-Memory,Mysql,Cassandra 和 ElasticSearch。

![avatar](https://gitee.com/xuzimian/Image/raw/master/Spring/SpringCloud/zipkin_struct_flow.png)

1. instrumented 数据采集：我们可以想象数据就像一个水流，流过一个管道这个管道就是 instrumented。instrumented 需要过滤那些数据需要采集上报，那些不需要。就形成了图里面 instrumented clinet 的两个分支。
2. transport 数据上报代理：数据在发送到服务端可以使用多种渠道，例如：发送 http 请求、发送 mq 消息等。
3. collector 数据接收汇总：接收 transport 发送的数据由于发送渠道多种多样，所以接收端要适配各种渠道
4. storage 数据存储模块：由于数据存储介质多种多样，例如：内存、mysql 等，所以存储模块要适配各种介质
5. api 和 ui 负责监控数据的展示：通过浏览器展示监控数据

### Zipkin deploy and run

- Docker 方式:

```sh
docker run -d -p 9411:9411 openzipkin/zipkin
```

- Jar 包方式（JDK8）

```sh
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```

设置启动参数例子:

```sh
java -jar zipkin-server-***-exec.jar --zipkin.collector.rabbitmq.addresses=localhost --zipkin.collector.rabbitmq.port=6572 --zipkin.collector.rabbitmq.username=guest --zipkin.collector.rabbitmq.password=guest --STORAGE_TYPE=mysql --MYSQL_HOST=localhost --MYSQL_TCP_PORT=3306 --MYSQL_DB=zipkin --MYSQL_USER=root --MYSQL_PASS=rootroot
```

### Spring Cloud Sleuth Intergation Zipkin

在 application.yaml 文件中配置 zikpin server url 即可

```yaml
spring:
  zipkin:
    base-url: http://localhost:9411/
```
