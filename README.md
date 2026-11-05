# LinkedIn UI Automation Testing

## Project Description

This project was developed for the **IT3153 – Software Quality Assurance** assignment at General Sir John Kotelawala Defence University (KDU).

The purpose is to automate basic LinkedIn website functions using Java, Selenium WebDriver, and TestNG.

## Technologies Used

- **Java** – Programming language
- **Selenium WebDriver** – Browser automation
- **TestNG** – Test execution and assertions
- **Maven** – Dependency management
- **Google Chrome** – Browser used for testing
- **VS Code** – Development environment

## Project Structure

```text
linkedin-test/
├── pom.xml
├── README.md
├── .gitignore
├── screenshots/
└── src/
    └── test/
        └── java/
            ├── base/
            │   └── BaseTest.java
            ├── pages/
            │   ├── LoginPage.java
            │   └── SearchPage.java
            └── tests/
                └── LinkedInTest.java
```

## Test Cases

| Test ID | Test Case | Expected Result |
|---|---|---|
| TC01 | Valid Login | User successfully logs in to LinkedIn |
| TC02 | Invalid Login | An error message is displayed |
| TC03 | Search | Results for "software testing" are displayed |
| TC04 | Click Search Result | The selected result page opens successfully |

## How to Run the Project

### 1. Requirements

Install the following software:

- Java 17 or later
- Apache Maven
- Google Chrome
- VS Code (or another Java IDE)

### 2. Configure LinkedIn Credentials

Open the project in VS Code and start a PowerShell terminal.

Enter:

```powershell
$env:LINKEDIN_EMAIL = Read-Host "Enter LinkedIn email"
$env:LINKEDIN_PASSWORD = Read-Host "Enter LinkedIn password"
```

Enter your own LinkedIn account credentials when prompted.

Credentials are read using environment variables and are not stored in the Java source code.

### 3. Run All Tests

In the same terminal, execute:

```powershell
mvn clean test
```

Maven will compile the project and execute all four test cases.

### 4. Run Individual Tests

Valid login:

```powershell
mvn clean "-Dtest=LinkedInTest#validLogin" test
```

Invalid login:

```powershell
mvn clean "-Dtest=LinkedInTest#invalidLogin" test
```

Search:

```powershell
mvn clean "-Dtest=LinkedInTest#searchTest" test
```

Click a search result:

```powershell
mvn clean "-Dtest=LinkedInTest#clickResultTest" test
```

## Test Results and Screenshots

TestNG displays the test results in the terminal.

Detailed test reports are generated in:

`target/surefire-reports/`

Screenshots are automatically saved in:

`screenshots/`

The project captures screenshots for successful test cases and failed tests.

## Test Design

The project uses the **Page Object Model (POM)** to separate page actions from test cases.

- `BaseTest.java` – Browser setup, closing, and failure screenshots.
- `LoginPage.java` – LinkedIn login actions and error message verification.
- `SearchPage.java` – Search actions and result selection.
- `LinkedInTest.java` – Four automated test cases using TestNG.

Explicit waits are used to handle website loading times and dynamic elements.

## Challenges and Limitations

- LinkedIn contains dynamic elements, so some locators may change.
- Multiple matching elements may exist, including hidden input fields.
- Explicit waits help Selenium interact with visible elements.
- CAPTCHA or security verification may interrupt automated testing.
- LinkedIn's website structure can change without notice.

Only the test account owner's credentials should be used. Security checks must not be bypassed.

## Conclusion

This project demonstrates basic UI automation testing using Selenium WebDriver and TestNG. It covers login validation, searching, and opening search results while using Page Object Model, explicit waits, assertions, and automatic screenshots.