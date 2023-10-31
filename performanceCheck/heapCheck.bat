@echo off
echo %1
jmap -histo:live %1 > performanceCheck\heapDataCash