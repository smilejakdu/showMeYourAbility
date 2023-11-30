package com.example.showmeyourability.shared;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseTimeEntitiy is a Querydsl query type for BaseTimeEntitiy
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseTimeEntitiy extends EntityPathBase<BaseTimeEntitiy> {

    private static final long serialVersionUID = -313928868L;

    public static final QBaseTimeEntitiy baseTimeEntitiy = new QBaseTimeEntitiy("baseTimeEntitiy");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QBaseTimeEntitiy(String variable) {
        super(BaseTimeEntitiy.class, forVariable(variable));
    }

    public QBaseTimeEntitiy(Path<? extends BaseTimeEntitiy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTimeEntitiy(PathMetadata metadata) {
        super(BaseTimeEntitiy.class, metadata);
    }

}

