package com.labex.Controller;

import com.labex.Beans.*;
import com.labex.Services.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class WorkController {
    @Autowired
    ComposeServiceImpl composeService;
    @Autowired
    StudentItemServiceImpl studentItemService;
    @Autowired
    StudentItemOthersServiceImpl studentItemOthersService;
    @Autowired
    ComposeController composeController;
    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    StudentComposeServiceImpl studentComposeService;

    @PostMapping("/stuWork")
    public InfoBean stuWork(
            @RequestParam Integer student_id,
            @RequestParam Integer compose_no,
            @RequestParam Integer compose_type,
            @RequestParam List<String> answerList,
            @RequestParam(required = false) String pic,
            @RequestParam(required = false) String attachment
    ) {
        InfoBean infoBean = new InfoBean();
        HashMap<String, Object> columnMap = new HashMap<>();
        try {
            columnMap.put("compose_no",compose_no);
            Integer index = 1;
            for (String answer:
                    answerList) {
                columnMap.put("item_index",index);
                for (ComposeBean composeBean:
                        composeService.listByMap(columnMap)) {
                    StudentItemBean studentItemBean = new StudentItemBean();
                    StudentItemOthersBean studentItemOthersBean = new StudentItemOthersBean();
                    studentItemBean.setStudent_id(student_id);
                    studentItemBean.setItem_id(composeBean.getItem_id());
                    studentItemBean.setContent(answer);
                    studentItemBean.setScore(-1);
                    //StudentItemBean还要补全非表字段
                    studentItemBean.setCompose_no(compose_no);
                    studentItemBean.setCompose_type(compose_type);
                    studentItemOthersBean.setStudent_item_id(studentItemService.getMaxId()+1);
                    studentItemOthersBean.setCompose_no(compose_no);
                    studentItemOthersBean.setCompose_type(compose_type);
                    studentItemService.save(studentItemBean);
                    studentItemOthersService.save(studentItemOthersBean);
                }
                index++;
            }
            infoBean.setStatus_code(0);
            infoBean.setInfo("提交成功");
            HashMap<String, Object> columnMap1 = new HashMap<>();
            columnMap1.put("student_id",student_id);
            columnMap1.put("compose_no",compose_no);
            composeController.changeState(studentComposeService.listByMap(columnMap1).get(0).getStudent_compose_id());
        } catch (Exception e) {
            e.printStackTrace();
            infoBean.setStatus_code(1);
            infoBean.setInfo("提交失败");
        }
        return infoBean;
    }

    @GetMapping("/teaGetAns")
    public List<String> teaGetAns(@RequestParam Integer no,@RequestParam Integer type) {
        List<String> answerList = new ArrayList<>();
        for (ItemBean itemBean:
        composeController.getComposedItems(no,type)) {
            //TODO 未处理答案顺序
            answerList.add(itemBean.getAnswer());
        }
        return answerList;
    }

    @PostMapping("/teaWork")
    public InfoBean teaWork(
            @RequestParam Integer compose_no,
            @RequestParam Integer type,
            @RequestParam Integer teacher_id,
            @RequestParam(value = "markingList[]") List<JsonMarking> markingList
            ) {
        InfoBean infoBean = new InfoBean();
        Map<Integer, Integer> markingMap = markingList.stream().collect(Collectors.toMap(JsonMarking::getIndex,JsonMarking::getScore));
        HashMap<String, Object> columnMap = new HashMap<>();
        HashMap<String, Object> columnMap1 = new HashMap<>();
        try {
            columnMap.put("teacher_id",teacher_id);
            columnMap.put("compose_no",compose_no);
            for (StudentComposeBean studentComposeBean:
                    studentComposeService.listByMap(columnMap)) {
                Integer student_id = studentComposeBean.getStudent_id();
                Integer score_total = 0;
                for (ItemBean itemBean:
                        composeController.getComposedItems(compose_no,type)) {
                    Integer item_id = itemBean.getItem_id();
                    Integer item_index = composeController.getItemIndexInCompose(compose_no,item_id);
                    columnMap1.put("item_id",item_id);
                    columnMap1.put("student_id",student_id);
                    for (StudentItemBean studentItemBean:
                        //TODO StudentItemBean还要补全非表字段
                            studentItemService.listByMap(columnMap1)) {
                        studentItemBean.setScore(markingMap.get(item_index));
                        studentItemBean.setCompose_no(compose_no);
                        studentItemBean.setCompose_type(type);
                        score_total+=markingMap.get(item_index);
                        studentItemService.save(studentItemBean);
                    }
                }
                studentComposeBean.setScore_total(score_total);
                studentComposeService.save(studentComposeBean);
            }
            infoBean.setStatus_code(0);
            infoBean.setInfo("批改成功");
        } catch (Exception e) {
            e.printStackTrace();
            infoBean.setStatus_code(1);
            infoBean.setInfo("批改失败");
        }
        return infoBean;
    }

    @GetMapping("/reviewStuWork")
    public List<ReviewVO> reviewStuWork(@RequestParam Integer student_id, @RequestParam Integer compose_no, @RequestParam Integer type) {
        List<ReviewVO> reviewVOList = new ArrayList<>();
        for (ItemBean itemBean:
        composeController.getComposedItems(compose_no,type)) {
            ReviewVO reviewVO = new ReviewVO();
            reviewVO.setStudent_id(student_id);
            reviewVO.setItem_id(itemBean.getItem_id());
            reviewVO.setCompose_no(compose_no);
            reviewVO.setTitle(itemBean.getTitle());
            reviewVO.setContent(itemBean.getContent());
            reviewVO.setAnswer(itemBean.getAnswer());
            HashMap<String, Object> columnMap = new HashMap<>();
            columnMap.put("item_id",itemBean.getItem_id());
            columnMap.put("student_id",student_id);
            for (StudentItemBean studentItemBean:
            studentItemService.listByMap(columnMap)) {
                //TODO S/tudentItemBean还要补全非表字段
                studentItemBean.setCompose_no(compose_no);
                studentItemBean.setCompose_type(type);
                reviewVO.setStu_answer(studentItemBean.getContent());
                reviewVO.setScore(studentItemBean.getScore());
                reviewVO.setIsCorrect(studentItemBean.getScore() >= itemService.getById(itemBean.getItem_id()).getFull_score());
                reviewVOList.add(reviewVO);
            }
        }
        return reviewVOList;
    }

}
