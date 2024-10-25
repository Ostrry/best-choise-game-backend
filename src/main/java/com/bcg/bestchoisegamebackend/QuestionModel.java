package com.bcg.bestchoisegamebackend;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "questions")
public class QuestionModel {
    @Id
    private String Uuid;
    private int id1;
    private int id2;
}
