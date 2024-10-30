package com.udemy.petsted.comment.repository;

import com.udemy.petsted.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
