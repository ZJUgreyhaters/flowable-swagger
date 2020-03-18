+ env:

    Tomcat 9.0.31
    Flowable-engine 6.5.0
    
    flowable-6.5.0/wars目录下提供了engine的模块，可以直接放到apache-tomcat-9.0.31/webapps下

    运行APACHE_HOME/bin/startup.sh

    访问模块 ex: http://localhost:8080/flowable-modeler

+ 默认使用H2内存数据库，可以修改/WEB-INFO/classes/*.properties
```
# H2 example (default)

##spring.datasource.driver-class-name=org.h2.Driver
#spring.datasource.url=jdbc:h2:tcp://localhost/flowableadmin
##spring.datasource.url=jdbc:h2:~/flowable-db/db;AUTO_SERVER=TRUE;AUTO_SERVER_PORT=9091;DB_CLOSE_DELAY=-1

spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/flowable?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=******
```

+ /resources/flowable.cfg.xml
    见项目配置