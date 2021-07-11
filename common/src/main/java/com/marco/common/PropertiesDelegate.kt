package com.marco.common

import java.io.File
import java.io.FileInputStream
import java.net.URL
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class InfoProps : AbsProperties("info.properties") {
    var name: String by prop
    var age: Int by prop
    var isStudent: Boolean by prop
    var height: Double by prop
}

class PropertiesDelegate(private val path: String) {

    private lateinit var url: URL

    private val properties: Properties by lazy {
        val prop = Properties()
        url = try {
            javaClass.getResourceAsStream(path).use {
                prop.load(it)
            }
            javaClass.getResource(path)
        } catch (e: Exception) {
            try {
                ClassLoader.getSystemClassLoader().getResourceAsStream(path).use {
                    prop.load(it)
                }
                ClassLoader.getSystemClassLoader().getResource(path)
            } catch (e: Exception) {
                FileInputStream(path).use {
                    prop.load(it)
                }
                URL("file://${File(path).canonicalPath}")
            }
        }
        prop
    }

    operator fun <T> getValue(thisRef: Any, property: KProperty<*>): T {
        val value = properties[property.name]
        val kClass = property.returnType.classifier as KClass<*>
        return when (kClass) {
            Boolean::class -> value.toString().toBoolean()
            Int::class -> value.toString().toInt()
            Double::class -> value.toString().toDouble()
            String::class -> value.toString()
            else -> throw IllegalArgumentException("not support type")
        } as T
    }

    operator fun <T> setValue(thisRef: Any, property: KProperty<*>, value: T) {
        properties[property.name] = value.toString()
        File(url.toURI()).outputStream().use {
            properties.store(it, "")
        }
    }
}

abstract class AbsProperties(path: String) {
    protected val prop = PropertiesDelegate(path)
}