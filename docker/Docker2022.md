# **Docker与微服务实战2022 尚硅谷讲师:周阳**

![](image/image1.png){width="1.0413429571303587in"
height="0.7093842957130359in"}

![](image/image2.png){width="4.306310148731408in"
height="2.6264490376202976in"}



## 1、基础篇

### 1.1、docker简介

####  是什么

-   问题：为什么会有docker出现

> 假定您在开发一个尚硅谷的谷粒商城，您使用的是一台笔记本电脑而且您的开发环境具有特定的配置。其他开发人员身处的环境配置也各有不同。您正在开发的应用依赖于您当前的配置且还要依赖于某些配置文件。此外，您的企业还拥有标准化的测试和生产环境，且具有自身的配置和一系列支持文件。您希望尽可能多在本地模拟这些环境而不产生重新创建服务器环境的开销。请问？
>
> 您要如何确保应用能够在这些环境中运行和通过质量检测？并且在部署过程中不出现令人头疼的版本、配置问题，也无需重新编写代码和进行故障修复？
>
>  
>
> 答案就是使用容器。Docker之所以发展如此迅速，也是因为它对此给出了一个标准化的解决方案\-\-\-\--系统平滑移植，容器虚拟化技术。
>
>  
>
> 环境配置相当麻烦，换一台机器，就要重来一次，费力费时。很多人想到，能不能从根本上解决问题，软件可以带环境安装？也就是说，安装的时候，把原始环境一模一样地复制过来。开发人员利用
> Docker 可以消除协作编码时"在我的机器上可正常工作"的问题。
>
>  
>
>  ![](image/image5.jpeg){width="4.6194444444444445in"
> height="2.2868536745406822in"}
>
> 之前在服务器配置一个应用的运行环境，要安装各种软件，就拿尚硅谷电商项目的环境来说，Java/RabbitMQ/MySQL/JDBC驱动包等。安装和配置这些东西有多麻烦就不说了，它还不能跨平台。假如我们是在
> Windows 上安装的这些环境，到了 Linux
> 又得重新装。况且就算不跨操作系统，换另一台同样操作系统的服务器，要**移植**应用也是非常麻烦的。
>
> 传统上认为，软件编码开发/测试结束后，所产出的成果即是程序或是能够编译执行的二进制字节码等(java为例)。而为了让这些程序可以顺利执行，开发团队也得准备完整的部署文件，让维运团队得以部署应用程式，开发需要清楚的告诉运维部署团队，用的全部配置文件+所有软件环境。不过，即便如此，仍然常常发生部署失败的状况。Docker的出现使得Docker得以打破过去「程序即应用」的观念。透过镜像(images)将作业系统核心除外，运作应用程式所需要的系统环境，由下而上打包，达到应用程式跨平台间的无缝接轨运作。

#### docker理念

> Docker是基于Go语言实现的云开源项目。
>
> Docker的主要目标是"Build，Ship and Run Any
> App,Anywhere"，也就是通过对应用组件的封装、分发、部署、运行等生命周期的管理，使用户的APP（可以是一个WEB应用或数据库应用等等）及其运行环境能够做到"一次镜像，处处运行"。
>
> ![](image/image6.jpeg){width="4.6194444444444445in"
> height="2.0984448818897636in"}
>
> Linux容器技术的出现就解决了这样一个问题，而 Docker
> 就是在它的基础上发展过来的。将应用打成镜像，通过镜像成为运行在Docker容器上面的实例，而
> Docker容器在任何操作系统上都是一致的，这就实现了跨平台、跨服务器。只需要一次配置好环境，换到别的机子上就可以一键部署好，大大简化了操作。

-   一句话

    -   解决了运行环境和配置问题的软件容器，
        方便做持续集成并有助于整体发布的容器虚拟化技术。

#### 容器与虚拟机比较

-   容器发展简史

>  
>
> ![](image/image7.jpeg){width="4.6194444444444445in"
> height="1.484821741032371in"}
>
>  
>
> ![](image/image8.jpeg){width="4.6194444444444445in"
> height="3.2209470691163604in"}

-   传统虚拟机技术

> 虚拟机（virtual machine）就是带环境安装的一种解决方案。
>
> 它可以在一种操作系统里面运行另一种操作系统，比如在Windows10系统里面运行Linux系统CentOS7。应用程序对此毫无感知，因为虚拟机看上去跟真实系统一模一样，而对于底层系统来说，虚拟机就是一个普通文件，不需要了就删掉，对其他部分毫无影响。这类虚拟机完美的运行了另一套系统，能够使应用程序，操作系统和硬件三者之间的逻辑不变。  

-------------- -------------- ------------ ---------------------------------- ------------
  Win10          VMWare         Centos7      各种cpu、内存网络额配置+各种软件   虚拟机实例

-------------- -------------- ------------ ---------------------------------- ------------

>  ![](image/image9.png){width="4.6194444444444445in"
> height="0.6280216535433071in"}
>
> ![](image/image10.png){width="4.6194444444444445in"
> height="3.377308617672791in"}
>
> 虚拟机的缺点：
>
> 1    资源占用多               2    冗余步骤多                 3
>    启动慢

-   容器虚拟化技术

> 由于前面虚拟机存在某些缺点，Linux发展出了另一种虚拟化技术：
>
> Linux容器(Linux Containers，缩写为 LXC)
>
> Linux容器是与系统其他部分隔离开的一系列进程，从另一个镜像运行，并由该镜像提供支持进程所需的全部文件。容器提供的镜像包含了应用的所有依赖项，因而在从开发到测试再到生产的整个过程中，它都具有可移植性和一致性。
>
>  
>
> Linux
> 容器不是模拟一个完整的操作系统而是对进程进行隔离。有了容器，就可以将软件运行所需的所有资源打包到一个隔离的容器中。容器与虚拟机不同，不需要捆绑一整套操作系统，只需要软件工作所需的库资源和设置。系统因此而变得高效轻量并保证部署在任何环境中的软件都能始终如一地运行。
>
>  ![](image/image11.png){width="4.6194444444444445in"
> height="3.5437970253718287in"}
>
> ![](image/image12.png){width="4.6194444444444445in"
> height="0.6085498687664042in"}
>
>  

