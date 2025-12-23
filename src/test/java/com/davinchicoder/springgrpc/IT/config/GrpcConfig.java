package com.davinchicoder.springgrpc.IT.config;

import com.davinchicoder.springgrpc.proto.CrudGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean(destroyMethod = "shutdown")
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }

    @Bean
    public CrudGrpc.CrudBlockingStub crudBlockingStub(ManagedChannel channel) {
        return CrudGrpc.newBlockingStub(channel);
    }
}
