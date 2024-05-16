package com.ead.notification.model

import com.ead.notification.dto.NotificationCommandDto
import com.ead.notification.enums.NotificationStatus
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_NOTIFICATIONS")
data class NotificationModel(

    @Id
    val notificationId: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val userId: UUID,

    @Column(nullable = false, length = 150)
    val title: String = "",

    @Column(nullable = false)
    val message: String = "",

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val creationDate: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var notificationStatus: NotificationStatus = NotificationStatus.CREATED

) {

    constructor(notification: NotificationCommandDto): this(
        userId = notification.userId,
        title = notification.title,
        message = notification.message
    )

}