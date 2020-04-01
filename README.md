# springboot+mybatis-plus基本业务生成器

## 使用方法
 
 1. [下载生成器](https://github.com/oleolema/springboot.myabtis-plus.crud/releases)，并解压
 
 2. 修改`application.yml`配置文件中`boot-root-path`为你项目的springboot启动类的路径  
 例如在下面这个项目中，该路径为 `file:E:\CODE\Java\intelij IDEA workspace\springboot-high\mybaits-plus\src\main\java\com\yqh\mybaitsplus`  
 
 ![](https://raw.githubusercontent.com/oleolema/mybatis-plus-generator/img/QQ截图20190928160025.png)
 
 3. 原项目需要配置好数据源`resource/application.yml`,生成器会根据上面项目路径找到该配置文件并连接数据库生成业务代码
 
```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/testDb1
    driver-class-name: com.mysql.cj.jdbc.Driver
```
 4. 运行jar `java -jar auto-create-mybatis-files-0.0.1-SNAPSHOT.jar`  
 
 ![](https://raw.githubusercontent.com/oleolema/mybatis-plus-generator/img/20190928155507.png)
 
 5. 生成结果  
 
   生成结果包含了bean，mapper，service，controller  其中controller为restful风格的增删改查
 
 ![](https://raw.githubusercontent.com/oleolema/mybatis-plus-generator/img/QQ截图20190928161941.png)
 
 ## 注意1，需要生成的项目必须依赖springboot，mybatis-plus，lombok
 
 ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.2.0</version>
    </dependency>
```

