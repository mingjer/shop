package net.realme.scm.wms.impl;

import net.realme.scm.wms.beantool.DelhiveryConverter;
import net.realme.scm.wms.dao.ProductCreateHistoryMapper;
import net.realme.scm.wms.domain.PushProductPayload;
import net.realme.scm.wms.domain.delhivery.DelhiveryProduct;
import net.realme.scm.wms.facade.ProductCreateHistoryService;
import net.realme.scm.wms.model.ProductCreateHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductCreateHistoryServiceImpl implements ProductCreateHistoryService {
    @Autowired
    private ProductCreateHistoryMapper productCreateHistoryMapper;
    @Autowired
    private DelhiveryConverter delhiveryConverter;

    @Override
    public int batchInsert(List<? extends PushProductPayload> delhiveryProducts) {

        List<ProductCreateHistory> productCreateHistories = delhiveryConverter.toProductCreateHistroyList(delhiveryProducts);

        Integer i = productCreateHistoryMapper.batchInsert(productCreateHistories);

        return i;
    }

   @Override
    public int insert(PushProductPayload pushProductPayload) {
        DelhiveryProduct delhiveryProduct=(DelhiveryProduct) pushProductPayload;
        ProductCreateHistory productCreateHistory=delhiveryConverter.toProductCreateHistroy(pushProductPayload);
        Integer i= productCreateHistoryMapper.insert(productCreateHistory);
        return i;
    }

    @Override
    public List<DelhiveryProduct> filterHistroy(List<DelhiveryProduct> delhiveryProducts) {
        List<DelhiveryProduct> result = new ArrayList<>();

        Example example = new Example(ProductCreateHistory.class); ;
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("vendor","delhivery");
        List<ProductCreateHistory> productCreateHistories = productCreateHistoryMapper.selectByExample(example);

        List<String> list = productCreateHistories.stream().parallel().map(item -> {
            String s = item.getNumber();
            return s;
        }).collect(Collectors.toList());

//        List<DelhiveryProduct> delhiveryProducts2 = delhiveryConverter.toDelhiveryProductList(productCreateHistories);

        result =  delhiveryProducts.stream().filter(item ->
        {
            if (list.contains(item.getNumber())) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        return result;
    }
}
