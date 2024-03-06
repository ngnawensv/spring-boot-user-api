package com.belrose.springbootuserapi.service;

import com.belrose.springbootuserapi.exception.RoleAlreadyExistException;
import com.belrose.springbootuserapi.model.Role;
import com.belrose.springbootuserapi.model.User;
import com.belrose.springbootuserapi.repository.RoleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Flux<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Mono<Role> CreateRole(Role role) {
        return findByName(role.getName())
                .flatMap(checkRole-> checkRole==null?
                        roleRepository.save(role): Mono.error(new RoleAlreadyExistException(String.format("%s role already exist",role.getName()))));
    }

    @Override
    public Mono<Void> deleteRole(String roleId) {
        this.removeAllUserFromRole(roleId);
        return roleRepository.deleteById(roleId);
    }

    @Override
    public Mono<Role> findByName(String name) {
        return roleRepository.findByName(name).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Role> findById(String roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public Mono<User> removeUserFromRole(String userId, String roleId) {
        return null;
    }

    @Override
    public Mono<User> assingUserToRole(String userId, String roleId) {
        return null;
    }

    @Override
    public Mono<Role> removeAllUserFromRole(String roleId) {
        return roleRepository.findById(roleId).flatMap(role -> {
            assert role!=null;
            role.removeAllUsersFromRole();
            return roleRepository.save(role);
        });
    }
}
