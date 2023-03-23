package com.codingrecipe.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
// 시간 정보를 공통적으로 사용하기 위해서 만듬
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false) // 수정시 관여안함
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(insertable = false) // 삽입시 관여안함
    private LocalDateTime updatedTime;
}
