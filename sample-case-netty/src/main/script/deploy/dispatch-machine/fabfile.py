#!/usr/bin/env python
#encoding=utf-8
import os.path
import os
import time
cwd=os.getcwd()
print cwd
pyc=os.path.join(cwd,"fabfile.pyc")
if os.path.exists(pyc):
    os.unlink(pyc)
    print "yo yo"
    print "fabfile.pyc file found and deleted"


import socket
original_timeout = socket.getdefaulttimeout()
new_timeout = 80
socket.setdefaulttimeout(new_timeout)

from fabric.api import *

#env.hosts=["root@183.203.8.19:12580","root@183.203.8.20:12580"]
#env.hosts=["cis@10.10.243.179"]
#env.hosts=["root@10.10.224.98"]

def hosts(ips="100#132#210#213#70#103#135"):
    dict={
#        "19":"root@183.203.8.19:12580",
#        "20":"root@183.203.8.20:12580",
#        "21":"root@183.203.8.21:12580",
#        "23":"183.203.8.23:12580",
#        "24":"183.203.8.24:12580",
#        "25":"183.203.8.25:12580",
#        "22":"183.203.8.22:12580",
        "100":"root@10.10.21.100",
        "132":"root@10.10.21.132",
        "210":"root@10.10.21.210",
        "213":"root@10.10.21.213",
        "70":"root@10.10.21.70",
        "76":"root@10.10.21.76",
        "87":"root@183.60.7.87",
        "88":"root@183.60.7.88",
        "96":"root@10.10.224.96",
        "103":"root@10.10.21.103",
        "135":"root@10.10.21.135",
        "138":"root@10.10.21.138",
        "204":"root@10.10.21.204",
        "207":"root@10.10.21.207",
        "216":"root@10.10.21.216", 
        "106":"root@10.10.21.106",
        "141":"root@10.10.21.141",
        "144":"root@10.10.21.144",
        "146":"root@10.10.21.146", 
        "147":"root@10.10.21.147", 
        "198":"root@10.10.21.198", 
        "sz68":"root@113.105.80.68",
        "sz69":"root@113.105.80.69",
        "sz70":"root@113.105.80.70",
        "sz71":"root@113.105.80.71",
        "sz72":"root@113.105.80.72",
        "sz73":"root@113.105.80.73",
        "sz74":"root@113.105.80.74",
        "sz75":"root@113.105.80.75"
        }
    ips=[ip.strip() for ip in ips.split("#")]
    print ips    
    li=[dict[ip] for ip in ips if ip in dict.keys()]
    print "="*20
    print li
    env.hosts.extend(li)
        

def dispatch():
    run("if [ ! -d /export/workspace/build/ ];then mkdir -p /export/workspace/build/ ;fi;cd /export/workspace/build/ && if [ -f spider-fetcher.zip ];then mv spider-fetcher.zip spider-fetcher.zip.bak;fi")
    put("/root/build/spider-fetcher.zip","/export/workspace/build/")    
    #run("python /export/script/mydaemon.py && echo 'finished'")
    
    
    #run("cd /export/script/ && sh ./restartCrawl.sh && echo 'finished'")
    
def kill():
    c="ps -ef|grep com.jd.cis.spider.node.CrawlNode|grep -v grep|awk '{print $2}'|xargs kill -9"
    c="""a=`ps -ef|grep com.jd.cis.spider.node.CrawlNode|grep -v grep|awk '{print $2}'`;if [ -n "$a" ];then echo "original process is $a be killed";kill -9 $a;fi"""
    print "="*20
    print c
    run(c)


def start():
    run("python /export/script/mydaemon.py && echo 'finished'")
    c="""sleep 6;a=`ps -ef|grep com.jd.cis.spider.node.CrawlNode|grep -v grep|awk '{print $2}'`;echo "start process is $a";"""
    run(c)

def rollback():
    run("cd /export/workspace/build/ && if [ -f spider-fetcher.zip.bak ];then mv spider-fetcher.zip.bak spider-fetcher.zip;fi")
    run("python /export/script/mydaemon.py && echo 'finished'")
    
def dev_dispatch_file(t,d='/tmp'):
    if (os.path.exists(t) and os.path.isfile(t) and os.path.isdir(t)==False):
        if t.startswith(d):
            put(t,t)
        else:
            put(t,os.path.join(d,os.path.basename(t)))
            
    else:
        print "%s is not exits"%t
        
def logserver_start():
    "start log server at http://183.203.8.xx:8888"
    #run("pidof python|xargs kill -9")
    c="""
    a=`pidof python`;if [[ "$a" = "" ]];then echo $a empty ;else echo $a not empty ; kill -9 $a ; sleep 5 ; fi
    """
    run(c)
    #time.sleep(5)
    run("python /export/script/mydaemon2.py && echo 'finished'")
    
def sync_date():
    run("service iptables stop;sleep 2;rdate -s rdate.darkorb.net;rdate -s stdtime.gov.hk ")    