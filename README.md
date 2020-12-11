# modern-tier

친구들의 LOL tier 한눈에 보아요

> http(port:80)으로 접속하기 위해서는 로드 밸런서를 활성화 시켜야합니다. 아래의 헤더를 참고해주세요.

# load balancer start

`nginx.conf`가 변경되어 로드 벨런서를 다시 시작하기 위해서는 host PC의 `./nginx` 경로 에서 아래의 명령어를 실행하세요.

```shell
docker rm -f nginx-load-balancer || true
docker-compose -p load-balancer up -d --build
```

# db 접속 계정

docker in docker의 한계로 복호화가 가능한 tomcat 이미지를 사용해야 합니다.
복호화 파일은 server에 존재합니다.

## 복호화 tomcat image build command

```
docker build -t tomcat-modern-tier:1 .

docker run -p 8888:8080 tomcat-modern-tier:1
```

# commit 컨벤션

| Type     | 내용                                                          |
| -------- | ------------------------------------------------------------- |
| init     | helloworld 수준의 모듈이 동작될 때                            |
| add      | 코드 추가                                                     |
| del      | 코드 삭제                                                     |
| edit     | 코드 수정                                                     |
| refactor | 코드 및 프로젝트의 기능은 전혀 변함이 없으며 이름만 변경될 때 |
| build    | cicd 빌드 트리거시 시용됨 사용시 이유를 작성하기              |
