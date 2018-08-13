#!/usr/bin/env python
#encoding=utf-8
import daemon
import time
import os
import sys
#a=sys.stdin.readline()
#"import daemon,os;with daemon.DaemonContext():;os.system('ls')"
#"import os;a=1;if a==1:print a;"
with daemon.DaemonContext():
    print "zz"
    os.system("cd /export/log && python -m SimpleHTTPServer 8888 &")
    #os.system("cd /export/workspace/spider-fetcher/spider-fetcher/ && sh ./CrawlNode.sh")