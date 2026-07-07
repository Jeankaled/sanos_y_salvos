package cl.sanosysalvos.mascota_service.infrastructure.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.awspring.cloud.sqs.operations.SqsTemplate;

/**
 * Productor de mensajes para AWS SQS.
 * Esta clase se encarga de enviar notificaciones de forma asíncrona
 * cuando ocurre un evento importante en el sistema (ej: Mascota perdida).
 */
@Component
public class SqsNotificationProducer {

    private final SqsTemplate sqsTemplate;
    
    // Inyectamos el nombre de la cola desde el archivo application.yml
    @Value("${aws.sqs.queue-name}")
    private String queueName;

    public SqsNotificationProducer(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    public void enviarEventoMascotaPerdida(String mascotaId, String nombreMascota) {
        // Creamos un mensaje estructurado (JSON o texto plano)
        String mensaje = String.format("ALERTA: La mascota %s (ID: %s) ha sido reportada como perdida.", 
                                        nombreMascota, mascotaId);
        
        try {
            // Uso de la API Fluida de SqsTemplate (Mejor práctica en AWS 3.x)
            sqsTemplate.send(to -> to
                .queue(queueName)
                .payload(mensaje)
            );
            
            System.out.println("Mensaje enviado exitosamente a la cola: " + queueName);
        } catch (Exception e) {
            System.err.println("Error al enviar mensaje a SQS: " + e.getMessage());
        }
    }
}   