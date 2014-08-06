package com.kurento.kmf.turotial.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kurento.kmf.media.factory.KmfMediaApi;
import com.kurento.kmf.media.factory.MediaPipelineFactory;

/**
 * 
 * @author Ivan Gracia (izanmail@gmail.com)
 * @since 4.3.1
 */
@Configuration
@EnableWebSocket
@EnableAutoConfiguration
public class GroupCall implements WebSocketConfigurer {
	
	@Bean
	public UserRegistry registry() {
		return new UserRegistry();
	}

	@Bean
	public RoomManager roomManager() {
		return new RoomManager();
	}

	@Bean
	public GroupCallHandler groupCallHandler() {
		return new GroupCallHandler();
	}

	@Bean
	MediaPipelineFactory mediaPipelineFactory() {
		return KmfMediaApi.createMediaPipelineFactoryFromSystemProps();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GroupCall.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(groupCallHandler(), "/groupcall");
	}
}
