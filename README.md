+ # 더 자바 8
+ ### 함수형 인터페이스와 람다 표현식 1
    + 함수형 인터페이스 (Functional Interface)
        + 추상 메소드를 딱 하나만 가지고 있는 인터페이스
        + SAM (Single Abstract Method) 인터페이스
        + @FunctionalInterface 애노테이션을 가지고 있는 인터페이스
    + 람다 표현식 (Lambda Expressions)
      + 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있다.
      + 코드를 줄일 수 있다.
      + 메소드 매개변수, 리턴 타입, 변수로 만들어 사용할 수도 있다.
    + 자바에서 함수형 프로그래밍
      + 함수를 First class object로 사용할 수 있다.
      + 순수 함수 (Pure function)
        + 사이드 이펙트 만들 수 없다. (함수 밖에 있는 값을 변경하지 못한다.)
        + 상태가 없다. (함수 밖에 정의되어 있는)
      + 고차 함수 (Higher-Order Function)
        + 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
      + 불변성
      
+ ### 함수형 인터페이스와 람다 표현식 2
  + Java가 기본으로 제공하는 함수형 인터페이스
    + <U>**java.lang.function**</U> 패키지
    + 자바에서 미리 정의해둔 자주 사용할만한 함수 인터페이스
      + Function<T, R>
        + T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
          + > ex) R apply(T t)
        + 함수 조합용 메소드
          + > ex) andThen
          + > ex) compose
      + BiFunction<T, U, R>
        + 두개의 값 (T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
          + > ex) R apply(T t, U u)
      + Consumer<T>
        + T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
          + > ex) void Accept(T t)
        + 함수 조합용 메소드
          + > ex) andThen
      + Supplier<T>
        + T 타입의 값을 제공하는 함수 인터페이스
        + > ex) T get()
      + Predicate<T>
        + T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
          + > ex) boolean test(T t)
        + 함수 조합용 인터페이스
          + > ex) And
          + > ex) Or 
          + > ex) Negate
      + UnaryOperator<T>
        + Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
      + BinaryOperator<T>
        + BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입력값 두개를 받알 리턴하는 함수 인터페이스

+ ### 람다 표현식
  + 람다
    + (인자 리스트) -> {바디}
  + 인자 리스트
    + 인자가 없을 때: ()
    + 인자가 한개일 때: (one) 또는 one
    + 인자가 여러개 일 때: (one, two)
    + 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)
  + 바디
    + 화상표 오른쪽에 함수 본문을 정의한다.
    + 여러 줄인 경우에 { }를 사용해서 묶는다.
    + 한 줄인 경우에 생략 가능, return도 생략 가능.
  + 변수 캡처 (Variable Capture)
    + 로컬 변수 캡처
      + final이거나 effective final 인 경우에만 참조할 수 있다.
      + 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일가 방지한다.
    + effective final
      + 이것도 역시 자바 8부터 지원하는 기능으로 “사실상" final인 변수.
      + final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
    + 익명 클래스 구현체와 달리 ‘쉐도윙’하지 않는다. -> <U>**ShadowService.class 참고**</U>
    + 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.

+ ### 메소드 레퍼런스
  + 람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있다.
  + 메소드 참조하는 방법
    + > 스태틱 메소드 참조 타입::스태틱 메소드
    + > 특정 객체의 인스턴스 메소드 참조 객체 레퍼런스::인스턴스 메소드
    + > 임의 객체의 인스턴스 메소드 참조 타입::인스턴스 메소드
    + > 생성자 참조 타입::new
  + 메소드 또는 생성자의 매개변수로 람다의 입력값을 받는다.
  + 리턴값 또는 생성한 객체는 람다의 리턴값이다.

+ ### 인터페이스 기본 메소드와 스태틱 메소드
  + 기본 메소드 (Default Methods)
    + 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
    + 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
      + > DefaultAndStaticInterface.class 참고
    + 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
      + 컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다.
      + 반드시 문서화 할 것. (@implSpec 자바독 태그 사용)
      + > DefaultAndStaticInterface.class 의 printNameUpperCase() 메소드 참고 
    + Object가 제공하는 기능 (equals, hasCode)는 기본 메소드로 제공할 수 없다.
      + 구현체가 재정의해야 한다.
    + 본인이 수정할 수 있는 인터페이스에만 기본 메소드를 제공할 수 있다.
    + 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
      + <span style="color:red"><U>**구현한 클래스에서의 인터페이스 메소드가 겹치면 Compile Error가 발생한다.**</U></span>
        + <span style="color:yellow"><U>**해결 방법 : 구현클래스에선 @Override 필수!!**</U></span>
      + > Bar.class 의 printNameUpperCase() 메소드 참고
    + 인터페이스 구현체가 재정의 할 수도 있다.
  + 스태틱 메소드 (Static Methods)
    + 해당 타입 관련 헬터 또는 유틸리티 메소드를 제공할 때 인터페이스에 스태틱 메소드를 제공할 수 있다.

+ ### 자바 8 API의 기본 메소드와 스태틱 메소드
  + 자바 8에서 추가한 기본 메소드로 인한 API 변화
  
  + Iterable의 기본 메소드
    + forEach()
    + spliterator()
  + Collection의 기본 메소드
    + stream() / parallelStream()
    + removeIf(Predicate)
    + spliterator()
  + Comparator의 기본 메소드 및 스태틱 메소드
    + reversed()
    + thenComparing()
    + static reverseOrder() / naturalOrder()
    + static nullsFirst() / nullsLast()
    + static comparing()

