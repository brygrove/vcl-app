@echo off

echo Running VCL SWT Client ...

set JAVA_32BIT_HOME=..\..\..\jre6


%JAVA_32BIT_HOME%\bin\java -cp vcl-swt-client.jar; com.vcl.application.VclManagerApplication > log.txt 2>&1
