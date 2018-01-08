FROM       centos:7.1.1503

MAINTAINER cs <cs876321722@outlook.com>

ENV TZ "Asia/Shanghai"

ENV TERM xterm 


RUN yum install -y epel-release 

RUN yum install -y python-pip 

RUN yum install -y java-1.8.0-openjdk.x86_64

RUN    pip install -U pip && pip install   supervisor==3.0

#初始化配置
RUN  echo_supervisord_conf > /etc/supervisord.conf  

#每个进程的单独配置
RUN  mkdir -p /etc/supervisor.conf.d  /var/log/supervisor

RUN  set -x  \
     #去掉注释
     sed -i '$!N;$!P;$!D;s/;//' /etc/supervisord.conf  && \
     #挂载 /etc/supervisor.conf.d/*.conf
     sed -i '$s/^;//;$s/relative\/directory\//\/etc\/supervisor.conf.d\//;$s/ini/conf/' /etc/supervisord.conf 

VOLUME ["/app"]

EXPOSE 8080


ENTRYPOINT ["/usr/bin/supervisord", "-n", "-c", "/etc/supervisord.conf"]
