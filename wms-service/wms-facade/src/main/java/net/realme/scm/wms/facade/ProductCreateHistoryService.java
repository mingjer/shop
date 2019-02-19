package net.realme.scm.wms.facade;

import net.realme.scm.wms.domain.PushProductPayload;
import net.realme.scm.wms.domain.delhivery.DelhiveryProduct;

import java.util.List;

public interface ProductCreateHistoryService {
     int batchInsert(List<? extends PushProductPayload> delhiveryProducts) ;

    int insert(PushProductPayload pushProductPayload);

    List<DelhiveryProduct> filterHistroy(List<DelhiveryProduct> delhiveryProducts);
}
