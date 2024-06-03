package com.ead.notification.controller

import com.ead.notification.dto.NotificationDto
import com.ead.notification.model.NotificationModel
import com.ead.notification.service.NotificationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users/{userId}/notifications", produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin(origins = ["*"], maxAge = 3600)
class UserNotificationController(

    private val notificationService: NotificationService

) {

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping
    fun getNotifications(
        @PathVariable userId: UUID,
        @PageableDefault(page = 0, size = 10, sort = ["notificationId"], direction = ASC) pageable: Pageable
    ): ResponseEntity<Page<NotificationModel>> {
        return ResponseEntity.ok(notificationService.findAllByUserId(userId, pageable))
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/{notificationId}")
    fun updateNotification(
        @PathVariable userId: UUID,
        @PathVariable notificationId: UUID,
        @RequestBody body: NotificationDto
    ): ResponseEntity<Any> {
        val notificationModel = notificationService.findByNotificationIdAndUserId(notificationId, userId)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found")

        notificationModel.notificationStatus = body.notificationStatus
        notificationService.saveNotification(notificationModel)

        return ResponseEntity.ok(notificationModel)
    }

}