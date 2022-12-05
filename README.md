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

- Open Library: API REST aberta relacionada con Internet Archive. Proporciona datos relativos a libros publicados.
- The Movie Data Base (TMDB): API REST que proporciona datos sobre películas e series.

## Estructura

### Clases

### Database schema

