# Twitter CLI Application

## <ins>Introduction

The `Twitter Command Line Interface (CLI) Application` is capable of posting, showing, and deleting a post on Twitter
using the command line. Libraries such as `HTTP` and `OAuth 1.0` were used to send/receive/authenticate requests using
the Twitter Rest APIs. The Jackson library was used to convert JSON objects to Tweet objects. Moreover, Apache Maven was
used to handle project dependencies, testing was done using `JUnit4` and `Mockito`, and the app was deployed using
Docker

__Targeted User:__ The product can be used by anyone who would like to post | show | delete a tweet using command line
interface. It will be especially useful for people who post regularly on Twitter

__Technologies:__
> <span style = "color:green"> Git | Docker | Java SE 8 | Apache Maven | Twitter Rest API </span>

## <ins> Quick Start

Pull image from Docker

`docker pull deelango/twittercli`

Obtain your
<span style = "color:blue"> consumerKey </span> |
<span style = "color:blue"> consumerSecret </span> |
<span style = "color:blue"> accessToken </span> |
<span style = "color:blue"> tokenSecret </span>
from Twitter Developer's Portal

```
docker run --rm \
-e consumerKey= null \
-e consumerSecret= null \
-e accessToken= null \
-e tokenSecret= null \
deelango/twittercli post|show|delete [options]
```

#### Post

```
docker run --rm \
-e consumerKey= null \
-e consumerSecret= null \
-e accessToken= null \
-e tokenSecret= null \
deelango/twittercli post "message" latitude:longitude
```

#### Show

```
docker run --rm \
-e consumerKey= null \
-e consumerSecret= null \
-e accessToken= null \
-e tokenSecret= null \
deelango/twittercli show id_of_tweet
```

#### Delete

```
docker run --rm \
-e consumerKey= null \
-e consumerSecret= null \
-e accessToken= null \
-e tokenSecret= null \
deelango/twittercli delete id_of_tweet
```

## <ins> UML Diagram

![my image](./assets/twittercli.png)

#### Description of the components

The application consists of four major components:

- `TwitterCLIApp`:
  When the user communicates with the command line, this script is responsible for calling the Post | Show | Delete
  methods in
  `TwitterController`. In addition, this script is also responsible for creating an instance of`TwitterController`
  , `TwitterDao`, `TwitterHttpHelper`, and`TwitterService`


- `TwitterController`:
  The data that is sent from the `TwitterCLIApp` will be validated to see if its in an acceptable format before sending
  it over to `TwitterService` where it will be parsed for logic errors. The final result will be then sent back
  to `TwitterCLIApp` where it will be outputted to the user


- `TwitterService`:
  This class is responsible for analyzing the data with respect to the Business logic that was established before
  development. Only if the data passes the validation, it is sent to
  `TwitterDao` where it will communicate with Twitter


- `TwitterDao`:
  The data access layer is responsible for handling the models to initiate communication with the Twitter's servers. It
  does this with the help of its `TwitterHttpHelper` class as well as by constructing the URI for request calls

## <ins> Model

The following diagram will illustrate the `Tweet` model and its dependencies
[`Coordinates`, `Entities`, `Hashtags`, and `UserMentions`]

![my image1](./assets/twitterclimodel.png)

## <ins> Spring

The biggest challenge in the project had to do with dependency management. With each class having its own dependencies,
it can become rather inefficient to create each component and set up their dependencies manually.

I opted to go with the Spring Framework so that all the dependencies can be managed by the concept of Inversion of
Control (IOC). The main classes were
annotated `@Component` <span style = "color:blue">*[TwitterHttpHelper] [TwitterCLIApp]*,
`@Controller` *[TwitterController]*,`@Service` *[TwitterService]*,
</span> and <span style = "color:blue">`@Repository` *[TwitterDao]* </span> as well as placing `@Autowire` before all
the constructors to indicate that the class is a `Bean` and to notify the IoC container to inject any dependencies
through the constructor.

`@SpringBootApplication` was used in `TwitterCLISpringBoot` to completely automate the 
process of finding the `Beans` and injecting the dependencies into the IOC container.

## <ins> Deployment
The `Twitter CLI Application` was deployed using Docker to Docker Hub using the following steps:
#### Build the package
>mvn clean package

#### Build the image
>docker build username/nameOfApplication 

#### Push the built image to Docker Hub
> docker push username/nameOfApplication
 

## <ins> Test

Testing for each module was done using `Junit4` and `Mockito`. For each component an integration and unit test was
performed to see if it does what it is supposed to do

## <ins> Improvements
- Automatically detect the longitude and latitude when posting a tweet
- Have a local database to store all the tweets that were posted/retrieved/deleted
from all the users
  
- Implement a feature that will allow users to post tweets at a preferred time/date