package art.example.w4tlocompse

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CoroutynyTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("art.example.w4tlocompse", appContext.packageName)
    }

    @Test
    fun test1 ()  {
        val a = 20
        val task1  = CoroutynyExamples()
        task1.s1()
        assertEquals("Wynik", 20, a)
    }
    @Test
    fun test2() {
        val task2 = CoroutynyExamples()
        task2.s2()
    }
}