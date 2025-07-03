@echo off
setlocal

:: === CONFIGURATION ===
@REM set TOMCAT_DIR=C:\Program Files\Apache Software Foundation\Tomcat 10.1
set TOMCAT_DIR=C:\Program Files\Apache Software Foundation\apache-tomcat-10.1.28
set PROJECT_NAME=bibliotheque
set WAR_NAME=%PROJECT_NAME%.war

echo.
echo ========================
echo Deploiement de %PROJECT_NAME%
echo ========================

:: Étape 1 : Compilation du projet
echo [1/4] Compilation avec Maven...
call mvn clean package
if errorlevel 1 (
    echo Erreur de compilation Maven.
    exit /b 1
)

:: Vérification que le .war a été créé
echo [2/4] Verification du fichier .war...
if not exist "target\%WAR_NAME%" (
    echo Fichier target\%WAR_NAME% introuvable
    exit /b 1
)

:: Étape 2 : Suppression ancienne version
echo [3/4] Suppression de l'ancienne version dans Tomcat...
if exist "%TOMCAT_DIR%\webapps\%WAR_NAME%" (
    del /Q "%TOMCAT_DIR%\webapps\%WAR_NAME%"
)
if exist "%TOMCAT_DIR%\webapps\%PROJECT_NAME%" (
    rmdir /S /Q "%TOMCAT_DIR%\webapps\%PROJECT_NAME%"
)

:: Étape 3 : Copie du nouveau .war
echo [4/4] Copie du nouveau .war dans Tomcat...
copy /Y "target\%WAR_NAME%" "%TOMCAT_DIR%\webapps\"
if errorlevel 1 (
    echo Erreur lors de la copie du fichier WAR
    exit /b 1
)

echo.
echo Deploiement termine avec succes !
echo Acces : http://localhost:8080/%PROJECT_NAME%
echo.

endlocal
pause