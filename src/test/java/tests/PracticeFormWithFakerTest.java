package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.InputFields;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormWithFakerTest extends TestBase {
    InputFields steps = new InputFields();

    @Test
    void checkDataInFormWithFaker() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                mobile = faker.phoneNumber().subscriberNumber(10),
                currentAddress = faker.address().fullAddress();

        steps.openPracticeForm()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setUserNumber(mobile)
                .clickRadioButtonGenderMale()
                .clickOnDateOfBirth()
                .selectMonth("November")
                .selectYear("1993")
                .selectBirthDayInCalendar()
                .setSubject("Math")
                .clickOnMusicCheckBoxHobby()
                .clickOnSportsCheckBoxHobby()
                .uploadFile("cat.jpg")
                .setCurrentAddress(currentAddress)
                .clickOnStateDropDown()
                .chooseStateNCR()
                .clickOnCityDropDown()
                .chooseCityDelhi()
                .clickSubmit();

        $(".table-responsive").shouldHave(
                text("Student Name " + firstName + " " + lastName),
                text("Student Email " + email),
                text("Gender Male"),
                text("Mobile " + mobile),
                text("Date of Birth 17 November,1993"),
                text("Subjects Maths"),
                text("Hobbies Music, Sports"),
                text("Picture cat.jpg"),
                text("Address " + currentAddress),
                text("State and City NCR Delhi"));
    }

    @Test
   // @Tag("negative")
    void negativeCheckDataInForm() {
        steps.openPracticeForm()
                .clickOnStateDropDown()
                .clickSubmit();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }
}