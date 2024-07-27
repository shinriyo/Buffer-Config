package com.shinriyo.bufferconfig

import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

object BufferConfigNotifier {
    private val NOTIFICATION_GROUP: NotificationGroup = NotificationGroupManager.getInstance().getNotificationGroup("Buffer Config Notifications")

    fun notify(project: Project, content: String) {
        val notification = NOTIFICATION_GROUP.createNotification(content, NotificationType.INFORMATION)
        notification.notify(project)
    }
}
