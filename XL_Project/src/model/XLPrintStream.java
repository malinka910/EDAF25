package model;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Set;

//TODO move to another package
// Moved to data package
public class XLPrintStream extends PrintStream {
    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    // TODO Change Object to something appropriate
    // Will be an object of type data.Slot
    public void save(Set<Entry<String, Slot>> set) {
        for (Entry<String, Slot> entry : set) {
            print(entry.getKey());
            print('=');
            println(entry.getValue().getContent());
        }
        flush();
        close();
    }
}
