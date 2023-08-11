
### Cambiar la imagen linux para despligue 
FROM amazoncorretto:11
LABEL key="CardAPI"

### Ruta final del artefacto
ADD build/libs /opt/spring-boot

### Variable de entorno para ruta del artefacto
ENV SERVER_HOME /opt/spring-boot

### Directorio principal del artefacto
WORKDIR /opt/spring-boot

#### Variables de entorno para desplegar en zona horaria de Bogotá 
ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

### Puerto expuesto
EXPOSE 8081

### Comandos para iniciar instancia
ENTRYPOINT ["java", "-jar", "-Dfile.encoding=UTF-8", "/opt/spring-boot/cardAPI.0.0.1-SNAPSHOT.jar"]