package com.kinthrahub.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "id_sequence")
public class IdSequence extends BaseEntity {

    @Id
    @Column(name = "sequence_id", nullable = false)
    private String sequenceId;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "start_value", nullable = false)
    private Long startValue;

    @Column(name = "current_value", nullable = false)
    private Long currentValue;

}