![TigerZone - Group N](https://github.com/brandongoldman/TigerZone/blob/master/stylesheets/tigerzone.JPG)
---

## Contributors:
* Adam Schuster [(@adamschuster)](https://github.com/adamschuster)
* Adam Wexler [(@adamwexler01)](https://github.com/adamwexler01)
* Brandon Goldman [(@brandongoldman)](https://github.com/brandongoldman)
* Bradley Lazar [(@lazarbrad)](https://github.com/lazarbrad)
* Alexander Krepacki [(@alexbrucek)](https://github.com/alexbrucek)
* David Weng [(@rorath)](https://github.com/rorath)

---

## Table of Contents

* [Objective](#objective-muscle)
* [Sprint Outlines](#sprint-outlines-runner)
  * [Sprint 1](#sprint-1)
  * [Sprint 2](#sprint-2)
  * [Sprint 3](#sprint-3)
  * [Sprint 4](#sprint-4)
* [Deliverables](#deliverables-email)
* [Getting Started](#getting-started-computer)
  * [Installing](#installing)
  * [Running the Client](#running-the-client)
* [Testing](#testing-pray)
* [Screenshots](#screenshots-smiley_cat)
* [Bugs](#bugs-scream_cat)

--- 

## Objective :muscle:
Our client, Professor Dave Small, presented us with a task of creating an Artificial Intelligence (AI) for a tile-based strategy game. This game, dubbed "TigerZone" is a spinoff of the popular board game "Carcassonne". TigerZone reflects adaptations to Carassonne by including new features such as new scoring features. The full game description and rules can be found on our client's website [here](http://www.cise.ufl.edu/~dts/cen3031/TigerZone%20v2.2.pdf).

The objective of this project was to utilize agile software development skills that were discussed throughout the course. Our team utilized sprints to get specific features working and to build our overall project progress in incremental ways that helped with testing. As explained above, the goal of this project was to create an AI that could play against other teams in the class by connecting to a TCP server. A game was won by scoring points strategically by completing game features.


## Sprint Outlines :runner:
Click each of the sprints below to view the documentation that includes sprint summary and work accomplished by each team member.


* ### [Sprint 1](https://drive.google.com/open?id=0B1yJp_1wTi1bSWQ0eVh5SE5pUFU) 
Timeline: 10/31/16 - 11/7/16


* ### [Sprint 2](https://drive.google.com/open?id=0B1yJp_1wTi1beXZGWkx6Y05xUUk) 
Timeline: 11/8/16 - 11/14/16


* ### [Sprint 3]() 
Timeline: 11/15/16 - 11/21/16


* ### [Sprint 4]() 
Timeline: 11/22/16 - 12/2/16


## Deliverables :email:

### UML Diagram
[View UML](https://github.com/brandongoldman/TigerZone/blob/master/stylesheets/UML.png)

### Hexagonal Architecture
![Hexagonal](https://github.com/brandongoldman/TigerZone/blob/master/stylesheets/Hexagonal.png)

### Story Tracking
For this project, our team utilized PivotalTracker to track story progress and issues. Pivotal Tracker is a collaborative & lightweight project management tool developed by Pivotal Labs, a company whose expertise in agile development is widely known. Our PivotalTracker is available [here](https://www.pivotaltracker.com/n/projects/1914531).

## Getting Started :computer:
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. For project documentation, including rules and server protocals, please view the documents in the ``` Related Docs``` folder.

### Installing

Clone the repository locally using a BASH terminal.

```
git clone https://www.github.com/brandongoldman/TigerZone
```

To compile TigerZone, navigate to the /src folder and compile ```TigerClient.java```.

```
javac TigerClient.java
```

### Running the Client

To run TigerZone on a TCP server, confirm that a server is running. You will need to use the following arguments in your BASH terminal: ```IP_ADDRESS```, ```PORT```, ```TOURNAMENT_PASSWORD```, ```USERNAME```, and ```PASSWORD```.

To connect to the tournament server, you need to login with the following command in a BASH terminal.

```
java TigerClient [IP_ADDRESS] [PORT] [TOURNAMENT_PASSWORD] [USERNAME] [PASSWORD]
```

If login is successful, you will receive the following message.

```
THIS IS SPARTA!
```

If desired, you may run the server on your local machine. Instructions and source code for this can be found [here](https://github.com/chausen/TigerZoneServer).

## Testing :pray:
All testing files, both acceptance and JUnit, are located in the ```Tests```  folder.

To compile the testing suite, navigate to the /Tests folder and compile ```GameTest.java```.

```
javac GameTest.java
```

To run the test, run ```GameTest```.

```
java GameTest
```

To run the JUnit tests through Eclipse, load in the following files: 
```
BestMoveTest.java
PlacementTest.java
testBusiness.java
testTileReads.java
```

Run each individual file by pressing the start button in Eclipse. More information about how to run JUnit tests can be found [here](http://www.eclipseonetips.com/2014/06/16/run-a-single-junit-test-method-in-eclipse/)

## Screenshots :smiley_cat:

### GUI showing placed tiles
  ![GUI](https://github.com/brandongoldman/TigerZone/blob/master/stylesheets/GUI.png)

### Connecting successfully to the TCP Server
  ![Successful Connection](https://github.com/brandongoldman/TigerZone/blob/master/stylesheets/successful_connect.png)

### Completed Game
  ![Completed Game](https://github.com/brandongoldman/TigerZone/blob/master/stylesheets/completed_game.png)

## Bugs :scream_cat:
- [ ] Game disconnects if other player forfeits both games
- [ ] Game ends if three players are waiting for same game
