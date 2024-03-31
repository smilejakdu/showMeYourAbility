package com.example.showmeyourability.Field.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QField is a Querydsl query type for Field
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QField extends EntityPathBase<Field> {

    private static final long serialVersionUID = 21141824L;

    public static final QField field = new QField("field");

    public final com.example.showmeyourability.shared.QBaseTimeEntitiy _super = new com.example.showmeyourability.shared.QBaseTimeEntitiy(this);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final EnumPath<com.example.showmeyourability.shared.Enum.FieldType> fieldType = createEnum("fieldType", com.example.showmeyourability.shared.Enum.FieldType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.showmeyourability.lesson.domain.Lesson, com.example.showmeyourability.lesson.domain.QLesson> lessons = this.<com.example.showmeyourability.lesson.domain.Lesson, com.example.showmeyourability.lesson.domain.QLesson>createList("lessons", com.example.showmeyourability.lesson.domain.Lesson.class, com.example.showmeyourability.lesson.domain.QLesson.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QField(String variable) {
        super(Field.class, forVariable(variable));
    }

    public QField(Path<? extends Field> path) {
        super(path.getType(), path.getMetadata());
    }

    public QField(PathMetadata metadata) {
        super(Field.class, metadata);
    }

}

