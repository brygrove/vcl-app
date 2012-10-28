@echo off

rem set JETTY_DIR=C:\Users\Bryant\GitHub\vcl-app\Jetty\jetty-distribution-7.4.2

rem cd %JETTY_DIR%

java -Xmx512m -XX:MaxPermSize=256m -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.Log4JLogger -jar start.jar 
