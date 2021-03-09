package com.meli.project.quasar.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meli.project.quasar.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Override
	public String getMessage(String[] msg) {
		if(ArrayUtils.isEmpty(msg)) {
			logger.info("Received an empty message.");
		};
		List<String> msgList = Arrays.asList(msg);
		return msgList.stream().filter(StringUtils::isEmpty).map(String::trim).collect(Collectors.joining(" "));
	}

}
