heroku-jpa2-rest-vaadin
=======================

Project Template for JPA2 , REST &amp; VAADIN server on Heroku

How-to:
-------------

### Create an Heroku account

You can register on Heroku for free, just follow <a href="www.heroku.com">this link</a> and follow Heroku instructions

### Download Heroku Toolbelt

Download and install Heroku Toolbelt (it is in the Heroku website)

### Clone this repository

git-clone this repository to a target [folder] on your system

### Create a new Heroku server

Now we are going to create a new server for the app, go into [folder] and type:

*heroku create*

this command should return something like:

*Creating safe-escarpment-6048... done, stack is cedar
http://safe-escarpment-6048.herokuapp.com/ | git@heroku.com:safe-escarpment-6048.git<br/>*
*Git remote heroku added<br/>*

which means heroku created a server with name safe-escarpment-6048, which is a really confusing name, so we change it by using:

*heroku -apps:rename [type_a_new_name]*

this command should return something like this:

*Renaming safe-escarpment-6048 to drmillan-javaserver... done
http://[new_name].herokuapp.com/ | git@heroku.com:[new_name].git<br/>*

### Create a new PostgreSQL database

Lets add a new database to our heroku server, type in your command line:

*heroku addons:add heroku-postgresql*

you should get this result:

*Adding heroku-postgresql on [new_name]… done, v3 (free)<br/>
Attached as HEROKU_POSTGRESQL_ORANGE_URL<br/>
Database has been created and is available<br/>
 ! This database is empty. If upgrading, you can transfer<br/>
 ! data from another database with pgbackups:restore.<br/>
Use `heroku addons:docs heroku-postgresql` to view documentation.*

Ok, at this moment you have a brand new PostgreSQL database on heroku, (a free one, you are limited to 10000 rows).

Now you need the database info from Heroku website, so log in, and check database properties.

### Configure JDBC properties

In the project folder, under src/main/resources, you can edit *jdbc.properties* and set the values you got from Heroku

*jdbc.driverClassName=org.postgresql.Driver<br/>
jdbc.url=jdbc:postgresql://SERVER_POSTGRESQL_ADDRESS:5432/SERVER_POSTGRESQL_PASSWORD?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory<br/>
jdbc.username=USER_POSTGRESQL_HEROKU<br/>
jdbc.password=CLAVE_POSTGRESQL_HEROKU<br/>*

### Deploy server

You can push the project into heroku by using 

*git push heroku master*

And now you can load the website with the url:

http://[new_name].heroku
