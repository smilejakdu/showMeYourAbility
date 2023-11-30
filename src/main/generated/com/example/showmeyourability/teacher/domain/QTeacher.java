package com.example.showmeyourability.teacher.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeacher is a Querydsl query type for Teacher
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacher extends EntityPathBase<Teacher> {

    private static final long serialVersionUID = -911107263L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeacher teacher = new QTeacher("teacher");

    public final com.example.showmeyourability.shared.QBaseTimeEntitiy _super = new com.example.showmeyourability.shared.QBaseTimeEntitiy(this);

    public final StringPath career = createString("career");

    public final ListPath<com.example.showmeyourability.comments.domain.Comments, com.example.showmeyourability.comments.domain.QComments> comments = this.<com.example.showmeyourability.comments.domain.Comments, com.example.showmeyourability.comments.domain.QComments>createList("comments", com.example.showmeyourability.comments.domain.Comments.class, com.example.showmeyourability.comments.domain.QComments.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.showmeyourability.order.domain.Order, com.example.showmeyourability.order.domain.QOrder> orders = this.<com.example.showmeyourability.order.domain.Order, com.example.showmeyourability.order.domain.QOrder>createList("orders", com.example.showmeyourability.order.domain.Order.class, com.example.showmeyourability.order.domain.QOrder.class, PathInits.DIRECT2);

    public final StringPath skill = createString("skill");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.example.showmeyourability.users.domain.QUser user;

    public QTeacher(String variable) {
        this(Teacher.class, forVariable(variable), INITS);
    }

    public QTeacher(Path<? extends Teacher> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeacher(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeacher(PathMetadata metadata, PathInits inits) {
        this(Teacher.class, metadata, inits);
    }

    public QTeacher(Class<? extends Teacher> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.showmeyourability.users.domain.QUser(forProperty("user")) : null;
    }

}

