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

Notes
-------
1. This project is just providing a prototype for Multi-factor Anthentication.  
Actually the basic idea should be more important than this prototype.  
2. This project can be developed further more.
3. Any questions, welcome to contact me via mndarren@gmail.com
