Simon Kuang, 004066363, ef1786@wayne.edu

Adam Molner, ge8766, ge8766@wayne.edu

Contributions:

ER diagram - Both

Button Initialization and table creation commands - Simon

Assign PC members to a paper ID, front end and backend - Adam 


The instructions for compiling and running the program are listed below. 

For more detailed instructions with pictures, please refer the following document 
which has been included in the project .war file

Instructions for operation of csc4710 project, Simon Kuang and Adam Molner.docx


Instructions on how to create the new user named john for mysql

Login into mysql shell as the root user.
Now create a new username called john with the password, pass1234, by typing in the following command:
GRANT ALL PRIVILEGES ON *.* TO 'john'@'localhost' IDENTIFIED BY 'pass1234';
Exit out of the mysql shell.
Login into the mysql shell with the newly created user. 
mysql -u john -p
Pass1234



Steps to run the website:

Before you do anything, download the file csc4710_Kuang_Molner_part01.war and place the file into your computer’s eclipse workspace

Boot up eclipse kepler. 
Import the War file csc4710project
File -> Import -> (type in war) and select it and then click next
In the WAR Import menu, click browse and locate the war file and select it. 	
Click next and include all of the java libraries. Now click finish
Refer to the “Removing and readding the java library” instructions to remove the errors before proceeding to the next step. 
Now locate the file main.jsp through the project explorer
csc4710project -> WebContent ->jsps -> main.jsp
Right click main.jsp and select Run As -> 1 Run on Server
Under Run On Server pop up, click next and add csc4710project to the configured list. Click finish.
IMPORTANT: copy the url and paste it on another web browser. Do not use the default browser, because the styling and javascript will not work.


Removing and re-adding the java jre library:

Sometimes, when importing a java dynamic web project, a bug appears. In most cases, the project will not compile because of a glitch involving the jre library. After you first import our project into eclipse, that you first do the following:

Right click on the project and go to properties


Make Sure that ‘Java Build Path’ from the left hand column is selected. Then, in the window where the cursor appears, click on ‘JRE System Library’.


Next, click remove.


Next, click on add library


Finally, once inside the add library, window, select the JRE system library. 


At this point, any potential errors should have disappeared and the project is ready to run. 



 

Steps to initialize the database:

On the main page of the website, click the button called Initalize DB
The button execute the code that willl create the database called sampledb and the button will also create the tables: author, pcmember, paper, review, and written. The tables will be populated by the appropriate tuples. 
After initialization DB is clicked, the webpage will now display the tables and the tuples under each of them. 


Assigning PC members to papers and how it works:


In order to test the assigning PC members to a paper, simply click the link up top ‘Assign three reviewers to a paper. It will take you to the following screen. 


Once here, you can select up to three PC members to assign to one paper:


After submitting the form, any constraints are handled on the back end. If a paper already has too many members assigned to it or if a pc member is already assigned to a paper, the results of the input will be outputted on the next page:


From the above page, you will be able to assign at most 3 pc members to 1 paper at a time. 


***UPDATE: since the submission of PART 1, the assign 3 PC members has been updated.*** 


Now, when you see the list of PC members and the list of papers, these lists are ONLY populated by PC members who are eligible to be assigned to AT LEAST 1 paper, and the user will now only see papers that can be assigned to AT LEAST 1 pc member. This way, at least 1 PC member that has been selected will be assigned to the paper that has been selected.


Inserting, Updating, and Deleting a field:

In order to do any of the upbove, select modify from the navigation bar in the top left.


 


Simply click on the function that you would like to carry out whether it be insert, delete, or update. This will reveal more fields. 


After the fields become visible, click on the attribute that you would like to modify. This will reveal even more fields. 


For inserting a tupple:

When inserting, you will be presented with a field, or in the case of insert paper, both a paper field and an author field. Fill in the values for each field and then hit submit. This will add a new tupple to whichever database is selected. For insert author, multiple authors can be added. Click ‘add author’ to add another author field. 


For Deleting a tupple:

When delting, simply select a primary key from the drop down menu and press ‘submit’


For Updating a tupple:

Simply select a primary key from the drop downmenu corresponding to the tupple you would like to modify. Additionally, you will presented with a list of fields. For any field that you wish to update, write somthing new in that field and hit submit. Any field that is left blank, will not be updated. ‘


The methodology for Inserting, Deleting, and Updating works the same for Paper, PC Member, and Review. 


