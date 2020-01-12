package org.dev.app.back.chat.service;

import java.util.List;

import org.dev.app.back.chat.model.document.Mensaje;

public interface ChatService {

	List<Mensaje> obtenerUltimos10Mensajes();
	
	Mensaje guardarMensaje(Mensaje mensaje);
}
