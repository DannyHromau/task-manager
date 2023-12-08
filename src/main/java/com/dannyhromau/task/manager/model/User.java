package com.dannyhromau.task.manager.model;

import com.dannyhromau.task.manager.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usr")
public class User extends BaseEntity {
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Task> issuedTaskList;
    @OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
    private List<Task> receivedTaskList;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Comment> commentList;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
