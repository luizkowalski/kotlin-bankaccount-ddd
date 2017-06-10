package net.luizkowalski.accountsservice.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Data
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        var id: Long? = null,

        @CreatedDate
        var createdAt: Date? = null,

        @LastModifiedDate
        var updatedAt: Date? = null
)