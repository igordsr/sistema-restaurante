# Use a imagem oficial do OpenJDK 17 como base
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o jar do seu projeto para o diretório de trabalho
COPY ./build/libs/sistema-restaurante-0.0.1-SNAPSHOT.jar ./sistema-restaurante-0.0.1-SNAPSHOT.jar

# Exponha a porta 8080 para o mundo exterior
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "sistema-restaurante-0.0.1-SNAPSHOT.jar"]
