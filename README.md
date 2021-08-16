
# LEAP Consent Management Service and UI
A service (including a user interface) for patients to analyze, create, revoke, reinstate, and audit consents.  Storing them 
in the form of FHIR Consent resources in a FHIR Server (Consent Store).

## Key Features
Patient can;
- Manange all FHIR consents from a single view.
- Create FHIR consents for: Patient-Privacy, Advance Directives, Portable Medical Order (POLST). 
Create **Informed Consent** for treatment - Medication Requests, research - Research Studies. 
(These are Experimental)
- Creates a FHIR QuestionnaireResponse resource capturing meaningful answers within UI.
- Creates FHIR Provenance resource linking Consent, QuestionnaireResponse resources, and the participating actors.
- Upload and analyze their clinical data in V2, CCDA, FHIR Bundle, and FHIR Resource formats for confidentialy analysis
- Utilize questionnaire like flow within UI to capture responses.
- Receive notifications of recommended patient-privacy exchange policies, medication requests,  
and research studies where patient is a possible candidate. (Experimental)
- View all authorization events where access to their clinical record is permitted or denied.  FHIR AuditEvents are generated by the Consent 
Decision Service (CDS) .  
- Capture electronic signatures.
- Support notary flow for advance directives.
- Digitally sign attachments with trusted custodian cert.

## Implementation Objectives
Create a near-production quality patient experience for creation and management of their FHIR Consents.  Identify and overcome limitations 
of FHIR R4 Consent resource.  Identify and overcome limitations in workflow when other FHIR resources integral to process, such as 
MedicationRequest, ServiceRequest, ResearchSubject, and ResearchStudy, are not necessarily consent aware.  

This service and UI are modeled after the requirements in the state of **Arizona**.  It however is not unique to 
**Arizona**, this state was utilized to create a baseline model for work flow, FHIR Questionnare, Codesystem, and 
QuestionnareResponse for consents which in the past have not been computable, but now are, such as this implementation's
Advance Directives. 

Things not considered or out of scope of this project:
- Patient/User provisioning.  We have pre-populated the user base for testing and demonstration purposes
- Patient/User settings and preferences.  Again we have pre-populated that data for testing and demonstration purposes.
- Patient/User production quality identity and authentication service.  This project implements spring-security for demonstration purposes
 only. We assume a future production deployment would utilize the hosting organizations tools and services, 
 and that this codebase could be easily updated to support such a deployment.
 - Assumes all signatories, witnesses, and other participant are in the room.  It is limited to this for purposes of demonstration.

## Quick Start
You may access the latest deployed version @ https://leap-gui-yop7t2tkfq-uc.a.run.app/login.
Contact ddecouteau@saperi.io for credentials. 

If you are a Developer and wish to investigate further you can run this implementation locally and utilize the services already deployed 
in the San Diego HealthConnect LEAP project Google cloud.
- Check out this repository:
```
> git clone https://github.com/sdhealthconnect/leap-consent-ui.git
```
- Create leap_consent database:
```
Using mysql client or mysql workbench create empty schema `leap_consent`
```
- Update local configuration files:
```
Modify src/main/resources/config/application-local.yaml and src/main/resources/db/local-liguidbase.properties
to reflect your local mysql environment
```
- Load user preferences:
```
> cd leap-consent-ui/leap-consent-ui
> mvn liquibase:update -Plocal
```
- Build it:
```
> mvn clean install -DskipTests
```
- Run it:
```
> export SLS_HOST_URL=http://34.94.253.50:9091
> export HAPI_FHIR_URL=http://34.94.253.50:8080/hapi-fhir-jpaserver/fhir/
> java -jar -Dspring.profiles.active=local -Dvaadin.productionMode=true target/leap-consent-ui-1.0-SNAPSHOT.war
```
- Use it:

The LEAP Consent UI is now available @ http://localhost:8080 10 test users, their preferences, and 
clincial data have been loaded for testing puposes.  

Again for credential info contact ddecouteau@saperi.io. 


## Architectural View
![High Level Architecture](docs/assets/high-level-architecture.png?raw=true)

The Consent Management Service and UI are just some of the components in a cloud based multi-service framework for the creation and enforcement of a patient's consent.  
This implementation is geared for a deployment at a regional Health Information Exchange (HIE).  However, it's decisioning and enforcement reach is 
not limited to just exchanges between that HIE (custodian) and other entities.

## My Consent Documents
This provides the patient with a view of all FHIR consents that are Active, Expired (exceeded provision end date), Revoked (by patient or other workflow), or Pending requiring some 
action from patient.

![My Consent Documents](docs/assets/MyConsentDocuments.png?raw=true)

The patient can retrieve detail information about a specific consent by clicking on the row of interest.  Where they may access details, 
revoke or reinstate that consent, act on requirements for consent in pending state, or view the attached digitally signed human readable form.

![Completed Form](docs/assets/completePOLST.png?raw=true)

## Analyze My Data
This empowers the patient to make an informed decision on whether or not to constrain specific types of data based
on their confidentialy code.  It utilizes the same Security Labeling Service (SLS) used within the LEAP Consent Enforcement 
Services.

![Analyze My Data](docs/assets/AnalyzeMyData.png?raw=true)

## Share My Data
This will be the most commonly used views within the LEAP Consent UI.  Allows the patient to create a patient-privacy scoped 
consent, where purpose of that consent is treatment, and in foundation policy is OPTIN.  Patient can constrain this exchange
by defining components of the provision(exceptions) such as: enforcement period, class of data allowed, the source/custodian, and 
destination/recipient, and whether to redact sensitive information or not.
  
![Share My Data](docs/assets/ShareMyData.png?raw=true)

## Advance Directives
Covers LivingWill, Power of Attorney for Health Care and Mental Health Care, and Do Not Resuscitate.  This create a consent scope 
of adr.  Utilizes and OPTOUT policy, with provision(exceptions) when purpose of use is ETREAT. A questionnaire response is linked
to this consent via an extension, where responses are captured in computable form.  Two path are supported 1) where responses
are captured as well as electronic signature, and digital signing of legal form, 2) where responses are captured, patient is
allowed to print and obtain signatures through a notary flow.  

![Advance Directives](docs/assets/AdvanceDirectives.png?raw=true)

## Portable Medical Order (POLST)
POLST is treated much like an Advance Directive.  This flow creates a consent scoped of adr.  Utilizes an OPTOUT policy.  Creates
provision where purpose of use is ETREAT.  A questionnaire response is linked to this consent via an extension,  
where responses are captured in computable form.

![POLST](docs/assets/POLST.png?raw=true)

## Notifications
Notifications address 3 areas of consent management 1) define a policy, which could be driven by the custodian organization,
or jurisdictional, where specific types of consents are required or optional based on a specific patient 
population, example age group. The other areas with notification covers informed consent, both of these are experimental.  2) Consent for treatment, where we 
utilized a medication request for an antidrepressant.  3) Consent for Research, using and an example flow of Pre-Diabetes 
clinical trial.

![Notfications](docs/assets/Notifications.png?raw=true)

