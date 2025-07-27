package abstract_factory_pattern;

//Specific Product
public class WindowsButton  implements Button{
    @Override
    public void paint() {
        System.out.println("Rendering Windows button");
    }
}
