package com.dannyhromau.task.manager.model;

import com.dannyhromau.task.manager.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Column(name = "value", nullable = false)
    private String value;
    @Column(name = "author_id", nullable = false)
    private UUID authorId;
    @Column(name = "task_id", nullable = false)
    private UUID taskId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

}
