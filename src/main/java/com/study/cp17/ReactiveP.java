package com.study.cp17;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.Flow.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class ReactiveP {

  public static void main(String[] args) {
    getTemperatures("New York").subscribe(new TempSubscriber());
    /**
     * 해다 상태에서는 stackOverFlow()가 발생 어떻게 개선?
     *  {@link TempSubscription} Executor 추가
     * */

    getCelsiusTemperature("NEW YORK").subscribe(new TempSubscriber());
    /**
     * 섭씨 온도 전송 Publisher 생성
     * {@link TempSubscriber}를 Publisher로 구독
     * */

    Observable<TempInfo> observable = getTemperature("NEW YORK");
    observable.blockingSubscribe(new TempObserver());
    /**
     * 뉴욕을 1초마다 온도를 계산 반환해줌
     * */
    Observable<TempInfo> observables = getCelsiusTemperature("NEW YORK", "CHICAGO", "SAN FRANCISCO");
    observables.blockingSubscribe(new TempObserver());
  }

  private static Publisher<TempInfo> getTemperatures(String town) {
    return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
  }

  public static Publisher<TempInfo> getCelsiusTemperature(String town) {
    return subscriber -> {
      TempProcesser processor = new TempProcesser();
      processor.subscribe(subscriber);
      processor.onSubscribe(new TempSubscription(processor,town));
      /**
       * {@link TempProcesser}을 만들고 {@link Subscriber}와 반환된 {@link Publisher}사이로 연결
       * */
    };
  }

  public static Observable<TempInfo> getTemperature(String town) {
    return Observable.create(emitter -> //Onserver로 부터 Observerale 만들기
        Observable.interval(1, TimeUnit.SECONDS) //매 초마다 무한으로 증가하는
                              //일련의 long값을 방출하는 Observable
            .subscribe(aLong -> {
              if (!emitter.isDisposed()) {  //소비되는 옵저버가 아직 폐기 되지 않았다면
                                          //어떤 작업을 수행(이전 에러)
                if (aLong >= 5) {   //온도를 다섯 번 보고했으면 옵저버를 완료하고 스트림 종료
                  emitter.onComplete();
                } else {      //아니면 온도를 Observer에 보고
                  try {
                    emitter.onNext(TempInfo.fetch(town));
                  } catch (Exception exception) {
                    emitter.onError(exception); //에러 발생시 옵저버에 보고
                  }
                }
              }
            }));
  }
  /**
   * {@link io.reactivex.rxjava3.core.Emitter}은 새 {@link io.reactivex.rxjava3.disposables.Disposable}을
   * 설정하는 메서드 시퀸스가 이미 다운스트림을 폐기했는지 확인 하는 메서드 제공
   * */

  public static Observable<TempInfo> getCelsiusTemperature(String... towns) {
    return Observable.merge(Arrays.stream(towns)
        .map(TempObservable::getCelsiusTemperatures)
        .collect(Collectors.toList()));
    /**
     * RxJava나 기타 리액티브 라이브러리는 자바 플로에 비해 스트림의 조작 API를 다양하게 제공해준다.
     * map, filter merge
     * TempObservable - getCelsiusTemperatures
     * merge -  obervable 로 평면화를 진행한 반환 값에 대해서 merge로 다시 observer가 수신할 수 있도록
     * 변환
     * */
  }
}
