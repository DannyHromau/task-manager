package com.dannyhromau.task.manager.model;

import com.dannyhromau.task.manager.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task extends BaseEntity {
    @Column(name = "header", nullable = false)
    private String header;
    @Column(name = "description", nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Priority priority;
    @Column(name = "author_id", nullable = false)
    private UUID author_id;
    @Column(name = "executor_id", nullable = false)
    private UUID executor_id;
    @Column(name = "created_on", nullable = false)
    private ZonedDateTime createdOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id", insertable = false, updatable = false)
    private User executor;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<Comment> commentList;

    public enum Status {
        AWAITING, IN_PROGRESS, COMPLETED
    }

    public enum Priority {
        HIGH, MEDIUM, LOW
    }
}
