:: 全部重新打包
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
:: 重新打包war
cd /d %~dp0/ec-cf-service
call mvn clean install

cd /d %~dp0/eshop
call mvn clean install

cd /d %~dp0/ec-weixin
call mvn clean install

cd /d %~dp0/ec-member
call mvn clean install

cd /d %~dp0/ec-cm
call mvn clean install

cd /d %~dp0/ec-cf
call mvn clean install

cd /d %~dp0/eoms-web
call mvn clean install

cd /d %~dp0/eis
call mvn clean install
 
:: 将war包复制到部署包目录
cd /d %~dp0/ec-dist 
:: del %~dp0\ec-dist\target\*.war
rd /S/Q target
md target\
copy  %~dp0\eoms-web\target\eoms-web.war %~dp0\ec-dist\target\
copy  %~dp0\eshop\target\eshop.war %~dp0\ec-dist\target\
copy  %~dp0\eis\target\eis.war %~dp0\ec-dist\target\
copy  %~dp0\ec-weixin\target\ec-weixin.war %~dp0\ec-dist\target\
copy  %~dp0\ec-member\target\ec-member.war %~dp0\ec-dist\target\
copy  %~dp0\ec-cm\target\ec-cm.war %~dp0\ec-dist\target\
copy  %~dp0\ec-cf\target\ec-cf.war %~dp0\ec-dist\target\
pause 
