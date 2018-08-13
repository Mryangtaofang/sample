#!/bin/sh 

keyword=com.jd.cis.spider.node.CrawlNode
pid=`ps aux | grep $keyword | grep -v grep | grep -v killProcess | awk '{print $2}'` 
echo "pid=$pid"
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