import java.util.*;
import java.io.*;

public class FactoryPattern {

    public static void main(String[] args) {

    }
}

class F16Factory {
    F16 getF16(String version) {
        switch (version){
            case "v1": return new F16();
            case "v2": return new F16V2();
            case "v3": return new F16V3();
            default: return null;
        }
    }
}

class F16 {
    F16Engine f16Engine;
    F16Cockpit f16Cockpit;

    F16 makeF16() {
        f16Engine = new F16Engine();
        f16Cockpit = new F16Cockpit();
        return this;
    }
}

class F16V2 extends F16 {

    @Override
    F16 makeF16() {
        super.makeF16();
        f16Cockpit = new F16V2Cockpit();
        return this;
    }
}

class F16V3 extends F16 {

    @Override
    F16 makeF16() {
        super.makeF16();
        f16Cockpit = new F16V2Cockpit();
        f16Engine = new F16AV3Engine();
        return this;
    }
}

class F16Engine {

}

class F16Cockpit {

}

class F16AV3Engine extends F16Engine {

}

class F16V2Cockpit extends F16Cockpit {

}