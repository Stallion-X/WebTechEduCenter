package com.labex.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labex.Beans.*;
import com.labex.Services.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class ComposeController {
    @Autowired
    ComposeServiceImpl composeService;
    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    StudentComposeServiceImpl studentComposeService;
    @Autowired
    StudentController studentController;

    @PostMapping("/compose")
    public InfoBean compose(@RequestBody JsonCompose jsonCompose) {
        InfoBean infoBean = new InfoBean();
        try {
            Integer maxNo = composeService.getMaxNo()+1;
            for (int i=0;i<jsonCompose.getIndex_id().size();i++) {
                ComposeBean composeBean = new ComposeBean();
                composeBean.setCompose_no(maxNo);
                composeBean.setCompose_type(jsonCompose.getCompose_type());
                composeBean.setCompose_name(jsonCompose.getCompose_name());
                composeBean.setTeacher_id(jsonCompose.getTeacher_id());
                composeBean.setClazz_no(jsonCompose.getClazz_no());
                composeBean.setStart_time(jsonCompose.getStart_time());
                composeBean.setEnd_time(jsonCompose.getEnd_time());
//            for (Integer item_id:
//                    jsonCompose.getItem_id()) {
//                composeBean.setItem_id(item_id);
//                composeService.save(composeBean);
//            }
                Integer item_id = jsonCompose.getIndex_id().get(i).getItem_id();
                Integer item_index = jsonCompose.getIndex_id().get(i).getItem_index();
                composeBean.setItem_id(item_id);
                composeBean.setItem_index(item_index);
                composeService.save(composeBean);
            }
            studentController.getStudentsByClazz(jsonCompose.getClazz_no()).forEach(studentBean -> {
                StudentComposeBean studentComposeBean = new StudentComposeBean();
                studentComposeBean.setCompose_no(maxNo);
                studentComposeBean.setCompose_state(0);
                studentComposeBean.setStudent_id(studentBean.getStudent_id());
                studentComposeBean.setTeacher_id(jsonCompose.getTeacher_id());
                studentComposeBean.setScore_total(0);
                studentComposeService.save(studentComposeBean);
            });
            infoBean.setStatus_code(0);
            infoBean.setInfo("组卷成功");
        } catch(Exception e) {
            e.printStackTrace();
            infoBean.setStatus_code(1);
            infoBean.setInfo("组卷失败");
        }
        return infoBean;
    }

    @PostMapping("/decompose")
    public InfoBean decompose(@RequestParam Integer no) {
        InfoBean infoBean = new InfoBean();
        HashMap<String, Object> columnMap = new HashMap<>();
        try {
            columnMap.put("compose_no",no);
            composeService.removeByMap(columnMap);
            infoBean.setStatus_code(0);
            infoBean.setInfo("删除成功");
        } catch(Exception e) {
            infoBean.setStatus_code(1);
            infoBean.setInfo("删除失败");
        }
        return infoBean;
    }

    @GetMapping("/getCompose")
    public List<ComposeBean> getCompose(@RequestParam Integer no) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("compose_no",no);
        return composeService.listByMap(columnMap);
    }

    public Integer getItemIndexInCompose(Integer compose_no, Integer item_id) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("compose_no",compose_no);
        columnMap.put("item_id",item_id);
        return composeService.listByMap(columnMap).get(0).getItem_index();
    }

    @GetMapping("/getComposedItems")
    public List<ItemBean> getComposedItems(@RequestParam Integer no,@RequestParam Integer type) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("compose_no",no);
        columnMap.put("compose_type",type);
        List<ItemBean> itemBeanList = new ArrayList<>();
        for (ComposeBean composeBean:
        composeService.listByMap(columnMap)) {
            ItemBean itemBean = itemService.getById(composeBean.getItem_id());
            itemBean.setItem_index(getItemIndexInCompose(no,composeBean.getItem_id()));
            itemBeanList.add(itemBean);
        }
        return itemBeanList;
    }

    @GetMapping("/getComposeLite")
    public ComposeBean getComposeLite(@RequestParam Integer no) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("compose_no",no);
        ComposeBean result = composeService.listByMap(columnMap).get(0);
        ComposeBean liteComposeBean = new ComposeBean();
        liteComposeBean.setCompose_no(result.getCompose_no());
        liteComposeBean.setCompose_name(result.getCompose_name());
        liteComposeBean.setTeacher_id(result.getTeacher_id());
        liteComposeBean.setClazz_no(result.getClazz_no());
        return liteComposeBean;
    }

