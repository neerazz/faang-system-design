/**
 * @implNote :<br>
 * • Assume you have a Logger interface, which take care of different type of logging. <br>
 * ○ Console Logger writes the log to console,<br>
 * ○ File Logging, writes the log to file, also writes it to console.<br>
 * ○ Error Logger triggers some error event, and then write to File and also write to Console.<br>
 * • Now while implementing this design:<br>
 * ○ Say for, File logger you don’t have to write the implementation of both File and then console.<br>
 * ○ The Logger can be chained with a field called sentLogger()<br>
 * While creating the logger object you define the chain of order.<br>
 */

public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        LoggerFactory loggerFactory = new LoggerFactory();
        Logger consoleLogger = loggerFactory.getLogger(1);
        consoleLogger.logMessage(1, "Testing Console Logger");
    }
}

abstract class Logger {
    Logger nextLogger;
    int level;

    public Logger(int level) {
        this.level = level;
    }

    void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    void logMessage(int level, String message) {
        if (this.level <= level) write(message);
        if (this.nextLogger != null) this.nextLogger.logMessage(level, message);
    }

    abstract void write(String message);
}

class FileLogger extends Logger {

    public FileLogger(int level) {
        super(level);
    }

    @Override
    void write(String message) {
        System.out.println("File Logger message:   " + message);
    }
}

class ConsoleLogger extends Logger {

    public ConsoleLogger(int level) {
        super(level);
    }

    @Override
    void write(String message) {
        System.out.println("Console Logger message:" + message);
    }
}

class ErrorLogger extends Logger {

    public ErrorLogger(int level) {
        super(level);
    }

    @Override
    void write(String message) {
        System.out.println("Error Logger message:  " + message);
    }
}

class LoggerFactory {
    Logger errorLogger;
    Logger fileLogger;
    Logger consoleLogger;

    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR = 3;

    public LoggerFactory() {
        consoleLogger = new ConsoleLogger(INFO);
        fileLogger = new FileLogger(DEBUG);
        errorLogger = new ErrorLogger(ERROR);

        fileLogger.setNextLogger(consoleLogger);
        errorLogger.setNextLogger(fileLogger);
    }

    Logger getLogger(int level) {
        switch (level){
            case INFO : return consoleLogger;
            case DEBUG : return fileLogger;
            case ERROR : return errorLogger;
            default: return null;
        }
    }
}