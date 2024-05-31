# Manual de despliegue

- **Descargar ZIP**
  ```
  git clone https://github.com/deiivvv/GymTracker.git
  ```
  
- **Verificar la situación actual de tu repositorio Git y tus cambios:**
  ```
  git status
  ```
# Despliegue de GymTracker con Docker

## Visión General

Este repositorio contiene los archivos necesarios para desplegar la aplicación GymTracker utilizando Docker. Sigue las instrucciones a continuación para descargar el repositorio, descomprimir el archivo ZIP y ejecutar Docker Compose para generar los contenedores.

## Descomprimir el Archivo ZIP llamado `despliegue_docker`

1. Localiza el archivo ZIP llamado `despliegue_docker.zip` en la carpeta donde descargaste el repositorio.
2. Descomprime el archivo ZIP en la ubicación deseada.

## Descargar Docker

### En Linux

1. Instala Docker siguiendo las instrucciones específicas para tu distribución de Linux desde [el sitio oficial de Docker](https://docs.docker.com/get-docker/).

### En Windows

1. Descarga Docker Desktop desde [el sitio oficial de Docker](https://docs.docker.com/desktop/).
2. Sigue las instrucciones de instalación proporcionadas por el instalador de Docker Desktop.

## Ejecutar Docker Compose para Generar Contenedores

1. Abre una terminal.
2. Navega hasta el directorio donde descomprimiste el archivo ZIP `despliegue_docker`.
3. Ejecuta el siguiente comando:

   ```bash
   docker-compose -p contenedor-gymtracker up --build
   
Este comando generará los contenedores de GymTracker y los iniciará. Si es la primera vez que ejecutas este comando o si realizaste cambios en la configuración, se reconstruirán las imágenes de Docker antes de iniciar los contenedores.

## Detener los Contenedores

Para detener los contenedores de GymTracker, sigue estos pasos:

1. En la terminal donde se están ejecutando los contenedores, presiona `Ctrl + C`.
   
##Gracias por confiar en gymtracker

¡Ahora deberías tener la aplicación GymTracker ejecutándose en contenedores Docker en tu sistema. Disfruta de tu experiencia con GymTracker!
