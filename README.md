# 개발환경
1. IDE: IntelliJ IDEA Community
2. Spring Boot 3.0.4
3. JDK 17
4. mysql
5. Spring Data JPA
6. Thymeleaf

# 게시판 주요기능
1. 글쓰기(/board/save)
2. 글목록(/board/)
3. 글조회(/board/{id})
4. 글수정(/board/update/{id})
    - 상세화면에서 수정 버튼 클릭
    - 서버에서 해당 게시글의 정보를 가지고 수정 화면 출력
    - 제목, 내용 수정 입력 받아서 서버로 요청
    - 수정 처리
5. 글삭제(/board/delete/{id})
6. 페이징처리(/board/paging)
   - /board/paging?page=2
   - /board/paging/2
   - 게시글 14
     - 한페이지에 5개씩 => 3개
     - 한페이지에 3개씩 => 5개
7. 파일(이미지) 첨부하기
    - 단일 파일 첨부
    - 다중 파일 첨부
    - 파일 첨부와 관련하여 추가될 부분들
      - save.html
      - BoardDTO
      - BoardService.save()
      - BoardEntity
      - BoardFileEntity, BoardFileRepository  추가
      - detail.html

## mysql DataBase 계정 생성 및 권한 부여
```
 create database db_condingrecipe;
 create user user_codingrecipe@localhost identified by '1234';
 grant all privileges on db_codingrecipe.* to user_codingrecipe@localhost;
```

-board_table(부모) - board_file_table(자식)

on delete cascade
부모 삭제시 자식도 삭제

8. 댓글처리하기
   - 글 상세 페이지에서 댓글 입력 (ajax)
     - ajax 다뤄보기 재생목록
   - 상세조회할 때 기존에 작성된 댓글목록이 보임
   - 댓글을 입력하면 기존 댓글 목록에 새로 작성한 댓글 추가
   - 댓글용 테이블 필요
