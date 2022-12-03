## 프로젝트 실행화면
<image src ="https://user-images.githubusercontent.com/115937254/204548405-77b07ae8-7cd9-4aef-8a3b-a0649371854b.gif" width= "200"/>

## 주요 로직
<img width="813" alt="image" src="https://user-images.githubusercontent.com/115937254/205419593-31ddd8ac-73bb-488f-877b-9c962ca4dc0c.png">

## 모듈 구조
<p>
<img width="300" alt="image" src="https://user-images.githubusercontent.com/115937254/205419207-127c31d8-10fe-4d30-a900-7131106a9846.png">
<img width="500" alt="image" src="https://user-images.githubusercontent.com/115937254/205418869-05098baf-acc7-47db-a59a-2ac46b126ab2.png">
</p>
<img width="1064" alt="image" src="https://user-images.githubusercontent.com/115937254/205418940-d868ffd5-1653-4abf-b2f9-65bd0e0b1f8e.png">

## 트러블슈팅
- [#1 시세 업데이트 로직](https://github.com/andgo-practice/dunamu_portfolio/issues/1#issuecomment-1322840195)
  - `WebSocket`으로 list가 아닌 코인 하나의 데이터가 들어오는데, 업데이트 형태를 어떻게 가져가야할지 구조 고민이 되었습니다.
- [#2 정렬 반영 로직](https://github.com/andgo-practice/dunamu_portfolio/issues/2)
  - 정렬과 시세를 같이 업데이트 하기 위한 구조를 어떻게 가져가야할 지 고민이 되었습니다.
- [#5 compose 리컴포지션 이슈](https://github.com/andgo-practice/dunamu_portfolio/issues/5#issuecomment-1323051232)
  - 데이터 하나만 업데이트 되더라도 모든 리스트가 매번 recomposition이 발생하는 이슈가 있었습니다.
- [#6 Scarlet Event가 오지 않는 이슈](https://github.com/andgo-practice/dunamu_portfolio/issues/6)
  - [Scarlet](https://github.com/Tinder/Scarlet) 사용 시 `WebSocketEvent` 가 넘어오지 않는 이슈가 있었습니다.
    - 디버깅시 라이브러리 내부 rx 쪽으로는 값이 잘 넘어오는 것으로 확인하였으나, <br> 
    선언한 `@get:Receive`를 붙인 event로는 값이 넘어오지 않았습니다.
  - 라이브러리 사용법에 의존하기 보다 `WebSocket`을 직접 제어하는 쪽으로 구현 방향을 선택하였습니다.
  - 관련해서 lifecycle에 대한 socket제어도 고민해보았습니다.
    - https://github.com/andgo-practice/dunamu_portfolio/issues/6#issuecomment-1328547429  
- [#13 의존성 정리](https://github.com/andgo-practice/dunamu_portfolio/issues/13)
  - hilt를 사용하는 부분에서 의존성이 잘못 걸린 부분등을 개선하였습니다.

## 기타
- [프로젝트 진행방식](https://github.com/andgo-practice/dunamu_portfolio/issues/15)
