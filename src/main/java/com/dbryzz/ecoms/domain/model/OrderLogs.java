package com.gg.ecoms.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "order_logs")
public class OrderLogs {

    @Id
    @Column(name = "logId")
    private Long logId;

    @NotBlank
    @OneToOne
    private Order order;



}
