![Company Logo](https://revature.com/wp-content/themes/revature/imgs/logo-white-color.png "Revature")
# Gift-Registry--P1

## Project Description

This project functions as a gift registry. The program has two SQL tables, one named "items" filled with the requested gifts and one named "cart" which is empty. 
Both tables are being displayed to the user in the browser. There is also a form being displayed which the user can enter the name and number of an item and it will be removed from the "items"
table and added to the cart table.
## Technologies Used

* Java 11
* HTML
* CSS
* JavaScript
* Tomcat
* PostgreSQL
* Docker
* Gradle
* Spring


## Features

List of features ready and TODOs for future development
* SQL Tables with entries
* Functionality to get, add and remove entries
* Http communication
* Duplicate entry protection

To-do list:
* Self updating numbering 
* Images of items


## Getting Started
   For Windows
```bash
$ cd C:/User/user
$ mkdir Gift-Registry
$ cd C:/Users/user/Gift-Registry
$ git init
$ git clone https://github.com/yung-thane/p1-musica
$ cd C:/Users/user/Gift-Registry/p1-musica
$ docker-compose build
$ docker-compose up
```
For Linux
```bash
$ cd /home/user/
$ mkdir Gift-Registry
$ cd home/user/Gift-Registry
$ git init
$ git clone https://github.com/yung-thane/p1-musica
$ cd home/user/Gift-Registry/p1-musica
$ docker-compose build
$ docker-compose up
```
You may now open your browser and access your localhost:8080 to view the program. 


## Usage

Upon accessing the webpage you will see two divisions and an entry form. On the left you will see a list with the available items in the gift registry you can commit to.
In the entry form you can type in the name and number of the item you wish to commit to and click the button, the entry will be added to the empty list in the division
on the right and removed from the list on the left.


## License

This project uses the following license: [<license_name>](<link>).
