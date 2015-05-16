package com.devesion.commons.camel;

import com.beust.jcommander.internal.Sets;
import com.googlecode.catchexception.CatchException;
import org.fest.assertions.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

import static com.googlecode.catchexception.CatchException.caughtException;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link DynamicRecipientsList}
 */
public class DynamicRecipientsListTest {

	@DataProvider(name = "dataProvider1")
	public Object[][] createData() {
		return new Object[][] {
				{ 1 },
				{ 100 },
				{ 1000},
		};
	}

	@Test(dataProvider = "dataProvider1")
	public void shouldNotifyAllRecipients(int recipientsNum) {
		// given
		Set<DynamicRecipient<Object>> recipients = makeRecipientsSet(recipientsNum);
		DynamicRecipientsList<Object> recipientsList = new DynamicRecipientsList<>(recipients);
		String message = "Text message";

		// when
		recipientsList.routeToAll(message);

		// then
		for (DynamicRecipient<Object> recipient : recipients) {
			verify(recipient).receive(message);
		}
	}

	@Test
	public void shouldThrowExceptionWhenNullMessage() {
		// given
		Set<DynamicRecipient<Object>> recipients = makeRecipientsSet(10);
		DynamicRecipientsList<Object> recipientsList = new DynamicRecipientsList<>(recipients);
		String message = null;

		// when
		CatchException.catchException(recipientsList).routeToAll(message);

		// then
		Throwable caughtException = caughtException();
		Assertions.assertThat(caughtException).isInstanceOf(RuntimeException.class);
	}

	private Set<DynamicRecipient<Object>> makeRecipientsSet(int recipientsNum) {
		Set<DynamicRecipient<Object>> recipients = Sets.newHashSet();
		for (int i = 0; i < recipientsNum; i++) {
			recipients.add(makeDynamicRecipientMock());
		}

		return recipients;
	}

	private DynamicRecipient<Object> makeDynamicRecipientMock() {
		@SuppressWarnings("unchecked")
		DynamicRecipient<Object> recipient = Mockito.mock(DynamicRecipient.class);
		doThrow(new NullPointerException()).when(recipient).receive(null);
		return recipient;
	}
}
