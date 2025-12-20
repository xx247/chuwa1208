package PaperResource;
//- Create `DataProcessor` class implementing `AutoCloseable`:
//  - `private String dataSource`
//  - Constructor accepting data source name
//  - `public void processData()` - simulates processing
//  - `@Override public void close()` - print "Closing data processor: [dataSource]"
public class DataProcessor implements AutoCloseable{
    private String dataSource;

    public DataProcessor(String dataSource){
        this.dataSource = dataSource;
    }

    public void processData(){
        System.out.println("Data is processing");
    }

    @Override
    public void close(){
        System.out.println("Closing data processor: " + dataSource);
    }
}
