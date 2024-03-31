package com.example.showmeyourability.lesson.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLesson is a Querydsl query type for Lesson
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLesson extends EntityPathBase<Lesson> {

    private static final long serialVersionUID = -1007396375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLesson lesson = new QLesson("lesson");

    public final com.example.showmeyourability.shared.QBaseTimeEntitiy _super = new com.example.showmeyourability.shared.QBaseTimeEntitiy(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final com.example.showmeyourability.Field.entity.QField field;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> likes = createNumber("likes", Double.class);

    public final com.example.showmeyourability.teacher.domain.QTeacher teacher;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLesson(String variable) {
        this(Lesson.class, forVariable(variable), INITS);
    }

    public QLesson(Path<? extends Lesson> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLesson(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLesson(PathMetadata metadata, PathInits inits) {
        this(Lesson.class, metadata, inits);
    }

    public QLesson(Class<? extends Lesson> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.field = inits.isInitialized("field") ? new com.example.showmeyourability.Field.entity.QField(forProperty("field")) : null;
        this.teacher = inits.isInitialized("teacher") ? new com.example.showmeyourability.teacher.domain.QTeacher(forProperty("teacher"), inits.get("teacher")) : null;
    }

}

