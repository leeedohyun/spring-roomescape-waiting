# spring-roomesacpe-waiting

## 1단계 요구사항

* [x] auth 패키지를 nextstep 패키지로부터 분리한다
  * [x] auth 패키지에서 nextstep로 의존하는 부분을 제거한다.
  * [x] auth 패키지 내에서 스프링 빈으로 사용되던 객체를 Component Scan이 아닌 Java Configuration으로 빈 등록한다.

## 2단계 요구사항

* [x] 이미 예약이 된 스케줄 대상으로 예약 대기를 신청할 수 있다.
  * [x] 예약이 없는 스케줄에 대해서 예약 대기 신청을 할 경우 예약이 된다.
* [x] 자신의 예약 대기를 취소할 수 있다.
  * [x] 자신의 예약 대기가 아닌경우 취소할 수 없다.
* [x] 나의 예약 목록을 조회할 수 있다.
  * [x] 예약과 예약 대기를 나눠서 조회한다.
  * [x] 예약은 reservation을 조회하고 예약 대기는 reservation-waiting을 조회한다.
  * [x] 예약 대기의 경우 대기 순번도 함께 조회할 수 있다.
  * [x] 취소된 예약도 조회할 수 있다.

## 3단계 요구사항

* [x] 예약 승인
  * [x] 예약 사용자가 예약금을 입금을 하면 예약 승인을 한다
  * [x] 이 때 예약금이 매출로 기록된다.
  * [x] 예약의 상태가 입금 대기에서 예약 승인 상태로 변경된다.
* [x] 사용자 예약 취소
  * [x] 사용자가 예약을 취소하는 경우 예약 미승인 상태와 승인 상태로 구분이 된다.
  * [x] 예약 미승인 상태면 예약의 상태가 예약 철회로 변경된다
  * [x] 예약이 승인된 상태라면 관리자의 승인이 필요하다
    * [x] 관리자가 예약 취소를 승인하면 예약 취소가 진행된다
    * [x] 매출에 환불이 기록된다
    * [x] 예약의 상태가 예약 철회로 변경된다.
    * [x] 예약 대기자가 있는 경우 예약자로 변경할 수 있다.
* [x] 관리자 예약 취소
  * [x] 관리자도 예약을 취소할 수 있는데 이 역시 예약 미승인 상태와 승인 상태로 구분이 된다.
  * [x] 예약 미승인 상태면 예약의 상태가 예약 취소로 변경된다
  * [x] 예약 승인 상태면 별도의 승인 절차 없이 예약이 취소된다
    * [x] 사용자 예약 취소와 같은 프로세스를 진행한다.
