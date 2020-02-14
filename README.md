# We-Shop-E-Commerce-Android-Application :computer:

An Android Application that allows users to shop for products in different categories, users can register an account and login. The data will get saved to a Firebase Database and then the users can login with the credentials from Firebase.

# Record of Progress :closed_book:

Tuesday 14th January 2020: On this day I started to write the project proposal which describes what this Android Application will be about. 

I also made a start on writing about the different kinds of Feasibility studies which explains in different sub-sections whether or not the project is feasible, for example whether this project is legally feasible or economically feasible.
Implemented a Gantt Chart that shows how much time each task will take to develop.

Wednesday 15th January 2020: I created a survey that contains multiple questions regarding my project, for example, “Would you like to see a drop-down menu for the quantities of the products?”.

On this day I also started to compile a list of functional requirements for the application that I have extracted from the results of my survey.

Finished gathering data from end-users by using Survey Monkey.
Thursday 16th January 2020: On this day I started to write the Requirements Specification for the application based on my data gathering results (survey). 

I have also created an Agile Sprint Board that shows how much time I have allocated to each requirement. A sprint is usually 1 week; however, each task will take less time than that.

On this day I have also created a Kanban Board (product backlog) that lists all of the tasks that will be worked on in order.
Friday 17th January 2020: Made a start on designing the user interface for the Homepage (main activity) and the registration page of the application. On this day I also finished implementing the product backlog and the sprints which outlines how much time each set of requirements will take to complete.

Saturday 18h January 2020: On this day I started to design the user interfaces for the Sports & Outdoors activity, Tech, Clothing & DIY pages. Screenshots of the user interfaces are displayed in the design section of the document. 

I also started to design the class diagrams for the application using draw.io. The class diagrams show the classes that will be used in the application and also the links that they have along with the methods and private variables that the classes will contain to make the application functional.

Monday 20th January 2020: Continued to work on the UML class diagrams.

Wednesday 22nd January 2020: Went back to the homepage design and payment activity design because I forgot to add a couple of extra features, hence I had to redesign the UI and add those features, also therefore the requirements changed.

Tuesday 28th January 2020: Finished designing the application. I made a start on implementing the register activity and making it look nice. I also completed validating the fields, for example the Username cannot be left blank and must contain numbers and no regex characters.

I also made a start on writing some Junit tests that tests if the activities load correctly. I also supplied some sample tests for validating the inputs, however I don’t get the results required. I will ask for some help regarding this.

# Project Proposal - Version 1.0 :blue_book:

I am planning on designing and implementing an e-Commerce shopping application in Android that will allow end-users to register, login to the app and browse for products in different category sections. 

This application will currently have 4 pages of product categories that users can browse through and they are Sports & Outdoors, Tech, Clothing and DIY and once the users have chosen the products that they wish to purchase from category X, they can add it to their basket and proceed to the checkout. Customers can view the products added to the basket.

Before browsing for products, users are required to register and login into their account.

The main scope of this application is to allow end-users to shop for products in different categories, very similar to Amazon’s application.

After registration customers will receive a notification saying that they have registered successfully and the data from the registration will get sent to a back-end database. I will be making use of the Firebase Service to store the user’s registration data.

When customers have decided on what they want to purchase they can add the products to their basket and the users can view their basket in the form of a list view.

Users will be presented with a payment form where they are prompted to fill out their payment information and pay for the products chosen. After the payment is complete and processed users will receive an invoice through e-mail.

Each category with products will be shown on different activity pages on the application. For example, on page 1 the sports and outdoors category section will be displayed with the products available in stock etc.

I will be making use of version control using Git in order to track the changes made to the project and to also commit and push new changes when developing the application. In case I lose my work, I can revert back to previous commits to start from that checkpoint.

My initial inspiration for choosing this type of application was from the Amazon application.
I will be also making use of some Agile approaches such as Kanban Boards, Product Backlogs and Sprint Boards.

# Feasibility Study - Version 1.1 :open_file_folder:
In this section I will be writing about the different kinds of feasibility studies that will impact this project. These will determine whether this project is feasible enough to develop or not.

# Legal Feasibility :office:
Under the Copyright Design and Patents Act, all of the images and source code that I will be using throughout this project I will reference in an index at the end of the project.

Under the Data Protection Act all of the registration data will get stored in a secure back-end database using Firebase.

Under the Computer Misuse Act, I will not any malware that will harm and render other people’s computer unavailable.

# Schedule Feasibility :anchor:
The total time taken for each task will be stated in a Gantt Chart and also the start and end dates for that task. The Gantt Chart will also show whether or not that task has been completed or if it’s still pending.

# Economic Feasibility :bank:
I will not be purchasing any software in order to achieve project completion, all of the software and tools that I will be using to develop this project will be free to use. No costs will be incurred.

# Technical Feasibility :carousel_horse:
I will be making use of my MacBook Pro to develop my project. The software that will be used are:

-	Android Studio: Where I will implement the User Interface 

-	Draw.io: Used to design the class diagrams using UML.

-	Zube: Used to implement Kanban Boards & implement the sprints for the project.

-	Firebase: Used to store the registration details of the customers.

-	Adobe XD CC: Used to design the User Interface of the application.

-	Survey Monkey: Used to gather data from end-users by creating a survey of 10 questions.
