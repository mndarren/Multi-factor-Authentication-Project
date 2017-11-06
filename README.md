# Multi-factor Authentication Project
======================================<br/><br/>
Description
--------
This project will simulate a Multi-factor Authentication algorithm via a  web application. This prototype is developed on IDE Eclipse. Running it on Tomcat 8 Web server.

Algorithm/Policy
----------------
We built a Risk Engine which will be responsible for calculating risk score  for each user logging in. The policy for this algorithm is based on  4 big factors: Location, Device, Weekday and Time. Different login facors will get different risk score. Differnt risk score will lead to user providing different user information to confirm correct user login.
[Algorithm](https://github.com/mndarren/Multi-factor-Authentication/blob/master/documents/algorithmPolicy.pdf)<br/>
*Sequence Dia*<br/>
![alt Sequence Diagram](https://github.com/mndarren/Multi-factor-Authentication/blob/master/documents/SequenceDia.PNG)<br/>
*Risk Engine*<br/>
![alt Risk Engine](https://github.com/mndarren/Multi-factor-Authentication/blob/master/documents/RiskEngine.PNG)

Documentation
--------------
In the documents folder, we provide Use Case diagram, Sequence Diagram, ERdiagram and Algorithm files.

Others
---------
In the others folder, there are MySQL code file, 3rd party library files (from IP address to location). Those are very important parts besides the src code.

Example
--------
1. Original page<br/>
![alt before_test](https://github.com/mndarren/Multi-factor-Authentication/blob/master/others/before_test.PNG)
2. Test step 1<br/>
![alt test_step1](https://github.com/mndarren/Multi-factor-Authentication/blob/master/others/test_step1.PNG)
3. Test step 2, because this login only got 70/100, based on the policy, the user had to provide secret question answer. Since the user provided correct answer and logged in successfully, this login information was inserted in the login DB.<br/>

![alt test_step2](https://github.com/mndarren/Multi-factor-Authentication/blob/master/others/test_step2.PNG)

Notes
-------
1. This project is just providing a prototype for Multi-factor Anthentication.  
Actually the basic idea should be more important than this prototype.  
2. This project can be developed further more. For example, all queries in the java code can be moved in the DB, which makes Web Application more robust.
3. In Policcy.java file, we should change the path of the GEODATA which is used to convert IP to location. The 3 GEODATA library files locates in others folder.
4. Any questions, welcome to contact me via mndarren@gmail.com<br/>
***Have Fun***