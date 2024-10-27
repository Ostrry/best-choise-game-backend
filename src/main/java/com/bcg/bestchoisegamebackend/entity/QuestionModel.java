package com.bcg.bestchoisegamebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "questions")
@NoArgsConstructor
public class QuestionModel {
    @Id
    private String Uuid;
    private int id1;
    private int id2;
}
