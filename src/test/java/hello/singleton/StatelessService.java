package hello.singleton;

public class StatelessService {
    public int order(String name, int price){
        System.out.println(price);
        return price;
    }
}
