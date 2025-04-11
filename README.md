# Human-TrAIts

Este repositorio corresponde al back-end del proyecto "Human-TrAIts", desarrollado para la asignatura "Laboratori de Projectes del Software" de la UIB.
Se ha desarrollado utilizando Spring boot, siguiendo el modelo hexagonal para organizar el código.

## Como ejecutarlo en local
```
git clone https://github.com/Laboratori-de-Projectes-de-Software/back-end-bc-spn
git clone https://github.com/Laboratori-de-Projectes-de-Software/front-end-bc-spn
cd front-end-bc-spn
npm install
cd ../back-end-bc-spn
./deploy.sh
```

Hecho esto, podremos acceder a la página web en http://localhost:8080

Además, cada vez que modifiquemos el código, podremos ver los cambios simplemente ejecutando el script ```deploy.sh``` del repositorio back-end-bc-spn.
El repositorio front-end-bc-spn debe existir en el mismo directorio padre para que los cambios del frontend aparezcan. 