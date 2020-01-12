package org.dev.app.back.chat.service.impl;

import java.util.List;

import org.dev.app.back.chat.model.document.Mensaje;
import org.dev.app.back.chat.model.repository.ChatRepository;
import org.dev.app.back.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

	@Autowired
	@Qualifier("chatRepository")
	private ChatRepository chatRepository;
	
	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		return chatRepository.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardarMensaje(Mensaje mensaje) {
		return chatRepository.save(mensaje);
	}
}
