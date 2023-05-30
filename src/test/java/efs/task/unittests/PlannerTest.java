package efs.task.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class PlannerTest {
    @ParameterizedTest(name = "activityLevel={0}")
    @EnumSource(ActivityLevel.class)
    public void shouldReturnCorrectCaloriesDemand_ForAnyActivityLevel(ActivityLevel activityLevel) {
        // given
        final Planner planner = new Planner();
        final User user = TestConstants.TEST_USER;

        // when
        int result = planner.calculateDailyCaloriesDemand(user, activityLevel);

        // then
        assertEquals(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel), result);
    }

    @Test()
    public void shouldReturnProperDailyIntake_ForGivenUser() {
        // given
        final Planner planner = new Planner();
        final User user = TestConstants.TEST_USER;

        // when
        DailyIntake result = planner.calculateDailyIntake(user);

        // then
        final DailyIntake expected = TestConstants.TEST_USER_DAILY_INTAKE;
        assertEquals(expected.getCalories(), result.getCalories());
        assertEquals(expected.getProtein(), result.getProtein());
        assertEquals(expected.getFat(), result.getFat());
        assertEquals(expected.getCarbohydrate(), result.getCarbohydrate());
    }
}
