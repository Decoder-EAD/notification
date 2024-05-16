package com.ead.notification.service.impl

import com.ead.notification.enums.NotificationStatus
import com.ead.notification.model.NotificationModel
import com.ead.notification.repository.NotificationRepository
import com.ead.notification.service.NotificationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class NotificationServiceImpl(

    private val notificationRepository: NotificationRepository

): NotificationService {

    override fun saveNotification(notification: NotificationModel) {
        notificationRepository.save(notification)
    }

    override fun findAllByUserId(userId: UUID, pageable: Pageable): Page<NotificationModel> {
        return notificationRepository.findAllByUserIdAndNotificationStatus(userId, NotificationStatus.CREATED, pageable)
    }

    override fun findByNotificationIdAndUserId(notificationId: UUID, userId: UUID): NotificationModel? {
        return notificationRepository.findByNotificationIdAndUserId(notificationId, userId)
    }
}