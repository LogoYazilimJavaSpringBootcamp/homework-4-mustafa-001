package com.isbasi.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.isbasi.dto.EmailDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailListener {

	@RabbitListener(queues = "isbasi.email")
	public void emailListener(EmailDto emailDto) {
		log.info("email address: {}", emailDto.getEmail());
		// TO DO mail at
	}

}
