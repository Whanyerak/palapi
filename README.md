# Pal API

Mise en pratique API Mongo Docker

Maintenant que vous savez monter une base mongo sur Docker, vous y connecter avec une appli Spring Boot et interagir avec, votre objectif est de mettre en place une API de gestion de Pals dont les attributs sont :

```
id
key
name
wiki
types []
suitability []
	type
	level
drops []
skills []
	level
	name
	type
	cooldown
	power
	description
stats
	hp
	attack
		melee
		ranged
	defense
	speed
		ride
		run
		walk
	stamina
	support
	food
rarity
price
size
```

### L’API doit pouvoir gérer :

GET par id<br>
GET par name<br>
GET par type<br>
<br>
SAVE nouveau Pal<br>
GET skills d'un Pal<br>
ADD un skill d'un Pal<br>
MODIFY un skill d'un Pal<br>
<br>
GET types d'un Pal<br>
ADD type d'un Pal<br>
REMOVE type d'un Pal<br>
<br>
#### BONUS :
GET x Pal SORTED BY rarity<br>
GET x Pal SORTED BY price<br>

#### BONUS 2 :
Dockeriser l'app et sa connection à la mongo
