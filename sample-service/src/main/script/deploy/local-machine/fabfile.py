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

#exit(0)
import psutil
def gen_pid_file():
    a,b=os.path.splitext(os.path.basename(__file__))
    pidfile="/var/run/%s.pid"%a
    if os.path.exists(pidfile):
        pid=-1
        try:
            pid=int(file(pidfile,"r").read())
        except Exception,e:
            print e
            print "not right int covert"
        print pid
        if pid>0:
            try:
                a=psutil.Process(pid)
                print("alreay has running this script")
                exit(1)
            except psutil.NoSuchProcess,e:
                print e
                f=file(pidfile,"w")
                f.write(str(os.getpid()))
                f.close()
                #logging.info("current pid is %s"%os.getpid())
    else:
        f=file(pidfile,"w")
        f.write(str(os.getpid()))
        f.close()
        #logging.info("current pid is %s"%os.getpid())
gen_pid_file()
    
from fabric.api import *
env.hosts=["cis@10.10.243.179:22"]
ips="100#132#210#213#70#103#135#204#207#138#216#76#87#141#144#146#147#106#198#sz68#sz69#sz70#sz71#sz72#sz73#sz74#sz75"
#def dev():
#    env.hosts=["mlzboy@localhost:22"]
#
#def prod():
#    env.hosts=["cis@10.10.243.179:22"]

def package():
    "build production package from svn use maven"
    run("cd /home/cis/source && fab package")

def upload():
    "upload spider-fetcher.zip to dispatch machine"
    #local("cd /home/cis/source/build/production/ && scp spider-fetcher.zip root@10.10.224.98:/root/build/")
    #run("/home/cis/source/build/production/spider-fetcher.zip","/root/build/")
    run("cd /home/cis/source && fab upload")
    
def dispatch(ips=ips):
    "invoke example:fab dispatch:19 or fab dispatch:19#20#30"
    #env.hosts=["cis@10.10.243.104","cis@10.10.243.179"]
    print env.host
    print env.hosts
    print ips
    run("pwd")
    run("cd /home/cis/source && fab dispatch:%s"%ips)

def kill(ips=ips):
    "kill:19#20 default kill all servers"
    run("cd /home/cis/source && fab kill:%s"%ips)

def start(ips=ips):
    "start crawler,default start all servers"
    run("cd /home/cis/source && fab start:%s"%ips)    

def rollback(ips):
    """
    invoke example:fab rollback:19 or fab rollback:19#20#30
    """
    run("cd /home/cis/source && fab rollback:%s"%ips)
    
def all(ips=ips):
    """
    all or all:100#132 equals fab package upload dispatch:100#132 start:100#132
    增加一键部署
    fab all
    fab all:100#132
    fab all:100
    等价于fab package upload dispatch:100#132#210#213#70#103#135#204#207#138#216#76#87#96 start:100#132#210#213#70#103#135#204#207#138#216#76#87#96
    """
    run("cd /home/cis/source && fab all:%s"%ips)

def dev_dispatch_file(filepath,d="/tmp",ips=ips):
    """
    in dev env dispatch file to fetch machines
    fab dev_dispatch_file:t.txt,/export,19
    fab dev_dispatch_file:/home/mlzboy/t.txt
    fab dev_dispatch_file:../fetch-machine/mydaemon2.py,/export/script,19
    """
    print ips
    print filepath
    print d
    if os.path.exists(filepath) and os.path.isfile(filepath) and os.path.isdir(filepath)==False:
        p=os.path.abspath(filepath)
        print "....."
        print p
        print os.path.basename(p)
        t=os.path.join("/tmp",os.path.basename(filepath))
        put(p,t)
        run("cd /home/cis/source && fab dev_dispatch_file:%s,%s,%s"%(t,d,ips))
    else:
        print "file is not correct"
        
def dev_logserver_start(ips=ips):
    "start log server at http://183.203.8.xx:8888"
    run("cd /home/cis/source && fab logserver_start:%s"%ips)
    
def show_default_ips():
    print ips

def sync_date(ips=ips):
    "sync_date:ip1#ip2 or sync_date"
    run("cd /home/cis/source && fab sync_date:%s"%ips)
    