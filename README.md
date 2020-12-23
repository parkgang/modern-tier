# modern-tier

친구들의 LOL tier 한눈에 보아요

> http(port:80)으로 접속하기 위해서는 로드 밸런서를 활성화 시켜야합니다. 아래의 헤더를 참고해주세요.

> **자동화 배포라인이 구축되어있어 `master branch`에 함부로 push할 수 없습니다. <br/>
> 반드시 PR 승인 후 배포가 가능합니다. (필수 승인자: 박경은(`ruddms936`))**

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

# 파일 명 컨벤션

| 파일 | 타입 |
| -- | -- |
| 모듈 | 파스칼 |
| 속성 | 카멜 |
| 디렉토리 | 카멜 |

> 묶음으로 빠지는 모듈 혹은 하나만 존재 파일은 `index`으로 묶어서 관리합니다. (그래야 import가 편리합니다)

> 모듈 빼는 건 파일 명 혹은 묶음의 디렉터리명과 동일하게 맞추도록 합니다.

# ESLint 초기화

`node_modules/.bin/eslint --init`

## 현재 프로젝트에 적용된 setting

<img width="1420" alt="스크린샷 2020-12-07 오전 3 50 30" src="https://user-images.githubusercontent.com/63892989/103026894-32873780-4598-11eb-89e5-41782241ec86.png">

# 이슈

Kakao OAuth Redirect URI이 도메인이 아닌 public IP으로 넘어가서 서비스 접속시 public IP으로 들어가야합니다

# local 환경 구축

## 의존성 변경

1. `module-react+jsp/src/main/java/com/constant/Service.java`의 `USE_DOMAIN = LOCAL_DOMAIN;`으로 수정
1. `module-react+jsp/src/main/webapp/react/src/constants/index.js`의 `export const USE_DOMAIN = LOCAL_DOMAIN;`으로 수정

## 빌드 프로세스

1. `react`에서 `yarn build`
1. tomcat 실행
