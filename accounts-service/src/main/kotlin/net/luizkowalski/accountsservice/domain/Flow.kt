package net.luizkowalski.accountsservice.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(name = "flows", indexes = arrayOf(
        Index(columnList = "accountFrom"),
        Index(columnList = "accountTo"),
        Index(columnList = "flowId")
))
data class Flow(
        @Column(length = 1000)
        var amount: Long = 0L,

        @Column(nullable = false)
        var accountFrom: String = "",

        @Column(nullable = false)
        var accountTo: String = "",

        @Column(nullable = false, length = 100)
        var flowId: String = ""
) : BaseEntity() {

}