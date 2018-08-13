# About This Project

This project is a simple framework solution to validate the asset filters on https://www.wolf-of-wilderness.com application , when search products with

# Tools , Approaches and Technologies Used

* Cucumber , Gherkin
* Core Java
* Selenium WebDriver API 3.9.1
* Page Object Model
* Junit
* Maven
* Chrome Latest 


# How to Setup & Configure

  1. Install Eclipse ( any latest version will do )
  2. Install Maven plugin in ecplise if it is not present
  3. Download / Clone this automation framework to your computer local drive from below GIT URL and extract
  4. Locate pom.XML and right click and choose Run As -> Maven Build
  5. In the run configurations provide below values and click apply and run.
		Goals : generate-resources
		Profiles: <profile-to-be-used>
  6. Check whether the build is successful
  7. Now , locate MasterTest.java under com.zooplus.tests package , right click on the java file and choose Run As -> Junit Test

This step would launch the browser and launch the application and runs the scenarios written in <modulename>.feature files under resources/feature_files/ path.

#How to run feature specific tests

To run feature specific tests , please edit test runner class MasterTest.java and update the tags = "@<feature-name>".
