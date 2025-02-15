package gov.hhs.onc.leap.session;

import gov.hhs.onc.leap.backend.model.ConsentUser;
import org.hl7.fhir.r4.model.Consent;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;

public class ConsentSession {
    private String username;
    private String userId;
    private String fhirPatientId;
    private String languagePreference;  // Manual entry default English
    private String primaryState;        // Manual entry default "Arizona"
    private Patient fhirPatient;        // Fhir entry
    private Organization fhirCustodian;
    private Practitioner primaryPhysician;
    private Consent currentConsentObject;
    private ConsentUser consentUser;
    private String fhirbase;

    public ConsentSession(String fhirbase){
        this.fhirbase = fhirbase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguagePreference() {
        return languagePreference;
    }

    public void setLanguagePreference(String languagePreference) {
        this.languagePreference = languagePreference;
    }

    public String getPrimaryState() {
        return primaryState;
    }

    public void setPrimaryState(String primaryState) {
        this.primaryState = primaryState;
    }

    public Patient getFhirPatient() {
        return fhirPatient;
    }

    public void setFhirPatient(Patient fhirPatient) {
        this.fhirPatient = fhirPatient;
    }

    public Organization getFhirCustodian() {
        return fhirCustodian;
    }

    public void setFhirCustodian(Organization fhirCustodian) {
        this.fhirCustodian = fhirCustodian;
    }

    public Practitioner getPrimaryPhysician() {
        return primaryPhysician;
    }

    public void setPrimaryPhysician(Practitioner primaryPhysician) {
        this.primaryPhysician = primaryPhysician;
    }

    public Consent getCurrentConsentObject() {
        return currentConsentObject;
    }

    public void setCurrentConsentObject(Consent currentConsentObject) {
        this.currentConsentObject = currentConsentObject;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ConsentUser getConsentUser() {
        return consentUser;
    }

    public void setConsentUser(ConsentUser consentUser) {
        this.consentUser = consentUser;
    }

    public String getFhirbase() {
        return fhirbase;
    }

    public void setFhirbase(String fhirbase) {
        this.fhirbase = fhirbase;
    }

    public String getFhirPatientId() {
        return fhirPatientId;
    }

    public void setFhirPatientId(String fhirPatientId) {
        this.fhirPatientId = fhirPatientId;
    }
}
