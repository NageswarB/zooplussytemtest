SET PWD=%~dp0

SET SELENIUM_JAR_LOC="%PWD%\src\main\resources\webdriver\selenium-server-standalone-3.13.0.jar"
SET CHROME_DRIVER_EXE="%PWD%\src\main\resources\webdriver\chromedriver.exe"

cmd /C start/MIN java -jar %SELENIUM_JAR_LOC% -role hub -timeout 3000 -browserTimeout 3600 -port 4444

cmd /C start/MIN java -Dwebdriver.chrome.driver=%CHROME_DRIVER_EXE% -jar %SELENIUM_JAR_LOC% -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome,version=ANY,maxInstances=2,platform=WINDOWS,maxSession=2" -port 5555

cmd /C start/MIN java -Dwebdriver.chrome.driver=%CHROME_DRIVER_EXE% -jar %SELENIUM_JAR_LOC% -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome,version=ANY,maxInstances=2,platform=WINDOWS,maxSession=2" -port 6666