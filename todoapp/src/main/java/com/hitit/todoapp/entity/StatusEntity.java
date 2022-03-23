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
@Table(name = "status")
@AllArgsConstructor
@NoArgsConstructor
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "inserted_at")
    private Date insertedAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
