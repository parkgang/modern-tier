# modern-tier

친구들의 LOL tier 한눈에 보아요

> http(port:80)으로 접속하기 위해서는 로드 밸런서를 활성화 시켜야합니다. 아래의 헤더를 참고해주세요.

# Load Balancer restart

`nginx.conf`가 변경되어 로드 벨런서를 다시 시작하기 위해서는 host PC의 `./nginx` 경로 에서 아래의 명령어를 실행하세요.

```shell
docker rm -f nginx-load-balancer || true
docker-compose -p load-balancer up -d --build
```
