package com.shinriyo.bufferconfig

import com.intellij.openapi.components.ApplicationComponent
import org.jetbrains.annotations.NotNull

class BufferConfigComponent : ApplicationComponent {

    override fun initComponent() {
        val bufferConfigService = BufferConfigService.getInstance()
        bufferConfigService.applyBufferSize()
    }

    override fun disposeComponent() {
        // 必要なクリーンアップ処理
    }

    @NotNull
    override fun getComponentName(): String {
        return "BufferConfigComponent"
    }
}
