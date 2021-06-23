# hello-world

## Introduction

- Plugin tutorial reference link:
  https://wiki.jenkins.io/display/JENKINS/Plugin+tutorial#Plugintutorial-CreatingaNewPlugin
- Plugin Cookbook:https://wiki.jenkins.io/display/JENKINS/Plugin+Cookbook
- Plugin parameter extension point: https://www.jenkins.io/doc/developer/extensions/jenkins-core/#parameterdefinition
- Jelly reference link: http://commons.apache.org/proper/commons-jelly/index.html
- Jelly Tag: https://reports.jenkins.io/core-taglib/jelly-taglib-ref.html#form:nested
- 开发 Jenkins 插——在 Hello World 之后: https://leavez.xyz/2020/06/06/jenkins-plugin/
- 浅析 Jenkins 插件开发: http://www.uml.org.cn/jchgj/201802073.asp
- Jenkins插件开发: https://github.com/sunweisheng/Jenkins/blob/master/Plug-in-Dev.md

### 可供参考的实例

- GithubBranchParameterDefinition: https://github.com/jenkinsci/DotCi
- listGitBranches:  https://github.com/jenkinsci/git-parameter-plugin
- buildSelector:  https://github.com/jenkinsci/copyartifact-plugin
- agentParameter:  https://github.com/jenkinsci/agent-server-parameter-plugin


- idea plugin: Stapler Framework Support for IntelliJ IDEA

## Getting started

install dependency

````shell
./mvnw verfiy
````

enable a embedded jenkins instance

````shell
./mvnw hpi:run
````

## 知识点

### Jenkins运行周期:

1. checkout -check out出源码
2. Pre-build - 预编译
3. Build wrapper-准备构建的环境，设置环境变量等
4. Builder runs -执行构建，比如调用calling Ant, Make 等等
5. Recording - 记录输出，如测试结果
6. Notification - 通知成员

### Jenkins提供的扩展点：

1. Builder：这个扩展点支撑的是构建这个阶段需要做的事情，包括prebuild postbuid、构建环境等步骤
2. Notifier：包括通知、消息通知等情况，

### ExtensionPoint

每个 Extension Point 都实现了一个叫 ExtensionPoint 的 interface。但我们要做的不是要直接实现 interface 的方法，而是为我们的子类添加 @Extension
的注解（Annotation）。这样 Jenkins 在启动的时候，就会自动把这个类的功能加载到系统中。

````java

@Extension
public class MyHandler extends QueueDecisionHandler {
}
````

但并不是所有 Extension Point 都是简单对子类添加 @Extension 即可。需要添加 UI 元素的 Extension Point，需要对 DescriptorImpl 添加注解，而不是对子类本身。

具体 UI 样式由 Jelly 文件来实现，是一种 xml 描述。Jelly 文件需要存放在 resource 目录下的对应路径里。例如下面代码中的 MyBuilder 的 UI 就应该在 resource 下建一个
MyBuilder/config.jelly 文件。每个可以实现 UI 的 Extension Point 都符合 Describable 接口，一般需要在子类里实现一个符合该接口的内部类。UI 出现的位置，是由 Extension
Point 的种类决定的。比如 Builder 就会出现在 Job 设置中的 Build 段，而 BuildWrapper 则出现在 Build Environment 段。

```java
public class MyBuilder extends Builder {

    @DataBoundConstructor
    public MyBuilder(MyJobSetting setting, String script) {
        // ...
    }

    @Extension
    public static class DescriptorImpl extends BuildStepDescriptor<Builder> {
        // BuildStepDescriptor 即为 Describable 的一个抽象类
        @Override
        public String getDisplayName() { // UI 元素中显式的名字
            return "my builder";
        }
    }
}
```

在 UI 与代码的数据流动中，代码中的 getter 供给 jelly 文件中 filed 字段同名的 UI 元素的内容；在从 web 界面保存设置时，jelly 中对应的字段会寻找标有 @DataBoundConstructor
的初始化方法，并供给同名参数，生成我们的子类。如果一个字段不是基本类型，则需要该类型实现 Describable 接口并实现自己的 jelly 文件。

### Debug

```shell
export MAVEN_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"

mvn hpi:run -Djetty.port=8090
```

然后可使用idea remote debug 5005端口

### Basic plugins

> Notes: GitParameter 需要安装一些基础的jenkins插件:
> - Bitbucket Branch Source Plugin : cloudbees-bitbucket-branch-source
> - Pipeline : workflow-aggregator
> - Multibranch Pipeline : workflow-multibranch

### jenkins bitbucket app password

RAbwMyXYwqzBDXrh6B9L

````shell
#!/bin/sh
if [ -z "${DISPLAY}" ]; then
  DISPLAY=:123.456
  export DISPLAY
fi
ssh -i "/var/folders/hv/zym77pr50v34m9w3njtfbgw00000gn/T/git_parameter_2966853048841129381@tmp/jenkins-gitclient-ssh11007080527814696297.key" -l "git" -o StrictHostKeyChecking=no "$@"
````

### Bitbucket APi Url:


- https://api.bitbucket.org/2.0/repositories/xuzimian/jenkins-pipeline/Commits?include