package com.example;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.exceptions.HttpStatusException;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/flow")
public class FlowableErrorController implements FlowableOperations {

    Logger logger = LoggerFactory.getLogger(FlowableErrorController.class);

    @Override
    @Get(uri = "/thing",
        produces = MediaType.TEXT_PLAIN)
    public Flowable<String> thatErrorThing() {
        return createResultError()
            .doOnError(throwable -> logger.error("thatErrorThing is {}", throwable.getMessage()));
    }

    private Flowable<String> createResultError() {
        return Flowable.error(
            new HttpStatusException(HttpStatus.BAD_REQUEST, "testing flowable error")
        );
    }
}
