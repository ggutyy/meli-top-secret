package com.meli.project.quasar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.meli.project.quasar.exception.MessageServiceException;
import com.meli.project.quasar.service.impl.MessageServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceImplTest {

	@InjectMocks
	private MessageServiceImpl messageServiceImpl;
	
	@Test
	public void givenMessageWhenThenGetMessageOk() {
		List<List<String>> message = new ArrayList<>();
		List<String> positionWords = Arrays.asList("hola",""," ");
		message.add(positionWords);
		positionWords = Arrays.asList(" ","soy"," mensaje");
		message.add(positionWords);
		//Given
		//When
		//Then
		String resultMessage = messageServiceImpl.getMessage(message);
		//Asserts
		Assert.assertNotNull(resultMessage);
	}
	
	@Test
	public void givenNullMessageWhenThenGetMessageThrowsMessageService() {
		//Given
		MessageServiceException excp = null;
		//When
		//Then
		try {
			messageServiceImpl.getMessage(null);
		}catch (MessageServiceException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
	}
	
	@Test
	public void givenMessageWhenThenJoinMessageWithComma() {
		//Given
		String[] message = new String[] {"", " hola",};
		//When
		//Then
		String joinedMessage = messageServiceImpl.joinMessageWithComma(message);
		//Asserts
		Assert.assertNotNull(joinedMessage);
	}
	
	@Test
	public void givenMessageWhenThenSplitCommaMsg() {
		//Given
		String message = ", hola, ,soy ,un, mensaje";
		//When
		//Then
		String[] result = messageServiceImpl.splitCommaMsg(message);
		//Asserts
		Assert.assertNotNull(result);
	}
}

