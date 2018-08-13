SET SELENIUM_JAR_LOC="E:\Ability\zooplus\zooplus-systemtest\src\main\resources\webdriver\selenium-server-standalone-3.13.0.jar"
SET CHROME_DRIVER_EXE="E:\Ability\zooplus\zooplus-systemtest\src\main\resources\webdriver\chromedriver.exe"

cmd /C start/MIN java -jar %SELENIUM_JAR_LOC% -role hub -timeout 3000 -browserTimeout 3600 -port 5555

cmd /C start/MIN java -Dwebdriver.chrome.driver=%CHROME_DRIVER_EXE% -jar %SELENIUM_JAR_LOC% -role node -hub http://localhost:5555/grid/register -browser "browserName=chrome,version=ANY,maxInstances=3,platform=WINDOWS,maxSession=2" -port 6666