package Model.Services;

public class Counter implements AutoCloseable {
    public static int idCount = 0;

    public Counter(int start){
        idCount = start+1;
    }
    public  int add(int size) throws CounterException {
        if(size<3){
            throw new CounterException();
        }
        return idCount++;
    }

    @Override
    public void close() {
        idCount = 0;
        System.out.println("Counter closed");
    }
}
