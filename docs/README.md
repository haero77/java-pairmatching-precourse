- 미션을 시작하기 전 페어를 매칭하는데 같은 레벨 동안은 같은 페어를 만나지 않는다.

- [ ] 프로그램을 실행한다.
  - [ ] 과정에 해당하는 마크다운 파일을 읽어와서, 저장소에 크루를 저장한다.
- [x] 기능 선택 문구를 출력한다.
- [x] 기능 선택 문구를 입력한다.
- [ ] 선택한 요청에 맞게 로직을 실행한다.

## 과정, 레벨, 미션 선택 기능

- [ ] 과정, 레벨, 미션 정보를 출력한다.
- [ ] 과정, 레벨, 미션을 입력받는다.
  - [ ] 구분자는 ", " 로 입력받는다.
  - [ ] 예외) 구분자로 구분한 길이가 3이 아닌 경우
  - [ ] 예외) 미션이 없는 레벨을 입력한 경우

## 페어 매칭 기능

- [ ] 과정, 레벨, 미션을 선택한다.
- [ ] 페어 매칭 로직을 실행한다.
- [ ] 매칭 완료 후 매칭 결과를 출력한다.

### 페어 매칭 로직

- [ ] 크루들의 이름 목록을 List<String> 형태로 준비한다.
- [ ] 크루 목록의 순서를 랜덤으로 섞는다. 이 때 `camp.nextstep.edu.missionutils.Randoms`의 shuffle 메서드를 활용해야 한다.

```text
List<String> crewNames; // 파일에서 로드한 크루 이름 목록
List<String> shuffledCrew = Randoms.shuffle(crewNames); // 섞인 크루 이름 목록
```

- [ ] 랜덤으로 섞인 페어 목록에서 페어 매칭을 할 때 앞에서부터 순서대로 두명씩 페어를 맺는다.
- [ ] 홀수인 경우 마지막 남은 크루는 마지막 페어에 포함시킨다.
  - 페어 매칭 대상이 홀수인 경우 한 페어는 3인으로 구성한다.
- [ ] 같은 레벨에서 이미 페어를 맺은 크루와는 다시 페어로 매칭될 수 없다.
- [ ] 매칭이 완료되었을 경우 저장소에 저장한다.

### 재매칭 로직

- [ ] 재매칭 시도시 안내 문구를 출력한다.

```text
매칭 정보가 있습니다. 다시 매칭하시겠습니까?
네 | 아니오
아니오
```
- [ ] 아니오를 선택할 경우 코스, 레벨, 미션을 다시 선택한다.
- [ ] 같은 레벨에서 이미 페어로 만난적이 있는 크루끼리 다시 페어로 매칭 된다면, 크루 목록의 순서를 다시 랜덤으로 섞어서 매칭을 시도한다.
- [ ] 3회 시도까지 매칭이 되지 않거나 매칭을 할 수 있는 경우의 수가 없으면 에러 메시지를 출력한다.


## 페어 조회 기능

- [ ] 과정, 레벨, 미션을 선택한다.
  - [ ] 정보 출력
  - [ ] 정보 입력
- [ ] 선택한 정보에 맞는 페어 매칭 결과를 출력한다.

## 페어 초기화 기능

- [ ] 페어 매칭 기록을 초기화한다.