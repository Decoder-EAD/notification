package com.ead.notification.service

import com.ead.notification.model.NotificationModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface NotificationService {

    fun saveNotification(notification: NotificationModel)

    fun findAllByUserId(userId: UUID, pageable: Pageable): Page<NotificationModel>

    fun findByNotificationIdAndUserId(notificationId: UUID, userId: UUID): NotificationModel?

}