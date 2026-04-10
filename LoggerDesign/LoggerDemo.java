public class LoggerDemo {
    public static void main(String[] args) {
        LogProcessor info = new InfoProcessor();
        LogProcessor debug = new DebugProcessor();
        LogProcessor error = new ErrorProcessor();

        info.setNextLogProcessor(debug);
        debug.setNextLogProcessor(error);
        error.setNextLogProcessor(null);

        info.logMessage(LogType.DEBUG, "debugging");
        info.logMessage(LogType.ERROR, "Error");
        info.logMessage(LogType.INFO, "Info");
    }
}