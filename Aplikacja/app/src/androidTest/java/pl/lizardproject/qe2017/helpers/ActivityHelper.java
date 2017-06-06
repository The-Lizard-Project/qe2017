package pl.lizardproject.qe2017.helpers;

import android.app.Activity;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class ActivityHelper {

    private static Activity currentActivity;

    private static Activity getActivityInstance() {
        getInstrumentation().runOnMainSync(new Runnable() {

            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity;
    }

    public static String getActivityName() {
        return getActivityInstance().getClass().getCanonicalName();
    }
}
