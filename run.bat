@echo off
echo Running Reconditioned Car Management System...
where mvn >nul 2>nul
if %errorlevel% equ 0 (
    mvn clean javafx:run
) else (
    echo Maven (mvn) was not found in your system PATH.
    echo Please run this project from NetBeans / IntelliJ / Eclipse by opening pom.xml,
    echo or install Apache Maven and add it to your System PATH variables.
    pause
)
