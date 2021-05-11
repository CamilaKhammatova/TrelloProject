package ru.itis.khammatova.web.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	@GetMapping("/chat")
	public String getChatPage() {
		return "chat";
	}

	@MessageMapping("/message")
	@SendTo("/chat/messages")
	public Message getMessages(Message message) {
		return message;
	}


	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	private static class Message {
		private String username;
		private String text;
	}
}

