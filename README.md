# Proyecto UD3 - Hibernate

## Participantes

- Manuel Landín Gómez
- Sergio Alonso Pazo

## Supuesto textual
Se queire contruir una aplicación que permita a sus usuarios
almacenar listas con sus libros y películas favoritos. 
Para ello un usuario puede crear distintos *vaults* o almacenes donde
guardar sus listas.

* Un usuario puede registrarse en la app o iniciar sesión para recuperar
sus *vaults*.
* Un *vault* puede almacenar una lista de libros o de películas, pero no ambos.
Además un usuario podrá crear una cantidad de *vaults* arbitraria. De esta manera,
un usuario dispondá de dos listas de *vaults* independientes, una con sus
*vaults* de libros y otra con sus *vaults* de películas. 


Los datos de libros y películas serán incluidos por el usuario, recibiendo asistencia
de APIs externas con información adicional como autor, descripción, fecha de publicación, etc.
Además, los datos almacenados se mostrarán al usuario, 
pudiendo ser modificados, o eliminados a placer.

La aplicación podrá ser lanzada en dos modos: CLI y GUI.




## APIs utilizadas 
### Open Library Search API
API REST aberta que proporciona datos relativos a libros publicados.

### Open Library Covers API
API REST aberta que proporciona acceso a las portadas de libros y las fotos de autor disponibles en el repositorio de portadas de la biblioteca abierta.
### The Movie Data Base (TMDB)

API REST que proporciona datos sobre películas e series.  
API key: 19ccdf01a305d5f5c3485958c90ef5d6

**Para conseguir a imaxe do póster dunha película**:  
`https://image.tmdb.org/t/p/original/<poster_path>`

#### Endpoints

- **/search/movie**
  **Formato da query**: `https://api.themoviedb.org/3/search/movie?api_key=<api_key>&query=<query>`
  Poden engadirse filtros de busca opcionais.
- **/movie/**  
  **Formato da query**: `https://api.themoviedb.org/3/movie/<movie_id>?api_key=<api_key>`
  Devolve un único obxecto `Film` segundo o _movie\_id_ empregado.

## Manual Técnico

### Clases

### Database schema

## Manual de usuario

### Comandos para CLI
#### Comandos para la gestión de la sesión:
- `login --user user --password password` Inica sesión de usuario.
- `logout` Cierra sesión de usuario.
- `status` Muestra información general sobre el usuario y sus colecciones de vaults.
- `exit` Cierra sesión y sale del programa.

#### Comandos para la gestion de vaults:
- `create --bookvault --name name` Crea un vault de libros vacío con nombre.
- `open --bookvault --name name` Abre y muestra un vault por nombre. El vault debe existis previamente en una colección del usuario.
- `delete --bookvault --name name` Borra un vault por nombre. 

#### Comandos para gestión de libros y películas
- `search --book --title title` Busca un libro por título en API Open Library.
- `add --book --isbn isbn --vault name` Añade el libro de ISBN dado en el vault de nombre dado.
- `delete --book --isbn isbn --vault name` Borra el libro de ISBN dado en el vault de nombre dado.


