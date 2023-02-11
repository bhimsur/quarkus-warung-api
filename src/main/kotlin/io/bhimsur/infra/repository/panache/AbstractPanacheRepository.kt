package io.bhimsur.infra.repository.panache

import io.bhimsur.infra.repository.entity.UserEntity
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import java.util.*
import java.util.stream.Collectors

open class AbstractPanacheRepository<ENTITY, ID> : PanacheRepositoryBase<ENTITY, ID> {
    protected fun findUserEntityById(id: UUID): UserEntity {
        return entityManager.find(UserEntity::class.java, id)
    }

    protected fun isNotEmpty(list: List<*>): Boolean {
        return list.isNotEmpty()
    }

    protected fun toUpperCase(list: List<String>): List<String> {
        return list.stream().map(String::uppercase).collect(Collectors.toList())
    }
}