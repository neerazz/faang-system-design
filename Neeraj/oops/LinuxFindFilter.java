import java.security.InvalidParameterException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on:  Mar 28, 2021
 * Questions:
 * Design an implement Linux find command as an API.
 * The API will support finding files that has given size requirements, and a file with a certain format like
 */

public class LinuxFindFilter {

    public static void main(String[] args) {
        FilterRunner filterRunner = new FilterRunner();
        System.out.println(filterRunner.applyFilter(Arrays.asList(
                new File("file1", ".txt", 10),
                new File("file2", ".zip", 100),
                new File("file3", ".zar", 20),
                new File("file4", ".mp3", 150),
                new File("file5", ".txt", 250),
                new File("file6", ".txt", 2560),
                new File("file7", ".txt", 120)
        ), "file"));

        System.out.println(filterRunner.applyFilter(Arrays.asList(
                new File("file1", ".txt", 10),
                new File("file2", ".zip", 100),
                new File("file3", ".zar", 20),
                new File("file4", ".mp3", 150),
                new File("file5", ".txt", 250),
                new File("file6", ".txt", 2560),
                new File("file7", ".txt", 120)
        ), "newFile"));

        System.out.println(filterRunner.applyFilter(Arrays.asList(
                new File("file1", ".txt", 10),
                new File("file2", ".zip", 100),
                new File("file3", ".zar", 20),
                new File("file4", ".mp3", 150),
                new File("file5", ".txt", 250),
                new File("file6", ".txt", 2560),
                new File("file7", ".txt", 120)
        ), "file", ">150"));

        System.out.println(filterRunner.applyFilter(Arrays.asList(
                new File("file1", ".txt", 10),
                new File("file2", ".zip", 100),
                new File("file3", ".zar", 20),
                new File("file4", ".mp3", 150),
                new File("file5", ".txt", 250),
                new File("file6", ".txt", 2560),
                new File("file7", ".txt", 120)
        ), "7", "<100"));

        System.out.println(filterRunner.applyFilter(Arrays.asList(
                new File("file1", ".txt", 10),
                new File("file2", ".zip", 100),
                new File("file3", ".zar", 20),
                new File("file4", ".mp3", 150),
                new File("file5", ".txt", 250),
                new File("file6", ".txt", 2560),
                new File("file7", ".txt", 120)
        ), "file", "<=150", ".txt"));
    }
}

abstract class Filter {
    Filter nextFilter;
    Function<File, Boolean> fileBooleanFunction;

    Filter(String filter) {
        setFileBooleanFunction(filter);
    }

    public List<File> process(List<File> files) {
        List<File> filtered = files.stream().filter(file -> fileBooleanFunction.apply(file)).collect(Collectors.toList());
        return next(filtered);
    }

    public List<File> next(List<File> files) {
        if (nextFilter == null) return files;
        return nextFilter.process(files);
    }

    public Filter setNextFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
        return this;
    }

    public abstract void setFileBooleanFunction(String filter);

}

class File {
    String name, type;
    int size;

    public File(String name, String type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}

class SizeFilter extends Filter {

    SizeFilter(String filter) {
        super(filter);
    }

    @Override
    public void setFileBooleanFunction(String filter) {
        if (filter == null || filter.length() < 2) {
            throw new InvalidParameterException("Invalid Size Filter");
        }
        try {
            if (filter.startsWith(">=")) {
                int val = Integer.parseInt(filter.trim().substring(2));
                fileBooleanFunction = file -> file.size >= val;
            } else if (filter.startsWith("<=")) {
                int val = Integer.parseInt(filter.trim().substring(2));
                fileBooleanFunction = file -> file.size <= val;
            } else {
                int val = Integer.parseInt(filter.trim().substring(1));
                if (filter.charAt(0) == '>') {
                    fileBooleanFunction = file -> file.size > val;
                } else if (filter.charAt(0) == '<') {
                    fileBooleanFunction = file -> file.size < val;
                } else if (filter.charAt(0) == '=') {
                    fileBooleanFunction = file -> file.size == val;
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Number Value");
        }
    }
}

class TypeFilter extends Filter {

    TypeFilter(String filter) {
        super(filter);
    }

    @Override
    public void setFileBooleanFunction(String type) {
        fileBooleanFunction = file -> file.type.equals(type);
    }
}

class NameFilter extends Filter {

    NameFilter(String filter) {
        super(filter);
    }

    @Override
    public void setFileBooleanFunction(String filter) {
        fileBooleanFunction = file -> file.name.contains(filter);
    }
}

class FilterRunner {

    //    Keep adding more Methods or Create an builder patern that creates filter.
    List<File> applyFilter(List<File> files, String name) {
        Filter filter = new NameFilter(name);
        return filter.process(files);
    }

    List<File> applyFilter(List<File> files, String name, String size) {
        Filter nameFilter = new NameFilter(name);
        Filter sizeFilter = new SizeFilter(size).setNextFilter(nameFilter);
        return sizeFilter.process(files);
    }

    List<File> applyFilter(List<File> files, String name, String size, String type) {
        Filter nameFilter = new NameFilter(name);
        Filter sizeFilter = new SizeFilter(size).setNextFilter(nameFilter);
        Filter typeFilter = new TypeFilter(type).setNextFilter(sizeFilter);
        return typeFilter.process(files);
    }
}