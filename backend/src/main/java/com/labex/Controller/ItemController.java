package com.labex.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labex.Beans.*;
import com.labex.Services.Impl.*;
import com.labex.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class ItemController {
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    StudentItemServiceImpl studentItemService;
    @Autowired
    StudentItemOthersServiceImpl studentItemOthersService;
    @Autowired
    ComposeController composeController;
    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/createItem")
    public InfoBean createItem(@RequestBody ItemBean newItem) {
        InfoBean infoBean = new InfoBean();
        try {
            itemService.save(newItem);
            infoBean.setStatus_code(0);
            infoBean.setInfo("创建题目成功");
        } catch(Exception e) {
            infoBean.setStatus_code(1);
            infoBean.setInfo("创建题目失败");
        }
        return infoBean;
    }

    @PostMapping("/removeItem")
    public InfoBean removeItem(@RequestParam Integer id) {
        InfoBean infoBean = new InfoBean();
        try {
            itemService.removeById(id);
            infoBean.setStatus_code(0);
            infoBean.setInfo("删除成功");
        } catch(Exception e) {
            infoBean.setStatus_code(1);
            infoBean.setInfo("删除失败");
        }
        return infoBean;
    }

    @GetMapping("/getItemInfo")
    public ItemBean getItemInfo(@RequestParam Integer id) {
        return itemService.getById(id);
    }

    public List<ItemBean> getItemListLite(HashMap<String, Object> columnMap) {
        List<ItemBean> result = itemService.listByMap(columnMap);
        List<ItemBean> liteItemBeanList = new ArrayList<>();
        for (ItemBean itemBean:
                result) {
            ItemBean liteItemBean = new ItemBean();
            liteItemBean.setItem_id(itemBean.getItem_id());
            liteItemBean.setQuestion_type(itemBean.getQuestion_type());
            liteItemBean.setContent(itemBean.getContent());
            liteItemBeanList.add(liteItemBean);
        }
        return liteItemBeanList;
    }

    @GetMapping("/getItemInfoLite")
    public List<ItemBean> getItemInfoLite(@RequestParam Integer teacher_id, @RequestParam Integer item_type) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("teacher_id",teacher_id);
        columnMap.put("item_type",item_type);
        return getItemListLite(columnMap);
    }

    @GetMapping("/getComposedItemsInfo")
    public List<StudentItemBean> getComposedItemsInfo(@RequestParam Integer compose_no,@RequestParam Integer type,@RequestParam Integer student_id) {
        List<ItemBean> itemBeanList = composeController.getComposedItems(compose_no,type);
        List<StudentItemBean> studentItemBeans = new ArrayList<>();
        HashMap<String, Object> columnMap = new HashMap<>();
        itemBeanList.forEach(itemBean -> {
            columnMap.put("item_id",itemBean.getItem_id());
            columnMap.put("student_id",student_id);
            //TODO 修改支持查询学生对同一道题做多次的情况
            //TODO StudentItemBean还要补全非表字段
            if (studentItemService.listByMap(columnMap).size()>0) {
                studentItemService.listByMap(columnMap).forEach(temp -> {
                    HashMap<String, Object> columnMap1 = new HashMap<>();
                    columnMap1.put("student_item_id",temp.getStudent_item_id());
                    StudentItemOthersBean othersBean = studentItemOthersService.listByMap(columnMap1).get(0);
                    temp.setCompose_no(othersBean.getCompose_no());
                    temp.setCompose_type(othersBean.getCompose_type());
                    studentItemBeans.add(temp);
                });

            }
        });
        return studentItemBeans;
    }
}
