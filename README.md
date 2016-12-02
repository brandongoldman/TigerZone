# __TigerZone__
## *CEN3031 - Fall 2016 (Group N)*
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

* [Objective](#objective)
* [Sprint Outline](#sprint-outline)
  * [Sprint 1](#sprint-1)
  * [Sprint 2](#sprint-2)
  * [Sprint 3](#sprint-3)
  * [Sprint 4](#sprint-4)
* [Deliverables](#deliverables)
* [Screenshots](#screenshots)
* [Getting Started](#getting-started)
  * [Installing](#installing)
  * [Running the Client](#running-the-client)
* [Bugs](#bugs)
* [Development Summary](#development-summary)

--- 

## Objective
Our client, Professor Dave Small, presented us with a task of creating an Artificial Intelligence (AI) for a tile-based strategy game. This game, dubbed "TigerZone" is a spinoff of the popular board game "Carcassonne". TigerZone reflects adaptations to Carassonne by including new features such as new scoring features. The full game description and rules can be found on our client's website [here](http://www.cise.ufl.edu/~dts/cen3031/TigerZone%20v2.2.pdf).

The objective of this project was to utilize agile software development skills that were discussed throughout the course. Our team utilized sprints to get specific features working and to build our overall project progress in incremental ways that helped with testing. As explained above, the goal of this project was to create an AI that could play against other teams in the class by connecting to a TCP server. A game was won by scoring points strategically by completing game features.


## Sprint Outline
Click each of the sprints below to view the documentation that includes sprint summary and work accomplished by each team member.


* ### [Sprint 1](https://drive.google.com/open?id=0B1yJp_1wTi1bSWQ0eVh5SE5pUFU) 
Timeline: 10/31/16 - 11/7/16


* ### [Sprint 2]() 
Timeline: 11/8/16 - 11/14/16


* ### [Sprint 3]() 
Timeline: 11/15/16 - 11/21/16


* ### [Sprint 4]() 
Timeline: 11/22/16 - 12/2/16


## Deliverables

* ### UML Diagram

* ### Hexagonal Architecture

* ### Story Tracking

* ### Tile Logic
maybe include a scanned picture showing how we made our tiles (edges, corners, ect)


## Screenshots
ideas: GUI, connecting to server


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

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

To connect to the tournament server, you need to login with the following command.

```
java TigerClient [IP_ADDRESS] [PORT] [TOURNAMENT_PASSWORD] [USERNAME] [PASSWORD]
```

If login is successful, you will receive the following string.

```
THIS IS SPARTA!
```

## Bugs
a detailed list of all the known bugs/issues needing to be addressed

## Development Summary
discuss what you learnt while doing this project, what you took away, ect.

* ### Adam Schuster

* ### Adam Wexler

* ### Brandon Goldman

* ### Bradley Lazar

* ### Alexander Krepacki

* ### David Weng
