package com.example;

import io.reactivex.Flowable;

public interface FlowableOperations {

    Flowable<String> thatErrorThing();
}
