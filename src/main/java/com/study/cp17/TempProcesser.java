package com.study.cp17;

import java.util.concurrent.Flow.*;

public class TempProcesser implements Processor<TempInfo, TempInfo> {
  //TempInfo -> TempInfo로 변경
  private Subscriber<? super TempInfo> subscriber;

  @Override
  public void subscribe(Subscriber<? super TempInfo> subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void onNext(TempInfo item) {
    subscriber.onNext(new TempInfo(item.getTown(), (item.getTemp() - 32) * 5 / 9));
    //섭씨 변환 -> TempInfo 전달
  }

  @Override
  public void onSubscribe(Subscription subscription) {
    subscriber.onSubscribe(subscription);
  }

  @Override
  public void onError(Throwable throwable) {
    subscriber.onError(throwable);
  }

  @Override
  public void onComplete() {
    subscriber.onComplete();
  }
  /**
   *  다른 모든 신호는 업스트림 구독자에게 전달.
   * */
}
