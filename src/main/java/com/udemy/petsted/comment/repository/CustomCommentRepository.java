package com.udemy.petsted.comment.repository;

import com.udemy.petsted.comment.entity.CommentEntity;
import java.util.List;

public interface CustomCommentRepository {

    List<CommentEntity> findAllByLastId(Long commentId);

}
