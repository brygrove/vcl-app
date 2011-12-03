@echo off

echo Running VCL SWT Client ...

java -cp vcl-swt-client.jar; com.vcl.application.VclManagerApplication > log.txt 2>&1
