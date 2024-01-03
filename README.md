# Picle-Server
숙명여자대학교 소프트웨어학부 졸업 프로젝트 🥒 피클 🥒 서버 레포

</br>


## 😊 역할분담 & 팀원 소개

|담당자|담당 내용|
|:---|:---|
|김윤지|Git & 프로젝트 세팅, 전체 API 작성, AWS 설정 및 배포|


</br>



# 📁 Folder 구조

```jsx
📁 src. 📁 main. 📁 java
|                   |_ 📁 sopt. 📁 org. 📁 Picle
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


## 😉 API 최종명세서

[최종명세서]()

</br>

## 💽 Database ERD
<img width="673" alt="Picle ERD" src="https://github.com/Team-Picle/Picle-Server/assets/101168694/4d4154cd-9764-4351-981c-f47da1ea547b">


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

