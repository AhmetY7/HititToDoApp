package com.hitit.todoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Where(clause = "is_deleted=false")
@Entity
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(targetEntity = StatusEntity.class)
    @JoinColumn(referencedColumnName = "id")
    private StatusEntity status;

    @Column(name = "target_date")
    private Date targetDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "inserted_at")
    private Date insertedAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