//    public List<ComposeBean> getComposeListLite1(HashMap<String, Object> columnMap) {
//        List<ComposeBean> result = composeService.listByMap(columnMap);
//        List<ComposeBean> liteComposeBeanList = new ArrayList<>();
//        Integer tempNo = null;
//        boolean isFirst = true;
//        for (ComposeBean composeBean:
//                result) {
//            ComposeBean liteComposeBean = new ComposeBean();
//            liteComposeBean.setCompose_no(composeBean.getCompose_no());
//            liteComposeBean.setCompose_name(composeBean.getCompose_name());
//            liteComposeBean.setClazz_no(composeBean.getClazz_no());
//            liteComposeBean.setCompose_state(composeBean.getCompose_state());
//            liteComposeBean.setEnd_time(composeBean.getEnd_time());
//            if (!Objects.equals(composeBean.getCompose_no(), tempNo)|| isFirst) {
//                liteComposeBeanList.add(liteComposeBean);
//                isFirst = false;
//            }
//            tempNo = composeBean.getCompose_no();
//        }
//        return liteComposeBeanList;
//    }
    public List<StudentComposeBean> getComposeListLite(HashMap<String, Object> columnMap) {
        List<ComposeBean> result = composeService.listByMap(columnMap);
        List<StudentComposeBean> liteComposeBeanList = new ArrayList<>();
        Integer tempNo = null;
        boolean isFirst = true;
        for (ComposeBean composeBean:
                result) {
            StudentComposeBean liteComposeBean = new StudentComposeBean();
            liteComposeBean.setCompose_no(composeBean.getCompose_no());
            HashMap<String, Object> columnMap1 = new HashMap<>();
            columnMap1.put("compose_no",composeBean.getCompose_no());
            if (studentComposeService.listByMap(columnMap1).size()>0) {
                StudentComposeBean studentComposeBean = studentComposeService.listByMap(columnMap1).get(0);
                liteComposeBean.setCompose_state(studentComposeBean.getCompose_state());
            }
            liteComposeBean.setCompose_state(0);
            liteComposeBean.setCompose_name(composeBean.getCompose_name());
            liteComposeBean.setClazz_no(composeBean.getClazz_no());
            liteComposeBean.setEnd_time(composeBean.getEnd_time());
            if (!Objects.equals(composeBean.getCompose_no(), tempNo)|| isFirst) {
                liteComposeBeanList.add(liteComposeBean);
                isFirst = false;
            }
            tempNo = composeBean.getCompose_no();
        }
        return liteComposeBeanList;
    }

    @GetMapping("/tGetComposeList")
    public List<StudentComposeBean> tGetComposeList(@RequestParam Integer id,@RequestParam Integer type) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("teacher_id",id);
        columnMap.put("compose_type",type);
        return getComposeListLite(columnMap);
    }

    @GetMapping("/sGetComposeList")
    public List<StudentComposeBean> tGetComposeList(@RequestParam String no,@RequestParam Integer type) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("clazz_no",no);
        columnMap.put("compose_type",type);
        return getComposeListLite(columnMap);
    }
    //TODO 还要补充一个 学生交卷时改变StudentCompose的状态 学生或教师查阅时联查StudentCompose的状态
    public void changeState(Integer student_compose_id) {
        StudentComposeBean studentComposeBean = studentComposeService.getById(student_compose_id);
        if (studentComposeBean.getCompose_state().equals(0)) {
            studentComposeBean.setCompose_state(1);
        }
        studentComposeService.save(studentComposeBean);
    }
}
