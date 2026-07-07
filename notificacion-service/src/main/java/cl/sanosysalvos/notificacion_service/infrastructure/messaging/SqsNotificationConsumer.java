package cl.sanosysalvos.notificacion_service.infrastructure.messaging;

import org.springframework.stereotype.Component;

import io.awspring.cloud.sqs.annotation.SqsListener;

/**
 * Consumidor de mensajes de AWS SQS.
 * Escucha eventos de forma asíncrona (ej. cuando se pierde una mascota)
 * y simula el envío de una notificación (SMS, Push, Email).
 */
@Component
public class SqsNotificationConsumer {

    // La anotación @SqsListener hace toda la magia.
    // Se queda escuchando la cola que definimos en el application.yml
    @SqsListener("${aws.sqs.queue-name}")
    public void recibirMensaje(String mensaje) {
        System.out.println("\n=======================================================");
        System.out.println("  NUEVA NOTIFICACIÓN RECIBIDA DESDE AWS SQS ");
        System.out.println("=======================================================");
        System.out.println("Contenido del mensaje: " + mensaje);
        System.out.println("Simulando envío de correo/SMS a los usuarios cercanos...");
        System.out.println("=======================================================\n");
        
        // En un proyecto real, aquí llamarías a Twilio (para SMS) o SendGrid (para correos).
        // Para la evaluación, imprimirlo en consola demuestra que el mensaje viajó por la nube y llegó.
    }
}