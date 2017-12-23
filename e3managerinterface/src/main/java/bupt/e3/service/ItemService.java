package bupt.e3.service;

import bupt.e3.pojo.TbItem;
import bupt.e3.pojo.TbItemCat;
import e3.common.pojo.EasyUiDataGridResult;
import e3.common.pojo.EasyUiTreeNode;
import e3.common.utils.E3Result;

import java.util.List;
import java.util.Map;

/**
 * Created by king on 2017/10/14.
 */
public interface ItemService {
    public TbItem getItemById(Long id);
//    public Map<String,Object> getDataGrid(int pageStart,int pageSize);

    public EasyUiDataGridResult getItemList(int page,int rows);


    public E3Result addItem(TbItem item,String desc);

}
