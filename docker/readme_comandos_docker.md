```markdown
## Ver Contenedores

```bash
docker ps -a
```

Este comando muestra una lista de todos los contenedores, incluidos los que están detenidos.

## Ver Volúmenes

```bash
docker volume ls
```

Este comando muestra una lista de todos los volúmenes Docker en el sistema.

## Acceder a la Base de Datos del Contenedor

Para acceder a la base de datos del contenedor de la base de datos de GymTracker, primero accede al shell del contenedor y luego ingresa a MySQL:

```bash
docker exec -it contenedor-gymtracker_database_1 /bin/bash
mysql -u root -p 
```

La contraseña predeterminada es "root_password".

## Acceder a los Logs de los Otros Contenedores

Puedes acceder a los logs de los contenedores de la aplicación y la API de GymTracker de la siguiente manera:

```bash
docker logs contenedor-gymtracker_app_1
docker logs contenedor-gymtracker_api_1
```

## Borrar Contenedores de GymTracker

```bash
docker rm contenedor-gymtracker_database_1 contenedor-gymtracker_app_1 contenedor-gymtracker_api_1
```

Este comando elimina los contenedores específicos de GymTracker.

## Borrar Volumen de GymTracker

```bash
docker volume rm contenedor-gymtracker_db_data
```

Este comando elimina el volumen llamado "contenedor-gymtracker_db_data", utilizado por la base de datos de GymTracker.

## Ejecutar Docker Compose para Generar o Iniciar los Contenedores

```bash
docker-compose -p contenedor-gymtracker up --build
```

Este comando genera los contenedores de GymTracker y los inicia. Si es la primera vez que se ejecuta este comando o si se realizaron cambios en la configuración, se reconstruirán las imágenes de Docker antes de iniciar los contenedores.

## Parar Dockers

Para detener los contenedores de GymTracker, presiona Ctrl + C en la terminal donde se están ejecutando.
```
