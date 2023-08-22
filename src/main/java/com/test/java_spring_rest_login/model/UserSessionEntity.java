package com.test.java_spring_rest_login.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="sessionTb")
@Data
public class UserSessionEntity {
    @Id
    @Column(name="session_id")
    private String sessionId;

    @Column(name="user_id")
    private String userId;    

    @Column(name="start_date")
    private Date startDate;

    @Column(name="limit_date")
    private Date limitDate;
}
