package PaperResource;
//- Create `LogWriter` class implementing `AutoCloseable`:
//  - `private String filename`
//  - Constructor accepting filename
//  - `public void writeLog(String message)` - simulates writing (just print)
//  - `@Override public void close()` - print "Closing log file: [filename]"
public class LogWriter implements AutoCloseable{
    private String filename;

    public LogWriter(String filename){
        this.filename = filename;
    }

    public void writeLog(String message){
        System.out.println("write log: " + message);
    }
    @Override
    public void close(){
        System.out.println("Closing log file: "+filename);
    }

}
