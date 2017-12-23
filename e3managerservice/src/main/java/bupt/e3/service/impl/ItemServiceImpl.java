package bupt.e3.service.impl;

import bupt.e3.mapper.TbItemCatMapper;
import bupt.e3.mapper.TbItemDescMapper;
import bupt.e3.mapper.TbItemMapper;
import bupt.e3.pojo.*;
import bupt.e3.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import e3.common.pojo.EasyUiDataGridResult;
import e3.common.pojo.EasyUiTreeNode;
import e3.common.utils.E3Result;
import e3.common.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by king on 2017/10/14.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;


    @Override
    public TbItem getItemById(Long id) {
        //根据主键查询
//        return itemMapper.selectByPrimaryKey(id);
        //根据条件查询 构建样本条件对象
        TbItemExample example = new TbItemExample();
        //创建查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        //执行查询条件
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public EasyUiDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        EasyUiDataGridResult res = new EasyUiDataGridResult();
        TbItemExample example = new TbItemExample();
        //查询数据 返回Page对象(设置了分页查询,查询返回的是一个已经封装原结果集的ArrayList对象)
        List<TbItem> list = itemMapper.selectByExample(example);
        //根据Page对象构建PageInfo对象
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        res.setTotal(pageInfo.getTotal());
        res.setRows(pageInfo.getList());
        return res;
    }

    @Override
    public E3Result addItem(TbItem item, String desc) {
        //id是自己生成的 所以要给商品生成id
        long itemId=IDUtil.getItemId();
        item.setId(itemId);
        //补全item时间属性
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //1-正常 2-下架 3-删除
        item.setStatus((byte) 1);
        itemMapper.insert(item);

        //插入商品描述信息
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return E3Result.ok();
    }


















    /*@Override
    public Map<String,Object> getDataGrid(int startPage, int pageSize) {
        //设置分页查询条件
        PageHelper.startPage(startPage,pageSize);
        //执行条件查询
        TbItemExample example=new TbItemExample();
        List<TbItem> list=itemMapper.selectByExample(example);
        //构建分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
        //构建datagrid表格所需要的数据格式
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }*/
}
