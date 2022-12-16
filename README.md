# Proyecto UD3 - Hibernate

## Participantes

- Manuel Landín Gómez
- Sergio Alonso Pazo

## Supuesto textual
Se queire contruir una aplicación que permita a sus usuarios
almacenar listas con sus libros y películas favoritos. 
Para ello un usuario puede crear distintos *vaults* o almacenes donde
guardar sus listas.

* Un usuario puede iniciar sesión en la app, recuperando la configuración de su perfil y *vaults* creados.
* Un *vault* puede almacenar una lista de libros o de películas, pero no ambos.
Además un usuario podrá crear una cantidad de *vaults* arbitraria. De esta manera,
un usuario dispondá de dos listas de *vaults* independientes, una con sus
*vaults* de libros y otra con sus *vaults* de películas. 

Los datos de libros y películas serán buscados a través de la aplicación, pudiendo ser
incluidos en los vaults que el usuario desee.
Además, los datos almacenados se mostrarán al usuario,
pudiendo ser modificados, o eliminados a placer.

Toda la información estará almacenada de manera persistente en una base de datos, admitiendo
dos modalidades:
- Sistema gestor MySQL en local.
- Sistema gestor SQL Server en Azure.

La aplicación podrá ser lanzada en dos modos: CLI y GUI.

![](.\docs\img\mapa_conceptual_app.jpg)






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
### Persistencia
![](.\docs\img\persistence_diagram.jpg)

### Clases

### Database schema

## Manual de usuario para CLI

### Comandos para CLI
#### Comandos para la gestión de la sesión:
- `login [-u|--user] USER [-p|--password] PASSWORD` Inica sesión de usuario.
- `logout` Cierra sesión de usuario.
- `status` Muestra información general sobre el usuario y sus colecciones de vaults.
- `exit` Cierra sesión y sale del programa.

#### Comandos para la gestion de vaults:
- `create [-bv|-fv|--bookvault|--filmvault] NAME` Crea un vault vacío con nombre.
- `open [-bv|-fv|--bookvault|--filmvault] NAME` Abre y muestra un vault por nombre. El vault debe existis previamente en una colección del usuario.
- `delete [-bv|-fv|--bookvault|--filmvault] NAME` Borra un vault por nombre. 

#### Comandos para gestión de libros y películas
- `search [-b|-f|--book|--film] NAME` Busca un libro por título en API Open Library.
- `add [-b|--book] --isbn ISBN [-v|--vault] VAULT_NAME` Añade el libro de ISBN dado en el vault de nombre dado.
- `add [-f|--film] --tmid TMID [-v|--vault] VAULT_NAME` Añade la película de TMID dado en el vault de nombre dado.
- `chsts --isbn ISBN [-v|--vault] VAULT_NAME` Cambia el estado de un libro.
- `chsts --tmid TMID [-v|--vault] VAULT_NAME` Cambia el estado de una película.
- `delete delete [-b|--book] --isbn ISBN [-v|--vault] VAULT_NAME` Borra el libro de ISBN dado en el vault de nombre dado.
- `delete delete [-f|--film] --tmid TMID [-v|--vault] VAULT_NAME` Borra la película de TMID dado en el vault de nombre dado.

## Manual de usuario para GUI
