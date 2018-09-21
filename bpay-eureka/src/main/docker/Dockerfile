# 基于哪个镜像
FROM frolvlad/alpine-oraclejdk8:slim
# 安装软件用 
# 创作者
MAINTAINER zpp
# CMD
# 可以将本地文件夹或者其他container的文件夹挂载到container中
VOLUME /tmp
#将文件<src>拷贝到container的文件系统对应的路径<dest>
ADD bpay-eureka-0.0.1-SNAPSHOT.jar app.jar
# 安装软件
RUN sh -c 'touch /app.jar'
# 用来设置环境变量
ENV JAVA_OPTS=""
# container启动时执行的命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
# container内部服务开启的端口
EXPOSE 1111