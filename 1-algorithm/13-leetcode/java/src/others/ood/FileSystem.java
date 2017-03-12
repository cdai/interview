package others.ood;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 */
public class FileSystem {

    private Directory root = new Directory("");

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        Directory test = fs.mkdir("test", fs.root);
        Directory hello = fs.mkdir("hello", test);
        fs.mkdir("empty", test);
        fs.create("world.txt", hello, "hello world~~~");
        fs.tree(fs.root, 0, System.out);
    }

    public Directory mkdir(String name, Directory parent) {
        Directory dir = new Directory(name, parent);
        parent.addEntry(dir);
        return dir;
    }

    public File create(String name, Directory parent, String data) {
        File file = new File(name, parent, data);
        parent.addEntry(file);
        return file;
    }

    public void tree(Entry entry, int depth, PrintStream out) {
        for (int i = 0; i < depth; i++) {
            out.print("\t");
        }
        out.println(entry.getName().isEmpty() ? "/" : entry.getName());

        // Traverse next level
        if (entry.isDirectory()) {
            for (Entry e : (Directory) entry) {
                tree(e, depth + 1, out);
            }
        }
    }

}

abstract class Entry {
    protected Directory parent;
    protected String name;
    protected long created;
    protected long lastUpdated;
    protected long lastAccessed;

    public Entry(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getFullPath() {
        if (parent == null) return "";
        return getFullPath() + "/" + name;
    }

    public abstract boolean isDirectory();

    public String getName() {
        return name;
    }
}

class File extends Entry {
    private String data;
    private int size;

    public File(String name, Directory parent, String data) {
        super(name, parent);
        this.data = data;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}

class Directory extends Entry implements Iterable<Entry> {
    private List<Entry> entries = new ArrayList<>();

    public Directory(String name) {
        super(name, null);
    }

    public Directory(String name, Directory parent) {
        super(name, parent);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public void deleteEntry(Entry entry) {
        entries.remove(entry);
    }

    @Override
    public Iterator<Entry> iterator() {
        return entries.iterator();
    }
}
