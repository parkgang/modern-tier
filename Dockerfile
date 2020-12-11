# 사용하는 이미지
FROM tomcat-modern-tier:1

# 해당 이미지 shell command 실행
RUN apt-get update

# 파일 추가(하위 디렉토리만 가능함)
ADD /module-react+jsp/build/libs/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# [tomcat 데몬이 실행되고 나면 죽어버리는 것을 방지하기 위함](https://skaqud.github.io/2016/08/21/Build-Docker-Container/)
CMD ["catalina.sh","run"]