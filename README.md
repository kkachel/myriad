Myriad Java Programming Problem
============

Objective:
-----------

Write a program that takes as input from the command line an absolute path, and prints out a list of 

subdirectories and files contained within that path; sorting by size from largest to smallest. The 

size of a subdirectory is the size of all of that subdirectories contents. The program should also 

display the size of the files, and subdirectories in kilobytes. 



Please write your code in a class called DU. 



**NOTE:** Do not list out the complete contents of subdirectories file by file. The only files explicitly 

listed should be those in the top level of the absolute path, and not those found in its 

subdirectories. 



For example, if we have a directory on our computer, c:\test_du that contains the following 

directories and files: 


    C:\test_du\foo\a.dat (100kb) 

    C:\test_du\foo\b.dat (200kb) 

    C:\test_du\foo\another_dir\jim.dat (500kb)

    C:\test_du\bar\ball.jpg (5kb) 

    C:\test_du\bar\sam\sam1.jpg (100kb) 

    C:\test_du\bar\sam\sam2.jpg (300kb) 

    C:\test_du\somefile.dat (700kb) 


Running the command **java DU c:\test_du** should produce the following output: 


    DIR C:\TEST_DU\FOO 800KB 

    FILE C:\TEST_DU\SOMEFILE.DAT 700KB 

    DIR C:\TEST_DU\BAR 405KB
