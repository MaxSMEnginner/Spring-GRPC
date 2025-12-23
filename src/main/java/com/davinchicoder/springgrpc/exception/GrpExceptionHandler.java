package com.davinchicoder.springgrpc.exception;

import io.grpc.Status;
import io.grpc.StatusException;
import org.springframework.grpc.server.exception.GrpcExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class GrpExceptionHandler implements GrpcExceptionHandler {

    @Override
    public StatusException handleException(Throwable exception) {

        if (exception instanceof PostNotFoundException) {
            return Status.NOT_FOUND.withDescription(exception.getMessage()).asException();
        }
        if (exception instanceof IllegalArgumentException) {
            return Status.INVALID_ARGUMENT.withDescription(exception.getMessage()).asException();
        }
        if (exception instanceof IllegalStateException) {
            return Status.FAILED_PRECONDITION.withDescription(exception.getMessage()).asException();
        }
        if (exception instanceof UnsupportedOperationException) {
            return Status.UNIMPLEMENTED.withDescription(exception.getMessage()).asException();
        }
        if (exception instanceof RuntimeException) {
            return Status.INTERNAL.withDescription(exception.getMessage()).asException();
        }

        return Status.UNKNOWN.withDescription(exception.getMessage()).asException();
    }

}
