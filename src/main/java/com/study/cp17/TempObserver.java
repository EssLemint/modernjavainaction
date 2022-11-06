package com.study.cp17;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TempObserver implements Observer<TempInfo> {
  @Override
  public void onSubscribe(Disposable d) {

  }

  @Override
  public void onNext(TempInfo tempInfo) {
    log.info("tempInfo = {}", tempInfo);
  }

  @Override
  public void onError(Throwable e) {
    log.info("Got Problem: " + e.getMessage());
  }

  @Override
  public void onComplete() {
    log.info("DONE");
  }
}
