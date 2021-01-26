package com.example

import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class FlowableErrorControllerTest extends Specification {

    @Inject
    FlowableClient flowableClient

    void 'test error'() {
        when:
        flowableClient.thatErrorThing()

        then:
        def e = thrown(HttpClientResponseException)
        verifyAll {
            e.status == HttpStatus.BAD_REQUEST      // actual is HttpStatus.OK
            e.message == 'testing flowable error'   // actual is "Empty body"
        }
    }

    @Client("/flow")
    static interface FlowableClient {
        @Get(uri = "/thing", consumes = MediaType.TEXT_PLAIN)
        String thatErrorThing();
    }
}