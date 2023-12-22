package com.example.showmeyourability.users.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -680210766L;

    public static final QUser user = new QUser("user");

    public final com.example.showmeyourability.shared.QBaseTimeEntitiy _super = new com.example.showmeyourability.shared.QBaseTimeEntitiy(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final ListPath<com.example.showmeyourability.comments.domain.Comments, com.example.showmeyourability.comments.domain.QComments> comments = this.<com.example.showmeyourability.comments.domain.Comments, com.example.showmeyourability.comments.domain.QComments>createList("comments", com.example.showmeyourability.comments.domain.Comments.class, com.example.showmeyourability.comments.domain.QComments.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final EnumPath<GenderType> genderType = createEnum("genderType", GenderType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath img = createString("img");

    public final StringPath name = createString("name");

    public final ListPath<com.example.showmeyourability.order.domain.Order, com.example.showmeyourability.order.domain.QOrder> orders = this.<com.example.showmeyourability.order.domain.Order, com.example.showmeyourability.order.domain.QOrder>createList("orders", com.example.showmeyourability.order.domain.Order.class, com.example.showmeyourability.order.domain.QOrder.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

