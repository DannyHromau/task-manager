package com.dannyhromau.task.manager.model;

import com.dannyhromau.task.manager.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usr")
public class Comment extends BaseEntity {
    @Column(name = "value", nullable = false)
    private String value;
    @Column(name = "author_id", nullable = false)
    private UUID authorId;
    @Column(name = "task_id", nullable = false)
    private UUID taskId;
}
