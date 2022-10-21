### Java环境配置
#### 1. java

1. 依赖版本1.8.0_351
下载地址：https://www.oracle.com/java/technologies/downloads/#java8-mac

2.环境变量配置：
```shell
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_351.jdk/Contents/Home
PATH=$JAVA_HOME/bin:/Users/jack/software/apache-maven-3.8.6/bin:$PATH
CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:.
export JAVA_HOME
export CLASSPATH
export PATH
```

### 2 mvn安装
下载地址：https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
加入path路径
PATH=$JAVA_HOME/bin:/Users/jack/software/apache-maven-3.8.6/bin:$PATH

### 3 本地运行
```shell
mvn spring-boot:run -P dev
mvn spring-boot:run -Dspring-boot.run.profiles=dev
# 测试curl
curl http://127.0.0.1:61999/home
```

### 4 打包(jar)
```shell

## 开发环境打包
mvn clean package -P dev -DskipTests
## 测试环境打包
mvn clean package -P test -DskipTests 
## 生产环境打包
mvn clean package -P prod -DskipTests
```

### 5 线上运行方式
```shell
##测试环境
java -jar -Dspring.profiles.active=prod cocafe-hyperchain-test-1.0.0-SNAPSHOT.jar

## 线上运行方式
java -jar -Dspring.profiles.active=prod cocafe-hyperchain-prod-1.0.0-SNAPSHOT.jar
```

### 6 FAQs
#### 6.1 本地运行mvn spring-boot:run 出错
原因：pom.xml没有拷贝配置文件到编译的目录里面
方法：cp src/main/resources/application.yml target/classes
