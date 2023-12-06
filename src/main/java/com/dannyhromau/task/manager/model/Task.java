package com.dannyhromau.task.manager.model;

import com.dannyhromau.task.manager.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
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
    @Column(columnDefinition = "status_enum", nullable = false)
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "priority_enum", nullable = false)
    private Priority priority;
    @Column(name = "author_id", nullable = false)
    private UUID author_id;
    @Column(name = "executor_id", nullable = false)
    private UUID executor_id;
    @Column(name = "created_on", nullable = false)
    private ZonedDateTime createdOn;


    public enum Status {
        AWAITING, IN_PROGRESS, COMPLETED
    }

    public enum Priority {
        HIGH, MEDIUM, LOW
    }
}
