## Nacos Server

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
