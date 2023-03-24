package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.CommentDTO;
import com.codingrecipe.board.entity.BoardEntity;
import com.codingrecipe.board.entity.CommentEntity;
import com.codingrecipe.board.repository.BoardRepository;
import com.codingrecipe.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO){
        System.out.println("commentDTO.getBoardId() = " + commentDTO.getBoardId());
        Long boardId = commentDTO.getBoardId();
        // 자식 데이터를 저장할 때 부모 엔티티가 필요하다
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }

        // builder

    }

    public List<CommentDTO> findAll(Long boardId) {
        // select * from comment_table where board_id=? order by id desc;
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

        // EntityList -> DTOList
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;

    }
}
