# MasterSystem
>>用于考研辅导系统的测试版
## 一、准备工作
>>该测试版本是使用的前后端分离技术，前端采用的是基于vue框架编写，后端采用的是基于springboot框架编写，为了增加系统的可移植性，使用docker容器技术打包。
需要准备的环境如下：
### 1.1 docker
>>需要在服务器安装docker，版本最好是较新的版本
### 1.2 docker-compose
>>在服务器上安装docker-compose
### 1.3 vpn
>>如果是国内服务器，需要安装vpn，且代理程序的port必须是7890。（因为在程序中端口写死了）
## 二、准备好相应的代码
### 从我的github上面拉取代码
>>需要链接仓库之后再下载，在服务器任何地方先创建文件夹，名字随意，然后在该文件夹下打开git的运行窗口，代码如下：
~~~
2.1 git init
~~~
>>该命令是在当前文件夹下初始化本地仓库
~~~
2.2 git remote add origin 远程仓库地址
~~~
>>链接远程仓库
~~~
2.3 git clone 远程仓库地址
~~~
>>从远程仓库拉取项目代码，master-spring是后端的代码，master-vue是前端的代码，masterSystemDocker是使用docker打包之后的前后端代码以及配置文件，下面主要讲的是使用docker布置项目
## 三、使用命令布置项目
### 3.1 布置前端容器
>> 在./masterSystem/vue的目录下运行如下命令:
~~~
docker build -t testvue .
~~~
>> 不要忘记最后的点，testvue和点之间有一个空格，testvue是前端容器的名字，不能随意更换容器名字，是写死的
### 3.2 布置后端容器
>> 在./masterSystemDocker/springboot的目录下运行如下命令:
~~~
docker-compose up -d
~~~
>> 该命令是启动后端容器并将前后端容器进行链接
### 3.3 运行vpn
>> 一定要让vpn运行的端口是7890，因为在后端程序中写死了
### 3.4 启动前端
~~~
使用localhost:8088进入前端页面
~~~
测试项目就可以正常运行
