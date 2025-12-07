//package com.rashmita.order_service.idempotency;
//
//import lombok.Builder;
//import software.amazon.lambda.powertools.idempotency.persistence.PersistenceStore;
//@Builder
//
//public class IdempotencyConfig {
//
//    IdempotencyConfig config = IdempotencyConfig.builder()
//            .tableName("IdempotencyTable")
//            .build();
//
//    PersistenceStore persistenceStore = new DynamoDbPersistenceStore(config);
//
//}
