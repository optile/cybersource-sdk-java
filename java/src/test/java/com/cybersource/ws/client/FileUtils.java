package com.cybersource.ws.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Shaun Butler
 */
public class FileUtils {

	private static final int BUFFER_SIZE = 1024;

	public static String readFileToString(String fileName) {
		try (InputStream resourceAsStream = ClassLoader.class.getResourceAsStream(fileName)) {
			return toString(resourceAsStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Cannot read from file: " + fileName);
		}
	}

	/**
	 * Reads any input stream into a String with specified character set.
	 * <p>
	 * Note: this method does not close the input stream! Make sure to close it afterwards.
	 *
	 * @param inputStream the input stream.
	 * @param charset the character set of input stream.
	 * @return the string which represents complete content that was read from the input stream.
	 * @throws IOException if any kind of IO problem has occurred while reading from input stream.
	 */
	private static String toString(final InputStream inputStream, final Charset charset) throws IOException {
		if (inputStream != null) {
			final StringBuilder sb = new StringBuilder();

			final char[] buffer = new char[BUFFER_SIZE];
			final BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, charset));
			int read = -1;
			while ((read = bf.read(buffer, 0, BUFFER_SIZE)) != -1) {
				sb.append(buffer, 0, read);
			}

			return sb.toString();
		} else {
			return null;
		}
	}
}
