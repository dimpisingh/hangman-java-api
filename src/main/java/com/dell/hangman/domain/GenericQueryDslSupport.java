package com.dell.hangman.domain;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.util.Assert;

import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericQueryDslSupport<T> {

    private EntityManager entityManager;

    public GenericQueryDslSupport() {
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        Assert.notNull(entityManager);
        this.entityManager = entityManager;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected JPAQuery<T> from(EntityPath<T> path) {
        return new JPAQuery<>(entityManager).select(path).from(path);
    }

    protected JPAQuery<?> fromSubquery(EntityPath<?> entity, Expression<?> path) {
        return new JPAQuery<>(entityManager).select(path).from(entity);
    }

    protected JPAQuery<T> from(EntityPath<T> path, EntityPath<?>... paths) {
        EntityPath<?>[] allPaths = Arrays.copyOf(paths, paths.length + 1);
        allPaths[paths.length] = path;
        return new JPAQuery<>(entityManager).select(path).from(allPaths);
    }

    protected JPAUpdateClause update(EntityPath<T> path) {
        return new JPAUpdateClause(entityManager, path);
    }

    protected JPADeleteClause delete(EntityPath<T> path) {
        return new JPADeleteClause(entityManager, path);
    }
}