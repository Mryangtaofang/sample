#!/usr/bin/env python
#encoding=utf-8
import daemon
import time
import os
#"import daemon,os;with daemon.DaemonContext():;os.system('ls')"
#"import os;a=1;if a==1:print a;"
with daemon.DaemonContext():
    os.system("sh /export/script/restartCrawl.sh")
    #os.system("cd /export/workspace/spider-fetcher/spider-fetcher/ && sh ./CrawlNode.sh")