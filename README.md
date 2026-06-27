Descripción de la Arquitectura
El sistema implementa una arquitectura orientada a microservicios desacoplados, comunicados mediante una puerta de enlace centralizada y procesamiento asíncrono para notificaciones críticas.

Microservicios:
usuario-service: Gestión de usuarios y perfiles.

mascota-service: Registro y seguimiento de mascotas.

notificacion-service: Manejo de alertas y avisos.

datos-analitica-service: Procesamiento de métricas.

integracion-externa-service: Conexión con servicios externos.

 Guía de Despliegue y Ejecución
1. Compilación del Código (Maven)
Para compilar y empaquetar cada uno de los microservicios desde la raíz del proyecto, ejecuta el siguiente comando:

mvn clean install

2. Despliegue en la Nube (Docker Swarm)
Para desplegar la infraestructura completa en el clúster de AWS, sigue estos pasos desde el nodo Manager:

Inicializar el clúster:
docker swarm init

Desplegar el stack utilizando el archivo de configuración:
docker stack deploy -c docker-compose.yml sanos_stack

Verificar el estado de los servicios:
docker service ls

Consumo de API (API Gateway)
Para asegurar la comunicación, el sistema utiliza un API Gateway de AWS que enruta las peticiones de forma profesional hacia nuestro clúster de contenedores.

URL Base: https://otbwgd55eb.execute-api.us-east-1.amazonaws.com

Endpoints disponibles:

GET /usuarios: Lista todos los usuarios.

POST /usuarios: Registra un nuevo usuario.

GET /mascotas: Lista las mascotas registradas.

POST /mascotas: Registra una mascota extraviada.

Procesamiento Asíncrono (Serverless)
El sistema utiliza una arquitectura orientada a eventos para desacoplar el envío de notificaciones:

AWS SQS: Cola de mensajes (sanos-notificaciones-queue) que recibe alertas.

AWS Lambda: Función Serverless (procesar-notificacion-sanos) que se activa automáticamente al recibir un mensaje en la cola para procesar la notificación sin bloquear el flujo principal del usuario.

CI/CD (GitHub Actions)
Todo el ciclo de vida está automatizado. Cada push a la rama main dispara el flujo de GitHub Actions:

Build: Compilación con Maven.

Containerize: Construcción de imágenes Docker.

Push: Envío de imágenes al registro en Docker Hub.
