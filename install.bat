:: 重新打包jar
cd /d %~dp0/eshop-service
call mvn clean install

cd /d %~dp0/eoms-core
call mvn clean install

cd /d %~dp0/ec-weixin-service
call mvn clean install

cd /d %~dp0/ec-member-service
call mvn clean install

cd /d %~dp0/ec-cm-service
call mvn clean install

cd /d %~dp0/ec-cf-service
call mvn clean install
pause
