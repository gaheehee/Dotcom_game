# Dotcom_game

+ DotCom을 상속 받아 ShortDotCom, LongDotCom, HeavyDotCom 클래스를 추가함 
+  각 클래스에 size() 메소드를 추가함
+ ShortDotCom클래스는 다음과 같이 확장함
+ size()는 2를 반환. 즉, ShortDotCom은 2개의 cell을 차지함
+ LongDotCom클래스는 다음과 같이 확장힘
+ size()는 4를 반환. 즉, LongDotCom은 4개의 cell을 차지함
+ HeavyDotCom클래스는 다음과 같이 확장함
+ size()는 3을 반환. 즉, HeavyDotCom은 3개의 cell을 차지함
+ HeavyDotCom은 장갑을 가지고 있어서 각 cell마다 2번씩 hit되어야 함
+ Submarine의 size() 메소드는 3을 반환한다. 즉, Submarine은 3개의 cell을
가지고 있다
+ DotCom 객체의 상태를 모니터링하는 Observer 클래스(DamageRate)를
구현함
+ “hit” 또는 “kill”이 된 DotCom 객체는 notifyObservers() 호출하여 자신의 observe들에게
상태 변화를 알려줌