+ ### Stream
  + Stream 소개
    + sequence of elements supporting sequential and parallel aggregate operations
    + 데이터를 담고 있는 저장소 (컬렉션)이 아니다.
    + Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
    + 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
    + 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.)
    + 중개 오퍼레이션은 근본적으로 lazy 하다.
    + 손쉽게 병렬 처리할 수 있다.
  + 스트림 파이프라인
    + 0 또는 다수의 중개 오퍼레이션 (intermediate operation)과 한개의 종료 오퍼레이션 (terminal operation)으로 구성한다.
    + 스트림의 데이터 소스는 오직 터미널 오퍼네이션을 실행할 때에만 처리한다.
  + 중개 오퍼레이션
    + Stream을 리턴한다.
    + Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다. 
      (대부분은 Stateless지만 distinct나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
    + filter, map, limit, skip, sorted, ...
  + 종료 오퍼레이션
    + Stream을 리턴하지 않는다.
    + collect, allMatch, count, forEach, min, max, ...

+ ### Stream API
  + 걸러내기
    + Filter(Predicate)
    + 예) 이름이 3글자 이상인 데이터만 새로운 스트림으로 
  + 변경하기
    + Map(Function) 또는 FlatMap(Function)
    + 예) 각각의 Post 인스턴스에서 String title만 새로운 스트림으로
    + 예) List<Stream<String>>을 String의 스트림으로
  + 생성하기
    + generate(Supplier) 또는 Iterate(T seed, UnaryOperator)
    + 예) 10부터 1씩 증가하는 무제한 숫자 스트림
    + 예) 랜덤 int 무제한 스트림
  + 제한하기
    + limit(long) 또는 skip(long)
    + 예) 최대 5개의 요소가 담긴 스트림을 리턴한다.
    + 예) 앞에서 3개를 뺀 나머지 스트림을 리턴한다.
  + 스트림에 있는 데이터가 특정 조건을 만족하는지 확인
    + anyMatch(), allMatch(), nonMatch()
    + 예) k로 시작하는 문자열이 있는지 확인한다. (true 또는 false를 리턴한다.)
    + 예) 스트림에 있는 모든 값이 10보다 작은지 확인한다.
  + 개수 세기
    + count()
    + 예) 10보다 큰 수의 개수를 센다.
  + 스트림을 데이터 하나로 뭉치기
    + reduce(identity, BiFunction), collect(), sum(), max()
    + 예) 모든 숫자 합 구하기
    + 예) 모든 데이터를 하나의 List 또는 Set에 옮겨 담기

### Optional
  + 자바 프로그래밍에서 NullPointerException을 종종 보게 되는 이유
    + null을 리턴하니까! && null 체크를 깜빡했으니까!
  + 메소드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법
    + 예외를 던진다. (비싸다, 스택트레이스를 찍어두니까.)
    + null을 리턴한다. (비용 문제가 없지만 그 코드를 사용하는 클리어인트 코드가 주의해야 한다.)
    + (자바 8부터) Optional을 리턴한다. (클라이언트에 코드에게 명시적으로 빈 값일 수도 있다는 걸 알려주고, 빈 값인 경우에 대한 처리를 강제한다.)
  + 오직 값 한 개가 들어있을 수도 없을 수도 있는 컨네이너.
  + 주의할 점
    + 리턴값으로만 쓰기를 권장한다. (메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자.)
      + 매개변수로 Optional을 이용하지 말자. 
        + 특정 로직에서 매개변수로 null을 사용할 수 있기 때문.
        + 그럼 Optional을 쓰는 의미가 없다.
      + Optional을 리턴하는 메소드에서 null을 리턴하지 말자.
      + Primitive 타입용 Optional을 따로 있다. OptionalInt, OptionalLong,...
      + Collection, Map, Stream Array, Optional은 Optional로 감싸지 말 것

### Optional API
  + Optional 만들기
    + Optional.of()
    + Optional.ofNullable()
    + Optional.empty()
  + Optional에 값이 있는지 없는지 확인하기
    + isPresent()
    + isEmpty() (Java 11부터 제공)
  + Optional에 있는 값 가져오기
    + get()
  + 만약에 비어있는 Optional에서 무언가를 꺼낸다면??
    + Optional에 값이 있는 경우에 그 값을 가지고 ~~를 하라.
    + ifPresent(Consumer)
  + 예) Spring으로 시작하는 수업이 있으면 id를 출력하라.
    + Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라.
    + orElse(T)
  + 예) JPA로 시작하는 수업이 없다면 비어있는 수업을 리턴하라.
    + Optional에 값이 있으면 가져오고 없는 경우에 ~~를 하라.
    + orElseGet(Supplier)
  + 예) JPA로 시작하는 수업이 없다면 새로 만들어서 리턴하라.
    + Optional에 값이 있으면 가졍고 없는 경우 에러를 던져라.
    + orElseThrow()
  + Optional에 들어있는 값 걸러내기
  + Optional filter(Predicate)
  + Optional에 들어있는 값 변환하기
  + Optional map(Function)
  + Optional flatMap(Function): Optional 안에 들어있는 인스턴스가 Optional인 경우에 사용하면 편리하다.