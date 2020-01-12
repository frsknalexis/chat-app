package org.dev.app.back.chat.model.repository;

import java.util.List;

import org.dev.app.back.chat.model.document.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("chatRepository")
public interface ChatRepository extends MongoRepository<Mensaje, String> {

	List<Mensaje> findFirst10ByOrderByFechaDesc();
}
