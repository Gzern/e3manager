package bupt.e3.service.impl;

import bupt.e3.mapper.TbItemMapper;
import bupt.e3.pojo.TbItem;
import bupt.e3.pojo.TbItemExample;
import bupt.e3.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by king on 2017/10/14.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(Long id) {
        //根据主键查询
//        return itemMapper.selectByPrimaryKey(id);
        //根据条件查询 构建样本条件对象
        TbItemExample example=new TbItemExample();
        //创建查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        //执行查询条件
        List<TbItem> list=itemMapper.selectByExample(example);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
