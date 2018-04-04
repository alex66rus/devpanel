package com.busylee.devpanel

import android.content.Context
import com.busylee.devpanel.info.ButtonInfo
import com.busylee.devpanel.info.MutableInfo
import com.busylee.devpanel.info.ObjectInfo
import com.busylee.devpanel.info.preferences.*

/**
 * Created by busylee on 05.07.16.
 */
class InfoBuilderResolver(val context: Context) {

    fun mutable(valueFunc: () -> Any): MutableInfoAdder {
        return MutableInfoAdder(valueFunc)
    }

    fun simple(value: String): ObjectInfoAdder {
        return ObjectInfoAdder(value)
    }

    fun button(): ButtonAdder {
        return ButtonAdder()
    }

    fun pref(): PreferencesInfoAdder {
        return PreferencesInfoAdder(context)
    }

    class ButtonAdder : ButtonInfo.Builder() {
        override fun onClick(onClick: (Context?) -> Unit): ButtonAdder {
            super.onClick(onClick)
            return this
        }

        override fun title(title: String): ButtonAdder{
            super.title(title)
            return this
        }

        fun add () {
            DevPanel.addInfo(build())
        }
    }

    class ObjectInfoAdder(value: Any) : ObjectInfo.Builder(value) {
        override fun title(title: String): ObjectInfoAdder {
            super.title(title)
            return this
        }

        fun add() {
            DevPanel.addInfo(build())
        }
    }

    class MutableInfoAdder(valueFunc: () -> Any) : MutableInfo.Builder(valueFunc) {
        override fun title(title: String): MutableInfoAdder {
            super.title(title)
            return this
        }

        fun add() {
            DevPanel.addInfo(build())
        }
    }

    class PreferencesInfoAdder(context: Context): PreferenceInfo.Builder(context) {

        var infoBuilder: PreferenceInfo.Builder? = null

        fun bool(default: Boolean = false): PreferencesInfoAdder {
            infoBuilder = BooleanPreferenceInfo.Builder(context, title, preferenceKey).default(default)
            return this
        }

        fun float(default: Float = 0f): PreferencesInfoAdder {
            infoBuilder = FloatPreferenceInfo.Builder(context, title, preferenceKey).default(default)
            return this
        }

        fun integer(default: Int = 0): PreferencesInfoAdder {
            infoBuilder = IntPreferenceInfo.Builder(context, title, preferenceKey).default(default)
            return this
        }

        fun llong(default: Long = 0L): PreferencesInfoAdder {
            infoBuilder = LongPreferenceInfo.Builder(context, title, preferenceKey).default(default)
            return this
        }

        fun string(default: String = ""): PreferencesInfoAdder {
            infoBuilder = StringPreferenceInfo.Builder(context, title, preferenceKey).default(default)
            return this
        }

        override fun title(title: String): PreferencesInfoAdder {
            super.title(title)
            infoBuilder?.title(title)
            return this
        }

        override fun key(key: String): PreferencesInfoAdder {
            super.key(key)
            infoBuilder?.key(key)
            return this
        }

        fun add() {
            if(infoBuilder == null) {
                throw IllegalArgumentException("Please specify preference type")
            }

            checkAndThrow()
            DevPanel.addInfo((infoBuilder as PreferenceInfo.Builder).build())
        }

        override fun build(): PreferenceInfo<Any> {
            throw UnsupportedOperationException()
        }
    }

}