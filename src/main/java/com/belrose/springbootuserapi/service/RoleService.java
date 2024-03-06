package com.belrose.springbootuserapi.service;

import com.belrose.springbootuserapi.model.Role;
import com.belrose.springbootuserapi.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleService {
    Flux<Role> getAllRoles();
    Mono<Role> CreateRole(Role role);
    Mono<Void> deleteRole(String roleId);
    Mono<Role> findByName(String name);
    Mono<Role> findById(String roleId);
    Mono<User> removeUserFromRole(String userId,String roleId);
    Mono<User> assingUserToRole(String userId,String roleId);
    Mono<Role> removeAllUserFromRole(String roleId);

}
