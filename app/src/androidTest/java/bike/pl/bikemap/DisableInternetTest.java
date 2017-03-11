package bike.pl.bikemap;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by eszykle on 2017-02-17.
 */
@RunWith(AndroidJUnit4.class)
/*
Remember to turn off the Internet connection before tests.
 */
public class DisableInternetTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    @Ignore("This test is ignored by default because it require to disable Internet connection")
    public void checkAlertDialogIsOpen() {
        onView(withText(R.string.cannot_connect_title)).check(matches(isDisplayed()));
    }

    @Test
    @Ignore("This test is ignored by default because it require to disable Internet connection")
    public void checkAlertDialogRetry() {
        //(button1 - retry, button2 - settings):
        onView(withId(android.R.id.button1)).perform(click());
        onView(withText(R.string.cannot_connect_title)).check(matches(isDisplayed()));
    }

}



