package com.devesion.commons.utils.types;

import com.google.common.io.Closeables;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Implements deep slow clone of given object using java serialization mechanism.
 */
@Slf4j
public final class Cloner {

	private Cloner() {
		throw new AssertionError();
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deepSlowCopy(T object) {
		if (object == null) {
			return null;
		}

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.flush();
			oos.close();
			oos = null;

			byte[] arr = bos.toByteArray();

			ByteArrayInputStream bis = new ByteArrayInputStream(arr);
			ois = new ObjectInputStream(bis);
			return (T) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new AssertionError("assertion error", e);
		} finally {
			try {
				Closeables.close(ois, true);
				Closeables.close(oos, true);
			} catch (IOException e) {
				log.error("cannot close streams after clone");
			}
		}
	}
}