# OnlineTicketBooking
This is for an application like Book My show

# OnlineTicketBooking
This is for an application like Book My show

Below are the tables which we have used in this application'
1- User - A user has to be added for the Booking. Primary key is the Mobile Number. 
2- Movie - A movie has to added for any booking. Movie_Id(Long) is the key. 
3- Theater - This table has all details about the theater. and Theater_Id is the key. 
4- Shows - This table contains details about the shows. Every show has Theater_Id, Movie_Id, Show starts at, Show Ends at, Show_date
5- Seats - Every seat is as per the show. Seat is a combination of row Number and Seat Number
6- Booking - Once any user try to book any seats, we will create an entry about that in Booking table. 

Steps to execute this project
1-  Download this project into the system and open it in any IDE.
2-  DB has to be created in the MySQL and name has to be updated in the application.properties. you can use H2 DB as well but you need to uncomment configurations in Application.properties for that. 
3-  do MVN clean and install so that all the dependencies are imported. 
4-  Start the application as a Spring Boot application.
5  Auto-update property is true in application.properties which means all the tables will be created automatically. 

Requirements for Booking

1- A user has to be present in the system. Use the below service to do that
URL - localhost:8080/saveUser  -- Method -- Post
{
	"mobileNumber":"1234567891",
	"emailId":"abc@email.com",
	"firstName":"fName",
	"lastName":"lName"
}

2- A Theater has to be present in the system. To add Theater use below service

URL - localhost:8080/theaters  -- Method - Post

  {
	"address":"Pacific Mall",
	"city":"Gurgaon",
	"state":"Haryana",
	"country":"India",
	"name":"Gurgaon3"
}

3- A Movie has to be present in the system. To add Movie, use below service
URL - localhost:8080/movie -- Method -- Post
{
	"movieName":"RedNotice",
	"type":"Action",
	"language":"English",
	"rating":"5",
	"release_year":"2021",
	"activate":true
}

4- Shows has to be there in the system to book any seats. To add shows, use below service
URL - localhost:8080/shows/ -- Method -- POST

{
	"theaterId": "1", // Theater ID should be present in Theater table
	"movieId": "5", //Movie ID should be present in Movie table
	"showStartsAt": "12:00PM",
	"showDate": "2021-12-05",
	"endsAt": "3:00PM"
}

5- TO Book Seats
URL - localhost:8080/bookseats/8800924224  -- Post 
[{
	"movieId":"1",
	"theaterId":"1",
	"showId":"1",
	"rowNo":"A",
	"seatNumber":6,
	"seatType":"EXECUTIVE",
	"price":100
}]

6- To get Movies by City & Movie Name 

URL - localhost:8080/getShowsByCityAndMovieName/Gurgaon/RedNotice/2021-12-06
Method - GET 
Parameters - {City}/{MovieName}/{Date}



