package com.meli.project.quasar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meli.project.quasar.exception.MessageServiceException;
import com.meli.project.quasar.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Override
	public String getMessage(List<List<String>> msg) {
		try {
			Integer stoper = msg.stream().mapToInt(List::size).max().getAsInt();
			Map<Integer, List<String>> joinedPositions = joinPositions(msg, stoper);
			return buildMessage(joinedPositions, joinedPositions.size());
		} catch(Exception excp) {
			throw new MessageServiceException("Failed at message build.", excp);
		}
	}	

	private Map<Integer, List<String>> joinPositions(List<List<String>> msg, Integer stoper) {
		boolean continueProces = Boolean.TRUE;
		Integer indexList = 0;
		Integer indexString = 0;
		AtomicInteger listCount = new AtomicInteger();
		AtomicInteger stringCount = new AtomicInteger();
		Map<Integer, List<String>> phrasesContained = new HashMap<>();
		List<String> joinedIndex = new ArrayList<>();
		while (continueProces) {
			List<String> emitPhrase = msg.get(indexList);
			try {
				joinedIndex.add(emitPhrase.get(indexString));
			} catch (Exception e) {
				logger.warn("Out index, it will continue because next list could had that index");
			}
			indexList = listCount.incrementAndGet();
			if (indexList > msg.size() - 1) {
				phrasesContained.put(indexString, joinedIndex);
				joinedIndex = new ArrayList<>();
				indexString = stringCount.incrementAndGet();
				if (indexString > stoper) {
					continueProces = Boolean.FALSE;
				}
				listCount.set(0);
				indexList = listCount.get();
			}
		}
		return phrasesContained;
	}

	private String buildMessage(Map<Integer, List<String>> mappedMsg, Integer stoper) {
		Integer indexString = 0;
		AtomicInteger stringCount = new AtomicInteger();
		List<String> phrase = new ArrayList<>();
		while (indexString < stoper - 1) {
			List<String> wordPositioned = mappedMsg.get(indexString);
			String word = wordPositioned.stream().filter(StringUtils::isNotEmpty).map(String::trim).distinct()
					.collect(Collectors.joining());
			phrase.add(word);
			indexString = stringCount.incrementAndGet();
		}
		return phrase.stream().distinct().collect(Collectors.joining(" "));
	}

}
