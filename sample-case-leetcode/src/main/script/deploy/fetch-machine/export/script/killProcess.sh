#!/bin/sh

keyword=$1
pid=`ps aux | grep $keyword | grep -v grep | grep -v killProcess | awk '{print $2}'`

if [ -n "$pid" ]
then
{
        echo ======== process[$keyword] found, pid[$pid] ========
        kill -9 $pid
        echo ======== process[$pid] killed ========
        sleep 2
}
else
        echo ======== no process[$keyword] found ========
fi