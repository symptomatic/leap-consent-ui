package gov.hhs.onc.leap.ui.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import gov.hhs.onc.leap.backend.model.ConsentUser;
import gov.hhs.onc.leap.session.ConsentSession;
import gov.hhs.onc.leap.ui.MainLayout;
import gov.hhs.onc.leap.ui.util.UIUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.sql.Blob;
import java.time.ZoneId;

@Slf4j
@PageTitle("User Preferences")
@Route(value = "userpreferencesview", layout = MainLayout.class)
@CssImport("./styles/components/user-view.css")
public class UserPreferencesView extends ViewFrame {
    private TextField firstName = new TextField();
    private TextField middleName = new TextField();
    private TextField lastName = new TextField();
    private TextField maritalStatus = new TextField();
    private TextField eyeColor = new TextField();
    private TextField hairColor = new TextField();
    private TextField weight = new TextField();
    private TextField gender = new TextField();
    private TextField state = new TextField();
    private TextField ethnicity = new TextField();

    private TextField username = new TextField();
    private TextField prefix = new TextField();
    private DatePicker dateOfBirth = new DatePicker();
    private TextField height = new TextField();
    private TextField streetAddress1 = new TextField();
    private TextField streetAddress2 = new TextField();
    private TextField city = new TextField();
    private TextField zipCode = new TextField();
    private TextField phone = new TextField();
    private TextField mobile = new TextField();
    private TextField emailAddress = new TextField();
    private TextField languagePreference = new TextField();
    private TextField primaryPhysician = new TextField();
    private TextField primaryPhysicianPhoneNumber = new TextField();
    private TextField emergencyContact = new TextField();
    private TextField emergencyContactPhone = new TextField();
    private TextField relationship = new TextField();
    private Image image = new Image();


    public UserPreferencesView() {
        setId("userpreferencesview");
        FormLayout layoutWithFormItems = new FormLayout();
        firstName.setPlaceholder("Firstname");
        middleName.setPlaceholder("Middlename");
        lastName.setPlaceholder("Lastname");
        maritalStatus.setPlaceholder("Marital Status");
        eyeColor.setPlaceholder("Eye color");
        hairColor.setPlaceholder("Hair color");
        weight.setPlaceholder("Weight");
        gender.setPlaceholder("Gender");
        state.setPlaceholder("State");
        ethnicity.setPlaceholder("Ethnicity");

        //DatePicker birthDate = new DatePicker();
        populateUSerFromSession();

        layoutWithFormItems.addFormItem(firstName, "First name");
        layoutWithFormItems.addFormItem(middleName, "Middle name");

        layoutWithFormItems.addFormItem(lastName, "Last name");
        layoutWithFormItems.addFormItem(maritalStatus, "Marital Status");

        layoutWithFormItems.addFormItem(eyeColor, "Eye Color");
        layoutWithFormItems.addFormItem(hairColor, "Hair Color");


        layoutWithFormItems.addFormItem(weight, "Weight");
        layoutWithFormItems.addFormItem(gender, "Gender");

        layoutWithFormItems.addFormItem(state, "State");
        layoutWithFormItems.addFormItem(ethnicity, "Ethnicity");

        layoutWithFormItems.addFormItem(username, "Username");
        layoutWithFormItems.addFormItem(prefix, "prefix");


        layoutWithFormItems.addFormItem(dateOfBirth, "Birthdate");
        layoutWithFormItems.addFormItem(height, "Height");

        layoutWithFormItems.addFormItem(streetAddress1, "Street address 1");
        layoutWithFormItems.addFormItem(streetAddress2, "Street address 2");

        layoutWithFormItems.addFormItem(city, "City");
        layoutWithFormItems.addFormItem(zipCode, "zipCode");

        layoutWithFormItems.addFormItem(phone, "Phone");
        layoutWithFormItems.addFormItem(mobile, "Mobile");

        layoutWithFormItems.addFormItem(emailAddress, "Email Address");
        layoutWithFormItems.addFormItem(languagePreference, "Language Preference");

        layoutWithFormItems.addFormItem(primaryPhysician, "Primary Physician");
        layoutWithFormItems.addFormItem(primaryPhysicianPhoneNumber, "Primary Physician Phone Number");

        layoutWithFormItems.addFormItem(emergencyContact, "Emergency Contact");
        layoutWithFormItems.addFormItem(emergencyContactPhone, "Emergency Contact Phone");

        layoutWithFormItems.addFormItem(relationship, "Relationship");
        layoutWithFormItems.addFormItem(image, "Avatar Image");


        setViewContent(layoutWithFormItems);
    }

