package com.marco.common

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun boolean_ext_check() {
        val ret1 = true.yes {
            1
        }.otherwise {
            2
        }
        Assert.assertEquals(ret1, 1)
        val ret2 = false.yes {
            1
        }.otherwise {
            2
        }
        Assert.assertEquals(ret2, 2)
        val ret3 = true.no {
            1
        }.otherwise {
            2
        }
        Assert.assertEquals(ret3, 2)
    }

    @Test
    fun testProperties() {
        InfoProps().let {
            println(it.name)
            println(it.age)
            println(it.isStudent)
            println(it.height)

            it.name = "石头"
            it.age = 18
            it.isStudent = true
            it.height = 165.1
        }
    }

}