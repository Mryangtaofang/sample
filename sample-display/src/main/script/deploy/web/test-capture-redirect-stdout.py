#!/usr/bin/env python
#encoding=utf-8
#from subprocess import Popen, PIPE
import os
import threading
import subprocess
import time
import psutil
inUse=False
stdout=[]

class MyClass(threading.Thread):
    def __init__(self,cmd):
        global inUse
        inUse=True
        self.cmd=cmd
        threading.Thread.__init__(self)

    def run(self):
        """from http://blog.kagesenshi.org/2008/02/teeing-python-subprocesspopen-output.html
        """
        global inUse,stdout
        stdout=[]
        print inUse
        os.chdir("/home/mlzboy/work/spider-java/spider-fetcher/src/main/script/deploy/local-machine")
        self.p = subprocess.Popen(self.cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
        while True:
            line = self.p.stdout.readline()
            stdout.append(line)
            print line,
            if line == '' and self.p.poll() != None:
                break
        inUse=False
        print inUse
        return ''.join(stdout)
        
    def kill(self):
        try:
            self.p.kill()
        except Exception,e:
            print e
        pidfile="/var/run/fabfile.pid"
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
                    a=psutil.Process(self.p.pid)
                    #检测系统内存使用情况过了就杀死
                    a.kill()
                    print "==============="
                    print pid
                    print self.p.pid
                    time.sleep(1)
                    os.system("kill -9 %s"%pid)        
                except psutil.NoSuchProcess,e:
                    print e
                    print "no such process "

#myclass=MyClass("sleep 10;echo 'hhh';")
myclass=MyClass("fab all")
#print a.run()
myclass.start()
time.sleep(20)
print "zz"
myclass.kill()
