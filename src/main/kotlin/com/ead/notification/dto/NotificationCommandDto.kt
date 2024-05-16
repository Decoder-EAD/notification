package com.ead.notification.dto

import java.util.UUID

data class NotificationCommandDto(

    val title: String = "",
    val message: String = "",
    var userId: UUID = UUID.randomUUID()

)