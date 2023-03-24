package com.codingrecipe.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String originalFileName;
    @Column
    private String storedFileName;
    @ManyToOne(fetch = FetchType.LAZY) // 패치타입 부모 엔티티 조회 시 자식 엔티티 이거랑 레이지 부모 조회할 떄 자식도 다 가져온다(이거타입) 필요한 상황에 호출가능(lazy)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity; // 부모 엔티티 타입, 실제 db는 id만 들어가 있다

    public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName){
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(boardEntity);
        return boardFileEntity;
    }
}
