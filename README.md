# comp303
Coding assignments from Software Design course.  

asg1:  
A high school graduation party only permits valid high school students over the age of 15. They ask you to build a Java program that has two important classes: Student and Queue. The Student class contains the student’s name, age and school ID card. A valid student is 15 or over and their 5-digit ID begins with the number 22, as in 22765 and 22156. The Queue class is a standard queue implementation that provides the following functionality: enqueues, dequeues, and stores only Student objects. The graduation party organizers do not know how many people will come to the party. The program does not use files. The purpose of the queue is to manage the lineup of people as they wait to enter the gym, where the party will be held.
<br />

asg2: Airplane Booking Simulator

asg3_4:  
Simulators are programs that attempt to duplicate real life in software. For this question, you will use well-formed techniques and design patterns to construct a portion of a simulator. As in question 1, you will need to provide a readme.txt file making explicit the well-formed techniques and the design patterns you used to answer this question.  
<br />

One of the most fundamental parts of a simulator is the World and those items that populate the World. Often a simple way to represent a world is using a 2D array. This array is imagined to be the ground. Items populate this array. Some items are immoveable, others are moveable, and others move autonomously.
<br />

We want to maximize good programming techniques to implement this simulator. You can use class, interface, and abstract when creating your objects. I am assuming you will use everything you have learned when designing and choosing your programming constructs. This includes inheritance, controls, and design patterns.
<br />

You will need to do the following:
 World: It contains a 2D array that can store one Moveable, Immoveable, and Autonomous object per cell of the array. This World class has a public method called “void step()” that iterates once changing the state of the world by updating the position of all the Autonomous and Moveable objects. It also calls the method display() (see below). The World also as a “void add(item)” that is used to create the world by adding items to the array. The World constructor defines the size of the array. A third method “void display()” prints the array to the screen by calling the “char getToken()” method of the item. This permits the user to see state change. Display the world in an way that is easy to view.

<br />

 Immovable: An object with a string name describing what it is. It also has a character variable that stores a symbol that represents the item. The method “char getToken()” returns the character symbol. If you want a greater challenge, you can replace the symbol by a graphic picture.
<br />

 Moveable: is implemented exactly as Immovable, however it can be moved by one cell position if bumped into by an Autonomous object. It is displaced in the same direction as it was bumped.
<br />

 Autonomous: is implemented exactly like Moveable (bumped by a Moveable object or another Autonomous object causes it to shift one cell in the direction it was bumped). This object has a “void step()” method that randomly picks a direction and updates the symbol’s array location to the new location by one cell.

<br />

 Construct a main method that builds a world and then runs a simulation for 100 iterations.
