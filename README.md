Heroku-Spring Data JPA-JerseyRest-Vaadin
=======================

Project Template for JPARepository + JerseyRest + Vaadin ready for Heroku

More info on each project:

* #### Spring Data JPA: 
<a href="http://blog.springsource.org/2011/02/10/getting-started-with-spring-data-jpa/">SpringSource</a>

* #### Jesey JAX-RS
Library for Restful Web Services
<a href="http://jersey.java.net/">Jersey</a>

* #### Vaadin
The best Java user interface for web applications
<a href="http://www.vaadin.com">Vaadin</a>



How-to:
-------------

### Create an Heroku account

You can register on Heroku for free, just follow [this link](www.heroku.com) and follow Heroku instructions

### Download Heroku Toolbelt

Download and install Heroku Toolbelt (it is in the Heroku website)

### Clone this repository

git-clone this repository to a target [folder] on your system

### Create a new Heroku server

Now we are going to create a new server for the app, go into [folder] and type:

	heroku create

this command should return something like:

	Creating safe-escarpment-6048... done, stack is cedar
	http://safe-escarpment-6048.herokuapp.com/ | git@heroku.com:safe-escarpment-6048.git
	Git remote heroku added

which means heroku created a server with name safe-escarpment-6048, which is a really confusing name, so we change it by using:

	heroku -apps:rename [type_a_new_name]

this command should return something like this:

	Renaming safe-escarpment-6048 to drmillan-javaserver... done
	http://[new_name].herokuapp.com/ | git@heroku.com:[new_name].git

### Create a new PostgreSQL database

Lets add a new database to our heroku server, type in your command line:

	heroku addons:add heroku-postgresql

you should get this result:

	Adding heroku-postgresql on [new_name]… done, v3 (free)
	Attached as HEROKU_POSTGRESQL_ORANGE_URL
	Database has been created and is available
	 ! This database is empty. If upgrading, you can transfer
	 ! data from another database with pgbackups:restore.
	Use heroku addons:docs heroku-postgresql to view documentation.

Ok, at this moment you have a brand new PostgreSQL database on heroku, (a free one, you are limited to 10000 rows).

Now you need the database info from Heroku website, so log in, and check database properties.

### Configure JDBC properties

In the project folder, under src/main/resources, you can edit *jdbc.properties* and set the values you got from Heroku

	jdbc.driverClassName=org.postgresql.Driver
	jdbc.url=jdbc:postgresql://SERVER_POSTGRESQL_ADDRESS:SERVER_POSTGRESQL_PORT/SERVER_POSTGRESQL_DATABASE?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory
	jdbc.username=USER_POSTGRESQL
	jdbc.password=PASSWORD_POSTGRESQL

### Deploy server

You can push the project into heroku by using 

	git push heroku master


### Test Services

And now you can load the website with the url:

	http://[new_name].heroku/rest/test/getAll

and get the following results:

	[{"id":1,"testName":"ElementName","testDescription":"ElementDescription"}]

### Test Vaadin

You can also load:

	http://[new_name].herokuapp.com/

and you will see a test Vaadin window (also configured in this project template)


## That´s All

And that´s all, now you can play with Vaadin , JPA database connections, JAX-RS rest services


## Test the real thing

You can test this project for real with the following data:

[Vaadin example window test](http://drmillan-javaserver.herokuapp.com)

[Jersey Restful response test](http://drmillan-javaserver.herokuapp.com/rest/test/getAll)

Thanks all!
Daniel Rodríguez.
