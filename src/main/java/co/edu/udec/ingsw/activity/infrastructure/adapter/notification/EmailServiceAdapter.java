package co.edu.udec.ingsw.activity.infrastructure.adapter.notification;

import org.springframework.stereotype.Component;

import co.edu.udec.ingsw.activity.application.usuario.port.out.EmailServicePort;

@Component
public class EmailServiceAdapter implements EmailServicePort {

  @Override
  public void sendPasswordResetEmail(String destinatario, String token) {
    System.out.println("========================================");
    System.out.println("📧 EMAIL SIMULADO - Recuperación de Contraseña");
    System.out.println("========================================");
    System.out.println("Para: " + destinatario);
    System.out.println("Asunto: Recuperación de Contraseña");
    System.out.println();
    System.out.println("Hola " + destinatario + ",");
    System.out.println();
    System.out.println("Has solicitado restablecer tu contraseña.");
    System.out.println("Tu token de recuperación es: " + token);
    System.out.println();
    System.out.println("En una aplicación real, este token estaría en un enlace:");
    System.out.println("https://tu-app.com/reset-password?token=" + token);
    System.out.println();
    System.out.println("Este token expira en 1 hora.");
    System.out.println("Si no solicitaste este cambio, ignora este mensaje.");
    System.out.println("========================================");
  }
}

