package com.belrose.springbootuserapi.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@Document(collection = "roles")
public class Role implements Serializable {
    @Id
    private String id;
    private String name;
    private Collection<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public void removeAllUsersFromRole(){
        if(this.getUsers()!=null){
            List<User> usersInRole = this.getUsers().stream().toList();
            usersInRole.forEach(this::removeUserFromRole);
        }
    }

    public void removeUserFromRole(User user) {
        user.getRoles().remove(this);
        this.getUsers().remove(user);
    }

    public void assignUserToRole(User user) {
        user.getRoles().add(this);
        this.getUsers().add(user);
    }
}
