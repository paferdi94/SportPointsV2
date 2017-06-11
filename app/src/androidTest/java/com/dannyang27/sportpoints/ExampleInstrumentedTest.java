package com.dannyang27.sportpoints;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dannyang27.sportpoints.activities.ConexionDB.ConexionFireBase;
import com.dannyang27.sportpoints.activities.Principal.LogIn;
import com.dannyang27.sportpoints.activities.Principal.Registro;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    //Inicializamos sesion de la clase login para el Testeo
    @Rule
    public ActivityTestRule<Registro> classRegistro =
            new ActivityTestRule<>(Registro.class, true, true);

    @Rule
    public ActivityTestRule<LogIn> classLogIn =
            new ActivityTestRule<>(LogIn.class, true, true);



    @Test
    public void urlSportPointsWorkSpace() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.dannyang27.sportpoints", appContext.getPackageName());
    }

    @Test
    public void testeo_logInButtonGmail() throws Exception {

        onView(withId(R.id.btn_gmail)).check(matches(isClickable()));
        onView(withId(R.id.btn_gmail)).check(matches(withText("Entrar con Google+")));

    }

    @Test
    public void testeo_logInButoonSingIn() throws Exception {

        onView(withId(R.id.btn_login)).check(matches(isClickable()));
        onView(withId(R.id.btn_login)).check(matches(withText("Entrar")));

    }

    @Test
    public void testeo_logInRegistrarse() throws Exception {

        onView(withId(R.id.txt_registrarse)).check(matches(isClickable()));
        onView(withId(R.id.txt_registrarse)).check(matches(withText("Regístrate")));

    }


}
