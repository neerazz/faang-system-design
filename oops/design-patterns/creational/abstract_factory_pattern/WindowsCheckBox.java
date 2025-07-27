package abstract_factory_pattern;

//Specific Product
public class WindowsCheckBox implements CheckBox {

    @Override
    public void paint() {
        System.out.println("Rendering Windows Checkbox");
    }
}
