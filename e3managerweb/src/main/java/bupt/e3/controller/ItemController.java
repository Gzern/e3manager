package bupt.e3.controller;

import bupt.e3.pojo.TbItem;
import bupt.e3.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by king on 2017/10/14.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;


    @RequestMapping("/get")
    @ResponseBody
    public TbItem getItem(Long id){
        return itemService.getItemById(id);
    }
}
