baseDir=`dirname "$0"`
cd $baseDir
cd ..
echo `pwd`
nohup java -Xmx3072M -cp .:conf/*:lib/* com.jd.cis.spider.node.CrawlNode 1>/dev/null 2>&1 &