package com.shinriyo.bufferconfig

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class BufferConfigComponent : StartupActivity {

    override fun runActivity(project: Project) {
        val bufferConfigService = BufferConfigService.getInstance()
        bufferConfigService.applyBufferSize()
    }
}
