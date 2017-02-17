package bike.pl.bikemap;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


/**
 * Created by eszykle on 2017-02-17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void checkNavigationDrawerIsOpen() {
        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
    }

    @Test
    public void checkNavigationDrawerIsClosedCorrectly() {
        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close());
        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));
    }

    @Test
    public void clickRefresh() {
        onView(withId(R.id.refresh_layout)).perform(click());
    }
}