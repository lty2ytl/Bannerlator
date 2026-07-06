# Bannerlator 中文语言包实施说明

## 已完成的工作

### 1. 创建中文语言包文件

**文件位置**: `Bannerlator/app/src/main/res/values-zh/strings.xml`

- 翻译了所有 450+ 个字符串资源
- 文件大小：39.7 KB
- 包含所有界面元素、功能模块、设置选项等的中文翻译

### 2. 创建语言管理器

**文件位置**: `Bannerlator/app/src/main/java/com/winlator/star/core/LanguageManager.kt`

功能：
- 管理语言设置（中文、英文、跟随系统）
- 应用语言配置到应用上下文
- 提供语言显示名称

### 3. 修改 MainActivity

**文件位置**: `Bannerlator/app/src/main/java/com/winlator/star/MainActivity.kt`

修改内容：
- 添加 `attachBaseContext` 方法，在 Activity 创建前应用语言设置
- 在 `onCreate` 方法中调用语言管理器

### 4. 修改设置界面

**文件位置**: `Bannerlator/app/src/main/java/com/winlator/star/ui/screens/SettingsScreen.kt`

修改内容：
- 添加语言选择下拉菜单
- 支持中文、英文、跟随系统三种选项
- 选择后自动重启应用以应用语言更改

### 5. 更新字符串资源

**修改的文件**:
- `Bannerlator/app/src/main/res/values/strings.xml` - 添加英文字符串
- `Bannerlator/app/src/main/res/values-zh/strings.xml` - 添加中文字符串

添加的字符串：
- `language_settings` - 语言设置标题
- `language_settings_description` - 语言设置描述
- `language_chinese` - 中文显示名称
- `language_english` - 英文显示名称
- `language_system` - 跟随系统显示名称

## 使用方法

1. **自动选择**：Android 系统会根据设备语言自动选择对应的字符串资源
   - 设备语言为中文 → 显示中文界面
   - 设备语言为英文 → 显示英文界面

2. **手动选择**：在应用设置中手动选择语言
   - 打开应用 → 设置 → 语言
   - 选择中文、英文或跟随系统
   - 应用将自动重启以应用更改

3. **默认语言**：应用默认设置为中文

## 技术实现

### 语言切换流程

1. 用户在设置界面选择语言
2. `LanguageManager.setLanguage()` 保存语言设置
3. 调用 `activity?.recreate()` 重启 Activity
4. `attachBaseContext()` 调用 `LanguageManager.applyLanguage()`
5. 应用使用新的语言配置加载界面

### 字符串资源结构

```
res/
├── values/           # 默认（英文）
│   └── strings.xml
├── values-zh/        # 中文
│   └── strings.xml
└── values-v27/       # API 27+ 特定
    └── strings.xml
```

## 注意事项

1. **专业术语保留英文**：DXVK、Vulkan、Wine、Box64 等专业术语保留英文
2. **占位符不变**：`%1$d`、`%1$s` 等占位符保持不变
3. **CDATA 内容已翻译**：帮助文本中的 CDATA 部分也已翻译
4. **重启生效**：语言更改需要重启 Activity 才能完全生效

## 测试建议

1. 测试设备语言为中文时的显示效果
2. 测试设备语言为英文时的显示效果
3. 测试在设置中手动切换语言的功能
4. 测试重启应用后语言设置的保持
