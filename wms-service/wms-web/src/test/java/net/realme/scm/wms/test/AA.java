package net.realme.scm.wms.test;

import java.io.Serializable;
import java.util.List;

public class AA {
    List<BB> b;

    public List<BB> getB() {
        return b;
    }

    public void setB(List<BB> b) {
        this.b = b;
    }

    public static class BB implements Serializable {


        private static final long serialVersionUID = 7659891715013501554L;
    }
}
