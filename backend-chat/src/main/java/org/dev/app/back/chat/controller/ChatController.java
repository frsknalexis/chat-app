package org.dev.app.back.chat.controller;

import java.util.Date;
import java.util.Random;

import org.dev.app.back.chat.model.document.Mensaje;
import org.dev.app.back.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	
	private String[] colores = { "red", "green", "blue", "magenta", "purple", "orange" };
	
	@Autowired
	@Qualifier("chatService")
	private ChatService chatService;

	@Autowired
	private SimpMessagingTemplate webSocketTemplate;
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo Usuario");
		} else {
			chatService.guardarMensaje(mensaje);
		}
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	String estaEscribiendo(String username) {
		return username.concat(" esta escribiendo...");
	}
	
	@MessageMapping("/historial")
	void historialMensajes(String clienteId) {
		webSocketTemplate.convertAndSend("/chat/historial/" + clienteId, chatService.obtenerUltimos10Mensajes());
	}
}
