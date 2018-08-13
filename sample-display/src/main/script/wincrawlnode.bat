::get disk driver£¬ eg£ºC: , D:
@set driver=%~d0
::get current bat dir, eg: D:/programBaseDir/bin/
@set baseDir=%~dp0
::since of our program located in D: , but we execute this bat from C:, so shoud first switch to D:
%driver%
cd %baseDir%
::switch to parent dir of current bat dir, eg: from D:/programBaseDir/bin/ to D:/programBaseDir/
cd ..
@echo %cd%
java -Xmx1024M -cp .;conf/*;lib/* com.jd.cis.spider.node.CrawlNode