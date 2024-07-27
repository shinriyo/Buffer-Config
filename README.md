# Buffer Configurator Plugin

The Buffer Configurator plugin for IntelliJ IDEA and Android Studio allows users to customize the buffer size for debugging sessions. This ensures that larger amounts of data can be viewed without truncation, improving the debugging experience. The plugin also supports multiple languages, allowing users to interact with the interface in their preferred language.

## Key Features

1. **Customizable Buffer Size**: Users can select from commonly used buffer sizes (1 MB, 2 MB, 5 MB, 10 MB, 20 MB) via a dropdown menu in the settings dialog.
2. **Persistent Settings**: The selected buffer size is saved and automatically applied each time the IDE starts.
3. **Multi-language Support**: The plugin supports multiple languages including English, Simplified Chinese, Traditional Chinese, Korean, and Vietnamese. The appropriate language is automatically selected based on the user's OS settings.
4. **User Notifications**: After applying the settings, users receive a notification confirming that the buffer size has been successfully updated.

## Installation and Usage

1. **Download and Install**: Obtain the plugin from the JetBrains Plugin Repository and install it through the IDE's plugin manager.
2. **Configuration**: Go to `File > Settings > Tools > Buffer Config` to open the settings dialog. Select the desired buffer size from the dropdown menu.
3. **Apply Settings**: Click `Apply` to save the settings. A notification will appear confirming that the settings have been applied.
4. **Automatic Application**: The plugin ensures that the buffer size setting is applied every time the IDE starts, ensuring a consistent debugging experience.

## Technical Details

- The plugin leverages the IntelliJ Platform's PersistentStateComponent to save settings.
- It uses ResourceBundle for internationalization, allowing the interface to adapt to the user's preferred language.

## Example Structure

```plaintext
Buffer Config/
│
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── shinriyo/
│   │   │           └── bufferconfig/
│   │   │               ├── BufferConfigSettings.kt
│   │   │               ├── BufferConfigService.kt
│   │   │               ├── BufferConfigNotifier.kt
│   │   │               ├── BufferConfigBundle.kt
│   │   │               └── BufferConfigComponent.kt
│   │   └── resources/
│   │       └── com/
│   │           └── shinriyo/
│   │               └── bufferconfig/
│   │                   ├── messages.properties
│   │                   ├── messages_en.properties
│   │                   ├── messages_zh_CN.properties
│   │                   ├── messages_zh_TW.properties
│   │                   ├── messages_ko.properties
│   │                   └── messages_vi.properties
```

## how to build

1. stop Android Studio.

2. run the command.

```
./gradlew buildPlugin
```

3. open the folder

```
build/distributions
```

## icon file

```
./src/main/resources/META-INF
```

