package com.study.cp17;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow.*;

@Slf4j
public class TempSubscriber implements Subscriber<TempInfo> {
  private Subscription subscription;

  @Override
  public void onSubscribe(Subscription subscription) {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onNext(TempInfo item) {
    log.info("item = {}", item);
    subscription.request(1);
  }

  @Override
  public void onError(Throwable throwable) {
    log.info("error = {}", throwable.getMessage());
  }

  @Override
  public void onComplete() {
    log.info("DONE");
  }
}
