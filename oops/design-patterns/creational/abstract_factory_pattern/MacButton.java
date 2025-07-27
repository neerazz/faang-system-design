package abstract_factory_pattern;

//Specific Product
public class MacButton  implements  Button{
    @Override
    public void paint() {
        System.out.println("Rendering Mac button");
    }
}
