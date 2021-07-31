public class AbstractFactoryPatternTester {

    public static void main(String[] args) {
        //Get OS from a property file
        GUIFactory factory = null;
        String os = "Mac";
        if ("Windows".equals(os)) {
            factory = new WindowsFactory();
        } else if ("Mac".equals(os)) {
            factory = new MacFactory();
        }
        Button button = factory.createButton();
        CheckBox checkBox = factory.createCheckBox();

        button.paint();
        checkBox.paint();
    }

}
