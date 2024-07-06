package br.inf.ufes.consiliario.entity

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Table("Meeting")
data class Meeting(
    @Id val id: UUID? = null,
    val dateTime: DateTime,
    val teacher: UUID,
    val student: UUID,
    val location: String
)