public class LogProcessor {
    LogType logType;
    LogProcessor nextLogProcessor;

    public LogProcessor(LogType logType) {
        this.logType = logType;
    }

    public void setNextLogProcessor(LogProcessor nextLogProcessor) {
        this.nextLogProcessor = nextLogProcessor;
    }

    public void logMessage(LogType logType, String message) {
        if(this.logType == logType) {
            System.out.println("Logging Message" + logType + "-" + message);
            return;
        }

        if(this.nextLogProcessor != null) {
            this.nextLogProcessor.logMessage(logType, message);
        }
    }

}