package com.winlator.star.core

import android.content.Context
import android.content.res.Configuration
import androidx.preference.PreferenceManager
import java.util.Locale

/**
 * 语言管理器 - 用于管理应用的语言设置
 */
object LanguageManager {
    
    private const val KEY_LANGUAGE = "app_language"
    const val LANGUAGE_CHINESE = "zh"
    const val LANGUAGE_ENGLISH = "en"
    const val LANGUAGE_SYSTEM = "system"
    
    /**
     * 获取当前语言设置
     */
    fun getLanguage(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(KEY_LANGUAGE, LANGUAGE_CHINESE) ?: LANGUAGE_CHINESE
    }
    
    /**
     * 设置语言
     */
    fun setLanguage(context: Context, language: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(KEY_LANGUAGE, language).apply()
    }
    
    /**
     * 应用语言设置到上下文
     */
    fun applyLanguage(context: Context): Context {
        val language = getLanguage(context)
        if (language == LANGUAGE_SYSTEM) {
            return context
        }
        
        val locale = when (language) {
            LANGUAGE_CHINESE -> Locale.CHINESE
            LANGUAGE_ENGLISH -> Locale.ENGLISH
            else -> Locale.CHINESE
        }
        
        return updateResources(context, locale)
    }
    
    /**
     * 更新资源配置
     */
    private fun updateResources(context: Context, locale: Locale): Context {
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }
    
    /**
     * 获取语言显示名称
     */
    fun getLanguageDisplayName(language: String): String {
        return when (language) {
            LANGUAGE_CHINESE -> "中文"
            LANGUAGE_ENGLISH -> "English"
            LANGUAGE_SYSTEM -> "跟随系统"
            else -> "中文"
        }
    }
}
