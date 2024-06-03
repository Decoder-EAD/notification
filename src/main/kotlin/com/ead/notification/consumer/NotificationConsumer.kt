package com.ead.notification.consumer

import com.ead.notification.dto.NotificationCommandDto
import com.ead.notification.model.NotificationModel
import com.ead.notification.service.NotificationService
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class NotificationConsumer(
    private val notificationService: NotificationService
) {

    @RabbitListener(
        bindings = [
            QueueBinding(
                value = Queue(value = "\${ead.broker.exchange.queue.notification-command-queue.name}", durable = "true"),
                exchange = Exchange(value = "\${ead.broker.exchange.notification-command-exchange}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
                key = ["\${ead.broker.exchange.key.notification-command-key}"]
            )
        ]
    )
    fun listen(@Payload payload: NotificationCommandDto) {
        val notification = NotificationModel(payload)
        notificationService.saveNotification(notification)
    }

}