@echo off

set JETTY_DIR=C:\projects\java\spring-source\Jetty\jetty-distribution-7.4.2
set JETTY_WEBAPPS=%JETTY_DIR%\webapps
set WEBAPP=vcl-app.war

del %JETTY_WEBAPPS%\%WEBAPP%

copy vcl-war\target\%WEBAPP% %JETTY_WEBAPPS%\

start %JETTY_DIR%\start.bat

