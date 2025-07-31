# Usa apenas o JRE para runtime (mais leve)
FROM eclipse-temurin:17-jre

# Define diretório de trabalho no container
WORKDIR /app

# Copia o JAR gerado pela build
COPY build/libs/pesquisa_uti_neonatal-*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
