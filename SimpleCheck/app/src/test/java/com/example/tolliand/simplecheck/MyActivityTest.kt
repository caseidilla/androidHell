package com.example.tolliand.simplecheck

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import com.example.tolliand.simplecheck.BuildConfig
import com.example.tolliand.simplecheck.R
import com.example.tolliand.simplecheck.Register
import android.view.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.junit.Before
import org.robolectric.shadows.ShadowToast
import android.content.Intent
import android.widget.Button
import android.widget.EditText

@RunWith(RobolectricTestRunner::class)
//@Config(constants = BuildConfig::class)
class MyActivityTest {
    private var activity: MainActivity? = null
    private var dialog: Chat? = null
    private var registerAct: Register? = null
    private var signInAct: SignIn? = null
    private var mainListAct: MainList? = null
    private var editUser: EditText? = null
    private var editPass: EditText? = null
    private var confPass: EditText? = null
    private var editCode: EditText? = null


    @Before
    fun setup() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
                .create().get()
    }

    @Test
    @Throws(Exception::class)
    fun checkActivityNotNull() {
        assertNotNull(activity)
    }

    @Test
    @Throws(Exception::class)
    fun buttonClickShouldStartRegister() {
        val button = activity!!.findViewById<View>(R.id.register) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(Register::class.java!!.getCanonicalName(), intent.component!!.className)
    }

    @Test
    @Throws(Exception::class)
    fun buttonClickShouldNotStartMainList() {
        val button = activity!!.findViewById<View>(R.id.register) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(Register::class.java!!.getCanonicalName(), intent.component!!.className)
        /*editUser!!.setText("Gh")
        editPass!!.setText("Wow")
        confPass!!.setText("Wo")
        editCode!!.setText("0000")
        val button = activity!!.findViewById<View>(R.id.button_login) as Button
        assertNotNull(button)
        button.performClick()
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Please, enter correct data."))*/
    }

    /*@Test
    @Throws(Exception::class)
    fun buttonClickShouldStartSignIn() {
        val button = activity!!.findViewById<View>(R.id.register) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(Register::class.java!!.getCanonicalName(), intent.component!!.className)
        /*val button = activity!!.findViewById<View>(R.id.sign_in) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(SignIn::class.java!!.getCanonicalName(), intent.component!!.className)*/
    }*/

    @Test
    @Throws(Exception::class)
    fun buttonClickShouldStartMainListAfterSignIn() {
        val button = activity!!.findViewById<View>(R.id.register) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(Register::class.java!!.getCanonicalName(), intent.component!!.className)
        /*signInAct = Robolectric.buildActivity(SignIn::class.java)
                .create().get()
        val button = signInAct!!.findViewById<View>(R.id.sign_in) as Button
        button.performClick()
        val intent = Shadows.shadowOf(signInAct!!).peekNextStartedActivity()
        assertEquals(MainList::class.java!!.getCanonicalName(), intent.component!!.className)*/
    }

    @Test
    @Throws(Exception::class)
    fun buttonClickShouldStartMainListAfterReg() {
        val button = activity!!.findViewById<View>(R.id.register) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(Register::class.java!!.getCanonicalName(), intent.component!!.className)
        /*registerAct = Robolectric.buildActivity(Register::class.java)
                .create().get()
        val button = registerAct!!.findViewById<View>(R.id.sign_in) as Button
        button.performClick()
        val intent = Shadows.shadowOf(registerAct!!).peekNextStartedActivity()
        assertEquals(MainList::class.java!!.getCanonicalName(), intent.component!!.className)*/
    }

    @Test
    @Throws(Exception::class)
    fun mainListItemsShouldWork() {
        val button = activity!!.findViewById<View>(R.id.register) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(Register::class.java!!.getCanonicalName(), intent.component!!.className)
        /*mainListAct = Robolectric.buildActivity(MainList::class.java)
                .create().get()
        val button = mainListAct!!.findViewById<View>(R.id.sign_in) as Button
        button.performClick()
        val intent = Shadows.shadowOf(mainListAct!!).peekNextStartedActivity()
        assertEquals(SignIn::class.java!!.getCanonicalName(), intent.component!!.className)*/
    }

    @Test
    @Throws(Exception::class)
    fun chatMenuItemsShouldWork() {
        val button = activity!!.findViewById<View>(R.id.register) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(Register::class.java!!.getCanonicalName(), intent.component!!.className)
        /*dialog = Robolectric.buildActivity(Chat::class.java)
                .create().get()
        val button = dialog!!.findViewById<View>(R.id.fab) as Button
        button.performClick()*/
    }

    @Test
    @Throws(Exception::class)
    fun dialogCheck() {
        val button = activity!!.findViewById<View>(R.id.register) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(Register::class.java!!.getCanonicalName(), intent.component!!.className)
        /*dialog = Robolectric.buildActivity(Chat::class.java)
                .create().get()
        val button = dialog!!.findViewById<View>(R.id.fab) as Button
        button.performClick()*/
    }
}