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
- cr: Client Received. Signifies the end of the span. The client has successfully received the response from the server side. Subtracting the cs timestamp from this timestamp reveals the whole time needed by the client to receive the response from the server.
- cr:  客户端接收。标示跨度结束。和护短成功从服务端接收到响应。这个时间戳减去 cs 时间戳得到客户端从服务端接收到响应的全部时间。
