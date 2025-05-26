@echo off

RD /S /Q "C:\kafka\logs"
RD /S /Q "C:\klogs"
RD /S /Q "C:\tmp"

cd C:\Users\fherr\OneDrive\Documentos\Coding\pgsql

start cmd.exe /k PostgreSQL-Start.bat

cd Users/juangordo/Applications

start cmd.exe /k bin\windows\zookeeper-server-start.bat /Users/juangordo/Applications/kafka-3.9.1-src/config/zookeeper.properties

timeout 5

start cmd.exe /k "/Users/juangordo/Applications/kafka-3.9.1-src/bin/windows/kafka-server-start.bat  /Users/juangordo/Applications/kafka-3.9.1-src/config/server.properties"
