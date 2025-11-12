package co.edu.udec.ingsw.activity.application.usuario.port.out;

/**
 * Port de salida para gestión de sesiones
 * 
 * En una implementación real, podría usar Redis o base de datos.
 * En esta simulación, usa un Map en memoria.
 */
public interface SessionServicePort {
  
  /**
   * Guarda una sesión activa
   * 
   * @param token Token de autenticación
   * @param usuarioId ID del usuario
   */
  void saveSession(String token, Integer usuarioId);
  
  /**
   * Verifica si una sesión está activa
   * 
   * @param token Token de autenticación
   * @return true si la sesión existe y está activa
   */
  boolean isSessionActive(String token);
  
  /**
   * Elimina una sesión (logout)
   * 
   * @param token Token de autenticación
   */
  void invalidateSession(String token);
  
  /**
   * Obtiene el ID de usuario asociado a un token
   * 
   * @param token Token de autenticación
   * @return ID del usuario o null si no existe
   */
  Integer getUsuarioIdByToken(String token);
}

