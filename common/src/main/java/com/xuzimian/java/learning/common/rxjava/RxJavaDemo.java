package com.xuzimian.globaldemo.common.rxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

public class RxJavaDemo {




    /**
     * 异步调用
     */
    @Test
    public  void testAsyn() {
        createObservable()
                .observeOn(null)
                .subscribeOn(Schedulers.newThread())
                .subscribe(createObserver());
    }


    /**
     * 同步调用
     */
    @Test
    public  void testSync() {
        Observable novel = createObservable();
        Observer<String> reader = createObserver();
        novel.subscribe(reader);
    }

    /**
     * 创建被观察
     */
    @Test
    public  Observable createObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("Observable.subscribe �?�?...");
                emitter.onNext("连载1");
                emitter.onNext("连载2");
                emitter.onNext("连载3");
                emitter.onComplete();
                System.out.println("Observable.subscribe 结束...");
            }
        });
    }

    /**
     * 创建观察�?
     *
     * @return
     */
    @Test
    public  Observer<String> createObserver() {
        return new Observer<String>() {
            Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                mDisposable = disposable;
                System.out.println("Observer.onSubscribe:" + disposable);
            }

            @Override
            public void onNext(String s) {
                System.out.println("Observer.onNext:" + s);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Observer.onError:" + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("Observer.onComplete");
            }
        };
    }

//     class AndroidScheduler implements Executor {
//
//        private static AndroidScheduler instance;
//
//        private final Scheduler mMainScheduler;
//        private final Handler mHandler;
//
//        private AndroidScheduler() {
//            mHandler = new Handler(Looper.myLooper());
//            mMainScheduler = Schedulers.from(this);
//        }
//
//        public static synchronized Scheduler mainThread() {
//            if (instance == null) {
//                instance = new AndroidScheduler();
//            }
//            return instance.mMainScheduler;
//        }
//
//        @Override
//        public void execute(@NonNull Runnable command) {
//            mHandler.post(command);
//        }

}
