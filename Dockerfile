### Cambiar la imagen linux para despligue 
FROM amazoncorretto:11
LABEL key="CardAPI"

### Variable de entorno para ruta del artefacto
ENV SERVER_HOME=/opt/spring-boot/
ENV JAR_FILE=cardAPI-0.0.1-SNAPSHOT.jar

### Directorio principal del artefacto
RUN mkdir SERVER_HOME
COPY target/$JAR_FILE $SERVER_HOME/$JAR_FILE
WORKDIR SERVER_HOME

#### Variables de entorno para desplegar en zona horaria de Bogotá 
ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

### Puerto expuesto
EXPOSE 8081

### Comandos para iniciar instancia
ENTRYPOINT ["java", "-jar", "-Dfile.encoding=UTF-8", "/opt/spring-boot/cardAPI.0.0.1-SNAPSHOT.jar"]
