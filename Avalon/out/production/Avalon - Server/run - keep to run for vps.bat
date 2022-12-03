@echo off
title Server
echo Launching Server...
"C:/Program Files/Java/jre1.8.0_271/bin/java.exe" -Xmx850m -cp bin;lib/*; com.rs.Launcher false false 43594 false
pause