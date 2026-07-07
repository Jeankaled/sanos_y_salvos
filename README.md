Proyecto Sanos y Salvos
1. Descripción de la Arquitectura
El sistema implementa una arquitectura orientada a microservicios altamente desacoplados, diseñados para escalabilidad y mantenibilidad. La comunicación se centraliza a través de un AWS API Gateway y se integra con un flujo de procesamiento asíncrono para notificaciones críticas, garantizando una alta disponibilidad.

Microservicios:
usuario-service: Gestión de perfiles y autenticación.

mascota-service: Registro, seguimiento y gestión de estado de mascotas.

notificacion-service: Manejo centralizado de alertas y avisos.

datos-analitica-service: Procesamiento y visualización de métricas del sistema.

integracion-externa-service: Orquestación con servicios de terceros.

2. Guía de Despliegue y Ejecución
Compilación (Maven)
Desde la raíz del proyecto, compila y genera los artefactos:
bash
   mvn clean install
Despliegue en la Nube (Docker Swarm)
Para desplegar la infraestructura en el clúster de AWS (Nodo Manager):

Inicializar el clúster (si no está activo):

bash

  docker swarm init
Despliegue del Stack:

bash

   docker stack deploy -c docker-compose.yml sanos_stack
Verificación:

   docker service ls

3. Arquitectura Asíncrona (Event-Driven)
El sistema desacopla procesos pesados (como el envío de notificaciones) para reducir la latencia de respuesta al usuario:

AWS SQS (sanos-y-salvos-queue): Actúa como buffer de mensajes. El microservicio de mascota-service publica eventos en la cola sin esperar respuesta.

AWS Lambda (EnviarNotificacionLambda): Función Serverless que consume los mensajes de la cola de forma automática y asíncrona, procesando la lógica de negocio sin bloquear el hilo principal de la aplicación.

4. Consumo de API
El acceso está protegido y enrutado mediante AWS API Gateway.

URL Base: https://85hv2hgk85.execute-api.us-east-1.amazonaws.com

Endpoints principales:

GET /mascotas: Lista todas las mascotas.

POST /mascotas: Registra una nueva mascota y dispara el flujo asíncrono de notificación.

5. CI/CD (GitHub Actions)
Automatización completa del ciclo de vida del software:

Build: Validación de código con Maven.

Containerize: Construcción de imágenes Docker optimizadas.

Push: Despliegue automático de imágenes al registro en Docker Hub.

6. Consideraciones Técnicas y Seguridad
Gestión de Secretos: Las credenciales de AWS (AWS_ACCESS_KEY, AWS_SECRET_KEY, AWS_SESSION_TOKEN) son inyectadas mediante Variables de Entorno para evitar la exposición de secretos en el control de versiones.

Resiliencia: Configuración de HikariCP con maximum-pool-size: 5 para optimizar el uso de conexiones a la base de datos y evitar el error FATAL: too many clients.
