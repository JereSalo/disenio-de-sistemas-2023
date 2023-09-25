package domain.notificaciones.notificador;

public class ConfiguracionWhatsapp {
  // Hay que configurar estas 2 como variables de entorno en IntelliJ: Run -> Edit Configurations -> Environment variables
  public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

  // Sandbox de Twilio para WhatsApp
  public static final String FROM = "whatsapp:+14155238886";
}
