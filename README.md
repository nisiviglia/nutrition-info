# Nutrition Info
A slim and fast web-app for getting nutrition information. No information overload. Get what you need without clutter.

## Install
database and index files are located at:
```
/var/lib/nutrition-info/
```
Make sure to create folder and chown to tomcat8/user group.

## Development

main config file:
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
yarn run start
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
  
run:
```
mvn package -P production-war -P build-frontend
```

### Tomcat8
  * Remember to have write access to the hibernate search index folder.
    * `/var/lib/nutrition-info
  * Backend stdout log file is located in `/var/log/tomcat8/catalina.out`.

## Deployment
Notes on setting up deployment from maven to tomcat8 found [here.](https://stackoverflow.com/a/39878427/5618691)   

*Make sure to change the homepage setting in the front-end's package.json file
to match the production servers url and path.*
```
"homepage": "192.168.1.5:8080/nutrient/"
```

### Staging Server
```
mvn tomcat7:deploy -P production-war -P deploy-staging
```

### Production Server
You may have to undeploy the old war before deploying again.  

```
mvn tomcat7:deploy -P production-war -P deploy-production
```

## Database Creation
config files: 
```
src/main/java/com/siviglia/web/nutritioninfo/util/hibernate.cfg.xml
src/main/java/com/siviglia/web/nutritioninfo/util/CreateDatabase.java 
```

running:
```
mvn compile exec:java@create-database -P create-database
```

## Disable Search Indexing On Start
set the following envirnment varible to false to disable.
```
NUTRITION_INFO_INDEXONSTART='FALSE'
```

## Data
Grab "All Foods" csv file.  
<https://fdc.nal.usda.gov/download-datasets.html>

