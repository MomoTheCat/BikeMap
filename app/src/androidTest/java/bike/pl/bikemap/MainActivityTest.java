package bike.pl.bikemap;

import android.app.Activity;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import bike.pl.bikemap.map.GMapFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;


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
        GMapFragment myFragment = (GMapFragment)mActivityRule.getActivity()
                .getFragmentManager()
                .findFragmentByTag(MainActivity.MAP_FRAGMENT_NAME);

        assert(myFragment.isVisible());
        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));
    }


//    @Test
//    public void clickBackButtonOnce() {
//        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        device.pressBack();
//        SystemClock.sleep(500);
//        device.pressBack();
//
//        onView(withText(R.string.press_back_exit))
//                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
//                .check(matches(isDisplayed()));
//
//        assertCurrentActivityIsInstanceOf();
//    }
//
//    public void assertCurrentActivityIsInstanceOf() {
//        Activity currentActivity = mActivityRule.getActivity();
//        checkNotNull(currentActivity);
//        checkNotNull(MainActivity.class);
//        assertTrue(currentActivity.getClass().isAssignableFrom(MainActivity.class));
//    }

}