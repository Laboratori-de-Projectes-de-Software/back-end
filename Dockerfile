# Utilizamos una imagen base de Java 21 como punto de partida
FROM alpine:latest

# Establecemos la carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo pom.xml para que Maven pueda generar el proyecto
COPY pom.xml pom.xml

# Copiamos el resto de los archivos del proyecto
COPY src src

# Instalamos Maven 3.3.3
RUN apk update && apk add openjdk21 && apk add maven

# Compilamos el proyecto
RUN mvn clean install -DskipTests

# Comando para ejecutar la compilación
CMD ["mvn", "spring-boot:run"]