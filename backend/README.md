
# Looqbox Backend Challenge




## Endpoint

### Retorna lista filtrada

```http
  GET /pokemon?q={query}&?sorting={sortingMode}
```

#### Query  Params
| Parameters   | Type       | Description    |      accepted values|
| :---------- | :--------- | :------------ | :-------------------------- |
| `?q`      | `string` | substring for query| any string           |
| `?sorting`      | `string` | sorting method| "alphabetical" or "length"  |

***Details:***
 
 * Both params are optional;
 * If a query is not passed, a list containing all pokemon will be returned;
 * If a sorting mode is not passed, the list will not be sorted (it's originally ordered by ID);
 * If "alphabetical" is passed as the sorting method, the results will be sorted by name using Bubblesort and returned in alphabetical order;
 * If "length" is passed as the sorting method, the results will be sorted by name length using Counting Sort and returned from shortest to longest;

  ps.: You can find details about the logic and implementation in the source code, as comments.

 examples:

 ```http
  GET /pokemon?q=abr&?sorting=alphabetical
```
Returns:
```
[
{
"name": "abra",
"url": "https://pokeapi.co/api/v2/pokemon/abra",
"highlight": "<pre>abr</pre>a"
},
{
"name": "crabrawler",
"url": "https://pokeapi.co/api/v2/pokemon/crabrawler",
"highlight": "cr<pre>abr</pre>awler"
},
{
"name": "kadabra",
"url": "https://pokeapi.co/api/v2/pokemon/kadabra",
"highlight": "kad<pre>abr</pre>a"
}
]
```

=================


 ```http
  GET /pokemon?q=abr&?sorting=length
```
Returns:

 ```
 [
{
"name": "abra",
"url": "https://pokeapi.co/api/v2/pokemon/abra",
"highlight": "<pre>abr</pre>a"
},
{
"name": "kadabra",
"url": "https://pokeapi.co/api/v2/pokemon/kadabra",
"highlight": "kad<pre>abr</pre>a"
},
{
"name": "crabrawler",
"url": "https://pokeapi.co/api/v2/pokemon/crabrawler",
"highlight": "cr<pre>abr</pre>awler"
}
]
```

## Deploy

To run the project:

1) Build the project, if it's not already built:
* go to the base folder (the one that has build.gradle and settings.gradle files);
* run the command:  
```bash
  gradle build
```
* make sure the command worked (a /build folder was created, with the /build/libs/backend-0.0.1-SNAPSHOT.JAR)

2) Create the container:
* make sure Docker is running;
* still in the base folder (the one with the file DOCKERFILE), run the command:
```bash
  docker build -t pokemon-api .
```
* It will generate a container called pokemon-api out of the build file

3) Run the containers:
* Make sure port localhost:8080 is available;
* still in the base folder (the one with the file docker-compose.yml), run the command:
```bash
  docker-compose up
```

4) Using the application:
  * The application will be exposed to the port 8080 of the localhost.
  * You can make a query by sending a get request to 
```http
  GET localhost:8080/pokemon?q={query}&?sorting={sortingMode}
```