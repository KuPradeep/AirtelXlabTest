package com.airtel.xlabs.assignment.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileScanner {

	private static final Pattern COMMA_DELIMITER = Pattern.compile(",");

	public List<List<String>> readRecords(String filename) {
		List<List<String>> records = new ArrayList<>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		} catch (Exception e) {
			System.err.println("Error reading records " + e.getMessage());
		} finally {
			scanner.close();
		}
		return records;
	}

	private List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		Scanner rowScanner = new Scanner(line);
		rowScanner.useDelimiter(COMMA_DELIMITER);
		// log.info("getRecordFromLine line=" + line);
		while (rowScanner.hasNext()) {
			String value = rowScanner.next();
			// log.info("getRecordFromLine value=" + value);
			values.add(value);
		}
		rowScanner.close();
		return values;
	}

}
