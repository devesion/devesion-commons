package com.devesion.commons.utils.types.uuid;

import com.devesion.commons.utils.MagicConstants;
import com.google.common.base.Preconditions;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Time UUID Generator based on com.fasterxml.uuid.impl.TimeBasedGenerator.
 * Allows build TimeUUID for given timestamp.
 */
public final class TimeUUIDGen {

	/**
	 * BYTE_OFFSET_CLOCK_SEQUENCE.
	 */
	private static final int BYTE_OFFSET_CLOCK_SEQUENCE = MagicConstants.INT_8;

	/**
	 * DEFAULT_MAC_ADDRESS.
	 */
	private static final byte[] DEFAULT_MAC_ADDRESS = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

	private static long lastTime = 0;

	private TimeUUIDGen() {
		throw new IllegalAccessError();
	}

	private static byte[] getMacAddress() {
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface iface = en.nextElement();
				if (!iface.isLoopback()) {
					byte[] data = iface.getHardwareAddress();
					if (data != null && data.length == MagicConstants.INT_6) {
						return data;
					}
				}
			}
		} catch (SocketException e) {
			throw new IllegalStateException("cannot read mac address", e);
		}

		return DEFAULT_MAC_ADDRESS;
	}

	private static long makeL1(long timeMs) {
		// Time field components are kind of shuffled, need to slice:
		int clockHi = (int) (timeMs >>> MagicConstants.INT_32);
		int clockLo = (int) timeMs;
		// and dice
		int midhi = (clockHi << MagicConstants.INT_16) | (clockHi >>> MagicConstants.INT_16);
		// need to squeeze in type (4 MSBs in byte 6, clock hi)
		midhi &= ~MagicConstants.HEX_F000; // remove high nibble of 6th byte
		midhi |= MagicConstants.HEX_1000; // type 1
		long midhiL = (long) midhi;
		midhiL = ((midhiL << MagicConstants.INT_32) >>> MagicConstants.INT_32); // to get rid of sign extension
		// and reconstruct
		return (((long) clockLo) << MagicConstants.INT_32) | midhiL;
	}

	private static long gatherLong(byte[] buffer, int offset) {
		long hi = ((long) gatherInt(buffer, offset)) << MagicConstants.INT_32;
		long lo = (((long) gatherInt(buffer, offset + MagicConstants.INT_4)) << MagicConstants.INT_32) >>> MagicConstants.INT_32;
		return hi | lo;
	}

	private static int gatherInt(byte[] buffer, int offset) {
		return (buffer[offset] << MagicConstants.INT_24) | ((buffer[offset + 1] & MagicConstants.HEX_FF) << MagicConstants.INT_16)
				| ((buffer[offset + 2] & MagicConstants.HEX_FF) << MagicConstants.INT_8) | (buffer[offset + MagicConstants.INT_3] & MagicConstants.HEX_FF);
	}

	private static long initUUIDSecondLong(long l2) {
		long l2New = ((l2 << 2) >>> 2); // remove 2 MSB
		l2New |= (2L << MagicConstants.INT_62); // set 2 MSB to '10'
		return l2New;
	}

	private static long makeL2(long timeMs) {
		byte[] uuidBytes = new byte[MagicConstants.INT_16];
		byte[] mac = getMacAddress();
		System.arraycopy(mac, 0, uuidBytes, MagicConstants.INT_10, MagicConstants.INT_6);

		// and add clock sequence
		uuidBytes[BYTE_OFFSET_CLOCK_SEQUENCE] = (byte) (timeMs >> MagicConstants.INT_8);
		uuidBytes[BYTE_OFFSET_CLOCK_SEQUENCE + 1] = (byte) timeMs;
		long l2 = gatherLong(uuidBytes, MagicConstants.INT_8);
		return initUUIDSecondLong(l2);
	}

	private static synchronized long generateUniqueTime(long currentTime) {
		if (lastTime >= currentTime) {
			lastTime++;
			return lastTime;
		} else {
			lastTime = currentTime;
			return lastTime;
		}
	}

	public static UUID buildUUID() {
		return buildUUID(System.currentTimeMillis());
	}

	public static UUID buildUniqueUUID() {
		long uniqueTime = generateUniqueTime(System.currentTimeMillis());
		return buildUniqueUUIDForCurrentTime(uniqueTime);
	}

	static UUID buildUniqueUUIDForCurrentTime(long currentTime) {
		return buildUUID(generateUniqueTime(currentTime));
	}

	/**
	 * Bulds UUID for a given timestamp.
	 *
	 * @param timeMs timestamp for TimeUUID in milliseconds
	 * @return new UUID with a given timestamp
	 */
	public static UUID buildUUID(long timeMs) {
		Preconditions.checkArgument(timeMs >= 0);
		return new UUID(makeL1(timeMs), makeL2(timeMs));
	}
}
