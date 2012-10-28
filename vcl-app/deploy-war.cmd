@echo off

set JETTY_DIR=..\Jetty\jetty-distribution-7.4.2
set JETTY_WEBAPPS=%JETTY_DIR%\webapps
set WEBAPP=vcl-app.war

del %JETTY_WEBAPPS%\%WEBAPP%

copy vcl-war\target\%WEBAPP% %JETTY_WEBAPPS%\

rem // save current directory
pushd .

cd %JETTY_DIR%
set JETTY_RUN_DIR=%CD%


start %JETTY_RUN_DIR%\start.bat


rem restore current directory
popd
