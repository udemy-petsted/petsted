package com.udemy.petsted.boards;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Boards, Integer> {

    List<Boards> findAll();
    List<Boards> findByUserId(Integer userid);

    List<Boards> findByHashtag(String content);
    List<Boards> findByContentLike(String content);


    List<Boards> findByPostType(Integer postType);
}
