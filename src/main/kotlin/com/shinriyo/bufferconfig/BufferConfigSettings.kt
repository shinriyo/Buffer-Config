package com.shinriyo.bufferconfig

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.ProjectManager
import javax.swing.*
import java.awt.FlowLayout

class BufferConfigSettings : Configurable {
    private var panel: JPanel? = null
    private var bufferSizeComboBox: JComboBox<String>? = null
    private var customBufferSizeField: JTextField? = null
    private var presetRadioButton: JRadioButton? = null
    private var customRadioButton: JRadioButton? = null

    private val bufferSizes = arrayOf("1 MB", "2 MB", "5 MB", "10 MB", "20 MB")

    override fun getDisplayName(): String {
        return BufferConfigBundle.message("buffer.config")
    }

    override fun createComponent(): JComponent? {
        panel = JPanel()
        panel?.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        val presetPanel = JPanel(FlowLayout(FlowLayout.LEFT))
        val customPanel = JPanel(FlowLayout(FlowLayout.LEFT))

        bufferSizeComboBox = JComboBox(bufferSizes)
        customBufferSizeField = JTextField(10)

        presetRadioButton = JRadioButton(BufferConfigBundle.message("preset"), true)
        customRadioButton = JRadioButton(BufferConfigBundle.message("custom"))

        val buttonGroup = ButtonGroup()
        buttonGroup.add(presetRadioButton)
        buttonGroup.add(customRadioButton)

        presetPanel.add(presetRadioButton)
        presetPanel.add(bufferSizeComboBox)

        customPanel.add(customRadioButton)
        customPanel.add(customBufferSizeField)
        customPanel.add(JLabel(" MB"))

        panel?.add(presetPanel)
        panel?.add(customPanel)

        val config = BufferConfigService.getInstance()
        bufferSizeComboBox?.selectedItem = formatBufferSize(config.maxBufferSize)
        customBufferSizeField?.text = (config.maxBufferSize / (1024 * 1024)).toString()
        customBufferSizeField?.isEnabled = false

        bufferSizeComboBox?.maximumSize = bufferSizeComboBox?.preferredSize

        presetRadioButton?.addActionListener {
            bufferSizeComboBox?.isEnabled = true
            customBufferSizeField?.isEnabled = false
        }

        customRadioButton?.addActionListener {
            bufferSizeComboBox?.isEnabled = false
            customBufferSizeField?.isEnabled = true
        }

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
        return if (presetRadioButton?.isSelected == true) {
            bufferSizeComboBox?.selectedItem != formatBufferSize(config.maxBufferSize)
        } else {
            customBufferSizeField?.text?.toIntOrNull() != (config.maxBufferSize / (1024 * 1024))
        }
    }

    override fun apply() {
        val config = BufferConfigService.getInstance()
        if (presetRadioButton?.isSelected == true) {
            config.maxBufferSize = parseBufferSize(bufferSizeComboBox?.selectedItem as String)
        } else {
            config.maxBufferSize = customBufferSizeField?.text?.toIntOrNull()?.times(1024 * 1024) ?: config.maxBufferSize
        }
        config.applyBufferSize()

        val project = ProjectManager.getInstance().openProjects[0]
        BufferConfigNotifier.notify(project, BufferConfigBundle.message("settings.applied"))
    }

    override fun reset() {
        val config = BufferConfigService.getInstance()
        bufferSizeComboBox?.selectedItem = formatBufferSize(config.maxBufferSize)
        customBufferSizeField?.text = (config.maxBufferSize / (1024 * 1024)).toString()
        customBufferSizeField?.isEnabled = customRadioButton?.isSelected == true
        bufferSizeComboBox?.isEnabled = presetRadioButton?.isSelected == true
    }
}
