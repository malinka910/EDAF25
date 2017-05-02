package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import util.XLException;

public class XLBufferedReader extends BufferedReader {
    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    public void load(Map<String, Slot> map) {
    	SlotFactory factory = new SlotFactory();
        try {
            while (ready()) {
                String string = readLine();
                System.out.println(string);
                int i = string.indexOf('=');
                map.put(string.substring(0, i), factory.build(string.substring(i+1)));
            }
        } catch (Exception e) {
            throw new XLException(e.getMessage());
        }
    }
}
