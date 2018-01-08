## 构建镜像

打包
```
cd app && mvn clean install  -D maven.test.skip=true
mv target/app-0.0.1-SNAPSHOT.jar .

```


jdk8环境作为base镜像 运行jar
```
docker build -t supervisor:3.0 .
```

## 运行容器

-d 后台运行
```
docker-compose up
```

ip:13838/swagger-ui.html