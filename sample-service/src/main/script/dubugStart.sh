baseDir=`dirname "$0"`
cd $baseDir
cd ..
echo `pwd`
nohup java -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8998,server=y,suspend=y -Xmx3072M -cp .:conf/*:lib/* com.jd.cis.spider.node.CrawlNode 1>/dev/null 2>&1 &
