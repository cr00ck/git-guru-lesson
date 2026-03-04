# Automation Testing Project for Kontur Company Website
## :scroll: Contents:

- [Tech Stack](#computer-tech-stack)
- [Running Tests](#arrow_forward-running-tests)
- [Jenkins Build](#-jenkins-build)
- [Allure Report Example](#-allure-report-example)
- [Allure TestOps Integration](#-allure-testops-integration)
- [Jira Integration](#-jira-integration)
- [Telegram Notifications](#-telegram-notifications)
- [Video Example of Test Run in Selenoid](#-video-example-of-test-run-in-selenoid)

## :computer: Tech Stack

<p>
<img width="6%" title="IntelliJ IDEA" src="src/test/resources/img/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="src/test/resources/img/logo/Java.svg">
<img width="6%" title="Selenide" src="src/test/resources/img/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="src/test/resources/img/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="src/test/resources/img/logo/Allure_Report.svg">
<img width="5%" title="Allure TestOps" src="src/test/resources/img/logo/AllureTestOps.svg">
<img width="6%" title="Gradle" src="src/test/resources/img/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="src/test/resources/img/logo/Junit5.svg">
<img width="6%" title="GitHub" src="src/test/resources/img/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="src/test/resources/img/logo/Jenkins.svg">
<img width="5%" title="Jira" src="src/test/resources/img/logo/Jira.svg">
</p>

Tests in this project are written in <code>Java</code> using the [Selenide](https://selenide.org/) testing framework, with <code>Gradle</code> as the build tool. <code>JUnit 5</code> is used as the unit testing framework.
During test execution, [Selenoid](https://aerokube.com/selenoid/) is used to launch browsers.
For remote test execution, a <code>Jenkins</code> job has been set up that generates an Allure report and sends results to <code>Telegram</code> via a bot. Integration with <code>Allure TestOps</code> and <code>Jira</code> has also been implemented.

Allure report contents:
* Test steps;
* Page screenshot at the last step;
* Page Source;
* Browser console logs;
* Video of test execution.

## :arrow_forward: Running Tests

### Running tests from terminal

gradle clean test -Dselenoid_url="selenoid.autotests.cloud/wd/hub" -Dbrowser_size="1920x1080" -Dbrowser="chrome" -Dbrowser_version="100.0"

When executing this command in the IDE terminal, tests will run remotely in `Selenoid`.

## <img width="4%" style="vertical-align:baseline" title="Jenkins" src="src/test/resources/img/logo/Jenkins.svg" > Jenkins Build

To start the build, navigate to the <code>Build with Parameters</code> section and click the `Build` button.
<p align="center">
<img title="Jenkins Build" src="" >
</p>
After the build is complete, in the <code>Build History</code> section next to the build number, <code>Allure Report</code> and <code>Allure TestOps</code> icons will appear. Clicking on them will open the generated HTML report and test documentation respectively.

## <img width="4%" style="vertical-align:center" title="Allure Report" src="src/test/resources/img/logo/Allure_Report.svg"> Allure Report Example
### Overview

<p align="center">
<img title="Allure Overview" src="media/screens/allure.png">
</p>

## <img width="4%" style="vertical-align:baseline" title="Allure TestOps" src="src/test/resources/img/logo/AllureTestOps.svg"> Allure TestOps Integration

The <code>Allure TestOps</code> *Dashboard* shows statistics on the number of tests: how many have been added and are run manually, and how many are automated. New tests, as well as test run results, are submitted via integration with each build run.

<p align="center">
<img title="Allure TestOps DashBoard" src="media/screens/AllureTestOps.png">
</p>

### Test Execution Results

<p align="center">
<img title="Test Results in Alure TestOps" src="media/screens/allurResults.png">
</p>

## <img width="4%" style="vertical-align:baseline" title="Jira" src="src/test/resources/img/logo/Jira.svg"> Jira Integration

<code>Allure TestOps</code> integration with <code>Jira</code> has been implemented. The ticket displays which test cases were written as part of the task and their execution results.

<p align="center">
<img title="Jira Task" src="media/screens/Jira.png">
</p>

## <img width="4%" style="vertical-align:baseline" title="Telegram" src="src/test/resources/img/logo/Telegram.svg"> Telegram Notifications Using a Bot

After the build is complete, a dedicated bot created in <code>Telegram</code> automatically processes and sends a message with the test run report.

<p align="center">
<img width="70%" title="Telegram Notifications" src="media/screens/Bot.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Selenoid" src="src/test/resources/img/logo/Selenoid.svg"> Video Example of Test Run in Selenoid

In Allure reports, each test includes not only a screenshot but also a video of the test execution.
<p align="center">
  <img title="Selenoid Video" src="media/screens/Video.gif">
</p>