#!/usr/bin/env python
#encoding=utf-8
import os.path
import os
cwd=os.getcwd()
print cwd
pyc=os.path.join(cwd,"fabfile.pyc")
if os.path.exists(pyc):
    os.unlink(pyc)
    print "yo yo"
    print "fabfile.pyc file found and deleted"

from fabric.api import *
ips="100#132#210#213#70#103#135#204#207#138#216#76#87#141#144#146#147#106#198#sz68#sz69#sz70#sz71#sz72#sz73#sz74#sz75"

env.hosts=["root@10.10.224.98:22"]
def package():
    "build production package from svn use maven"
    local("cd /home/cis/source && sh ./build-production.sh")

def upload():
    "upload spider-fetcher.zip to dispatch machine"
    #local("cd /home/cis/source/build/production/ && scp spider-fetcher.zip root@10.10.224.98:/root/build/")
    put("/home/cis/source/build/production/spider-fetcher.zip","/root/build/")

def dispatch(ips=ips):
    "invoke example:fab dispatch:19 or fab dispatch:19#20#30"
    c="cd /root/build/ && fab hosts:%s dispatch"%(ips)
    print "="*20
    print c
    run(c)
    #run("cd /root/build/ && fab hosts:ips=%s dispatch"%ips)

def kill(ips=ips):
    "kill:19#20 default kill all servers"
    c="cd /root/build/ && fab hosts:%s kill"%(ips)
    print "="*20
    print c
    run(c)


def start(ips=ips):
    "start crawler,default start all servers"
    c="cd /root/build/ && fab hosts:%s start"%(ips)
    print "="*20
    print c
    run(c)


def rollback(ips):
    """
    invoke example:fab rollback:19 or fab rollback:19#20#30
    """
    run("cd /root/build/ && fab hosts:%s rollback"%(ips))

def all(ips=ips):
    """
    all or all:100#132 equals fab package upload dispatch:100#132 start:100#132
    """
    package()
    upload()
    dispatch(ips)
    start(ips)

def logserver_start(ips):
    "start log server at http://183.203.8.xx:8888"
    run("cd /root/build/ && fab hosts:%s logserver_start"%(ips))

#def restart():
#    local("cd /home/cis/source && sh ./restartSpiderFetcher.sh")
    
def dev_dispatch_file(t,d="/tmp",ips=ips):
    """
    in dev env dispatch file to fetch machines
    fab dev_dispatch_file:t.txt,/export,19
    fab dev_dispatch_file:/home/mlzboy/t.txt
    """
    if (os.path.exists(t) and os.path.isfile(t) and os.path.isdir(t)==False):
        put(t,t)
        run("cd /root/build/ && fab hosts:%s dev_dispatch_file:%s,%s"%(ips,t,d))
    else:
        print "%s is not exits"%t

def show_default_ips():
    print ips    

def sync_date(ips=ips):
    "sync_date:ip1#ip2 or sync_date"
    run("cd /root/build/ && fab hosts:%s sync_date"%(ips))
