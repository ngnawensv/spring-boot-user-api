package com.belrose.springbootuserapi.repository;

import com.belrose.springbootuserapi.model.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RoleRepository extends ReactiveMongoRepository<Role,String> {
    Mono<Role> findByName(String name);
}
