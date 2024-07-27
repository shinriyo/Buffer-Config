package com.shinriyo.bufferconfig

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

@State(
    name = "BufferConfigService",
    storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
@Service
class BufferConfigService : PersistentStateComponent<BufferConfigService> {
    var maxBufferSize: Int = 1024 * 1024 // デフォルトは1MB

    companion object {
        fun getInstance(): BufferConfigService {
            return ServiceManager.getService(BufferConfigService::class.java)
        }
    }

    @Nullable
    override fun getState(): BufferConfigService? {
        return this
    }

    override fun loadState(@NotNull state: BufferConfigService) {
        XmlSerializerUtil.copyBean(state, this)
    }

    fun applyBufferSize() {
        System.setProperty("idea.cycle.buffer.size", maxBufferSize.toString())
    }
}
