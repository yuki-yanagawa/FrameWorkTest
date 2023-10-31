@echo off
echo %1
jcmd %1 GC.heap_dump performanceCheck\heapDumpCash