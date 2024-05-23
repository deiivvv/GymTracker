# Comandos Git

- **Clonar repositorio:**
  ```
  git clone https://github.com/deiivvv/GymTracker.git
  ```
  
- **Verificar la situación actual de tu repositorio Git y tus cambios:**
  ```
  git status
  ```

- **Crear y cambiarte a una nueva rama:**
  ```
  git checkout -b issue#XX
  ```

- **Añadir los cambios al área de preparación de Git:**
  ```
  git add .
  ```

- **Realizar un commit de tus cambios:**
  ```
  git commit -m "Mensaje descriptivo del commit"
  ```

- **Subir tu rama a un repositorio remoto:**
  ```
  git push origin issue#XX
  ```

- **Comprobar si estás actualizado con la rama principal (main):**
  ```
  git fetch --all
  ```

- **En caso de no estarlo, asegúrate de haber realizado un commit y cámbiate a la rama principal:**
  ```
  git checkout main
  ```

- **Actualizar tu rama local con los cambios más recientes de la rama principal:**
  ```
  git pull
  ```

- **Volver a tu rama de trabajo:**
  ```
  git checkout issue#XX
  ```

- **Actualizar tu rama con los nuevos cambios de la rama principal:**
  ```
  git merge -m "Fusionar main con issue #XX" main
  ```

- **Fusionar tu rama de trabajo con la rama principal (main), solo cuando todos estén de acuerdo:**
  ```
  git merge --no-ff -m "Fusionar issue #XX con main" issue#XX
  ```
