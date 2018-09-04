#!/bin/sh

buildDir=/export/workspace/build
buildFile=spider-fetcher.zip
workspace=/export/workspace/spider-fetcher
unzipDir=spider-fetcher
className=com.jd.cis.spider.node.CrawlNode

sh /export/script/killProcess.sh $className

mkdir -p $workspace

rm -rf $workspace/*
unzip $buildDir/$buildFile -d $workspace

echo ======== start spider-fetcher ========
cd $workspace/$unzipDir && sh CrawlNode.sh
