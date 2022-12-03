@echo off
echo Compiling...
"C:\Program Files (x86)\Java\jdk1.8.0_281\bin\javac.exe" -d bin -cp lib/*; -sourcepath src src/com/rs/*.java
@echo Finished.
pause