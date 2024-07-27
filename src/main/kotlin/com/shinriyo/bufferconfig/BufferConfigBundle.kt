package com.shinriyo.bufferconfig

import com.intellij.AbstractBundle
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.PropertyKey

object BufferConfigBundle : AbstractBundle("com.shinriyo.bufferconfig.messages") {
    fun message(@NotNull @PropertyKey(resourceBundle = "com.shinriyo.bufferconfig.messages") key: String, vararg params: Any): String {
        return getMessage(key, *params)
    }
}
