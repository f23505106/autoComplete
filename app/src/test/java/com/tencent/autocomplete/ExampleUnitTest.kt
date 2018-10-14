package com.tencent.autocomplete

import com.tencent.autocomplete.util.T9Data
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun t9(){
        assertEquals('2', T9Data.char2t9('a'))
        assertEquals('2', T9Data.char2t9('b'))
        assertEquals('2', T9Data.char2t9('c'))

        assertEquals('3', T9Data.char2t9('d'))
        assertEquals('3', T9Data.char2t9('e'))
        assertEquals('3', T9Data.char2t9('f'))

        assertEquals('4', T9Data.char2t9('g'))
        assertEquals('4', T9Data.char2t9('h'))
        assertEquals('4', T9Data.char2t9('i'))

        assertEquals('5', T9Data.char2t9('j'))
        assertEquals('5', T9Data.char2t9('k'))
        assertEquals('5', T9Data.char2t9('l'))

        assertEquals('6', T9Data.char2t9('m'))
        assertEquals('6', T9Data.char2t9('n'))
        assertEquals('6', T9Data.char2t9('o'))

        assertEquals('7', T9Data.char2t9('p'))
        assertEquals('7', T9Data.char2t9('q'))
        assertEquals('7', T9Data.char2t9('r'))
        assertEquals('7', T9Data.char2t9('s'))

        assertEquals('8', T9Data.char2t9('t'))
        assertEquals('8', T9Data.char2t9('u'))
        assertEquals('8', T9Data.char2t9('v'))

        assertEquals('9', T9Data.char2t9('w'))
        assertEquals('9', T9Data.char2t9('x'))
        assertEquals('9', T9Data.char2t9('y'))
        assertEquals('9', T9Data.char2t9('z'))
    }
}
