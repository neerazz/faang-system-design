package abstract_factory_pattern;

//Specific Product
public class MacCheckBox  implements CheckBox{
    @Override
    public void paint() {
        System.out.println("Rendering Mac checkbox");
    }
}
