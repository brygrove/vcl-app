REM mvn deploy:deploy-file -Dfile=jms.jar -DgroupId=jms -DartifactId=jms -Dversion=1.1 -Dpackaging=jar -DrepositoryId=build -Durl=http://192.168.0.104:8081/nexus/content/repositories/thirdparty/


mvn deploy:deploy-file -Dfile=javax.jms.jar -DgroupId=javax.jms -DartifactId=jms -Dversion=1.1 -Dpackaging=jar -DrepositoryId=build -Durl=http://192.168.0.104:8081/nexus/content/repositories/thirdparty/
