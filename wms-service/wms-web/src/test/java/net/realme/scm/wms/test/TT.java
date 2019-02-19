package net.realme.scm.wms.test;

import org.apache.logging.log4j.spi.CopyOnWrite;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class TT implements Serializable {
    private static final long serialVersionUID = -4895455323201322152L;

    @Test
    public void ss() throws IOException {
        long l = System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000;
        Date d = new Date(l);
        System.out.println(d.toString());
        System.out.println("tt");
        AA a=new AA();
        List<AA.BB> b = a.getB();
        }
    }
