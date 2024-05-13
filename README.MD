# Movie Reviews

## Description of the project

Movie Reviews is a small application designed to record reviews of movies and TV series. 

Users can add their review to the existing movies and series and also add new ones if they are not registered on the database.

Some useful queries have been added to better understand the data stored in the database.

The different user roles will limit the functions to be performed on movies, series and reviews and also on the management of users and roles.

## Class and Entity Relationship Diagram

<img width="572" alt="Class Diagram" src="https://github.com/celularfreak/movieReviews/blob/main/assets/Class%20Diagram.png">
<img width="572" alt="ER Diagram" src="https://github.com/celularfreak/movieReviews/blob/main/assets/sql.png">

## Setup
1. Clone the repository:
```
git clone https://github.com/your-username/your-repo-name.git
```

2. IntelliJ IDEA will automatically import dependencies from the pom.xml file. If you are using another IDE, you may need to manually import the dependencies.
3.Before running the application, make sure you have MySQL installed and running on your machine. You can download it from [here](https://dev.mysql.com/downloads/installer/).
4. Create a new database in MySQL:
```
CREATE DATABASE your_database_name;
```
5. In the project directory, you will find a file named `application-local-example.properties`. Rename this file to `application-local.properties`.
6. Open `application-local.properties` and replace the placeholders with your actual MySQL credentials and database name.

If you are using MySQL, you may need to add the following line to the `application-local.properties` file:
```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name 
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```
If you are using MariaDB, you may need to add the following line to the `application-local.properties` file:
```
spring.datasource.url=jdbc:mariadb://localhost:3306/your_database_name
spring.datasource.username=your_mariadb_username
spring.datasource.password=your_mariadb_password
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create-drop
```
7. Populate the database with some initial data by running the `Example data.sql` script located in the `assets` directory.
8. Run the application: `mvn spring-boot:run`

## Technologies Used
- Java 17
- Spring Boot
- Maven
- MySQL
- Lombok
- Jakarta Validation
- Spring Security (Bearer Authentication)
- springdoc-openapi
- Swagger UI
- JUnit 5
- Mockito


## Controllers and Routes structure

Here is a general overview of the controllers and routes in this project:

| Route                                             | HTTP Method | Controller               | Action                                 | Description                                                        |
|---------------------------------------------------|-------------|--------------------------|----------------------------------------|--------------------------------------------------------------------|
| `/films`                                          | GET         | FilmController           | `getAllFilms`                          | Returns a list of all films                                        |
| `/films/search/{id}`                              | GET         | FilmController           | `getFilmById`                          | Returns a specific film by its ID                                  |
| `/films/search/director/{director}`               | GET         | FilmController           | `searchFilmsByDirector`                | Returns a list of films by Director                                |
| `/films/search/genre/{genre}`                     | GET         | FilmController           | `searchFilmsByGenre`                   | Returns a list of films by Genre                                   |
| `/films/search/title/{title}`                     | GET         | FilmController           | `searchFilms`                          | Returns a list of films by Title                                   |
| `/films/add`                                      | POST        | FilmController           | `addFilm`                              | Creates a new film                                                 |
| `/films/{id}`                                     | PUT         | FilmController           | `updateFilm`                           | Update a specific film by its ID                                   |
| `/films/{id}`                                     | DELETE      | FilmController           | `deleteFilm`                           | Delete a specific film by its ID                                   |
| `/tv-series`                                      | GET         | TvSeriesController       | `getAllTvSeries`                       | Returns a list of all Tv Series                                    |
| `/tv-series/search/{id}`                          | GET         | TvSeriesController       | `getTvSeriesById`                      | Returns a specific Tv Serie by its ID                              |
| `/tv-series/search/title/{title}`                 | GET         | TvSeriesController       | `searchTvSeries`                       | Returns a list of Tv Series by its Title                           |
| `/tv-series/search/genre/{genre}`                 | GET         | TvSeriesController       | `searchTvSeriesByGenre`                | Returns a list of Tv Series by Genre                               |
| `/tv-series/search/seasons/{seasons}`             | GET         | TvSeriesController       | `searchTvSeriesBySeasons`              | Returns a list of Tv Series by Number of Seasons                   |
| `/tv-series/search/episodes/{episodes}`           | GET         | TvSeriesController       | `searchTvSeriesByEpisodes`             | Returns a list of Tv Series by Number of Episodes                  |
| `/tv-series/average-seasons`                      | GET         | TvSeriesController       | `getAverageSeasons`                    | Returns the average number of seasons of all Tv Series             |
| `/tv-series/average-episodes`                     | GET         | TvSeriesController       | `getAverageEpisodes`                   | Returns the average number of episodes of all Tv Series            |
| `/tv-series/average-episodes`                     | GET         | TvSeriesController       | `getAverageEpisodesPerSeason`          | Returns the average number of episodes per season of all Tv Series |
| `/tv-series/add`                                  | POST        | TvSeriesController       | `addTvSeries`                          | Creates a new Tv Series                                            |
| `/tv-series/{id}`                                 | PUT         | TvSeriesController       | `updateTvSeries`                       | Update a specific Tv Series by its ID                              |
| `/tv-series/{id}`                                 | DELETE      | TvSeriesController       | `deleteTvSeries`                       | Delete a specific Tv Series by its ID                              |
| `/miniseries`                                     | GET         | MiniSerieController      | `getAllMiniSeries`                     | Returns a list of all Miniseries                                   |
| `/miniseries/search/{id}`                         | GET         | MiniSerieController      | `getMiniSerieById`                     | Returns a specific Miniseries by its ID                            |
| `/miniseries/add`                                 | POST        | MiniSerieController      | `addMiniSerie`                         | Creates a new Miniseries                                           |
| `/miniseries/{id}`                                | PUT         | MiniSerieController      | `updateMiniSerie`                      | Update a specific Miniseries by its ID                             |
| `/miniseries/{id}`                                | DELETE      | MinieSrieController      | `deleteMiniSerie`                      | Delete a specific Miniseries by its ID                             |
| `/animes`                                         | GET         | AnimeController          | `getAllAnimes`                         | Returns a list of all Animes                                       |
| `/animes/search/{id}`                             | GET         | AnimeController          | `getAnimeById`                         | Returns a specific Anime by its ID                                 |
| `animes/search/animationStudio/{animationStudio}` | GET         | AnimeController          | `searchAnimesByAnimationStudio`        | Returns a list of all Animes by its Animation Studio               |
| `/animes/add`                                     | POST        | AnimeController          | `addAnime`                             | Creates a new Anime                                                |
| `/animes/{id}`                                    | PUT         | AnimeController          | `updateAnime`                          | Update a specific Anime by its ID                                  |
| `/animes/{id}`                                    | DELETE      | AnimeController          | `deleteAnime`                          | Delete a specific Anime by its ID                                  |
| `/film-reviews`                                   | GET         | FilmReviewController     | `getAllFilmReviews`                    | Returns a list of all Reviews                                      |
| `/film-reviews/search/{username}`                 | GET         | FilmReviewController     | `getFilmReviewByUsername`              | Returns a specific Review by its Username                          |
| `/film-reviews/search/{id}`                       | GET         | FilmReviewController     | `getFilmReviewById`                    | Returns a specific Review by its ID                                |
| `/film-reviews/average-rating/{title}`            | GET         | FilmReviewController     | `calculateAverageRatingForFilmByTitle` | Returns the average rating of a Film                               |
| `/film-reviews/add`                               | POST        | FilmReviewController     | `addFilmReview`                        | Creates a new Film Review                                          |
| `/film-reviews/{id}`                              | PUT         | FilmReviewController     | `updateFilmReview`                     | Updates a specific Review                                          |
| `/film-reviews/{id}`                              | DELETE      | FilmReviewController     | `deleteFilmReview`                     | Deletes a specific Review                                          |
| `/tv-series-reviews`                              | GET         | TvSeriesReviewController | `getAllTvSeriesReviews`                | Returns a list of all Reviews                                      |
| `/tv-series-reviews/search/{username}`            | GET         | TvSeriesReviewController | `getTvSeriesReviewByUsername`          | Returns a specific Review by its Username                          |
| `/tv-series-reviews/search/{id}`                  | GET         | TvSeriesReviewController | `getTvSeriesReviewById`                | Returns a specific Review by its ID                                |
| `/tv-series-reviews/add`                          | POST        | TvSeriesReviewController | `addTvSeriesReview`                    | Creates a new Tv Series Review                                     |
| `/tv-series-reviews/{id}`                         | PUT         | TvSeriesReviewController | `addTvSeriesReview`                    | Updates a specific Review                                          |
| `/tv-series-reviews/{id}`                         | DELETE      | TvSeriesReviewController | `deleteTvSeries`                       | Deletes a specific Review                                          |

Access the API Documentation at `http://localhost:8080/swagger-ui/index.html` while the server is running

## Extra links
Links to the project board and the project presentation.
- [Project Board](https://github.com/users/celularfreak/projects/3)
- [Presentation Slides](https://docs.google.com/presentation/d/1TNSh_OBqO9rq5QF2XTcHVYFExnJmtwsg-F6ESJS-PJo/edit?usp=sharing)

## Future Work
In the future I would like to implement a rating system between users, a “karma”, to have more interaction between them.
I would also like to add the calculation of the average ratings of television series, since the complexity of the inheritance has made me not have time to implement it properly.

Check [project board](https://github.com/users/celularfreak/projects/3) `"Furure Work"` column to know future implementations on the pipeline

## Resources
- [Spring Lombok](https://www.baeldung.com/spring-boot-lombok)
- [Spring JPA](https://www.baeldung.com/spring-boot-jpa)
- [Spring MySQL](https://www.baeldung.com/spring-boot-mysql)
- [Spring MariaDB](https://www.baeldung.com/spring-boot-mariadb)
- [Spring Boot Bearer Authentication](https://www.baeldung.com/spring-security-bearer-token)
- [Spring Swagger](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)
- [Spring Mockito](https://www.baeldung.com/mockito)
- [Spring  Validation](https://www.baeldung.com/spring-boot-bean-validation)
- [Defining Unique Constraints in JPA](https://www.baeldung.com/jpa-unique-constraints)
- [Spring Profiles](https://www.baeldung.com/spring-profiles)
- [Spring Boot with H2 Database](https://www.baeldung.com/spring-boot-h2-database)
