# Picle-Server
숙명여자대학교 소프트웨어학부 졸업 프로젝트 🥒 피클 🥒 서버 레포 (2023.09 ~ 2024.05🛠)

</br>


## 😊 역할분담 & 팀원 소개

|담당자|담당 내용|
|:---|:---|
|김윤지|Git & 프로젝트 세팅, ERD 설계, 이미지 유사도 측정 구현, 전체 API 구현, 배포|
</br>

## 🔑 Key Feature (개발 예정 포함)
<img width="800" alt="image" src="https://github.com/Team-Picle/Picle-Server/assets/101168694/d7144d6c-1fd8-4ca2-b963-1c98a4116a9e">
<img width="800" alt="image" src="https://github.com/Team-Picle/Picle-Server/assets/101168694/ed1af591-a16d-4024-9895-33898b7a4865">
</br></br>

![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/ed13690c-c7fd-44e6-9cae-d6debd506548) | ![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/4b51ba30-fd62-4698-b03d-5eb594a9be7b) |
:---------:|:----------:|
개발 배경 | 차별점 |

![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/d286b84d-57b3-46aa-83b4-72a870778e9b) | ![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/6284cece-ea3c-43f8-aed0-20abf82c3f5c) |
:---------:|:----------:|
핵심 기능(일정 등록) | 핵심 기능(루틴 인증) |

![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/8f4defe0-0def-4807-9d5c-5983eec632be) | ![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/86bffa81-0485-4d9c-a6f2-d95c147af550) |
:---------:|:----------:|
핵심 기능(루틴 인증) | 핵심 기능(루틴 인증)|

![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/f28fa045-bd4c-4383-8b34-62ce19415d06) | ![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/52e51cd2-0d07-4785-a2b3-1089eecc6b37) |
:---------:|:----------:|
핵심 기능(루틴 인증) | 핵심 기능(루틴 인증)|

![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/487e3510-09dd-49e3-925a-01a18d82affc) | ![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/0bfc8eb5-2168-43c4-9ffb-bbd7c1ef4972) |
:---------:|:----------:|
핵심 기능(루틴 인증) | 핵심 기능(소셜) |

![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/a0804df4-9708-4621-b6a9-d02408a98618) | <img src="https://github.com/Team-Picle/Picle-Server/assets/101168694/935563b0-622b-4316-96b2-3f14a07a8444"> |
:---------:|:----------:|
서비스 발전 가능성 | 서비스 발전 가능성 |

</br>

</br>

## 🛠 기술 스택
<img src="https://img.shields.io/badge/Java-1A6C80?style=flat-square&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=Hibernate&logoColor=white"> <img src="https://img.shields.io/badge/MYSQL-4479A1?style=flat-square&logo=MYSQL&logoColor=white"> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=Amazon EC2&logoColor=white"> <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=Amazon RDS&logoColor=white">

![image](https://github.com/Team-Picle/Picle-Server/assets/101168694/cc7d2506-c9a4-4c23-b5e0-fa7a2356a4b6)



</br>

</div>

# 📁 Folder 구조

```jsx
📁 src. 📁 main. 📁 java
|                   |_ 📁 gaedianz. 📁 org. 📁 Picle
|                   |                          |_ 📁 common
|                   |                          |_ 📁 controller
|                   |                          |_ 📁 domain
|                   |                          |_ 📁 exception
|                   |                          |_ 📁 external
|                   |                          |_ 📁 global
|                   |                          |_ 📁 infrastructure
|                   |                          |_ 📁 service
|                   |                          |_ PicleApplication.java
|                   |                  
|                   |
|                   |_ 📁 resources
|
|
📁 test
```


</br>


## 😉 API 명세서

[최종명세서](https://hollow-humidity-50e.notion.site/API-Docs-0d0c3eb6687245a5bff093bd412671ab?pvs=4)

</br>

## 💽 Database ERD
<img width="602" alt="스크린샷 2024-02-02 오후 5 34 54" src="https://github.com/Team-Picle/Picle-Server/assets/101168694/7ebce724-8485-4a62-a5a5-cbf54ea9a623">


</br>
</br>


## 🧵 Git Convention

### 🔹 Commit Message Convention
`ex) git commit -m "[FEAT] 카카오 로그인 구현`

```plain
- ✨ [FEAT]      새로운 기능 구현
- 🐛 [FIX]       버그, 오류 해결
- 🧹 [CHORE]     src 또는 test 파일을 수정하지 않는 기타 변경 사항 ( 새로운 파일 생성, 파일 이동, 이름 변경, 빌드/패키지 매니저 설정 변경 등 )
- 🔥 [REMOVE]    폴더 또는 파일 삭제
- ♻️ [REFACTOR]   기능 추가나 버그 수정이 없는 코드 변경 ( 코드 구조 변경 등의 리팩토링 )
- 🎨 [STYLE]     코드의 의미에 영향을 미치지 않는 변경 사항 ( 코드 형식, 변수명 변경, 오타 수정, 세미콜론 추가: 비즈니스 로직에 변경 없음 )
- 🧪 [TEST]      테스트 추가 또는 이전 테스트 수정
- 📝 [DOCS]      README나 WIKI 등의 문서 수정
- 📦 [CI]        CI 구성 파일 및 스크립트 변경
- 🎉 [INIT]      Initial commit을 하는 경우
- 🤝🏻 [MERGE]     Merge 하는 경우
```

</br>

### 🔹 Branch Strategy
### Git Flow

기본적으로 Git Flow 전략을 이용한다. Fork한 후 나의 repository에서 작업하고 구현 후 원본 repository에 pr을 날린다. 작업 시작 시 선행되어야 할 작업은 다음과 같다.

```java
1. Issue를 생성한다.
2. feature Branch를 생성한다.
3. Add - Commit - Push - Pull Request 의 과정을 거친다.
4. Pull Request가 작성되면 작성자 이외의 다른 팀원이 Code Review를 한다.
5. Code Review가 완료되면 Pull Request 작성자가 develop Branch로 merge 한다.
6. merge된 작업이 있을 경우, 다른 브랜치에서 작업을 진행 중이던 개발자는 본인의 브랜치로 merge된 작업을 Pull 받아온다.
7. 종료된 Issue와 Pull Request의 Label과 Project를 관리한다.
```

- 기본적으로 git flow 전략을 사용합니다.
- main, develop, feature 3가지 branch 를 기본으로 합니다.
- main → develop → feature. feature 브랜치는 feat/기능명으로 사용합니다.
- 이슈를 사용하는 경우 브랜치명을 feature/[issue num]-[feature name]로 합니다.

<br>

