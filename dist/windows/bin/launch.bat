@echo off
set JLINK_VM_OPTIONS=
set DIR=%~dp0
"%DIR%\java" %JLINK_VM_OPTIONS% -m baardsen.twentyone/baardsen.twentyone.main.TwentyOne %*
