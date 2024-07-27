package com.shinriyo.bufferconfig

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.ProjectManager
import javax.swing.*

class BufferConfigSettings : Configurable {
    private var panel: JPanel? = null
    private var bufferSizeComboBox: JComboBox<String>? = null

    private val bufferSizes = arrayOf("1 MB", "2 MB", "5 MB", "10 MB", "20 MB")

    override fun getDisplayName(): String {
        return "Buffer Config"
    }

    override fun createComponent(): JComponent? {
        panel = JPanel()
        bufferSizeComboBox = JComboBox(bufferSizes)
        panel?.add(JLabel("Buffer Size:"))
        panel?.add(bufferSizeComboBox)

        // 現在の設定値を表示
        val config = BufferConfigService.getInstance()
        bufferSizeComboBox?.selectedItem = formatBufferSize(config.maxBufferSize)

        return panel
    }

    private fun formatBufferSize(sizeInBytes: Int): String {
        val sizeInMB = sizeInBytes / (1024 * 1024)
        return "$sizeInMB MB"
    }

    private fun parseBufferSize(sizeString: String): Int {
        val sizeInMB = sizeString.split(" ")[0].toInt()
        return sizeInMB * 1024 * 1024
    }

    override fun isModified(): Boolean {
        val config = BufferConfigService.getInstance()
        return bufferSizeComboBox?.selectedItem != formatBufferSize(config.maxBufferSize)
    }

    override fun apply() {
        val config = BufferConfigService.getInstance()
        config.maxBufferSize = parseBufferSize(bufferSizeComboBox?.selectedItem as String)
        config.applyBufferSize()

        // 設定完了メッセージを表示
        val project = ProjectManager.getInstance().openProjects[0]
        BufferConfigNotifier.notify(project, BufferConfigBundle.message("settings.applied"))
    }

    override fun reset() {
        val config = BufferConfigService.getInstance()
        bufferSizeComboBox?.selectedItem = formatBufferSize(config.maxBufferSize)
    }
}
