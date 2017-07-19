package com.iGame.chart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
/*
 * EnableWebSocketMessageBroker標示打開websocket服務，
 * 並通過繼承AbstractWebSocketMessageBrokerConfigurer對websocket進行配置
 */
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//客户端可以通過/ws地址與後台建立websocket連接。
		registry.addEndpoint("/ws").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		//將/app開頭的地址標示為應用地址。此時用戶可以通过/ws地址与服务器建立websocket連接，並通過websocket進行通信。
		config.setApplicationDestinationPrefixes("/app");
	}
}
