package domain.notificaciones;

public class ConfiguracionWhatsapp {
  public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

  public static final String FROM = "whatsapp:+14155238886";
}
