# Nutrition Info
A slim and fast web-app for getting nutrition information. No information overload. Get what you need without clutter.

## Development

config files:
```
pom.xml
```

### Back-end

config files:
```
src/main/resources/application.properties 
```

run:
```
mvn spring-boot:run -Dserver.port=5000
```

### Front-end

config files:
```
src/main/app/package.json
```

run:
```
cd src/main/app/
yarn start
```

## Profiling
running jstatd:
```
jstatd -J-Djava.security.policy=jstatd.all.policy -J-Djava.rmi.server.hostname=<host address>
```

running java jar:
```
java -Dcom.sun.management.jmxremote=true \
-Dcom.sun.management.jmxremote.local.only=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.ssl=false \
-Djava.rmi.server.hostname=<host address> \
-Dcom.sun.management.jmxremote.port=9999 \
-Dcom.sun.management.jmxremote.rmi.port=9998 \
-jar target/<name of jar>.jar
```

## Package as war file
  
Change the following in pom.xml:

```
<packaging>war</packaging>
```
``` 
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <version>2.1.5.RELEASE</version>
    <scope>provided</scope>
</dependency>
```

running:
```
mvn package
```
### Tomcat8
  * Remember to have write access to the hibernate search index folder.
    * `/var/lib/tomcat8`
  * Backend stdout log file is located in `/var/log/tomcat8/catalina.out`.

## Database Creation
config files: 
```
src/main/java/com/siviglia/web/nutritioninfo/util/hibernate.cfg.xml
src/main/java/com/siviglia/web/nutritioninfo/util/CreateDatabase.java 
```

running:
```
mvn compile exec:java@create-database
```

## Data
Grab "All Foods" csv file.  
<https://fdc.nal.usda.gov/download-datasets.html>

