# Foro Hub - Challenge Alura Latam & Oracle Next

## Descripción
Foro Hub es una API REST desarrollada en Java con Spring Boot que permite a los usuarios interactuar a través de un foro. Los participantes pueden crear, consultar, actualizar y eliminar tópicos relacionados con cursos.

---

## Funcionalidades
- **Crear un tópico:** `POST /topicos`
- **Listar todos los tópicos:** `GET /topicos`
- **Obtener un tópico por ID:** `GET /topicos/{id}`
- **Listar tópicos por autor:** `GET /topicos/autor/{autorId}`
- **Actualizar un tópico:** `PUT /topicos`
- **Eliminar un tópico:** `DELETE /topicos/{id}`  
  - Físico para `ADMIN`  
  - Lógico (cambia status a "Eliminado") para `USER`

---

## Seguridad
- **Spring Security** y **JWT** para autenticación y autorización
- Contraseñas **hashadas**
- Roles: `ADMIN` y `USER`
- Endpoints seguros requieren token en header: `Authorization: Bearer <token>`

---

## Tecnologías
- Java 17, Spring Boot, Spring Data JPA, Spring Security
- JWT (`com.auth0:java-jwt`), MySQL, Flyway, Lombok, Maven

---

## Uso / Pruebas
1. Registrar usuario: `POST /auth/register`  
2. Loguear usuario: `POST /auth/login` → devuelve token  
3. Acceder a endpoints seguros usando header:  
   `Authorization: Bearer <token>`

---

## Autor
**Nils Pérez**  
GitHub: [https://github.com/Mortrer](https://github.com/Mortrer)
