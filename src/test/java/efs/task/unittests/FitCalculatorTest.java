package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

class FitCalculatorTest {
    @Test
    void shouldReturnTrue_whenDietRecommended() {
        // given
        double weight = 89.2;
        double height = 1.72;

        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        // given
        double weight = 60.0;
        double height = 1.72;

        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowException_whenHeightIsZero() {
        // given
        double weight = 69.5;
        double height = 0.0;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "weight={0}")
    @ValueSource(doubles = { 85.5, 90.0, 95.2 })
    public void shouldReturnTrue_whenWeightsAreTooBig(double weight) {
        // when
        boolean valid = FitCalculator.isBMICorrect(weight, 1.72);

        // then
        assertTrue(valid);
    }

    @ParameterizedTest(name = "weight={0}, height={1}")
    @CsvSource(value = { "55.5, 1.72", "60.0, 1.72", "65.2, 1.72" })
    public void shouldReturnFalse_forGivenCsv(double weight, double height) {
        // when
        boolean valid = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(valid);
    }

    @ParameterizedTest(name = "weight={0}, height={1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void shouldReturnFalse_forGivenCsvFile(double weight, double height) {
        // when
        boolean valid = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(valid);
    }

    @Test
    public void shouldReturnUserWithTheWorstBmi() {
        // given
        List<User> users = TestConstants.TEST_USERS_LIST;

        // when
        User worstBmi = FitCalculator.findUserWithTheWorstBMI(users);

        // then
        assertTrue(worstBmi.getWeight() == 97.3);
        assertTrue(worstBmi.getHeight() == 1.79);
    }

    @Test
    public void shouldReturnUserWithTheWorstBmi_whenUsersAreEmpty() {
        // given
        List<User> users = new ArrayList<>();

        // when
        User worstBmi = FitCalculator.findUserWithTheWorstBMI(users);

        // then
        assertTrue(worstBmi == null);
    }

    @Test
    public void shouldReturnBmiScoreForGivenUsersList() {
        // given
        List<User> users = TestConstants.TEST_USERS_LIST;

        // when
        double[] bmiScores = FitCalculator.calculateBMIScore(users);

        // then
        assertArrayEquals(TestConstants.TEST_USERS_BMI_SCORE, bmiScores);
    }
}