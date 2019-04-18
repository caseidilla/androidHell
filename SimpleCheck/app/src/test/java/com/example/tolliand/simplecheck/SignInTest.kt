package com.example.tolliand.simplecheck

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
import android.R.id
import android.widget.EditText
import org.hamcrest.CoreMatchers



@RunWith(RobolectricTestRunner::class)
//@Config(constants = BuildConfig::class)
class SignInTest {
    private var activity: SignIn? = null
    private var editUser: EditText? = null
    private var editPass: EditText? = null
    /*@Test
    @Throws(Exception::class)
    fun shouldHaveEditTexts() {
        val hello = MainActivity().resources.getString(
                R.string.hello_world)
        assertThat(hello, equalTo("Hello world!"))
    }*/

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(SignIn::class.java)
                .create().get()

        editUser = activity!!.findViewById(R.id.edit_user) as EditText
        editPass = activity!!.findViewById(R.id.edit_password) as EditText
    }

    @Test
    @Throws(Exception::class)
    fun checkActivityNotNull() {
        assertNotNull(activity)
        assertNotNull(editUser)
        assertNotNull(editPass)
    }

    @Test
    @Throws(Exception::class)
    fun buttonClickShouldStartMainList() {
        editUser!!.setText("loupa")
        editPass!!.setText("111")
        val button = activity!!.findViewById<View>(R.id.button_login) as Button
        button.performClick()
        val intent = Shadows.shadowOf(activity!!).peekNextStartedActivity()
        assertEquals(MainList::class.java!!.getCanonicalName(), intent.component!!.className)
    }

    @Test
    @Throws(Exception::class)
    fun buttonClickShouldNotStartMainList() {
        editUser!!.setText("Gh")
        editPass!!.setText("Woww")
        val button = activity!!.findViewById<View>(R.id.button_login) as Button
        assertNotNull(button)
        button.performClick()
        assertThat(ShadowToast.getTextOfLatestToast(), CoreMatchers.equalTo("No such user found"))
    }

}