-   []{#QcEpcVMaLU2pXBY2SvyWlA== .anchor}对比

  ---------------- ------------------------------------------------------
  关系             对比 - 指向
                   [[底层原理]{.underline}](#DsC1I8EsDk+9cwDs84NUPw==)

  ---------------- ------------------------------------------------------

>  ![](image/image13.jpeg){width="4.6194444444444445in"
> height="2.079408355205599in"}
>
> 比较了 Docker 和传统虚拟化方式的不同之处：
>
> \*传统虚拟机技术是虚拟出一套硬件后，在其上运行一个完整操作系统，在该系统上再运行所需应用进程；
>
> \*容器内的应用进程直接运行于宿主的内核，容器内没有自己的内核且也没有进行硬件虚拟。因此容器要比传统虚拟机更为轻便。
>
> \* 每个容器之间互相隔离，每个容器有自己的文件系统
> ，容器之间进程不会相互影响，能区分计算资源。

#### 能干嘛

-   技术职级变化

    -   coder

        -   programmer

            -   software engineer

                -   DevOps engineer

-   开发/运维（DevOps）新一代开发工程师

    -   一次构建、随处运行

        -   更快速的应用交付和部署

>  
>
> 传统的应用开发完成后，需要提供一堆安装程序和配置说明文档，安装部署后需根据配置文档进行繁杂的配置才能正常运行。Docker化之后只需要交付少量容器镜像文件，在正式生产环境加载镜像并运行即可，应用安装配置在镜像里已经内置好，大大节省部署配置和测试验证时间。

-   更便捷的升级和扩缩容

>  
>
> 随着微服务架构和Docker的发展，大量的应用会通过微服务方式架构，应用的开发构建将变成搭乐高积木一样，每个Docker容器将变成一块"积木"，应用的升级将变得非常容易。当现有的容器不足以支撑业务处理时，可通过镜像运行新的容器进行快速扩容，使应用系统的扩容从原先的天级变成分钟级甚至秒级。

-   更简单的系统运维

>  
>
> 应用容器化运行后，生产环境运行的应用可与开发、测试环境的应用高度一致，容器会将应用程序相关的环境和状态完全封装起来，不会因为底层基础架构和操作系统的不一致性给应用带来影响，产生新的BUG。当出现程序异常时，也可以通过测试环境的相同容器进行快速定位和修复。

-   更高效的计算资源利用

>  
>
> 　Docker是内核级虚拟化，其不像传统的虚拟化技术一样需要额外的Hypervisor支持，所以在一台物理机上可以运行很多个容器实例，可大大提升物理服务器的CPU和内存的利用率。

-   • Docker应用场景

>  
>
> ![](image/image14.jpeg){width="4.4527777777777775in"
> height="3.2406332020997377in"}

-   哪些企业在使用

    -   新浪

> ![](image/image15.jpeg){width="4.4527777777777775in"
> height="2.893530183727034in"}![](image/image16.jpeg){width="4.4527777777777775in"
> height="3.008634076990376in"}
>
> ![](image/image17.jpeg){width="4.4527777777777775in"
> height="3.3299037620297463in"}![](image/image18.jpeg){width="4.4527777777777775in"
> height="3.487211286089239in"}
>
>  

-   美团

>  
>
> ![](image/image19.jpeg){width="4.4527777777777775in"
> height="3.220087489063867in"}![](image/image20.jpeg){width="4.4527777777777775in"
> height="3.1258989501312335in"}

-   蘑菇街

>  
>
> ![](image/image21.jpeg){width="4.4527777777777775in"
> height="3.5738134295713038in"}![](image/image22.jpeg){width="4.4527777777777775in"
> height="3.270703193350831in"}

- \...\...

  #### 去哪下

-   官网

    -   docker官网：http://www.docker.com

-   仓库

    -   Docker Hub官网: https://hub.docker.com/

### 1.2、Docker安装

> ![](image/image23.png){width="0.3645844269466317in"
> height="0.3645844269466317in"}

1.  前提说明

> **CentOS Docker 安装**
>
> ![](image/image24.jpeg){width="4.591666666666667in"
> height="2.1930347769028873in"}
>
> **前提条件**
>
> 目前，CentOS 仅发行版本中的内核支持 Docker。Docker 运行在CentOS 7
> (64-bit)上，
>
> 要求系统为64位、Linux系统内核版本为 3.8以上，这里选用Centos7.x
>
>  
>
> **查看自己的内核**
>
> uname命令用于打印当前系统相关信息（内核版本号、硬件架构、主机名称和操作系统类型等）。
>
> ![](image/image25.jpeg){width="4.591666666666667in"
> height="1.306317804024497in"}

2.  **Docker的基本组成**

    -   镜像(image)

> Docker 镜像（Image）就是一个**只读**的模板。镜像可以用来创建 Docker
> 容器，一个镜像可以创建很多容器。
>
> 它也相当于是一个root文件系统。比如官方镜像 centos:7 就包含了完整的一套
> centos:7 最小系统的 root 文件系统。
>
> 相当于容器的"源代码"，docker镜像文件类似于Java的类模板，而docker容器实例类似于java中new出来的实例对象。
>
>  
>
>  
>
> ![](image/image26.png){width="4.6194444444444445in"
> height="1.1018110236220473in"}
>
>  
>
>  

-   容器(container)

>  
>
> 1 从面向对象角度
>
> Docker
> 利用容器（Container）独立运行的一个或一组应用，应用程序或服务运行在容器里面，容器就类似于一个虚拟化的运行环境，容器是用镜像创建的运行实例。就像是Java中的类和实例对象一样，镜像是静态的定义，容器是镜像运行时的实体。容器为镜像提供了一个标准的和隔离的运行环境，它可以被启动、开始、停止、删除。每个容器都是相互隔离的、保证安全的平台
>
> *** ***
>
> 2 从镜像容器角度
>
> ***可以把容器看做是一个简易版的 Linux
> 环境***（包括root用户权限、进程空间、用户空间和网络空间等）和运行在其中的应用程序。

-   仓库(repository)

>  
>
> 仓库（Repository）是集中存放镜像文件的场所。
>
>  
>
> 类似于
>
> Maven仓库，存放各种jar包的地方；
>
> github仓库，存放各种git项目的地方；
>
> Docker公司提供的官方registry被称为Docker Hub，存放各种镜像模板的地方。
>
>  
>
> 仓库分为公开仓库（Public）和私有仓库（Private）两种形式。
>
> 最大的公开仓库是 Docker Hub(https://hub.docker.com/)，
>
> 存放了数量庞大的镜像供用户下载。国内的公开仓库包括阿里云 、网易云等

-   小总结

> 需要正确的理解仓库/镜像/容器这几个概念:
>
> Docker
> 本身是一个容器运行载体或称之为管理引擎。我们把应用程序和配置依赖打包好形成一个可交付的运行环境，这个打包好的运行环境就是image镜像文件。只有通过这个镜像文件才能生成Docker容器实例(类似Java中new出来一个对象)。
>
>  
>
> image文件可以看作是容器的模板。Docker 根据 image
> 文件生成容器的实例。同一个 image
> 文件，可以生成多个同时运行的容器实例。
>
>  
>
> 镜像文件
>
> \*  image 文件生成的容器实例，本身也是一个文件，称为镜像文件。
>
> 容器实例
>
> \*
>  一个容器运行一种服务，当我们需要的时候，就可以通过docker客户端创建一个对应的运行实例，也就是我们的容器
>
> 仓库
>
> \*
> 就是放一堆镜像的地方，我们可以把镜像发布到仓库中，需要的时候再从仓库中拉下来就可以了。
>
>  

3.  **Docker平台架构图解(架构版)**

    -   首次懵逼正常，后续深入，先有大概轮廓，混个眼熟

    -   整体架构及底层通信原理简述

> Docker 是一个 C/S
> 模式的架构，后端是一个松耦合架构，众多模块各司其职。 
>
> ![](image/image27.jpeg){width="4.6194444444444445in"
> height="1.3088429571303588in"}
>
> ![](image/image28.jpeg){width="4.6194444444444445in"
> height="6.4259984689413825in"}

4.  **安装步骤**

    -   CentOS7安装Docker

        -   https://docs.docker.com/engine/install/centos/

        -   安装步骤

            -   确定你是CentOS7及以上版本

                -   cat /etc/redhat-release

            -   卸载旧版本

>  
>
> https://docs.docker.com/engine/install/centos/
>
>  
>
>  ![](image/image29.jpeg){width="4.4527777777777775in"
> height="2.9034864391951007in"}

-   yum安装gcc相关

    -   CentOS7能上外网

        ![](image/image30.png){width="3.4270833333333335in"
        height="2.2604166666666665in"}

    -   yum -y install gcc

    -   yum -y install gcc-c++

-   安装需要的软件包

    -   官网要求

> ![](image/image31.jpeg){width="4.4527777777777775in"
> height="2.9514577865266842in"}

-   执行命令

    -   yum install -y yum-utils

```{=html}
<!-- -->
```
-   设置stable镜像仓库

    -   大坑

  --------- -----------------------------------------------------------------------------------
  单图标    ![](image/image32.png){width="0.1527777777777778in"
            height="0.1527777777777778in"} CustomIcon-663735520; \[\"\",
            \"4MyPJwAAAAAAAAAAAAAAAA==\"\]

  --------- -----------------------------------------------------------------------------------

-   yum-config-manager \--add-repo
    https://download.docker.com/linux/centos/docker-ce.repo

> ![](image/image33.jpeg){width="4.4527777777777775in"
> height="0.830437445319335in"} 
>
>  
>
>  
>
> 报错：
>
> 1   \[Errno 14\] curl#35 - TCP connection reset by peer
>
>  
>
>  
>
> 2   \[Errno 12\] curl#35 - Timeout

-   官网要求

> ![](image/image33.jpeg){width="4.4527777777777775in"
> height="0.830437445319335in"}
>
>  报错：
>
>  1   \[Errno 14\] curl#35 - TCP connection reset by peer
>
> 
>
> 
>
>  2   \[Errno 12\] curl#35 - Timeout

-   推荐

  --------- -----------------------------------------------------------------------------------
  单图标    ![](image/image34.png){width="0.1527777777777778in"
            height="0.1527777777777778in"} CustomIcon\--1664269521; \[\"\",
            \"L0PNnAAAAAAAAAAAAAAAAA==\"\]

  --------- -----------------------------------------------------------------------------------

-   yum-config-manager \--add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    
-   我们自己

>  
>
> ![](image/image35.jpeg){width="4.4527777777777775in"
> height="0.5570297462817148in"}

-   更新yum软件包索引

    -   yum makecache fast

-   安装DOCKER CE

    -   yum -y install docker-ce docker-ce-cli containerd.io

        -   官网要求

>  
>
> ![](image/image36.jpeg){width="4.4527777777777775in"
> height="1.1422080052493437in"}

-   执行结果

> ![](image/image37.jpeg){width="4.4527777777777775in"
> height="1.2479593175853019in"}
>
> ![](image/image38.jpeg){width="4.4527777777777775in"
> height="1.1779254155730534in"}

-   启动docker

    -   systemctl start docker

-   测试

    -   docker version

        -   本次安装时间2021.11

>  
>
> 本次安装时间2021.11
>
>   
>
> ![](image/image39.jpeg){width="4.4527777777777775in"
> height="3.3534120734908135in"}

-   docker run hello-world

>  
>
> ![](image/image40.jpeg){width="4.4527777777777775in"
> height="1.6076662292213473in"}

-   卸载

>  
>
> ![](image/image41.jpeg){width="4.4527777777777775in"
> height="2.060411198600175in"}

-   systemctl stop docker

-   yum remove docker-ce docker-ce-cli containerd.io

-   rm -rf /var/lib/docker

-   rm -rf /var/lib/containerd

5.  **阿里云镜像加速**

    -   是什么

        -   https://promotion.aliyun.com/ntms/act/kubernetes.html

    -   注册一个属于自己的阿里云账户(可复用淘宝账号)

    -   获得加速器地址连接

        -   登陆阿里云开发者平台

> ![](image/image42.jpeg){width="4.4527777777777775in"
> height="1.984666447944007in"}

-   点击控制台

>  
>
> ![](image/image43.jpeg){width="4.4527777777777775in"
> height="2.1197036307961503in"}

-   选择容器镜像服务

>  
>
> ![](image/image44.jpeg){width="4.4527777777777775in"
> height="2.1768405511811024in"}

-   获取加速器地址

>  
>
> ![](image/image45.jpeg){width="4.4527777777777775in"
> height="3.0000984251968505in"}

-   粘贴脚本直接执行

    -   直接粘

>  

+-----------------------------------------------------------------------+
| mkdir -p /etc/docker                                                  |
+-----------------------------------------------------------------------+
| tee /etc/docker/daemon.json \<\<-\'EOF\'                              |
|                                                                       |
| {                                                                     |
|                                                                       |
|   \"registry-mirrors\": \[\"https://aa25jngu.mirror.aliyuncs.com\"\]  |
|                                                                       |
| }                                                                     |
|                                                                       |
| EOF                                                                   |
+-----------------------------------------------------------------------+

>  
>
> ![](image/image46.jpeg){width="4.4527777777777775in"
> height="2.0486165791776028in"}

-   或者分步骤都行

    -   mkdir -p /etc/docker

    -   vim /etc/docker/daemon.json

>  
>
>  
>
>  
>
>  #阿里云
>
> {
>
>   \"registry-mirrors\":
> \[\"https://｛自已的编码｝.mirror.aliyuncs.com\"\]
>
> }

-   重启服务器

    -   systemctl daemon-reload

    -   systemctl restart docker

6.  **永远的HelloWorld**

    -   启动Docker后台容器(测试运行 hello-world)

        -   docker run hello-world

> ![](image/image47.jpeg){width="4.4527777777777775in"
> height="2.104288057742782in"}
>
> 输出这段提示以后，hello world就会停止运行，容器自动终止。

-   run干了什么

>  
>
> ![](image/image48.jpeg){width="4.4527777777777775in"
> height="2.17417104111986in"}

7.  []{#DsC1I8EsDk+9cwDs84NUPw== .anchor}**底层原理**

------------------- ---------------------------------------------------
  关系                对比 - 开始
                      [[对比]{.underline}](#QcEpcVMaLU2pXBY2SvyWlA==)

------------------- ---------------------------------------------------

-   为什么Docker会比VM虚拟机快

> (1)docker有着比虚拟机更少的抽象层
>
>    由于docker不需要Hypervisor(虚拟机)实现硬件资源虚拟化,运行在docker容器上的程序直接使用的都是实际物理机的硬件资源。因此在CPU、内存利用率上docker将会在效率上有明显优势。
>
> (2)docker利用的是宿主机的内核,而不需要加载操作系统OS内核
>
>    当新建一个容器时,docker不需要和虚拟机一样重新加载一个操作系统内核。进而避免引寻、加载操作系统内核返回等比较费时费资源的过程,当新建一个虚拟机时,虚拟机软件需要加载OS,返回新建过程是分钟级别的。而docker由于直接利用宿主机的操作系统,则省略了返回过程,因此新建一个docker容器只需要几秒钟。
>
>  ![](image/image49.jpeg){width="4.6194444444444445in"
> height="2.470216535433071in"}
>
>  ![](image/image50.png){width="4.6194444444444445in"
> height="1.9047714348206475in"}
>
>  
>
>  

### 1.3、Docker常用命令

> ![](image/image51.png){width="0.38465004374453193in"
> height="0.38465004374453193in"}

#### 1.3.1、帮助启动类命令

-   启动docker： systemctl start docker

-   停止docker： systemctl stop docker

-   重启docker： systemctl restart docker

-   查看docker状态： systemctl status docker

-   开机启动： systemctl enable docker

-   查看docker概要信息： docker info

-   查看docker总体帮助文档： docker \--help

-   查看docker命令帮助文档： docker 具体命令 \--help

#### 1.3.2、镜像命令

```shell
docker images #列出本地主机上的镜像
	EPOSITORY：表示镜像的仓库源 
	TAG：镜像的标签版本号  
	IMAGE ID：镜像ID 
	CREATED：镜像创建时间 
	SIZE：镜像大小 
```

> ![](image/image52.jpeg){width="4.4527777777777775in"
> height="0.33870844269466316in"}
>
>  
>
> 各个选项说明:

>  同一仓库源可以有多个 TAG版本，代表这个仓库源的不同个版本，我们使用
> REPOSITORY:TAG 来定义不同的镜像。
>
> 如果你不指定一个镜像的版本标签，例如你只使用 ubuntu，docker 将默认使用
> ubuntu:latest 镜像

-   OPTIONS说明：

    -   -a :列出本地所有的镜像（含历史映像层）

    -   -q :只显示镜像ID。



```shell
docker search 某个XXX镜像名字  # 搜索
```

-   网站

    -   https://hub.docker.com

-   命令

    -   docker search \[OPTIONS\] 镜像名字

    -   案例

> ![](image/image53.png){width="4.4527777777777775in"
> height="1.3153488626421697in"}
>
> ![](image/image54.jpeg){width="4.4527777777777775in"
> height="1.062509842519685in"}
>
>  
>
>  

-   OPTIONS说明：

    -   \--limit : 只列出N个镜像，默认25个

    -   docker search \--limit 5 redis



```shell
docker pull 某个XXX镜像名字 # 下载镜像
```

下载镜像

-   docker pull 镜像名字\[:TAG\]

-   docker pull 镜像名字

    -   没有TAG就是最新版

    -   等价于

    -   docker pull 镜像名字:latest

    -   docker pull ubuntu

>  
>
> ![](image/image55.jpeg){width="4.4527777777777775in"
> height="1.4272353455818023in"}

```shell
docker system df # 查看镜像/容器/数据卷所占的空间
```

>  
>
> ![](image/image56.jpeg){width="4.6194444444444445in"
> height="0.9913254593175853in"}

```shell
docker rmi 某个XXX镜像名字ID # 删除镜像
```

-   删除镜像

-   删除单个

    -   docker rmi -f 镜像ID

-   删除多个

    -   docker rmi -f 镜像名1:TAG 镜像名2:TAG

-   删除全部

    -   docker rmi -f \$(docker images -qa)

-   面试题：谈谈docker虚悬镜像是什么？

    -   是什么

        -   仓库名、标签都是\<none\>的镜像，俗称虚悬镜像dangling image

        -   长什么样

>  
>
>  
>
> ![](image/image57.png){width="4.4527777777777775in"
> height="0.42043416447944004in"}

-   后续Dockerfile章节再介绍



-   思考

> ![](image/image58.png){width="0.31441272965879263in"
> height="0.31441272965879263in"}

-   结合我们Git的学习心得，大家猜猜是否会有 docker commit /docker
    push？？

#### 1.3.3、容器命令

-   **有镜像才能创建容器，
    这是根本前提(下载一个CentOS或者ubuntu镜像演示)**
    -   说明

>  
>
> ![](image/image59.jpeg){width="4.4527777777777775in"
> height="1.7891076115485565in"}

-   docker pull centos

-   docker pull ubuntu

>  
>
> ![](image/image55.jpeg){width="4.4527777777777775in"
> height="1.4272353455818023in"}

-   本次演示用ubuntu演示



```shell
docker run [OPTIONS] IMAGE [COMMAND] [ARG...] # 新建 + 启动容器
```

- OPTIONS说明

  ```
  OPTIONS说明（常用）：有些是一个减号，有些是两个减号
  --name="容器新名字"       为容器指定一个名称；
  
  -d: 后台运行容器并返回容器ID，也即启动守护式容器(后台运行)
  
  -i：以交互模式运行容器，通常与 -t 同时使用；
  -t：为容器重新分配一个伪输入终端，通常与 -i 同时使用；也即启动交互式容器(前台有伪终端，等待交互)；
  
  -P: 随机端口映射，大写P
  -p: 指定端口映射，小写p
  ```

  

> ![](image/image60.jpeg){width="4.4527777777777775in"
>height="1.4327712160979877in"}

-   启动交互式容器(前台命令行)

  --------- -----------------------------------------------------------------------
  关系      前后对比 - 开始
            [[启动守护式容器(后台服务器)]{.underline}](#QRvvQPSoeUSyQ50ciz6Q/g==)

  --------- -----------------------------------------------------------------------

> ![](image/image61.jpeg){width="4.4527777777777775in"
> height="1.3704713473315835in"}
>
> #使用镜像centos:latest以交互模式启动一个容器,在容器内执行/bin/bash命令。
>
> ```shell
> docker run -it  --name="ubuntu-con" ubuntu /bin/bash
> docker run -it ubuntu bash
> ```

```
参数说明：
-i: 交互式操作。
-t: 终端。
ubuntu : ubuntu 镜像。
/bin/bash：放在镜像名后的是命令，这里我们希望有个交互式 Shell，因此用的是 /bin/bash。         要退出终端，直接输入 exit: 
```



```shell
docker ps [OPTIONS] #列出当前所有正在运行的容器
	# OPTIONS说明
    -a :列出当前所有正在运行的容器+历史上运行过的
    -l :显示最近创建的容器。
    -n：显示最近n个创建的容器。
    -q :静默模式，只显示容器编号。
```



-   退出容器
-   exit
  
      -   run进去容器，exit退出，容器停止
  
-   ctrl+p+q
  
    -   run进去容器，ctrl+p+q退出，容器不停止

-   启动已停止运行的容器

    -   docker start 容器ID或者容器名

-   重启容器

    -   docker restart 容器ID或者容器名

-   停止容器

    -   docker stop 容器ID或者容器名

-   强制停止容器

    -   docker kill 容器ID或容器名

-   进入容器

    -   docker exec -it 容器ID bash

- 删除已停止的容器

  -   docker rm 容器ID

      -   一次性删除多个容器实例

          -   docker rm -f $(docker ps -a -q)

          -   docker ps -a -q \| xargs docker rm

- **重要**

  -   **有镜像才能创建容器，这是根本前提(下载一个Redis6.0.8镜像演示)**

  --------- -----------------------------------------------------------------------

- 在大部分的场景下，我们希望 docker 的服务是在后台运行的， 我们可以过
  -d 指定容器的后台运行模式。

- docker run -d 容器名

>  
>
> #使用镜像centos:latest以后台模式启动一个容器
>
> docker run -d centos
>
>  
>
> 问题：然后docker ps -a 进行查看, 会发现容器已经退出
>
> 很重要的要说明的一点: Docker容器后台运行,就必须有一个前台进程.
>
> 容器运行的命令如果不是那些一直挂起的命令（比如运行top，tail），就是会自动退出的。
>
>  
>
> 这个是docker的机制问题,比如你的web容器,我们以nginx为例，正常情况下,
>
> 我们配置启动服务只需要启动响应的service即可。例如service nginx start
>
> 但是,这样做,nginx为后台进程模式运行,就导致docker前台没有运行的应用,
>
> 这样的容器后台启动后,会立即自杀因为他觉得他没事可做了.
>
> 所以，最佳的解决方案是,将你要运行的程序以前台进程的形式运行，
>
> 常见就是命令行模式，表示我还有交互操作，别中断，O(∩\_∩)O哈哈\~

-   redis 前后台启动演示case

    -   前台交互式启动

        -   docker run -it redis:7.0.2

    -   后台守护式启动

        -   docker run -d redis:7.0.2



-   查看容器日志

    -   docker logs 容器ID

-   查看容器内运行的进程

    -   docker top 容器ID

-   查看容器内部细节

    -   docker inspect 容器ID

-   进入正在运行的容器并以命令行交互
    
  -------------------- -----------------------------------------------------
  关系                 开始
                       [[退出容器]{.underline}](#H51mnUNRmUykhMP+ErdHbA==)
  
  -------------------- -----------------------------------------------------
  
-   docker exec -it 容器ID bashShell

> ![](image/image62.jpeg){width="4.4527777777777775in"
> height="0.6850426509186351in"}
>
> ![](image/image63.jpeg){width="4.4527777777777775in"
> height="1.8109623797025372in"}

-   重新进入docker attach 容器ID

-   案例演示，用centos或者unbuntu都可以

-   上述两个区别

    -   attach 直接进入容器启动命令的终端，不会启动新的进程
        用exit退出，会导致容器的停止。

>  
>
> ![](image/image64.jpeg){width="4.4527777777777775in"
> height="1.0432841207349082in"}

-   exec 是在容器中打开新的终端，并且可以启动新的进程
    用exit退出，不会导致容器的停止。

>  
>
> ![](image/image65.jpeg){width="4.4527777777777775in"
> height="1.0165069991251094in"}

-   推荐大家使用 docker exec
    命令，因为退出容器终端，不会导致容器的停止。

-   用之前的redis容器实例进入试试

    -   进入redis服务

        -   docker exec -it 容器ID /bin/bash

        -   docker exec -it 容器ID redis-cli

        -   一般用-d后台启动的程序，再用exec进入对应容器实例



-   从容器内拷贝文件到主机上

    -   容器→主机

    -   docker cp 容器ID:容器内路径 目的主机路径

>  
>
> ![](image/image66.jpeg){width="4.4527777777777775in"
> height="0.8015004374453193in"}
>
>  
>
> 公式：docker cp  容器ID:容器内路径 目的主机路径
>
>  

-   导入和导出容器

    -   export 导出容器的内容留作为一个tar归档文件\[对应import命令\]

    -   import
        从tar包中的内容创建一个新的文件系统再导入为镜像\[对应export\]

    -   案例

        -   docker export 容器ID \> 文件名.tar
        
        > ```shell
        > docker export 3e80161bd8bc > /usr/local/docker-repository/redis_3e80161bd8bc.tar
        > ```
        
        
        
        

>  
>
> ![](image/image67.jpeg){width="4.4527777777777775in"
> height="2.03496062992126in"}

-   cat 文件名.tar \| docker import - 镜像用户/镜像名:镜像版本号

> ```shell
>  cat /usr/local/docker-repository/redis_3e80161bd8bc.tar | docker import - danghf/redis7.0.2:0.0.1
> ```



>  
>
>  ![](image/image68.jpeg)

![](image/Snipaste_2022-10-06_20-26-00.png)

import后变成了image

4.  **小总结**
-   常用命令

> 图片正下方还有命令
>
> ![](image/image69.jpeg){width="4.6194444444444445in"
> height="3.274673009623797in"}
>
>  
>
> attach    Attach to a running container                 \# 当前 shell
> 下 attach 连接指定运行镜像
>
> build     Build an image from a Dockerfile              \# 通过
> Dockerfile 定制镜像
>
> commit    Create a new image from a container changes   \#
> 提交当前容器为新的镜像
>
> cp        Copy files/folders from the containers filesystem to the
> host path   #从容器中拷贝指定文件或者目录到宿主机中
>
> create    Create a new container                        \#
> 创建一个新的容器，同 run，但不启动容器
>
> diff      Inspect changes on a container\'s filesystem   \# 查看
> docker 容器变化
>
> events    Get real time events from the server          \# 从 docker
> 服务获取容器实时事件
>
> exec      Run a command in an existing container        \#
> 在已存在的容器上运行命令
>
> export    Stream the contents of a container as a tar archive   \#
> 导出容器的内容流作为一个 tar 归档文件\[对应 import \]
>
> history   Show the history of an image                  \#
> 展示一个镜像形成历史
>
> images    List images                                   \#
> 列出系统当前镜像
>
> import    Create a new filesystem image from the contents of a tarball
> \# 从tar包中的内容创建一个新的文件系统映像\[对应export\]
>
> info      Display system-wide information               \#
> 显示系统相关信息
>
> inspect   Return low-level information on a container   \#
> 查看容器详细信息
>
> kill      Kill a running container                      \# kill 指定
> docker 容器
>
> load      Load an image from a tar archive              \# 从一个 tar
> 包中加载一个镜像\[对应 save\]
>
> login     Register or Login to the docker registry server    \#
> 注册或者登陆一个 docker 源服务器
>
> logout    Log out from a Docker registry server          # 从当前
> Docker registry 退出
>
> logs      Fetch the logs of a container                 \#
> 输出当前容器日志信息
>
> port      Lookup the public-facing port which is NAT-ed to
> PRIVATE_PORT    \# 查看映射端口对应的容器内部源端口
>
> pause     Pause all processes within a container        \# 暂停容器
>
> ps        List containers                               \#
> 列出容器列表
>
> pull      Pull an image or a repository from the docker registry
> server   \# 从docker镜像源服务器拉取指定镜像或者库镜像
>
> push      Push an image or a repository to the docker registry server
>    \# 推送指定镜像或者库镜像至docker源服务器
>
> restart   Restart a running container                   \#
> 重启运行的容器
>
> rm        Remove one or more containers                 \#
> 移除一个或者多个容器
>
> rmi       Remove one or more images       #
> 移除一个或多个镜像\[无容器使用该镜像才可删除，否则需删除相关容器才可继续或
> -f 强制删除\]
>
> run       Run a command in a new container              \#
> 创建一个新的容器并运行一个命令
>
> save      Save an image to a tar archive                \#
> 保存一个镜像为一个 tar 包\[对应 load\]
>
> search    Search for an image on the Docker Hub         \# 在 docker
> hub 中搜索镜像
>
> start     Start a stopped containers                    \# 启动容器
>
> stop      Stop a running containers                     \# 停止容器
>
> tag       Tag an image into a repository                \#
> 给源中镜像打标签
>
> top       Lookup the running processes of a container   \#
> 查看容器中运行的进程信息
>
> unpause   Unpause a paused container                    \#
> 取消暂停容器
>
> version   Show the docker version information           \# 查看 docker
> 版本号
>
> wait      Block until a container stops, then print its exit code   \#
> 截取容器停止时的退出状态值
>
>  

### 1.4、Docker镜像

> ![](image/image70.png){width="0.37124015748031497in"
> height="0.37124015748031497in"}

1.  **是什么**

    -   是什么

> **镜像**
>
> 是一种轻量级、可执行的独立软件包，它包含运行某个软件所需的所有内容，我们把应用程序和配置依赖打包好形成一个可交付的运行环境(包括代码、运行时需要的库、环境变量和配置文件等)，这个打包好的运行环境就是image镜像文件。
>
>  
>
> 只有通过这个镜像文件才能生成Docker容器实例(类似Java中new出来一个对象)。
>
>  
>
>  

-   分层的镜像

>  
>
> 以我们的pull为例，在下载的过程中我们可以看到docker的镜像好像是在一层一层的在下载
>
>  
>
>  ![](image/image71.png){width="4.6194444444444445in"
> height="2.6513003062117235in"}

-   UnionFS（联合文件系统）

> UnionFS（联合文件系统）：Union文件系统（UnionFS）是一种分层、轻量级并且高性能的文件系统，它支持对文件系统的修改作为一次提交来一层层的叠加，同时可以将不同目录挂载到同一个虚拟文件系统下(unite
> several directories into a single virtual filesystem)。Union
> 文件系统是 Docker
> 镜像的基础。镜像可以通过分层来进行继承，基于基础镜像（没有父镜像），可以制作各种具体的应用镜像。
>
> ![](image/image72.jpeg){width="4.6194444444444445in"
> height="2.7576148293963256in"} 
>
> 特性：一次同时加载多个文件系统，但从外面看起来，只能看到一个文件系统，联合加载会把各层文件系统叠加起来，这样最终的文件系统会包含所有底层的文件和目录

-   Docker镜像加载原理

>  
>
>  Docker镜像加载原理：
>
>    docker的镜像实际上由一层一层的文件系统组成，这种层级的文件系统UnionFS。
>
> bootfs(boot file system)主要包含bootloader和kernel,
> bootloader主要是引导加载kernel,
> Linux刚启动时会加载bootfs文件系统，在Docker镜像的最底层是引导文件系统bootfs。这一层与我们典型的Linux/Unix系统是一样的，包含boot加载器和内核。当boot加载完成之后整个内核就都在内存中了，此时内存的使用权已由bootfs转交给内核，此时系统也会卸载bootfs。
>
>  
>
> rootfs (root file system) ，在bootfs之上。包含的就是典型 Linux
> 系统中的 /dev, /proc, /bin, /etc
> 等标准目录和文件。rootfs就是各种不同的操作系统发行版，比如Ubuntu，Centos等等。 
>
> 。 ![](image/image73.jpeg){width="4.5625in"
> height="3.1770833333333335in"}
>
>  平时我们安装进虚拟机的CentOS都是好几个G，为什么docker这里才200M？？
>
> ![](image/image74.jpeg){width="4.6194444444444445in"
> height="0.6063024934383202in"}
>
> 对于一个精简的OS，rootfs可以很小，只需要包括最基本的命令、工具和程序库就可以了，因为底层直接用Host的kernel，自己只需要提供
> rootfs 就行了。由此可见对于不同的linux发行版, bootfs基本是一致的,
> rootfs会有差别, 因此不同的发行版可以公用bootfs。
>
>  

-   为什么 Docker 镜像要采用这种分层结构呢

>  镜像分层最大的一个好处就是共享资源，方便复制迁移，就是为了复用。
>
>  比如说有多个镜像都从相同的 base 镜像构建而来，那么 Docker Host
>只需在磁盘上保存一份 base 镜像；
> 
>同时内存中也只需加载一份 base
>  镜像，就可以为所有容器服务了。而且镜像的每一层都可以被共享。
>
> 
> 

2.  **重点理解**

    -   Docker镜像层都是只读的，容器层是可写的
        当容器启动时，一个新的可写层被加载到镜像的顶部。
        这一层通常被称作"容器层"，"容器层"之下的都叫"镜像层"。

> 当容器启动时，一个新的可写层被加载到镜像的顶部。这一层通常被称作"容器层"，"容器层"之下的都叫"镜像层"。
>
> 所有对容器的改动 -
> 无论添加、删除、还是修改文件都只会发生在容器层中。只有容器层是可写的，容器层下面的所有镜像层都是只读的。
>
> ![](image/image75.jpeg){width="4.6194444444444445in"
> height="3.8910673665791777in"}

3.  **Docker镜像commit操作案例**

    -   docker commit提交容器副本使之成为一个新的镜像

    -   docker commit -m=\"提交的描述信息\" -a=\"作者\" 容器ID
        要创建的目标镜像名:\[标签名\]

    -   案例演示ubuntu安装vim

        -   从Hub上下载ubuntu镜像到本地并成功运行

        -   原始的默认Ubuntu镜像是不带着vim命令的

>  
>
> ![](image/image76.jpeg){width="4.4527777777777775in"
> height="1.4345767716535434in"}

-   外网连通的情况下，安装vim

>  ![](image/image77.jpeg){width="2.4375in"
> height="1.0104166666666667in"}
>
> docker容器内执行上述两条命令：
>
> apt-get update
>
> apt-get -y install vim
>
>  
>
>  ![](image/image78.jpeg){width="4.4527777777777775in"
> height="1.523876859142607in"}
>
> ![](image/image79.jpeg){width="4.4527777777777775in"
> height="1.0152023184601924in"}
>
>  

-   安装完成后，commit我们自己的新镜像

> ![](image/image80.jpeg){width="4.4527777777777775in"
> height="1.0152023184601924in"}
>
> ![](image/image81.jpeg){width="4.4527777777777775in"
> height="1.0192661854768155in"}
>
>  
>
>  

-   启动我们的新镜像并和原来的对比

>  
>
> ![](image/image82.jpeg){width="4.4527777777777775in"
> height="1.0192661854768155in"}
>
> 1 官网是默认下载的Ubuntu没有vim命令
>
> 2我们自己commit构建的镜像，新增加了vim功能，可以成功使用。
>
>  

-   小总结

>  Docker中的镜像分层，支持通过扩展现有镜像，创建新的镜像。类似Java继承于一个Base基础类，自己再按需扩展。
>
> 新镜像是从 base
>镜像一层一层叠加生成的。每安装一个软件，就在现有镜像的基础上增加一层
> 
> ![](image/image83.jpeg){width="4.6194444444444445in"
>height="1.5091797900262467in"}

### 1.5、本地镜像发布到阿里云

> ![](image/image84.png){width="0.372412510936133in"
> height="0.372412510936133in"}

1.  **本地镜像发布到阿里云流程**

>  
>
> ![](image/image85.jpeg){width="4.591666666666667in"
> height="4.083865923009624in"}
>
>  

2.  **镜像的生成方法**

    -   上一讲已经介绍过

        -   基于当前容器创建一个新的镜像，新功能增强 docker commit
            \[OPTIONS\] 容器ID \[REPOSITORY\[:TAG\]\]

> OPTIONS说明：
>
> -a :提交的镜像作者；
>
> -m :提交时的说明文字；
>
> 本次案例centos+ubuntu两个，当堂讲解一个，家庭作业一个，请大家务必动手，亲自实操。
>
> ![](image/image86.jpeg){width="4.4527777777777775in"
> height="1.789841426071741in"}
>
>  
>
> ![](image/image87.jpeg){width="4.4527777777777775in"
> height="2.1856124234470693in"}

-   后面的DockerFile章节，第2种方法

3.  **将本地镜像推送到阿里云**

    -   本地镜像素材原型

>  
>
> ![](image/image88.jpeg){width="4.6194444444444445in"
> height="0.31581364829396325in"}
>
>  
>
>  
>
>  
>
>  
>
>  
>
> ![](image/image89.jpeg){width="4.6194444444444445in"
> height="0.4188921697287839in"}

-   阿里云开发者平台

    -   https://promotion.aliyun.com/ntms/act/kubernetes.html

>  
>
> ![](image/image90.jpeg){width="4.4527777777777775in"
> height="1.9681561679790027in"}

-   创建仓库镜像

>  
>
> ![](image/image91.jpeg){width="4.6194444444444445in"
> height="2.2145122484689415in"}

-   选择控制台，进入容器镜像服务

>  
>
> ![](image/image92.jpeg){width="4.4527777777777775in"
> height="3.4143602362204724in"}

-   选择个人实例

>  
>
> ![](image/image93.jpeg){width="4.4527777777777775in"
> height="1.919760498687664in"}

-   命名空间

> ![](image/image94.jpeg){width="4.4527777777777775in"
> height="2.2819925634295712in"}
>
>  

-   继续

> ![](image/image95.jpeg){width="4.4527777777777775in"
> height="2.3883081802274715in"}

-   仓库名称

>  ![](image/image96.jpeg){width="4.4527777777777775in"
> height="2.221363735783027in"}
>
>  
>
>  

-   继续

> ![](image/image97.jpeg){width="4.4527777777777775in"
> height="1.976738845144357in"}
>
> ![](image/image98.jpeg){width="4.4527777777777775in"
> height="2.103383639545057in"}

-   进入管理界面获得脚本

>  
>
> ![](image/image99.jpeg){width="4.4527777777777775in"
> height="1.4118558617672792in"}

-   将镜像推送到阿里云

    -   将镜像推送到阿里云registry

        -   管理界面脚本

> ![](image/image100.png){width="4.4527777777777775in"
> height="3.063432852143482in"}

-   脚本实例

  -----------------------------------------------------------------------
   docker login \--username=zzyybuy registry.cn-hangzhou.aliyuncs.com

  docker tag cea1bb40441c
  registry.cn-hangzhou.aliyuncs.com/atguiguwh/myubuntu:1.1

  docker push registry.cn-hangzhou.aliyuncs.com/atguiguwh/myubuntu:1.1

-----------------------------------------------------------------------

>  ![](image/image101.jpeg){width="4.4527777777777775in"
> height="2.0170319335083113in"}

4.  **将阿里云上的镜像下载到本地**

    -   下载到本地

>  
>
> ![](image/image102.jpeg){width="4.6194444444444445in"
> height="1.6466535433070866in"}
>
>  
>
> docker pull registry.cn-hangzhou.aliyuncs.com/atguiguwh/myubuntu:1.1

### 1.6、本地镜像发布到私有库

> ![](image/image103.png){width="0.3691994750656168in"
> height="0.3691994750656168in"}

1.  **本地镜像发布到私有库流程**

>  
>
>  ![](image/image104.png){width="4.591666666666667in"
> height="4.083865923009624in"}

2.  **是什么**

>  
>
> 1 官方Docker
> Hub地址：https://hub.docker.com/，中国大陆访问太慢了且准备被阿里云取代的趋势，不太主流。
>
>  
>
> 2
> Dockerhub、阿里云这样的公共镜像仓库可能不太方便，涉及机密的公司不可能提供镜像给公网，所以需要创建一个本地私人仓库供给团队使用，基于公司内部项目构建镜像。
>
>  
>
>     Docker Registry是官方提供的工具，可以用于构建私有镜像仓库

-   Docker Registry

3.  **将本地镜像推送到私有库**

    -   下载镜像Docker Registry

> docker pull registry 
>
> ![](image/image105.jpeg){width="4.6194444444444445in"
> height="0.8635444006999126in"}
>
> ![](image/image106.jpeg){width="4.6194444444444445in"
> height="1.2777187226596676in"}
>
>  
>
>  
>
>  

-   运行私有库Registry，相当于本地有个私有Docker hub



```shell
docker run -d -p 5000:5000 -v /usr/local/docker-repository/:/tmp/registry --privileged=true registry
	-p 端口映射，指定端口映射，格式为：主机(宿主)端口:容器端口
	-v 绑定一个卷
```





>  库内部的挂载目录一定要是 /tmp/registry，因为 docker registry 在运行后会在 /tmp/ 下面生成一个叫 docker-registry.db 的库文件，默认就是 /tmp/registry 目录来存放 images
>
> ![](image/image107.jpeg){width="4.6194444444444445in"
> height="0.785340113735783in"}
>
> 
>
>  

-   案例演示创建一个新镜像，ubuntu安装ifconfig命令

    -   从Hub上下载ubuntu镜像到本地并成功运行

    -   原始的Ubuntu镜像是不带着ifconfig命令的

>  
>
> ![](image/image108.jpeg){width="4.4527777777777775in"
> height="1.688766404199475in"}

-   外网连通的情况下，安装ifconfig命令并测试通过

> docker容器内执行上述两条命令：
>
> apt-get update
>
> apt-get install net-tools
>
> ![](image/image109.jpeg){width="4.4527777777777775in"
> height="2.933522528433946in"}
>
> ![](image/image110.jpeg){width="4.4527777777777775in"
> height="2.180952537182852in"}

-   安装完成后，commit我们自己的新镜像

```shell
docker commit -m"ubuntu add net-tools(e.g ifconfig)" -a"danghf" 1c6481e01009 danghf/myubuntu:0.0.2
```



> 公式：
>
> docker commit -m=\"提交的描述信息\" -a=\"作者\" 容器ID
> 要创建的目标镜像名:\[标签名\]
>
> ![](image/image111.jpeg){width="4.4527777777777775in"
>height="1.7596609798775154in"}
> 
> 

-   启动我们的新镜像并和原来的对比

> 1 官网是默认下载的Ubuntu没有ifconfig命令
>
> 2我们自己commit构建的新镜像，新增加了ifconfig功能，可以成功使用。
>
> ![](image/image112.jpeg){width="4.4527777777777775in"
> height="2.4045002187226596in"}
>
>  

-   curl验证私服库上有什么镜像



```xhell
 curl -XGET http://localhost:5000/v2/_catalog
```

![image-20221007160316025](./image/image-20221007160316025.png)

> 可以看到，目前私服库没有任何镜像上传过。。。。。。
>

-   将新镜像zzyyubuntu:1.2修改符合私服规范的Tag

```
 docker tag SOURCE_IMAGE[:TAG] TARGET_IMAGE[:TAG]
 docker   tag   镜像:Tag   Host:Port/Repository:Tag
```

>  ![](image/Snipaste_2022-10-07_16-08-56.png)

-   修改配置文件使之支持http

>  
>
> ![](./image/image115.jpeg){width="4.6194444444444445in"
> height="1.0329593175853018in"}

vim命令新增如下内容：vim /etc/docker/daemon.json

```json
{
  "registry-mirrors": ["https://ip8m1fe2.mirror.aliyuncs.com"],
  "insecure-registries": ["localhost:5000"]
}
```



>  上述理由：docker默认不允许http方式推送镜像，通过配置选项来取消这个限制。====\> 修改完后如果不生效，建议重启docker
>

-   push推送到私服库

> ```
> docker push localhost:5000/myubuntu:0.0.2
> ```
>
> ![](image/Snipaste_2022-10-07_16-20-34.png)
> 
>
>  

-   curl验证私服库上有什么镜像2

>  ```
>curl -XGET http://192.168.152.134:5000/v2/_catalog
> ```
>
>  ![](image/Snipaste_2022-10-07_16-22-06.png)

-   pull到本地并运行

> ```
> docker pull localhost:5000/myubuntu:0.0.2
> ```
>
> ![](image/Snipaste_2022-10-07_16-36-37.png)
>
> docker run -it 镜像ID /bin/bash
>
> ![](image/image119.jpeg){width="4.6194444444444445in"
> height="1.3721609798775154in"}

### 1.7、Docker容器数据卷

> ![](image/image120.png){width="0.3671894138232721in"
> height="0.3671894138232721in"}

1.  **坑：容器卷记得加入**

-   --privileged=true

-   why

>  
>
>  Docker挂载主机目录访问如果出现cannot open directory .: Permission
> denied
>
> 解决办法：在挂载目录后多加一个\--privileged=true参数即可
>
>  
>
>  如果是CentOS7安全模块会比之前系统版本加强，不安全的会先禁止，所以目录挂载的情况被默认为不安全的行为，
>
> 在SELinux里面挂载目录被禁止掉了额，如果要开启，我们一般使用\--privileged=true命令，扩大容器的权限解决挂载目录没有权限的问题，也即
>
> 使用该参数，container内的root拥有真正的root权限，否则，container内的root只是外部的一个普通用户权限。



2.  **回顾下上一讲的知识点，参数V**

>  
>
> 还记得蓝色框框中的内容吗？
>
>  ![](image/image121.jpeg){width="4.591666666666667in"
>height="1.2266415135608049in"}

3.  **是什么**

>  
>
>  卷就是目录或文件，存在于一个或多个容器中，由docker挂载到容器，但不属于联合文件系统，因此能够绕过Union
>File System提供一些用于持续存储或共享数据的特性：
> 
> 卷的设计目的就是数据的持久化，完全独立于容器的生存周期，因此Docker不会在容器删除时删除其挂载的数据卷
>
> 
>

-   一句话：有点类似我们Redis里面的rdb和aof文件

-   将docker容器内的数据保存进宿主机的磁盘中

-   运行一个带有容器卷存储功能的容器实例

    -   docker run -it \--privileged=true -v
        /宿主机绝对路径目录:/容器内目录 镜像名

4.  **能干嘛**

> \*  将运用与运行的环境打包镜像，run后形成容器实例运行
> ，但是我们对数据的要求希望是持久化的
>
>  
>
> Docker容器产生的数据，如果不备份，那么当容器实例删除后，容器内的数据自然也就没有了。
>
> 为了能保存数据在docker中我们使用卷。
>
>  
>
> 特点：
>
> 1：数据卷可在容器之间共享或重用数据
>
> 2：卷中的更改可以直接实时生效，爽
>
> 3：数据卷中的更改不会包含在镜像的更新中
>
> 4：数据卷的生命周期一直持续到没有容器使用它为止
>
>  

5.  **数据卷案例**

    -   宿主vs容器之间映射添加容器卷

        -   直接命令添加

            -   命令

>  公式：docker run -it -v /宿主机目录:/容器内目录 ubuntu /bin/bash

-----------------------------------------------------------------------
```shell
docker run -it --privileged=true -v /usr/local/test/:/usr/local/test ubuntu
```



-----------------------------------------------------------------------

>  ![](image/image122.jpeg){width="4.4527777777777775in"
> height="2.6652438757655292in"}

-   docker run -it \--privileged=true -v /宿主机绝对路径目录:/容器内目录
    镜像名



-   查看数据卷是否挂载成功

>  docker inspect 容器ID
>
> 
>
>  ![](image/Snipaste_2022-10-07_17-29-24.png)

-   容器和宿主机之间数据共享

> 1  docker修改，主机同步获得 
>
> 2 主机修改，docker同步获得
>
> 3 docker容器stop，主机修改，docker容器重启看数据是否同步。【会同步】
>
> ![](image/image124.jpeg){width="4.4527777777777775in"
> height="1.444963910761155in"}

-   读写规则映射添加说明

    -   读写(默认)

> ![](image/image125.jpeg){width="4.4527777777777775in"
> height="0.8049956255468067in"}
>
>  
>
>  rw = read + write
>
> ![](image/image126.jpeg){width="4.4527777777777775in"
> height="2.1592136920384952in"}
>
>  
>
>  
>
>  

-   docker run -it \--privileged=true -v
    /宿主机绝对路径目录:/容器内目录:rw 镜像名

-   默认同上案例，默认就是rw



-   只读

    -   容器实例内部被限制，只能读取不能写

> ![](image/image127.jpeg){width="4.4527777777777775in"
> height="1.2316196412948381in"}
>
>  
>
>  /容器目录:ro 镜像名
>               就能完成功能，此时容器自己只能读取不能写  
>
>  
>
> ro = read only
>
>  
>
> 此时如果宿主机写入内容，可以同步给容器内，容器可以读取到。

-   docker run -it \--privileged=true -v
    /宿主机绝对路径目录:/容器内目录:ro 镜像名



-   卷的继承和共享

    -   容器1完成和宿主机的映射

>  
>
> docker run -it  \--privileged=true -v /mydocker/u:/tmp \--name u1
> ubuntu
>
>  
>
> ![](image/image128.jpeg){width="4.4527777777777775in"
> height="0.8501607611548556in"}
>
>  
>
> ![](image/image129.jpeg){width="4.4527777777777775in"
> height="0.8570472440944882in"}

-   容器2继承容器1的卷规则

> ![](image/image130.jpeg){width="4.4527777777777775in"
> height="1.275767716535433in"}

```
docker run -it --privileged=true --volumes-from 父类 --name u2 ubuntu
```



### 1.8、Docker常规安装简介

> ![](image/image131.png){width="0.37124015748031497in"
> height="0.37124015748031497in"}

1.  **总体步骤**

    -   搜索镜像

    -   拉取镜像

    -   查看镜像

    -   启动镜像

        -   服务端口映射

    -   停止容器

    -   移除容器

2.  **安装tomcat**

    -   docker hub上面查找tomcat镜像

        -   docker search tomcat

> ![](image/image132.jpeg){width="4.4527777777777775in"
> height="2.2307370953630796in"}

-   从docker hub上拉取tomcat镜像到本地

    -   docker pull tomcat

> ![](image/image133.jpeg){width="4.4527777777777775in"
> height="2.5556430446194227in"}
>
> ![](image/image134.jpeg){width="4.4527777777777775in"
> height="0.7568613298337707in"}

-   docker images查看是否有拉取到的tomcat

>  
>
>  
>
> ![](image/image135.jpeg){width="4.6194444444444445in"
> height="0.7397145669291338in"}

-   使用tomcat镜像创建容器实例(也叫运行镜像)

    -   docker run -it -p 8080:8080 tomcat

        -   -p 小写，主机端口:docker容器端口

        -   -P 大写，随机分配端口

> ![](image/image136.jpeg){width="4.4527777777777775in"
> height="1.1679418197725284in"}

-   i:交互

-   t:终端

-   d:后台

-   访问猫首页

    -   问题

>  
>
> ![](image/image137.jpeg){width="4.4527777777777775in"
> height="1.5483147419072616in"}

-   解决

    -   可能没有映射端口或者没有关闭防火墙

> ![](image/image136.jpeg){width="4.4527777777777775in"
> height="1.1679418197725284in"}

-   把webapps.dist目录换成webapps

    -   先成功启动tomcat

> ![](image/image138.jpeg){width="4.4527777777777775in"
> height="1.2833475503062117in"}

-   查看webapps 文件夹查看为空

>  
>
> ![](image/image139.jpeg){width="4.4527777777777775in"
> height="2.7858694225721785in"}

-   免修改版说明

    ```
    docker pull billygoo/tomcat8-jdk8
    docker run -d -p 8080:8080 --name mytomcat8billygoo/tomcat8-jdk8
    ```
    
    

> ![](image/image140.jpeg){width="4.4527777777777775in"
> height="2.1375623359580054in"}

3.  **安装mysql**

    -   docker hub上面查找mysql镜像

>  
>
> ![](image/image141.jpeg){width="4.6194444444444445in"
> height="1.1520734908136483in"}

-   从docker hub上(阿里云加速器)拉取mysql镜像到本地标签为5.7

>  
>
> ![](image/image142.jpeg){width="4.6194444444444445in"
> height="2.0387489063867017in"}

-   使用mysql5.7镜像创建容器(也叫运行镜像)

    -   命令出处，哪里来的？

> ![](image/image143.jpeg){width="4.4527777777777775in"
> height="1.1372583114610673in"}

-   简单版

    -   使用mysql镜像

  -----------------------------------------------------------------------
  ```shell
  docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
  docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
  docker ps
  docker exec -it 容器ID /bin/bash
  
  使用datagrip连接，或者使用linux连接docker内的mysql，必须使用完整 ip e.g 192.168.152.134
  ```
  
  mysql -uroot -p
  
  -----------------------------------------------------------------------

>   
>
> ![](image/image144.jpeg){width="4.4527777777777775in"
> height="1.4710662729658792in"}

-   建库建表插入数据

> ![](image/image145.jpeg){width="4.4527777777777775in"
> height="3.828556430446194in"}

-   外部Win10也来连接运行在dokcer上的mysql容器实例服务

>  
>
> ![](image/image146.jpeg){width="4.4527777777777775in"
> height="3.277244094488189in"}

-   问题

    -   插入中文数据试试

>  
>
> ![](image/image147.jpeg){width="4.4527777777777775in"
> height="1.8205489938757655in"}

-   为什么报错?

    -   docker上默认字符集编码隐患

```ini
创建数据库时携带 charset utf8就不会出现错误

# MySQL8配置文件配置
# Copyright (c) 2017, Oracle and/or its affiliates. All rights reserved.
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; version 2 of the License.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA

#
# The MySQL  Server configuration file.
#
# For explanations see
# http://dev.mysql.com/doc/mysql/en/server-system-variables.html

[mysqld]
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

# Custom config should go here
!includedir /etc/mysql/conf.d/

# 慢查询日志
# 开启慢查询日志
slow_query_log = 1
# 超过 2 s未慢查询
long_query_time = 2
# utf8
collation_server = utf8mb4_0900_ai_ci
character_set_server = utf8mb4
[client]
default_character_set=utf8mb4
```



>   docker里面的mysql容器实例查看，内容如下：
>
>  ```sql
>  SHOW VARIABLES LIKE 'character%'
>  ```
>
>  
>
>  ![](image/image148.jpeg)

-   删除容器后，里面的mysql数据如何办

    -   容器实例一删除，你还有什么？ 删容器到跑路。。。。。？

-   实战版

    -   新建mysql容器实例

  

  ```shell
  docker run -d -p 3306:3306 --privileged=true -v /usr/mysql/log:/var/log/mysql -v /usr/mysql/data:/var/lib/mysql -v /usr/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=root --name mysql mysql:5.7.19
  ```

  ```
  日志。数据，配置
  /var/log/mysql
  /var/lib/mysql
  /etc/mysql/conf.d
  ```

  

-----------------------------------------------------------------------

>  
>
> ![](image/image149.jpeg){width="4.4527777777777775in"
> height="1.6595220909886264in"}

-   新建my.cnf

    -   通过容器卷同步给mysql容器实例

```ini
[client]
default_character_set=utf8
[mysqld]
collation_server = utf8_general_ci
character_set_server = utf8
```



>   ![](image/image150.jpeg){width="4.4527777777777775in"
>height="1.584387576552931in"}

-   重新启动mysql容器实例再重新进入并查看字符编码

```sql
SHOW VARIABLES LIKE 'character%';
```



>  ![](image/image151.jpeg){width="4.4527777777777775in"
> height="2.039745188101487in"}
>
> ![](image/image152.jpeg){width="4.4527777777777775in"
> height="1.9842443132108487in"}

-   再新建库新建表再插入中文测试

> ![](image/image153.jpeg){width="4.4527777777777775in"
> height="1.9169181977252843in"}
>
> ![](image/image154.jpeg){width="4.4527777777777775in"
> height="2.598105861767279in"}

-   结论

>  之前的DB  无效
>
> 修改字符集操作+重启mysql容器实例
>
>  之后的DB  有效，需要新建
>
> 结论：docker安装完MySQL并run出容器后，建议请先修改完字符集编码后再新建mysql库-表-插数据
>
>  
>
> ![](image/image155.jpeg){width="4.4527777777777775in"
>height="3.114886264216973in"}

-   假如将当前容器实例删除，再重新来一次，之前建的db01实例还有吗？trytry

4.  **安装redis**

    -   从docker hub上(阿里云加速器)拉取redis镜像到本地标签为6.0.8

>  
>
> ![](image/image156.jpeg){width="4.6194444444444445in"
> height="1.4730850831146107in"}

-   入门命令

>  
>
> ![](image/image157.jpeg){width="4.6194444444444445in"
> height="1.4371609798775153in"}

-   **命令提醒：容器卷记得加入**\--privileged=true

>  
>
> Docker挂载主机目录Docker访问出现cannot open directory .: Permission
> denied
>
> 解决办法：在挂载目录后多加一个\--privileged=true参数即可

-   在CentOS宿主机下新建目录/app/redis

>  
>
> ![](image/image158.jpeg){width="4.6194444444444445in"
> height="1.63915791776028in"}
>
>  
>
> 1 建目录
>
>   mkdir -p /use/local/docker-images-conf/redis

-   mkdir -p /usr/local/docker-images-conf/redis



-   将一个redis.conf文件模板拷贝进redis目录下

>  
>
> ![](image/image159.jpeg){width="4.6194444444444445in"
> height="2.1603718285214346in"}
>
> 2 拷贝配置文件
>
>   将准备好的redis.conf文件放进/app/redis目录下

-   redis目录下修改redis.conf文件

>  
>
> 3 redis目录下修改redis.conf文件
>
>   3.1 开启redis验证    可选
>
>     requirepass 123
>
>  
>
>   3.2 允许redis外地连接  必须
>
>      注释掉 \# bind 127.0.0.1
>
> ![](image/image160.jpeg){width="4.6194444444444445in"
> height="0.6256583552055993in"}
>
>  
>
>   3.3   daemonize no
>
>      将daemonize yes注释起来或者 daemonize no设置，因为该配置和docker
> run中-d参数冲突，会导致容器一直启动失败
>
>  ![](image/image161.jpeg){width="4.6194444444444445in"
> height="0.690485564304462in"}
>
>   3.4 开启redis数据持久化  appendonly yes  可选






- 使用7.0.2镜像创建容器(也叫运行镜像)

  ```shell
  docker run -p 6379:6379 --privileged=true -v /usr/local/docker-images-conf/redis/redis.conf:/etc/redis/redis.conf -v /usr/local/docker-images-conf/redis/data:/data -d redis:7.0.2 redis-server /etc/redis/redis.conf
  	
  	最后的 /etc/redis/redis.conf 是容器内的配置文件
  ```



>  ![](image/image162.jpeg){width="4.6194444444444445in"
>height="0.744851268591426in"}

-   测试redis-cli连接上来

>  
>
> ![](image/image163.jpeg){width="4.6194444444444445in"
> height="1.6534076990376203in"}
>
>  docker exec -it 运行着Rediis服务的容器ID redis-cli

-   请证明docker启动使用了我们自己指定的配置文件

    -   修改前

>  
>
> ![](image/image164.jpeg){width="4.4527777777777775in"
> height="2.02833552055993in"}
>
>  
>
> 我们用的配置文件，数据库默认是16个

-   修改后

> ![](image/image165.jpeg){width="4.4527777777777775in"
> height="1.910369641294838in"}
>
>  
>
> 宿主机的修改会同步给docker容器里面的配置。

-   记得重启服务

```shell
docker restart redis:7.0.2
```



-   测试redis-cli连接上来第2次

> ![](image/image166.jpeg){width="4.6194444444444445in"
> height="1.6716437007874017in"}

5.  **安装Nginx**
-   见高级篇Portainer

```html
<!-- -->
```
## 2、高级篇(大厂进阶)

### 2.1、Docker复杂安装详说

#### 1、安装mysql主从复制

-   主从复制原理
    
    -   默认你懂
    
-   主从搭建步骤
    
    -   新建主服务器容器实例3307

```shell
docker run -d -p 3307:3306 --name mysql-master \
-v /usr/local/docker-images-conf/mysql/mysql-master/log/:/var/log/mysql/ \
-v /usr/local/docker-images-conf/mysql/mysql-master/data/:/var/lib/mysql/ \
-v /usr/local/docker-images-conf/mysql/mysql-master/conf/:/etc/mysql/ \
-e MYSQL_ROOT_PASSWORD=root \
mysql:5.7.19

# \可以连接下一行，便于观察
```



-   进入/mydata/mysql-master/conf目录下新建my.cnf

    -   vim my.cnf

```ini
[mysqld]
## 设置server_id，同一局域网中需要唯一
server_id=101
## 指定不需要同步的数据库名称
binlog-ignore-db=mysql
## 开启二进制日志功能
log-bin=mall-mysql-bin
## 设置二进制日志使用内存大小(事务)
binlog_cache_size=1M
## 设置使用的二进制日志格式(mixed,statement,row)
binlog_format=mixed
## 二进制日志过期清理时间,默认值为0,表示不自动清理.
expire_logs_days=7
## 跳过主从复制中遇到的所有错误或指定类型的错误.避免slave端复制中断
## 如：1062错误是指一些主键重复,1032错误是因为主从数据库数据不一致
slave_skip_errors=1062
## 解决中文编码问题
collation_server=utf8_general_ci
character_set_server=utf8
```



-   修改完配置后重启master实例

    -   docker restart mysql-master

-   进入mysql-master容器

    -   docker exec -it mysql-master /bin/bash

    -   mysql -uroot -proot

- master容器实例内创建数据同步用户

  ```sql
  CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
  GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'slave'@'%';
  ```

  

- 新建从服务器容器实例3308

-   

```shell
docker run -d -p 3308:3306 --name mysql-slave \
-v /usr/local/docker-images-conf/mysql/mysql-slave/log/:/var/log/mysql/ \
-v /usr/local/docker-images-conf/mysql/mysql-slave/data/:/var/lib/mysql/ \
-v /usr/local/docker-images-conf/mysql/mysql-slave/conf/:/etc/mysql/ \
-e MYSQL_ROOT_PASSWORD=root \
mysql:5.7.19
```



-   进入/mysql-slave/conf目录下新建my.cnf

    -   vim my.cnf

```ini
[mysqld]
## 设置server_id，同一局域网中需要唯一
server_id=102
## 指定不需要同步的数据库名称
binlog-ignore-db=mysql
## 开启二进制日志功能，以备Slave作为其它数据库实例的Master时使用
log-bin=mall-mysql-slave1-bin
## 设置二进制日志使用内存大小（事务）
binlog_cache_size=1M
## 设置使用的二进制日志格式（mixed,statement,row）
binlog_format=mixed
## 二进制日志过期清理时间。默认值为0，表示不自动清理
expire_logs_days=7
##跳过主从复制中遇到的所有错误或指定类型的错误，避免slave端复制中断
## 如：1062错误是指一些主键重复，1032错误是因为主从数据库数据不一致
slave_skip_errors=1062
## relay_log配置中继日志
relay_log=mall-mysql-relay-bin
## log_slave_updates表示slave将复制事件写进自己的二进制日志
log_slave_updates=1
## slave设置为只读（具有super权限的用户除外）
read_only=1
```



-   修改完配置后重启slave实例

    -   docker restart mysql-slave

-   在主数据库中查看主从同步状态

    -   show master status;

-   进入mysql-slave容器

    -   docker exec -it mysql-slave /bin/bash

    -   mysql -uroot -proot

-   在  从数据库中  配置主从复制

  -----------------------------------------------------------------------
  ```sql
  change master to master_host='宿主机ip', master_user='slave', master_password='123456', master_port=3307,master_log_file='mall-mysql-bin.000001', master_log_pos=617, master_connect_retry=30;
  ```
  
  
  
  -----------------------------------------------------------------------

>  ![](image/image169.jpeg){width="4.4527777777777775in"
> height="1.2684470691163605in"}
>
>  

-   主从复制命令参数说明

```
master_host：主数据库的IP地址；
master_port：主数据库的运行端口；
master_user：在主数据库创建的用于同步数据的用户账号；
master_password：在主数据库创建的用于同步数据的用户密码；
master_log_file：指定从数据库要复制数据的日志文件，通过查看主数据的状态，获取File参数；
master_log_pos：指定从数据库从哪个位置开始复制数据，通过查看主数据的状态，获取Position参数；
master_connect_retry：连接失败重试的时间间隔，单位为秒。
```



> ![](image/image170.png){width="4.4527777777777775in"
>height="1.3966272965879265in"}
> 
>

-   在    从数据库中   查看主从同步状态

    ```sql
    show slave status \G;
    ```
    
    

>  
>
> ![](image/image170.png){width="4.4527777777777775in"
> height="1.3966272965879265in"}

-   在    从数据库     中开启主从同步

    ![](image/image171.png)
    
-   查看从数据库状态发现已经同步

>  
>
> ![](image/image172.jpeg){width="4.4527777777777775in"
> height="3.095304024496938in"}

-   主从复制测试

    -   主机新建库-使用库-新建表-插入数据，ok

    -   从机使用库-查看记录，ok

#### 2、安装redis集群

-   cluster(集群)模式-docker版 哈希槽分区进行亿级数据存储

    -   面试题

        -   1\~2亿条数据需要缓存，请问如何设计这个存储案例

        -   回答

            -   单机单台100%不可能，肯定是分布式存储，用redis如何落地？

        -   上述问题阿里P6\~P7工程案例和场景设计类必考题目，
            一般业界有3种解决方案
    -   哈希取余分区

> ![](image/image173.png){width="4.4527777777777775in"
> height="3.686012685914261in"}

+-----------------------------------------------------------------------+
| 2亿条记录就是2亿个k,v，我们单机不行必须要                             |
| 分布式多机，假设有3台机器构成一个集群，用户每次读写操作都是根据公式： |
|                                                                       |
| hash(key) %                                                           |
| N个机器台数，计算出哈希值，用来决定数据映射到哪一个节点上。           |
+-----------------------------------------------------------------------+
| 优点：                                                                |
|                                                                       |
| 简单粗暴，直接有效，只需要预                                          |
| 估好数据规划好节点，例如3台、8台、10台，就能保证一段时间的数据支撑。  |
| 使用Hash算法让固定的一部分请求落到同一台服务器上，这样每台服务器固定  |
| 处理一部分请求（并维护这些请求的信息），起到负载均衡+分而治之的作用。 |
+-----------------------------------------------------------------------+
| 缺点：                                                                |
|                                                                       |
| 原来规划好的节点，进行扩容或者缩                                      |
| 容就比较麻烦了额，不管扩缩，每次数据变动导致节点有变动，映射关系需要  |
| 重新进行计算，在服务器个数固定不变时没有问题，如果需要弹性扩容或故障  |
| 停机的情况下，原来的取模公式就会发生变化：Hash(key)/3会变成Hash(key)  |
| /?。此时地址经                                                        |
| 过取余运算的结果将发生很大变化，根据公式获取的服务器也会变得不可控。  |
|                                                                       |
| 某个redis机器宕机了，由于台数数量变化，会导致hash取余全部数据重新洗牌。 |
+-----------------------------------------------------------------------+



-   一致性哈希算法分区

-   -   是什么

> 一致性Hash算法背景
>
> 　　一致性哈希算法在1997年由麻省理工学院中提出的，设计目标是为了解决
>
> 分布式缓存数据变动和映射问题，某个机器宕机了，分母数量改变了，自然取余数不OK了。
>
>  

-   能干嘛

    -   提出一致性Hash解决方案。 目的是当服务器个数发生变动时，
        尽量减少影响客户端到服务器的映射关系

-   **3大步骤**

    -   算法构建一致性哈希环

> 一致性哈希环
>
>    一致性哈希算法必然有个hash函数并按照算法产生hash值，这个算法的所有可能哈希值会构成一个全量集，这个集合可以成为一个hash空间\[0,2\^32-1\]，这个是一个线性空间，但是在算法中，我们通过适当的逻辑控制将它首尾相连(0
> = 2\^32),这样让它逻辑上形成了一个环形空间。
> 
>
>  
>   它也是按照使用取模的方法，前面笔记介绍的节点取模法是对节点（服务器）的数量进行取模。而一致性Hash算法是对2\^32取模，简单来说，一致性Hash算法将整个哈希值空间组织成一个虚拟的圆环，如假设某哈希函数H的值空间为0-2\^32-1（即哈希值是一个32位无符号整形），整个哈希环如下图：整个空间按顺时针方向组织，圆环的正上方的点代表0，0点右侧的第一个点代表1，以此类推，2、3、4、......直到2\^32-1，也就是说0点左侧的第一个点代表2\^32-1，
> 0和2\^32-1在零点中方向重合，我们把这个由2\^32个点组成的圆环称为Hash环。
> 
>![](image/image174.jpeg){width="4.135416666666667in"
> height="4.322916666666667in"}

-   服务器IP节点映射

> 节点映射
>
>    将集群中各个IP节点映射到环上的某一个位置。
>
>    将各个服务器使用Hash进行一个哈希，具体可以选择服务器的IP或主机名作为关键字进行哈希，这样每台机器就能确定其在哈希环上的位置。假如4个节点NodeA、B、C、D，经过IP地址的哈希函数计算(hash(ip))，使用IP地址哈希后在环空间的位置如下：  
>
> ![](image/image175.jpeg){width="4.4527777777777775in"
> height="4.313917322834645in"}

-   key落到服务器的落键规则

> 当我们需要存储一个kv键值对时，首先计算key的hash值，hash(key)，将这个key使用相同的函数Hash计算出哈希值并确定此数据在环上的位置，**从此位置沿环顺时针"行走"**，第一台遇到的服务器就是其应该定位到的服务器，并将该键值对存储在该节点上。
>
> 如我们有Object A、Object B、Object C、Object
> D四个数据对象，经过哈希计算后，在环空间上的位置如下：根据一致性Hash算法，数据A会被定为到Node
> A上，B被定为到Node B上，C被定为到Node C上，D被定为到Node D上。
>
> ![](image/image176.jpeg){width="4.4527777777777775in"
> height="4.4970395888014in"}

-   优点

    -   一致性哈希算法的容错性

> **容错性**
>
> 假设Node
> C宕机，可以看到此时对象A、B、D不会受到影响，只有C对象被重定位到Node
> D。一般的，在一致性Hash算法中，如果一台服务器不可用，则受影响的数据仅仅是此服务器到其环空间中前一台服务器（即沿着逆时针方向行走遇到的第一台服务器）之间数据，其它不会受到影响。简单说，就是C挂了，受到影响的只是B、C之间的数据，并且这些数据会转移到D进行存储。
>
> ![](image/image177.jpeg){width="4.4527777777777775in"
> height="4.514014654418197in"}
>
>  

-   一致性哈希算法的**扩展性**

>  扩展性
>
> 数据量增加了，需要增加一台节点NodeX，X的位置在A和B之间，那收到影响的也就是A到X之间的数据，重新把A到X的数据录入到X上即可，
>
> 不会导致hash取余全部数据重新洗牌。
>
> ![](image/image178.jpeg){width="4.4527777777777775in"
> height="4.05659230096238in"}
>
>  

-   缺点

    -   一致性哈希算法的数据倾斜问题

>  
>
> Hash环的数据倾斜问题
>
> 一致性Hash算法在服务**节点太少时**，容易因为节点分布不均匀而造成**数据倾斜**（被缓存的对象大部分集中缓存在某一台服务器上）问题，
>
> 例如系统中只有两台服务器：
>
>  ![](image/image179.jpeg){width="3.6875in"
> height="3.8125in"}

-   小总结

> 为了在节点数目发生改变时尽可能少的迁移数据
>
>  
>
> 将所有的存储节点排列在收尾相接的Hash环上，每个key在计算Hash后会顺时针找到临近的存储节点存放。
>
> 而当有节点加入或退出时仅影响该节点在Hash环上顺时针相邻的后续节点。  
>
>  
>
> 优点
>
> 加入和删除节点只影响哈希环中顺时针方向的相邻的节点，对其他节点无影响。
>
>  
>
> 缺点 
>
> 数据的分布和节点的位置有关，因为这些节点不是均匀的分布在哈希环上的，所以数据在进行存储时达不到均匀分布的效果。

-   哈希槽分区

    -   是什么

>  
>
> 1 为什么出现
>
> ![](image/image180.jpeg){width="4.4527777777777775in"
> height="0.687755905511811in"}
>
> 哈希槽实质就是一个数组，数组\[0,2\^14 -1\]形成hash slot空间。
>
>  
>
> 2 能干什么
>
> 解决均匀分配的问题，在数据和节点之间又加入了一层，把这层称为哈希槽（slot），用于管理数据和节点之间的关系，现在就相当于节点上放的是槽，槽里放的是数据。
>
> ![](image/image181.jpeg){width="4.4527777777777775in"
> height="1.361593394575678in"}
>
> 槽解决的是粒度问题，相当于把粒度变大了，这样便于数据移动。
>
> 哈希解决的是映射问题，使用key的哈希值来计算所在的槽，便于数据分配。
>
>  
>
> 3 多少个hash槽
>
> 一个集群只能有16384个槽，编号0-16383（0-2\^14-1）。这些槽会分配给集群中的所有主节点，分配策略没有要求。可以指定哪些编号的槽分配给哪个主节点。集群会记录节点和槽的对应关系。解决了节点和槽的关系后，接下来就需要对key求哈希值，然后对16384取余，余数是几key就落入对应的槽里。slot
> = CRC16(key) %
> 16384。以槽为单位移动数据，因为槽的数目是固定的，处理起来比较容易，这样数据移动问题就解决了。
>
>  

-   哈希槽计算

>  
>
> Redis 集群中内置了 16384 个哈希槽，redis
> 会根据节点数量大致均等的将哈希槽映射到不同的节点。当需要在 Redis
> 集群中放置一个 key-value时，redis 先对 key 使用 crc16
> 算法算出一个结果，然后把结果对 16384 求余数，这样每个 key
> 都会对应一个编号在 0-16383
> 之间的哈希槽，也就是映射到某个节点上。如下代码，key之A 、B在Node2，
> key之C落在Node3上
>
>  
>
> ![](image/image182.jpeg){width="4.4527777777777775in"
> height="3.1080391513560803in"}![](image/image183.jpeg){width="4.4527777777777775in"
> height="1.4820133420822397in"}

-   3主3从redis集群扩缩容配置案例架构说明

    -   见自己的processon笔记

-   开打步骤

    -   3主3从redis集群配置

        -   关闭防火墙+启动docker后台服务

>  ![](image/image184.jpeg){width="4.4527777777777775in"
>height="0.11608705161854768in"}

-   systemctl start docker

```html
<!-- -->
```
-   新建6个docker容器redis实例



```shell
docker run -d --name redis-node-1 \
--net host \
--privileged=true \
-v /usr/local/docker-images-conf/redis/redis-cluster/redis-node-1:/data redis:7.0.2 \
--cluster-enabled yes \
--appendonly yes \
--port 6381

docker run -d --name redis-node-2 --net host --privileged=true -v /usr/local/docker-images-conf/redis/redis-cluster/redis-node-2:/data redis:7.0.2 --cluster-enabled yes --appendonly yes --port 6382

docker run -d --name redis-node-3 --net host --privileged=true -v /usr/local/docker-images-conf/redis/redis-cluster/redis-node-3:/data redis:7.0.2 --cluster-enabled yes --appendonly yes --port 6383

docker run -d --name redis-node-4 --net host --privileged=true -v /usr/local/docker-images-conf/redis/redis-cluster/redis-node-4:/data redis:7.0.2 --cluster-enabled yes --appendonly yes --port 6384

docker run -d --name redis-node-5 --net host --privileged=true -v /usr/local/docker-images-conf/redis/redis-cluster/redis-node-5:/data redis:7.0.2 --cluster-enabled yes --appendonly yes --port 6385

docker run -d --name redis-node-6 --net host --privileged=true -v /usr/local/docker-images-conf/redis/redis-cluster/redis-node-6:/data redis:7.0.2 --cluster-enabled yes --appendonly yes --port 6386
```



> **如果运行成功，效果如下：**
> 
> ![](image/image185.jpeg){width="4.4527777777777775in"
>height="1.2147331583552057in"}

-   命令分步解释

    -   docker run
        -   创建并运行docker容器实例
    
    -   \--name redis-node-6
        -   容器名字

    -   \--net host
        -   使用宿主机的IP和端口，默认，不需要端口映射，直接使用宿主机的
    
    -   \--privileged=true
        -   获取宿主机root用户权限

    -   -v /data/redis/share/redis-node-6:/data
        -   容器卷，宿主机地址:docker内部地址
    
    -   redis:7.0.2
        -   redis镜像和版本号

    -   \--cluster-enabled yes
        -   开启redis集群
    
    -   \--appendonly yes
        -   开启持久化

    -   \--port 6386
        -   redis端口号
    

```html
<!-- -->
```
-   进入容器redis-node-1并为6台机器构建集群关系

    -   进入容器

        -   docker exec -it redis-node-1 /bin/bash

    -   构建主从关系

> //注意，进入docker容器后才能执行一下命令，且注意自己的真实IP地址

-----------------------------------------------------------------------


```shell
redis-cli --cluster create 192.168.152.134:6381 192.168.152.134:6382 192.168.152.134:6383 192.168.152.134:6384 192.168.152.134:6385 192.168.152.134:6386 --cluster-replicas 1

# --cluster-replicas 1表示为每一个master结点创建一个slave结点
```



-----------------------------------------------------------------------

>  
>
> ![](image/image186.jpeg){width="4.4527777777777775in"
>height="0.24060148731408573in"}
>  
>![](image/Snipaste_2022-10-08_14-02-12.png)
> 
> ![](image/Snipaste_2022-10-08_14-03-42.png)

-   一切OK的话，3主3从搞定



-   链接进入6381作为切入点，查看集群状态

    -   链接进入6381作为切入点，查看节点状态

-   cluster info

-   cluster nodes

![](image/Snipaste_2022-10-08_18-54-39.png)

-   主从容错切换迁移案例

    -   数据读写存储

        -   启动6机构成的集群并通过exec进入

        -   对6381新增两个key

        -   防止路由失效加参数-c并新增两个key

```shell
-c 启用集群模式（遵循 -ASK 和 -MOVED 重定向）。
```



>  
>
> ![](image/image192.jpeg){width="4.4527777777777775in"
> height="0.5506966316710411in"}
>
> 加入参数-c，优化路由
>
> ![](image/image193.jpeg){width="4.4527777777777775in"
> height="1.6396314523184603in"}

-   查看集群信息

-----------------------------------------------------------------------
```shell
redis-cli --cluster check 192.168.152.134:6381
```



-----------------------------------------------------------------------

>  
>
> ![](image/image194.jpeg){width="4.4527777777777775in"
> height="2.528461286089239in"}
>
> ![](image/image195.jpeg){width="4.4527777777777775in"
> height="2.6106386701662294in"}

-   容错切换迁移

    -   主6381和从机切换，先停止主机6381

        -   6381主机停了，对应的真实从机上位

        -   6381作为1号主机分配的从机以实际情况为准，具体是几号机器就是几号

    -   再次查看集群信息

>  
>
> ![](image/image196.jpeg){width="4.4527777777777775in"
> height="0.9649879702537183in"}
>
>  
>
>  6381宕机了，6385上位成为了新的master。
>
> 备注：本次脑图笔记6381为主下面挂从6385。
>
> 每次案例下面挂的从机以实际情况为准，具体是几号机器就是几号

```shell
# 重新启动6381，发现6381成为了从机slave
```



-   先还原之前的3主3从

>  
>
> ![](image/image197.jpeg){width="4.4527777777777775in"
> height="1.4994050743657044in"}
>
>  
>
> 中间需要等待一会儿，docker集群重新响应。

-   先启6381

> ![](image/image198.jpeg){width="4.4527777777777775in"
> height="1.571761811023622in"}

-   docker start redis-node-1

-   再停6385

>  
>
> ![](image/image199.jpeg){width="4.4527777777777775in"
> height="0.7366972878390201in"}

-   docker stop redis-node-5

-   再启6385

>  
>
> ![](image/image200.jpeg){width="4.4527777777777775in"
> height="0.7913856080489938in"}

-   docker start redis-node-5

-   主从机器分配情况以实际情况为准

```html
<!-- -->
```
-   查看集群状态

    -   redis-cli \--cluster check 自己IP:6381

>  
>
> ![](image/image201.jpeg){width="4.4527777777777775in"
> height="3.0659492563429573in"}

##### 主从扩容案例

-   新建6387、6388两个节点+新建后启动+查看是否8节点

-----------------------------------------------------------------------
```shell
docker run -d --name redis-node-7 --net host --privileged=true \
-v /usr/local/docker-images-conf/redis/redis-cluster/redis-node-7:/data redis:7.0.2 \
--cluster-enabled yes \
--appendonly yes --port 6387

docker run -d --name redis-node-8 --net host --privileged=true \
-v /usr/local/docker-images-conf/redis/redis-cluster/redis-node-8:/data redis:7.0.2 \
--cluster-enabled yes \
--appendonly yes --port 6388
```

docker ps

>  

-   进入6387容器实例内部

    -   docker exec -it redis-node-7 /bin/bash

-   将新增的6387节点(空槽号)作为master节点加入原集群

```shell
# 将新增的6387作为master节点加入集群
redis-cli --cluster add-node 自己实际IP地址:6387 自己实际IP地址:6381
# 6387 就是将要作为master新增节点
# 6381就是原来集群节点里面的领路人，相当于6387拜拜6381的码头从而找到组织加入集群
```



>  
>
> ![](image/image202.jpeg){width="4.4527777777777775in"
> height="2.0452515310586175in"}
>
> ![](image/Snipaste_2022-10-09_20-17-05.png)

-   检查集群情况第1次

-----------------------------------------------------------------------
  redis-cli \--cluster check 真实ip地址:6381

-----------------------------------------------------------------------



>  ![](image/Snipaste_2022-10-09_20-18-17.png)
>

-   重新分派槽号

```shell
# 重新分派槽号
# 命令:redis-cli --cluster reshard IP地址:端口号
redis-cli --cluster reshard 192.168.152.134:6381
```



> ![](image/Snipaste_2022-10-09_20-21-39.png)
> 
>![](image/image207.jpeg){width="4.4527777777777775in"
> height="2.9950590551181104in"}

-   检查集群情况第2次

-----------------------------------------------------------------------
```shell
redis-cli --cluster check 真实ip地址:6381
```



-----------------------------------------------------------------------

>  
>
> ![](image/Snipaste_2022-10-09_20-26-34.png)
> 

-   槽号分派说明

+-----------------------------------------------------------------------+
| 为什么6387是3个新的区间，以前的还是连续？                             |
|                                                                       |
| 重新分配成本太高，所以前3家各自匀                                     |
| 出来一部分，从6381/6382/6383三个旧节点分别匀出1364个坑位给新节点6387  |
+-----------------------------------------------------------------------+



>  ![](image/image210.jpeg)

-   为主节点6387分配从节点6388

```shell
# redis-cli --cluster add-node ip:新slave端口 ip:新master端口 --cluster-slave --cluster-master-id 新主机节点ID
redis-cli --cluster add-node 192.168.152.134:6388 192.168.152.134:6387 --cluster-slave --cluster-master-id xx
```



>  
>
> ![](image/image211.jpeg)
> 
>![](image/image212.jpeg)

-   检查集群情况第3次

-----------------------------------------------------------------------
```shell
# redis-cli --cluster check 192.168.152.134:6381
```



-----------------------------------------------------------------------

>  
>
> ![](image/image213.jpeg)
> 
>![](image/image214.jpeg)

##### 主从缩容案例

-   目的：6387和6388下线

-   检查集群情况1获得6388的节点ID

-----------------------------------------------------------------------
  redis-cli \--cluster check 192.168.152.134:6382

-----------------------------------------------------------------------

>  
>
>  
>
> ![](image/image215.jpeg)
> 
>
>  
>

-   将6388删除 从集群中将4号从节点6388删除

```shell
# 命令：redis-cli --cluster del-node ip:从机端口 从机6388节点ID
redis-cli --cluster del-node 192.168.152.134:6388 5d149074b7e57b802287d1797a874ed7a1a284a8
```





>  ![](image/Snipaste_2022-10-09_20-40-26.png)

-----------------------------------------------------------------------
```shell
redis-cli --cluster check 192.168.152.134:6382
```



>   检查一下发现，6388被删除了，只剩下7台机器了。
>
>  ![](image/image217.jpeg)
>
> 

-   将6387的槽号清空，重新分配，本例将清出来的槽号都给6381

  

  ```shell
  redis-cli --cluster reshard 192.168.152.134:6381
  ```

  

-----------------------------------------------------------------------

>  
>
>  ![](image/image218.jpeg)
> 
>![](image/image219.jpeg)
> 
> ![](image/image220.jpeg){width="4.4527777777777775in"
>height="0.20239938757655293in"}

-   检查集群情况第二次

+-----------------------------------------------------------------------+
| redis-cli \--cluster check 192.168.111.147:6381                       |
|                                                                       |
|                                                                       |
|                                                                       |
| 4096个槽位都指给                                                      |
| 6381，它变成了8192个槽位，相当于全部都给6381了，不然要输入3次，一锅端 |
+-----------------------------------------------------------------------+



>  ![](image/image221.jpeg)

-   将6387删除

```shell
# redis-cli --cluster del-node ip:端口 6387节点ID
redis-cli \--cluster del-node 192.168.152.134:6387 e4781f644d4a4e4d4b4d107157b9ba8144631451
```



>  
>
> ![](image/image222.jpeg)

-   检查集群情况第三次

>  

-----------------------------------------------------------------------
```shell
redis-cli --cluster check 192.168.152.134:6381
```



-----------------------------------------------------------------------

>  
>
>  ![](image/image223.jpeg)

### 2.2、DockerFile解析

> ![](image/image224.png){width="0.3645833333333333in"
> height="0.3645833333333333in"}

1.  **是什么**

    -   Dockerfile是用来构建Docker镜像的文本文件，**是由一条条构建镜像所需的指令和参数构成的脚本**。

    -   概述

>  
>
> ![](image/image225.jpeg){width="4.6194444444444445in"
> height="3.804248687664042in"}

-   官网

    -   https://docs.docker.com/engine/reference/builder/

-   构建三步骤

    -   编写Dockerfile文件

        -   docker build命令构建镜像

            -   docker run依镜像运行容器实例

2.  **DockerFile构建过程解析**
-   Dockerfile内容基础知识
    
    -   1：每条保留**字指令** 都必须为**大写**字母且后面要跟随至少一个参数
    
    -   2：指令按照从上到下，顺序执行
    
    -   3：#表示注释 
    
    -   4：每条指令都会创建一个新的镜像层并对镜像进行提交
    
-   Docker执行Dockerfile的大致流程
    
    -   （1）docker从基础镜像运行一个容器
    
    -   （2）执行一条指令并对容器作出修改
    
    -   （3）执行类似docker commit的操作提交一个新的镜像层
    
    -   （4）docker再基于刚提交的镜像运行一个新容器
    
    -   （5）执行dockerfile中的下一条指令直到所有指令都执行完成
    
-   **小总结**



>  从应用软件的角度来看，Dockerfile、Docker镜像与Docker容器分别代表软件的三个不同阶段，
>
> \*  Dockerfile是软件的原材料
>
> \*  Docker镜像是软件的交付品
>
> \*  Docker容器则可以认为是软件镜像的运行态，也即依照镜像运行的容器实例
>
> Dockerfile面向开发，Docker镜像成为交付标准，Docker容器则涉及部署与运维，三者缺一不可，合力充当Docker体系的基石。
>
> ![](image/image226.jpeg){width="4.6194444444444445in"
>height="1.9680916447944008in"}
> 
> 1
>Dockerfile，需要定义一个Dockerfile，Dockerfile定义了进程需要的一切东西。Dockerfile涉及的内容包括执行代码或者是文件、环境变量、依赖包、运行时环境、动态链接库、操作系统的发行版、服务进程和内核进程(当应用进程需要和系统服务和内核进程打交道，这时需要考虑如何设计namespace的权限控制)等等;
> 
> 
>
>  2 Docker镜像，在用Dockerfile定义一个文件之后，docker
>build时会产生一个Docker镜像，当运行 Docker镜像时会真正开始提供服务;
> 
> 
>
>  3 Docker容器，容器是直接提供服务的。
>
> 
>

#### DockerFile常用保留字指令

-   参考tomcat8的dockerfile入门

    -   https://github.com/docker-library/tomcat

-   FROM

    -   基础镜像，当前新镜像是基于哪个镜像的，指定一个已经存在的镜像作为模板，第一条必须是from

-   MAINTAINER

    -   镜像维护者的姓名和邮箱地址

-   RUN

    -   容器构建时需要运行的命令

    -   两种格式

        -   shell格式

>  
>
> ![](image/image227.jpeg){width="4.4527777777777775in"
> height="0.7497484689413824in"}
>
>  
>
>  
>
> RUN yum -y install vim

-   exec格式

>  
>
>  
>
> ![](image/image228.jpeg){width="4.4527777777777775in"
> height="0.698982939632546in"}

-   RUN是在 docker build时运行

```html
<!-- -->
```
-   EXPOSE

    -   当前容器对外暴露出的端口

-   WORKDIR

    -   指定在创建容器后，终端默认登陆的进来工作目录，一个落脚点

-   USER

    -   指定该镜像以什么样的用户去执行，如果都不指定，默认是root

-   ENV

    -   用来在构建镜像过程中设置环境变量

>  
>
> ENV MY_PATH /usr/mytest
>
> 这个环境变量可以在后续的任何RUN指令中使用，这就如同在命令前面指定了环境变量前缀一样；
>
> 也可以在其它指令中直接使用这些环境变量，
>
>  
>
> 比如：WORKDIR \$MY_PATH

-   ADD

    -   将宿主机目录下的文件拷贝进镜像且会自动处理URL和解压tar压缩包

-   COPY

    -   类似ADD，拷贝文件和目录到镜像中。 将从构建上下文目录中
        \<源路径\> 的文件/目录复制到新的一层的镜像内的 \<目标路径\> 位置
        -   COPY src dest
        
        -   COPY \[\"src\", \"dest\"\]
        
        -   \<src源路径\>：源文件或者源目录
        
        -   \<dest目标路径\>：容器内的指定路径，该路径不用事先建好，路径不存在的话，会自动创建。
    
-   VOLUME

    -   容器数据卷，用于数据保存和持久化工作【-v容器卷】

-   CMD

    -   指定容器启动后的要干的事情

>  
>
> ![](image/image229.png){width="4.4527777777777775in"
> height="1.2867574365704286in"}

-   注意

    -   Dockerfile 中可以有多个 CMD 指令，但只有最后一个生效，CMD 会被
        docker run 之后的参数替换

    -   参考官网Tomcat的dockerfile演示讲解

        -   官网最后一行命令

            ![](image/image230.png){width="3.1041666666666665in"
            height="0.6145833333333334in"}

        -   我们演示自己的覆盖操作

            ![](image/image231.png){width="4.527777777777778in"
            height="0.3116524496937883in"}

-   它和前面RUN命令的区别

    -   CMD是在docker run 时运行。

    -   RUN是在 docker build时运行。

```html
<!-- -->
```
-   ENTRYPOINT  【entrypoint】

    -   也是用来指定一个容器启动时要运行的命令

    -   类似于 CMD 指令，但是ENTRYPOINT不会被docker run后面的命令覆盖，
        而且这些命令行参数会被当作参数送给 ENTRYPOINT 指令指定的程序

    -   命令格式和案例说明

>  
>
> 命令格式：![](image/image232.jpeg){width="4.4527777777777775in"
> height="0.3810258092738408in"}
>
> ENTRYPOINT可以和CMD一起用，一般是变参才会使用 CMD ，这里的 CMD
> 等于是在给 ENTRYPOINT 传参。
>
> 当指定了ENTRYPOINT后，CMD的含义就发生了变化，不再是直接运行其命令而是将CMD的内容作为参数传递给ENTRYPOINT指令，他两个组合会变成![](image/image233.jpeg){width="2.6458333333333335in"
> height="0.46875in"}
>
>  
>
> 案例如下：假设已通过 Dockerfile 构建了 nginx:test 镜像：
>
> ![](image/image234.jpeg){width="3.84375in"
> height="1.1875in"}

------------------ ------------------------ --------------------------------
  是否传参           按照dockerfile编写执行   传参运行

  Docker命令         docker run  nginx:test   docker run  nginx:test -c
                                              /etc/nginx/new.conf

  衍生出的实际命令   nginx -c                 nginx -c /etc/nginx/new.conf
                     /etc/nginx/nginx.conf    
------------------ ------------------------ --------------------------------

>  

-   优点

    -   在执行docker run的时候可以指定 ENTRYPOINT 运行所需的参数。

-   注意

    -   如果 Dockerfile 中如果存在多个 ENTRYPOINT 指令，仅最后一个生效。

```{=html}
<!-- -->
```
-   **小总结**

>  
>
> ![](image/image235.jpeg){width="4.6194444444444445in"
> height="3.1775765529308835in"}

4.  **案例**

    -   自定义镜像mycentosjava8

        -   要求

            -   Centos7镜像具备vim+ifconfig+jdk8

            -   JDK的下载镜像地址

                -   官网

>  
>
>  下载地址：
>
> https://www.oracle.com/java/technologies/downloads/#java8
>
> ![](image/image236.jpeg){width="4.4527777777777775in"
> height="1.5339370078740158in"}

-   https://mirrors.yangxingzhen.com/jdk/

```html
<!-- -->
```
-   编写

    -   准备编写Dockerfile文件

> ![](image/image237.jpeg){width="4.4527777777777775in"
> height="1.1525437445319335in"}
>
>  

```dockerfile
FROM ubuntu
MAINTAINER danghf<1049334207@qq.com>
ENV MYPATH /usr/local
WORKDIR $MYPATH
# vim
R
RUN apt-get -y install vim
# net-tools
RUN apt-get -y install net-tools
# java8 lib
RUN apt-get -y install glibc.i686
RUN mkdir /usr/local/java
# ADD tar.gz and Dockerfile same path
ADD jdk-8u351-linux-x64.tar.gz /usr/local/java
# ENV

ENV JAVA_HOME /usr/local/java/jdk1.8.0_351
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH
EXPOSE 80
CMD echo $MYPATH
CMD echo "success-----ok"
CMD /bin/bash
```



-   大写字母D

```html
<!-- -->
```
-   构建

    -   docker build -t 新镜像名字:TAG .

> docker build -t centosjava8:1.5 .
>
>  
>
>  ![](image/image238.jpeg){width="4.4527777777777775in"
> height="3.195122484689414in"}
>
> ![](image/image239.jpeg){width="4.4527777777777775in"
> height="3.2797758092738407in"}

-   注意，上面TAG后面有个空格，有个点

```html
<!-- -->
```
-   运行

    -   docker run -it 新镜像名字:TAG

>  docker run -it centosjava8:1.5 /bin/bash
>
>  
>
> ![](image/image240.jpeg){width="4.4527777777777775in"
> height="2.472648731408574in"}

-   再体会下UnionFS（联合文件系统）

> UnionFS（联合文件系统）：Union文件系统（UnionFS）是一种分层、轻量级并且高性能的文件系统，它支持对文件系统的修改作为一次提交来一层层的叠加，同时可以将不同目录挂载到同一个虚拟文件系统下(unite
> several directories into a single virtual filesystem)。Union
> 文件系统是 Docker
> 镜像的基础。镜像可以通过分层来进行继承，基于基础镜像（没有父镜像），可以制作各种具体的应用镜像。
>
> ![](image/image72.jpeg){width="4.4527777777777775in"
> height="2.658122265966754in"} 
>
> 特性：一次同时加载多个文件系统，但从外面看起来，只能看到一个文件系统，联合加载会把各层文件系统叠加起来，这样最终的文件系统会包含所有底层的文件和目录

-   虚悬镜像

    -   是什么

        -   仓库名、标签都是\<none\>的镜像，俗称dangling image

        -   Dockerfile写一个

>  
>
> 1 vim Dockerfile

+-----------------------------------------------------------------------+
| from ubuntu                                                           |
|                                                                       |
| CMD echo \'action is success\'                                        |
+-----------------------------------------------------------------------+

>  
>
> 2 docker build .
>
>  
>
> ![](image/image241.jpeg){width="4.4527777777777775in"
> height="0.42043416447944004in"}

-   查看

    -   docker image ls -f dangling=true

    -   命令结果

>  
>
> ![](image/image242.jpeg){width="4.4527777777777775in"
> height="0.6032797462817148in"}

-   删除

>  
>
> docker image prune
>
>  
>
> 虚悬镜像已经失去存在价值，可以删除
>
>  
>
> ![](image/image243.jpeg){width="4.4527777777777775in"
> height="1.547917760279965in"}

-   家庭作业-自定义镜像myubuntu

    -   编写

        -   准备编写DockerFile文件

> ![](image/image244.jpeg){width="4.4527777777777775in"
> height="0.8187095363079615in"}
>
>  

+-----------------------------------------------------------------------+
| FROM ubuntu                                                           |
|                                                                       |
| MAINTAINER zzyy\<zzyybs@126.com\>                                     |
|                                                                       |
|                                                                       |
|                                                                       |
| ENV MYPATH /usr/local                                                 |
|                                                                       |
| WORKDIR \$MYPATH                                                      |
|                                                                       |
|                                                                       |
|                                                                       |
| RUN apt-get update                                                    |
|                                                                       |
| RUN apt-get install net-tools                                         |
|                                                                       |
| #RUN apt-get install -y iproute2                                      |
|                                                                       |
| #RUN apt-get install -y inetutils-ping                                |
|                                                                       |
|                                                                       |
|                                                                       |
| EXPOSE 80                                                             |
|                                                                       |
|                                                                       |
|                                                                       |
| CMD echo \$MYPATH                                                     |
|                                                                       |
| CMD echo \"install inconfig cmd into ubuntu                           |
| success\-\-\-\-\-\-\-\-\-\-\-\-\--ok\"                                |
|                                                                       |
| CMD /bin/bash                                                         |
+-----------------------------------------------------------------------+

>  

-   构建

    -   docker build -t 新镜像名字:TAG .

-   运行

    -   docker run -it 新镜像名字:TAG

5.  **小总结**

>  
>
> ![](image/image225.jpeg){width="4.591666666666667in"
> height="3.7813724846894137in"}

### 2.3、Docker微服务实战

> ![](image/image245.png){width="0.3733300524934383in"
> height="0.3733300524934383in"}

1.  通过IDEA新建一个普通微服务模块

    -   建Module

        -   docker_boot

    -   改POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>docker</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.7.3</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
            <version>2.7.3</version>
        </dependency>
    </dependencies>
</project>
```



-   写YML

```yaml
server:
  port: 8000
spring:
  application:
    name: Docker_test
```



-   主启动

```java
@SpringBootApplication
public class DockerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerApplication.class,args);
    }
}
```



-   业务类

```java
@RestController
@RequestMapping("/docker")
public class DockerController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/hello")
    public String helloDocker(){
        return "hello Docker "+ port;
    }
    @GetMapping("/{obj}")
    public String testDocker(@PathVariable(value = "obj") Object o){
        return "docker "+ o.toString();
    }
}
```



2.  通过dockerfile发布微服务部署到docker容器

    -   IDEA工具里面搞定微服务jar包

>  
>
> docker_boot-0.0.1-SNAPSHOT.jar
>
>  
>
> ![](image/image246.jpeg){width="4.6194444444444445in"
> height="2.000711942257218in"}

-   编写Dockerfile

    -   Dockerfile内容

```dockerfile
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER danghf

# VOLUME指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为zzyy_docker.jar
ADD docker_boot-0.0.1-SNAPSHOT.jar zzyy_docker.jar
# 运行jar包

RUN bash -c 'touch zzyy_docker.jar'

ENTRYPOINT ["java","-jar","zzyy_docker.jar"]

#暴露6001端口作为微服务

EXPOSE 6001
```



-   将微服务jar包和Dockerfile文件上传到同一个目录下/mydocker

>  
>
> ![](image/image247.jpeg){width="4.4527777777777775in"
> height="1.0552537182852144in"}
>
>  
>
>  docker build -t zzyy_docker:1.6 .

-   构建镜像

>  
>
> ![](image/image248.jpeg){width="4.6194444444444445in"
> height="2.9927898075240593in"}

-   docker build -t zzyy_docker:1.6 .

-   打包成镜像文件

>  
>
> ![](image/image248.jpeg){width="4.4527777777777775in"
> height="2.884811898512686in"}

-   运行容器

>  docker run -d -p 6001:6001 zzyy_docker:1.6
>
> ![](image/image249.jpeg){width="4.6194444444444445in"
> height="2.105784120734908in"}

-   访问测试

>  
>
> ![](image/image250.jpeg){width="4.6194444444444445in"
> height="1.4159219160104988in"}

### 2.4、Docker网络

> ![](image/image251.png){width="0.3691994750656168in"
> height="0.3691994750656168in"}

1.  是什么

    -   docker不启动，默认网络情况

> ![](image/image252.jpeg){width="4.6194444444444445in"
> height="3.0944553805774277in"}

-   ens33

-   lo

-   virbr0

>  
>
> 在CentOS7的安装过程中如果有选择相关虚拟化的的服务安装系统后，启动网卡时会发现有一个以网桥连接的私网地址的virbr0网卡(virbr0网卡：它还有一个固定的默认IP地址192.168.122.1)，是做虚拟机网桥的使用的，其作用是为连接其上的虚机网卡提供
> NAT访问外网的功能。
>
>  
>
> 我们之前学习Linux安装，勾选安装系统的时候附带了libvirt服务才会生成的一个东西，如果不需要可以直接将libvirtd服务卸载，
>
> yum remove libvirt-libs.x86_64

-   docker启动后，网络情况

> **会产生一个名为docker0的虚拟网桥**
>
> ![](image/image253.jpeg){width="4.6194444444444445in"
> height="2.8863648293963253in"}

-   查看docker网络模式命令

>  
>
> 默认创建3大网络模式
>
> ![](image/image254.jpeg)

2.  常用基本命令

    -   All命令

> ![](image/image255.jpeg)

-   查看网络

    -   docker network ls

-   查看网络源数据

    -   docker network inspect XXX网络名字

-   删除网络

    -   docker network rm XXX网络名字

-   案例

>  
>
> ![](image/image256.jpeg){width="4.6194444444444445in"
> height="3.07704615048119in"}

3.  能干嘛

    -   容器间的互联和通信以及端口映射

    -   容器IP变动时候可以通过服务名直接网络通信而不受到影响

4.  **网络模式**

    -   总体介绍

> ![](image/image257.jpeg){width="4.6194444444444445in"
> height="2.4292279090113738in"}

-   bridge模式：使用\--network bridge指定，默认使用docker0

-   host模式：使用\--network host指定

-   none模式：使用\--network none指定

-   container模式：使用\--network container:NAME或者容器ID指定

```html
<!-- -->
```
-   容器实例内默认网络IP生产规则

    -   说明

> 1 先启动两个ubuntu容器实例
>
> ![](image/image258.jpeg){width="4.4527777777777775in"
> height="1.233779527559055in"}
>
> 2 docker inspect 容器ID or 容器名字
>
>  ![](image/image259.jpeg){width="4.4527777777777775in"
> height="1.785725065616798in"}
>
> 3  关闭u2实例，新建u3，查看ip变化
>
> ![](image/image260.jpeg){width="4.4527777777777775in"
> height="1.894251968503937in"}

-   结论

    -   docker容器内部的ip是有可能会发生改变的

```html
<!-- -->
```
-   **案例说明**

    -   bridge

        -   是什么

>  
>
> Docker 服务默认会创建一个 docker0 网桥（其上有一个 docker0
> 内部接口），该桥接网络的名称为docker0，它在内核层连通了其他的物理或虚拟网卡，这就将所有容器和本地主机都放到同一个物理网络。Docker
> 默认指定了 docker0 接口 的 IP
> 地址和子网掩码，让主机和容器之间可以通过网桥相互通信。
>
>  
>
> \# 查看 bridge 网络的详细信息，并通过 grep 获取名称项
>
> docker network inspect bridge \| grep name
>
> ![](image/image261.jpeg){width="4.4527777777777775in"
> height="0.5902176290463692in"}
>
>  
>
> ifconfig
>
> ![](image/image262.jpeg){width="4.4527777777777775in"
> height="0.6714501312335958in"}

-   案例

    -   说明

> 1
> Docker使用Linux桥接，在宿主机虚拟一个Docker容器网桥(docker0)，Docker启动一个容器时会根据Docker网桥的网段分配给容器一个IP地址，称为Container-IP，同时Docker网桥是每个容器的默认网关。因为在同一宿主机内的容器都接入同一个网桥，这样容器之间就能够通过容器的Container-IP直接通信。
>
>  
>
> 2 docker run
> 的时候，没有指定network的话默认使用的网桥模式就是bridge，使用的就是docker0。在宿主机ifconfig,就可以看到docker0和自己create的network(后面讲)eth0，eth1，eth2......代表网卡一，网卡二，网卡三......，lo代表127.0.0.1，即localhost，inet
> addr用来表示网卡的IP地址
>
>  
>
> 3
> 网桥docker0创建一对对等虚拟设备接口一个叫veth，另一个叫eth0，成对匹配。
>
>    3.1
> 整个宿主机的网桥模式都是docker0，类似一个交换机有一堆接口，每个接口叫veth，在本地主机和容器内分别创建一个虚拟接口，并让他们彼此联通（这样一对接口叫veth
> pair）；
>
>    3.2 每个容器实例内部也有一块网卡，每个接口叫eth0；
>
>    3.3
> docker0上面的每个veth匹配某个容器实例内部的eth0，两两配对，一一匹配。
>
>  通过上述，将宿主机上的所有容器都连接到这个内部网络上，两个容器在同一个网络下,会从这个网关下各自拿到分配的ip，此时两个容器的网络是互通的。
>
> ![](image/image263.jpeg){width="4.4527777777777775in"
> height="2.2299628171478565in"}

-   代码

    -   docker run -d -p 8081:8080 \--name tomcat81
        billygoo/tomcat8-jdk8

    -   docker run -d -p 8082:8080 \--name tomcat82
        billygoo/tomcat8-jdk8

-   两两匹配验证

> ![](image/image264.jpeg){width="4.4527777777777775in"
> height="1.8699518810148732in"}

-   host

    -   是什么

>  
>
> 直接使用宿主机的 IP 地址与外界进行通信，不再需要额外进行NAT 转换。

-   案例

    -   说明

>  
>
> 容器将不会获得一个独立的Network Namespace，
> 而是和宿主机共用一个Network
> Namespace。容器将不会虚拟出自己的网卡而是使用宿主机的IP和端口。
>
> ![](image/image265.jpeg){width="4.4527777777777775in"
> height="2.2176345144356957in"}

-   代码

    -   警告

        -   docker run -d -p 8083:8080 \--network host \--name tomcat83
            billygoo/tomcat8-jdk8

>  
>
> ![](image/image266.jpeg){width="4.4527777777777775in"
> height="0.8256277340332459in"}
>
>  
>
> 问题：
>
>      docke启动时总是遇见标题中的警告
>
> 原因：
>
>    
> docker启动时指定\--network=host或-net=host，如果还指定了-p映射端口，那这个时候就会有此警告，
>
> 并且通过-p设置的参数将不会起到任何作用，端口号会以主机端口号为主，重复时则递增。
>
> 解决:
>
>    
> 解决的办法就是使用docker的其他网络模式，例如\--network=bridge，这样就可以解决问题，或者直接无视。。。。O(∩\_∩)O哈哈\~

-   正确

    -   docker run -d \--network host \--name tomcat83
        billygoo/tomcat8-jdk8

```html
<!-- -->
```
-   无之前的配对显示了，看容器实例内部

>  
>
> ![](image/image267.jpeg){width="4.4527777777777775in"
> height="3.085754593175853in"}

-   没有设置-p的端口映射了，如何访问启动的tomcat83？？

>  
>
> http://宿主机IP:8080/
>
>  
>
> 在CentOS里面用默认的火狐浏览器访问容器内的tomcat83看到访问成功，因为此时容器的IP借用主机的，
>
> 所以容器共享宿主机网络IP，这样的好处是外部主机与容器可以直接通信。

-   none

    -   是什么

>  
>
>  在none模式下，并不为Docker容器进行任何网络配置。 
>
> 也就是说，这个Docker容器没有网卡、IP、路由等信息，只有一个lo
>
> 需要我们自己为Docker容器添加网卡、配置IP等。

-   禁用网络功能，只有lo标识(就是127.0.0.1表示本地回环)

```html
<!-- -->
```
-   案例

>  
>
> docker run -d -p 8084:8080 \--network none \--name tomcat84
> billygoo/tomcat8-jdk8
>
>  进入容器内部查看
>
> ![](image/image268.jpeg){width="4.4527777777777775in"
> height="1.247234251968504in"}
>
> 在容器外部查看
>
> ![](image/image269.jpeg){width="4.4527777777777775in"
> height="2.9685181539807526in"}

-   docker run -d -p 8084:8080 \--network none \--name tomcat84
    billygoo/tomcat8-jdk8

```html
<!-- -->
```
-   container

    -   是什么

> container⽹络模式 
>
> 新建的容器和已经存在的一个容器共享一个网络ip配置而不是和宿主机共享。新创建的容器不会创建自己的网卡，配置自己的IP，而是和一个指定的容器共享IP、端口范围等。同样，两个容器除了网络方面，其他的如文件系统、进程列表等还是隔离的。
>
> ![](image/image270.jpeg){width="4.4527777777777775in"
> height="2.3621095800524934in"}

-   案例

  --------- -----------------------------------------------------------------------------------
  ![](image/image32.png)
  
  --------- -----------------------------------------------------------------------------------

-   docker run -d -p 8085:8080 \--name tomcat85 billygoo/tomcat8-jdk8

-   docker run -d -p 8086:8080 \--network container:tomcat85 \--name
    tomcat86 billygoo/tomcat8-jdk8

-   运行结果

>  
>
> ![](image/image271.jpeg){width="4.4527777777777775in"
> height="0.4596675415573053in"}
>
>  
>
>  \# 相当于tomcat86和tomcat85公用同一个ip同一个端口，导致端口冲突
>
>  本案例用tomcat演示不合适。。。演示坑。。。。。。o(╥﹏╥)o
>
> 
>
> 换一个镜像给大家演示，

-   案例2

  --------- -----------------------------------------------------------------------------------
  ​    ![](image/image34.png)
  
  --------- -----------------------------------------------------------------------------------

-   Alpine操作系统是一个面向安全的轻型 Linux发行版

>  
>
>  
>
> Alpine Linux 是一款独立的、非商业的通用 Linux
> 发行版，专为追求安全性、简单性和资源效率的用户而设计。
> 可能很多人没听说过这个 Linux 发行版本，但是经常用 Docker
> 的朋友可能都用过，因为他小，简单，安全而著称，所以作为基础镜像是非常好的一个选择，可谓是麻雀虽小但五脏俱全，镜像非常小巧，不到
> 6M的大小，所以特别适合容器打包。

-   docker run -it \--name alpine1 alpine /bin/sh

-   docker run -it \--network container:alpine1 \--name alpine2 alpine
    /bin/sh

-   运行结果，验证共用搭桥

> ![](image/image272.jpeg){width="4.4527777777777775in"
> height="2.3926859142607175in"}

-   假如此时关闭alpine1，再看看alpine2

> ![](image/image273.jpeg){width="4.4527777777777775in"
> height="0.6302548118985127in"}
>
> 15: eth0@if16: 消失了。。。。。。关闭alpine1，再看看alpine2
>
> ![](image/image274.jpeg){width="4.4527777777777775in"
> height="1.7925524934383201in"}

-   自定义网络

    -   过时的link

> ![](image/image275.jpeg){width="4.4527777777777775in"
> height="2.7500021872265967in"}

-   是什么

-   案例

    -   before

        -   案例

            -   docker run -d -p 8081:8080 \--name tomcat81
                billygoo/tomcat8-jdk8

            -   docker run -d -p 8082:8080 \--name tomcat82
                billygoo/tomcat8-jdk8

            -   上述成功启动并用docker exec进入各自容器实例内部

        -   问题

            -   按照IP地址ping是OK的

> ![](image/image276.jpeg){width="4.4527777777777775in"
> height="1.2695352143482064in"}
>
> ![](image/image277.jpeg){width="4.4527777777777775in"
> height="1.216075021872266in"}

-   按照服务名ping结果???

>  
>
> ![](image/image278.jpeg){width="4.4527777777777775in"
> height="0.740757874015748in"}
>
>  
>
> ![](image/image279.jpeg){width="4.4527777777777775in"
> height="0.7305336832895888in"}

-   after

    -   案例

        -   自定义桥接网络,自定义网络默认使用的是桥接网络bridge

        -   新建自定义网络

>  
>
> ![](image/image280.jpeg){width="4.4527777777777775in"
> height="2.571804461942257in"}

-   新建容器加入上一步新建的自定义网络

-   docker run -d -p 8081:8080 \--network zzyy_network \--name tomcat81
    billygoo/tomcat8-jdk8

-   docker run -d -p 8082:8080 \--network zzyy_network \--name tomcat82
    billygoo/tomcat8-jdk8

-   互相ping测试

> ![](image/image281.jpeg){width="4.4527777777777775in"
> height="1.313137576552931in"}
>
> ![](image/image282.jpeg){width="4.4527777777777775in"
> height="1.2006889763779527in"}

-   问题结论

    -   自定义网络本身就维护好了主机名和ip的对应关系（ip和域名都能通）

    -   自定义网络本身就维护好了主机名和ip的对应关系（ip和域名都能通）

    -   自定义网络本身就维护好了主机名和ip的对应关系（ip和域名都能通）

5.  Docker平台架构图解

    -   整体说明

> 从其架构和运行流程来看，Docker 是一个 C/S
> 模式的架构，后端是一个松耦合架构，众多模块各司其职。 
>
>  
>
> Docker 运行的基本流程为：
>
>  
>
> 1 用户是使用 Docker Client 与 Docker Daemon
> 建立通信，并发送请求给后者。
>
> 2 Docker Daemon 作为 Docker 架构中的主体部分，首先提供 Docker Server
> 的功能使其可以接受 Docker Client 的请求。
>
> 3 Docker Engine 执行 Docker 内部的一系列工作，每一项工作都是以一个 Job
> 的形式的存在。
>
> 4 Job 的运行过程中，当需要容器镜像时，则从 Docker Registry
> 中下载镜像，并通过镜像管理驱动 Graph
> driver将下载镜像以Graph的形式存储。
>
> 5 当需要为 Docker 创建网络环境时，通过网络管理驱动 Network driver
> 创建并配置 Docker 容器网络环境。
>
> 6 当需要限制 Docker 容器运行资源或执行用户指令等操作时，则通过
> Execdriver 来完成。
>
> 7 Libcontainer是一项独立的容器管理包，Network driver以及Exec
> driver都是通过Libcontainer来实现具体对容器进行的操作。

-   整体架构

> ![](image/image28.jpeg){width="4.6194444444444445in"
> height="6.4259984689413825in"}

### 2.5、Docker-compose容器编排

> ![](image/image283.png){width="0.3666622922134733in"
> height="0.3666622922134733in"}

1.  是什么

>  
>
> Compose 是 Docker 公司推出的一个工具软件，可以管理多个 Docker
> 容器组成一个应用。你需要定义一个 YAML
> 格式的配置文件docker-compose.yml，写好多个容器之间的调用关系。然后，只要一个命令，就能同时启动/关闭这些容器

-   Docker-Compose是Docker官方的开源项目，
    负责实现对Docker容器集群的快速编排。

2.  能干嘛

>  
>
>  docker建议我们每一个容器中只运行一个服务,因为docker容器本身占用资源极少,所以最好是将每个服务单独的分割开来但是这样我们又面临了一个问题？
>
>  
>
> 如果我需要同时部署好多个服务,难道要每个服务单独写Dockerfile然后在构建镜像,构建容器,这样累都累死了,所以docker官方给我们提供了docker-compose多服务部署的工具
>
>  
>
> 例如要实现一个Web微服务项目，除了Web服务容器本身，往往还需要再加上后端的数据库mysql服务容器，redis服务器，注册中心eureka，甚至还包括负载均衡容器等等。。。。。。
>
>  
>
> Compose允许用户通过一个单独的docker-compose.yml模板文件（YAML
> 格式）来定义一组相关联的应用容器为一个项目（project）。
>
>  
>
> 可以很容易地用一个配置文件定义一个多容器的应用，然后使用一条指令安装这个应用的所有依赖，完成构建。Docker-Compose
> 解决了容器与容器之间如何管理编排的问题。

3.  去哪下

    -   官网

        -   https://docs.docker.com/compose/compose-file/compose-file-v3/

    -   官网下载

        -   https://docs.docker.com/compose/install/

    -   安装步骤

--------------------------------------------------------------------------------------
  curl -L
  \"https://github.com/docker/compose/releases/download/1.29.2/docker-compose-\$(uname
  -s)-\$(uname -m)\" -o /usr/local/bin/docker-compose

  chmod +x /usr/local/bin/docker-compose

  docker-compose \--version

>  
>
> ![](image/image284.jpeg){width="4.6194444444444445in"
> height="1.2563659230096238in"}

-   卸载步骤

>  
>
>  
>
> ![](image/image285.jpeg){width="4.6194444444444445in"
> height="1.4888276465441819in"}

4.  Compose核心概念

    -   一文件

        -   docker-compose.yml

    -   两要素

        -   服务（service）

            -   一个个应用容器实例，比如订单微服务、库存微服务、mysql容器、nginx容器或者redis容器

        -   工程（project）

            -   由一组关联的应用容器组成的一个完整业务单元，在
                docker-compose.yml 文件中定义。

5.  Compose使用的三个步骤

    -   编写Dockerfile定义各个微服务应用并构建出对应的镜像文件

    -   使用 docker-compose.yml
        定义一个完整业务单元，安排好整体应用中的各个容器服务。

    -   最后，执行docker compose up命令
        来启动并运行整个应用程序，完成一键部署上线

6.  Compose常用命令

> **Compose常用命令**
>
> docker compose -h                           \# 查看帮助
>
> docker compose up                           \#
> 启动所有docker-compose服务
>
> docker compose up -d                        \#
> 启动所有docker-compose服务并后台运行
>
> docker compose down                         \#
> 停止并删除容器、网络、卷、镜像。
>
> docker compose exec  yml里面的服务id                 #
> 进入容器实例内部  docker-compose exec
> docker compose.yml文件中写的服务id /bin/bash
>
> docker compose ps                      #
> 展示当前docker-compose编排过的运行的所有容器
>
> docker compose top                     #
> 展示当前docker-compose编排过的容器进程
>
>  
>
> docker compose logs  yml里面的服务id     \# 查看容器输出日志
>
> docker compose config     \# 检查配置
>
> docker compose config -q  \# 检查配置，有问题才有输出
>
> docker compose restart   \# 重启服务
>
> docker compose start     \# 启动服务
>
> docker compose stop      \# 停止服务
>
>  

7.  Compose编排微服务

    -   改造升级微服务工程docker_boot

        -   以前的基础版

> ![](image/image286.jpeg){width="3.5104166666666665in"
> height="5.0625in"}





-   写YML

```properties
server.port=6001
#========================alibaba.druid相关配置=====================
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://192.168.111.169:3306/db2021?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.druid.test-while-idle=false
# ========================redis相关配置=====================
spring.redis.database=0
spring.redis.host=192.168.111.169
spring.redis.port=6379
spring.redis.password=
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
# ========================mybatis相关配置===================
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.atguigu.docker.entities
# ========================swagger=====================
spring.swagger2.enabled=true


```




-   mvn package命令将微服务形成新的jar包
    并上传到Linux服务器/mydocker目录下

-   编写Dockerfile

```dockerfile
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER zzyy
# VOLUME
# 指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为zzyy_docker.jar
ADD docker_boot-0.0.1-SNAPSHOT.jar zzyy_docker.jar
# 运行jar包
RUN bash -c 'touch zzyy_docker.jar'
ENTRYPOINT ["java","-jar","zzyy_docker.jar"]
#暴露6001端口作为微服务
EXPOSE 6001
```



-   构建镜像

    -   docker build -t zzyy_docker:1.6 .

```html
<!-- -->
```
-   不用**Compose**

    -   单独的mysql容器实例

        -   新建mysql容器实例

  -----------------------------------------------------------------------
  ```shell
  docker run -p 3306:3306 --name mysql57 --privileged=true -v
  /zzyyuse/mysql/conf:/etc/mysql/conf.d -v /zzyyuse/mysql/logs:/logs -v
  /zzyyuse/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d
  mysql:5.7
  ```
  
  
  
  -----------------------------------------------------------------------

>  

-   单独的redis容器实例

```shell
docker run  -p 6379:6379 --name redis608 --privileged=true -v
/app/redis/redis.conf:/etc/redis/redis.conf -v /app/redis/data:/data
-d redis:6.0.8 redis-server /etc/redis/redis.conf
```



-   微服务工程



>   docker run -d -p 6001:6001 zzyy_docker:1.6

-   上面三个容器实例依次顺序启动成功

>  
>
> ![](image/image287.jpeg){width="4.4527777777777775in"
> height="0.6953182414698162in"}

-   swagger测试

    -   http://localhost:你的微服务端口/swagger-ui.html#/

-   上面成功了，有哪些问题?

    -   先后顺序要求固定，先mysql+redis才能微服务访问成功

    -   多个run命令\...\...

    -   容器间的启停或宕机，有可能导致IP地址对应的容器实例变化，映射出错，
        要么生产IP写死(可以但是不推荐)，要么通过服务调用

-   使用**Compose**

    -   服务编排，一套带走，安排

    -   编写docker-compose.yml文件

```yaml
# docker compose版本
version: "3"

# 服务实例
services:
  # 第一个服务，名称自定义 microService
  microService:
    image: zzyy_docker:1.6
    container_name: ms01
    ports:
   	  - "6001:6001"
	volumes:
      - /app/microService:/data
    networks:
      - atguigu_net
    depends_on: # 依赖redis + mysql
      - redis
      - mysql
  redis:
    image: redis:6.0.8
    ports:
      - "6379:6379"
    volumes:
      - /app/redis/redis.conf:/etc/redis/redis.conf
      - /app/redis/data:/data
    networks:
      - atguigu_net
    command: redis-server /etc/redis/redis.conf
  mysql:
    image: mysql:5.7
    environment:
      MYSQL_ROOT_PASSWORD: '123456'
      MYSQL_ALLOW_EMPTY_PASSWORD: 'no'
      MYSQL_DATABASE: 'db2021'
      MYSQL_USER: 'zzyy'
      MYSQL_PASSWORD: 'zzyy123'
    ports:
      - "3306:3306"
    volumes:
      - /app/mysql/db:/var/lib/mysql
      -app/mysql/conf/my.cnf:/etc/my.cnf
      - /app/mysql/init:/docker-entrypoint-initdb.d
    networks:
      - atguigu_net
    command: --default-authentication-plugin=mysql_native_password
#解决外部无法访问，docker network create atguigu_net
networks: 
   atguigu_net:
```



-   第二次修改微服务工程docker_boot

    -   写YML

```properties
server.port=6001
#========================alibaba.druid相关配置=====================
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource\
spring.datasource.driver-class-name**=**com.mysql.jdbc.Driver
#spring.datasource.url=jdbc:mysql://192.168.111.169:3306/db2021?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.url=jdbc:mysql://mysql:3306/db2021?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.druid.test-while-idle=false
# ========================redis相关配置=====================
spring.redis.database=0
# spring.redis.host=192.168.111.169
spring.redis.host=redis
spring.redis.port=6379
spring.redis.password=
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
# ========================mybatis相关配置===================
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.atguigu.docker.entities
# ========================swagger=====================
spring.swagger2.enabled=true
```



-   通过服务名访问，IP无关

```html
<!-- -->
```
-   mvn package命令将微服务形成新的jar包
    并上传到Linux服务器/mydocker目录下

-   编写Dockerfile

```dockerfile
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER zzyy
# VOLUME
# 指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为zzyy_docker.jar
ADD docker_boot-0.0.1-SNAPSHOT.jar zzyy_docker.jar
# 运行jar包
RUN bash -c 'touch zzyy_docker.jar'
ENTRYPOINT ["java","-jar","zzyy_docker.jar"]
#暴露6001端口作为微服务
EXPOSE 6001
```



-   构建镜像

    -   docker build -t zzyy_docker:1.6 .

```html
<!-- -->
```
-   执行 docker-compose up 或者 执行 docker-compose up -d

> ![](image/image288.jpeg){width="4.4527777777777775in"
> height="0.5452384076990376in"}
>
>  
>
>  
>
> ![](image/image289.jpeg){width="4.4527777777777775in"
> height="0.7877996500437445in"}

-   进入mysql容器实例并新建库db2021+新建表t_user



-   测试通过

-   Compose常用命令

> **Compose常用命令**
>
> docker-compose -h                           \# 查看帮助
>
> docker-compose up                           \#
> 启动所有docker-compose服务
>
> docker-compose up -d                        \#
> 启动所有docker-compose服务并后台运行
>
> docker-compose down                         \#
> 停止并删除容器、网络、卷、镜像。
>
> docker-compose exec  yml里面的服务id                 #
> 进入容器实例内部  docker-compose exec
> docker-compose.yml文件中写的服务id /bin/bash
>
> docker-compose ps                      #
> 展示当前docker-compose编排过的运行的所有容器
>
> docker-compose top                     #
> 展示当前docker-compose编排过的容器进程
>
>  
>
> docker-compose logs  yml里面的服务id     \# 查看容器输出日志
>
> dokcer-compose config     \# 检查配置
>
> dokcer-compose config -q  \# 检查配置，有问题才有输出
>
> docker-compose restart   \# 重启服务
>
> docker-compose start     \# 启动服务
>
> docker-compose stop      \# 停止服务
>
>  

-   关停

>  
>
> ![](image/image290.jpeg){width="4.4527777777777775in"
> height="2.188946850393701in"}

### 2.6、Docker轻量级可视化工具Portainer

> ![](image/image291.png){width="0.36666338582677166in"
> height="0.36666338582677166in"}

1.  是什么

>  Portainer
>是一款轻量级的应用，它提供了图形化界面，用于方便地管理Docker环境，包括单机环境和集群环境。

2.  安装

    -   官网

        -   https://www.portainer.io/

        -   https://docs.portainer.io/v/ce-2.9/start/install/server/docker/linux

    -   步骤

        -   docker命令安装

>  

-----------------------------------------------------------------------


```shell
docker run -d -p 8000:8000 -p 9000:9000 --name portainer \
--restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
-v portainer_data:/data \
portainer/portainer-ce:latest
```



-----------------------------------------------------------------------

>  
>
> ![](image/image292.jpeg){width="4.4527777777777775in"
> height="2.211709317585302in"}

-   第一次登录需创建admin，访问地址：xxx.xxx.xxx.xxx:9000

-----------------------------------------------------------------------
  用户名，直接用默认admin

  密码记得8位，随便你写

>  
>
>  ![](image/image293.jpeg){width="4.4527777777777775in"
>height="2.8479440069991253in"}

-   设置admin用户和密码后首次登陆

> ![](image/image294.jpeg){width="4.4527777777777775in"
> height="1.9731594488188977in"}

-   选择local选项卡后本地docker详细信息展示

> ![](image/image295.jpeg){width="4.4527777777777775in"
> height="2.4539938757655295in"}

-   上一步的图形展示，能想得起对应命令吗？

>  
>
> ![](image/image296.jpeg){width="4.4527777777777775in"
> height="1.307668416447944in"}

3.  登陆并演示介绍常用操作case

```html
<!-- -->
```
### 2.7、Docker容器监控之CAdvisor+InfluxDB+Granfana

> ![](image/image297.png){width="0.37332895888014in"
> height="0.37332895888014in"}

1.  原生命令

    -   操作

> ![](image/image298.jpeg){width="4.6194444444444445in"
> height="1.2575984251968504in"}
>
> **docker stats命令的结果**
>
> ![](image/image299.jpeg){width="4.6194444444444445in"
> height="0.6336734470691163in"}
>
>  

-   问题

>  
>
>  通过docker
>stats命令可以很方便的看到当前宿主机上所有容器的CPU,内存以及网络流量等数据，一般小公司够用了。。。。
> 
> 但是，
>
>  docker
>stats统计结果只能是当前宿主机的全部容器，数据资料是实时的，没有地方存储、没有健康指标过线预警等功能
> 
>

2.  是什么

    -   容器监控3剑客

        -   一句话

>  
>
> ![](image/image300.jpeg){width="4.4527777777777775in"
> height="2.92046697287839in"}

-   CAdvisor监控收集+InfluxDB存储数据+Granfana展示图表

```html
<!-- -->
```
-   CAdvisor

>  
>
> ![](image/image301.jpeg){width="4.4527777777777775in"
> height="2.0338506124234472in"}

-   InfluxDB

> ![](image/image302.jpeg)

-   Granfana

>  
>
> ![](image/image303.jpeg)

-   总结

>  
>
> ![](image/image304.jpeg){width="4.4527777777777775in"
> height="2.3065496500437446in"}

3.  compose容器编排，一套带走

    -   新建目录

>  
>
>  
>
> ![](image/image305.jpeg){width="3.65625in"
> height="1.3125in"}

-   新建3件套组合的 docker-compose.yml

```yaml
version: "3.1"
volumes:
  grafana_data: {}
 
services: 
  # 数据库
  influxdb: 
    image: tutum/influxdb:0.9 
    container_name: ms01 
    restart: always
    environment: 
      - PRE_CREATE_DB=cadvisor
    ports: 
      - "8083:8083" 
      - "8086:8086" 
    volumes: 
      - ./data/influxdb:/data
  # 收集数据
  cadvisor:
    image: google/cadvisor
    links:
      - influxdb:influxsrv
    command: -storage_driver=influxdb -storage_driver_db=cadvisor -storage_driver_host=influxsrv:8086
    restart: always
    ports:
      - "8080:8080"
    volumes: 
      - /:/rootfs:ro 
      - /var/run:/var/run:rw
      - /sys:/sys:ro 
      - /var/lib/docker/:/var/lib/docker:ro
  # 可视化
  grafana:
    user: "104"
    image: grafana/grafana
    user: "104"
    restart: always
    links:
      - influxdb:influxsrv
    ports:
      - "3000:3000"
    volumes:
      - grafana_data:/var/lib/grafana
    environment:
      - HTTP_USER=admin 
      - HTTP_PASS=admin 
      - INFLUXDB_HOST=influxsrv
      - INFLUXDB_PORT=8086 
      - INFLUXDB_NAME=cadvisor 
      - INFLUXDB_USER=root 
      - INFLUXDB_PASS=root
```



-   启动docker-compose文件

    -   docker-compose up

> ![](image/image306.jpeg){width="4.4527777777777775in"
> height="2.01248031496063in"}
>
> ![](image/image307.jpeg){width="4.4527777777777775in"
> height="1.8949168853893263in"}

-   查看三个服务容器是否启动

> ![](image/image308.jpeg){width="4.6194444444444445in"
> height="1.2789818460192477in"}

-   测试

    -   浏览cAdvisor收集服务，http://ip:8080/

> ![](image/image309.jpeg){width="4.4527777777777775in"
> height="2.9188484251968503in"}

-   第一次访问慢，请稍等

-   cadvisor也有基础的图形展现功能，这里主要用它来作数据采集

```{=html}
<!-- -->
```
-   浏览influxdb存储服务，http://ip:8083/

-   浏览grafana展现服务，http://ip:3000

    -   ip+3000端口的方式访问,默认帐户密码（admin/admin）

> ![](image/image310.jpeg){width="4.4527777777777775in"
> height="2.034430227471566in"}

-   配置步骤

    -   配置数据源

> ![](image/image311.jpeg){width="4.4527777777777775in"
> height="2.563040244969379in"}

-   选择influxdb数据源

>  
>
> ![](image/image312.jpeg){width="4.4527777777777775in"
> height="1.4964249781277341in"}

-   配置细节

    -   1

> ![](image/image313.jpeg){width="4.4527777777777775in"
> height="3.755994094488189in"}

-   2

>  
>
> ![](image/image314.jpeg){width="4.4527777777777775in"
> height="2.028487532808399in"}
>
> ![](image/image315.jpeg){width="4.4527777777777775in"
> height="3.6024989063867014in"}

-   配置面板panel

    -   1

> ![](image/image316.jpeg){width="4.4527777777777775in"
> height="1.7339162292213472in"}

-   2

> ![](image/image317.jpeg){width="4.4527777777777775in"
> height="2.6088713910761157in"}

-   3

> ![](image/image318.jpeg){width="4.4527777777777775in"
> height="1.4975415573053368in"}

-   4

> ![](image/image319.jpeg){width="4.4527777777777775in"
> height="1.517992125984252in"}

-   5

> ![](image/image320.jpeg){width="4.4527777777777775in"
> height="3.13865157480315in"}

-   6

>  
>
> ![](image/image321.jpeg){width="4.4527777777777775in"
> height="1.8157436570428696in"}

-   到这里cAdvisor+InfluxDB+Grafana容器监控系统就部署完成了

### 2.8、终章の总结

> ![](image/image322.png){width="0.3681517935258093in"
> height="0.3681517935258093in"}

1.  知识回顾简单串讲和总结

> ![](image/image323.jpeg){width="4.591666666666667in"
> height="2.0107874015748033in"}

2.  进阶篇：雷丰阳老师的K8S

> ![](image/image324.jpeg){width="4.591666666666667in"
> height="2.3455457130358703in"}加油