    private void populateUSerFromSession() {
        ConsentSession consentSession = (ConsentSession) VaadinSession.getCurrent().getAttribute("consentSession");
        ConsentUser user = consentSession.getConsentUser();
        this.firstName.setValue(user.getFirstName());
        this.firstName.setEnabled(false);
        this.middleName.setValue(user.getMiddleName());
        this.middleName.setEnabled(false);
        this.lastName.setValue(user.getLastName());
        this.lastName.setEnabled(false);
        this.maritalStatus.setValue(user.getMaritalStatus());
        this.maritalStatus.setEnabled(false);
        this.eyeColor.setValue(user.getEyeColor());
        this.eyeColor.setEnabled(false);
        this.hairColor.setValue(user.getHairColor());
        this.hairColor.setEnabled(false);
        this.weight.setValue(String.valueOf(user.getWeight()));
        this.weight.setEnabled(false);
        this.gender.setValue(user.getGender());
        this.gender.setEnabled(false);
        this.state.setValue(user.getState());
        this.state.setEnabled(false);
        this.ethnicity.setValue(user.getEthnicity());
        this.ethnicity.setEnabled(false);
        //Transient values
        this.username.setValue(user.getUserName());
        this.username.setEnabled(false);
        this.prefix.setValue(user.getPrefix());
        this.prefix.setEnabled(false);
        this.dateOfBirth.setValue(user.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.dateOfBirth.setEnabled(false);

        if (user.getHeight()!=null) {
            this.height.setValue(user.getHeight());
        }
        this.height.setEnabled(false);
        if (user.getStreetAddress1()!=null) {
            this.streetAddress1.setValue(user.getStreetAddress1());
        }
        this.streetAddress1.setEnabled(false);
        if (user.getStreetAddress2()!=null) {
            this.streetAddress2.setValue(user.getStreetAddress2());
        }
        this.streetAddress2.setEnabled(false);
        if (user.getCity()!=null) {
            this.city.setValue(user.getCity());
        }
        this.city.setEnabled(false);
        if (user.getZipCode()!=null) {
            this.zipCode.setValue(user.getZipCode());
        }
        this.zipCode.setEnabled(false);
        if (user.getPhone()!=null) {
            this.phone.setValue(user.getPhone());
        }
        this.phone.setEnabled(false);
        if (user.getZipCode()!=null) {
            this.mobile.setValue(user.getZipCode());
        }
        this.mobile.setEnabled(false);
        if (user.getEmailAddress()!=null) {
            this.emailAddress.setValue(user.getEmailAddress());
        }
        this.emailAddress.setEnabled(false);
        if (user.getLanguagePreference()!=null) {
            this.languagePreference.setValue(user.getLanguagePreference());
        }
        this.languagePreference.setEnabled(false);
        if (user.getPrimaryPhysician()!=null) {
            this.primaryPhysician.setValue(user.getPrimaryPhysician());
        }
        this.primaryPhysician.setEnabled(false);
        if (user.getPrimaryPhysicianPhoneNumber()!=null) {
            this.primaryPhysicianPhoneNumber.setValue(user.getPrimaryPhysicianPhoneNumber());
        }
        this.primaryPhysicianPhoneNumber.setEnabled(false);
        if (user.getEmergencyContact()!=null) {
            this.emergencyContact.setValue(user.getEmergencyContact());
        }
        this.emergencyContact.setEnabled(false);
        if (user.getEmergencyContactPhone()!=null) {
            this.emergencyContactPhone.setValue(user.getEmergencyContactPhone());
        }
        this.emergencyContactPhone.setEnabled(false);
        if (user.getRelationship()!=null) {
            this.relationship.setValue(user.getRelationship());
        }
        this.relationship.setEnabled(false);

        //this.image = UIUtils.createImageFromText("S.G");
        Blob b = user.getUser().getPhoto();
        try {
            if ( b != null) {
                this.image = UIUtils.createImage(b.getBytes(1, (int) b.length()), "avatar.png", "avatar");
                this.image.setMaxHeight("45px");
                this.image.setMaxWidth("45px");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @PostConstruct
    public void setup(){
        log.info("on setup");
    }

    public void onAttach(AttachEvent attachEvent) {
        MainLayout.get().getAppBar().setTitle("User Preferences");
    }

}