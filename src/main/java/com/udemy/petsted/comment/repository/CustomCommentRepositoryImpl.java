package com.udemy.petsted.comment.repository;

import static com.udemy.petsted.comment.entity.QCommentEntity.commentEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.udemy.petsted.comment.entity.CommentEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentEntity> findAllByLastId(Long commentId) {
        return jpaQueryFactory
            .selectFrom(commentEntity)
            .where(commentEntity.id.lt(commentId))
            .orderBy(commentEntity.id.desc())
            .fetch();

    }
}
