package com.ead.notification.dto

import com.ead.notification.enums.NotificationStatus

data class NotificationDto(

    val notificationStatus: NotificationStatus = NotificationStatus.READ

)
