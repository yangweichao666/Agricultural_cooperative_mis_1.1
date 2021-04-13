package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.Member;
import com.ywc.agric.pojo.Member;
import com.ywc.agric.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YWC
 * @Date 2021/4/12 20:37
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    MemberService memberService;

    @PostMapping("/findPage")
    public Result findById(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Member> memberPageResult = memberService.findPage(queryPageBean);
        return new Result(true, "查询会员信息成功", memberPageResult);

    }

    /**
     * 新增添加
     *
     * @param member
     * @return
     */
    //添加会员添加的权限控制
//    @PreAuthorize("ITEM_ADD")
    @PostMapping("/add")
    public Result add(@RequestBody Member member) {
        try {
            memberService.add(member);
            return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MEMBER_FAIL);
        }

    }

    /**
     * 进行删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result deleteById(int id) {
        //也可以使用已经定义了全局异常处理
//        这里用的是 上抛自定义异常
        try {
        memberService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_MEMBER_SUCCESS);

        } catch (RuntimeException e) {
            e.printStackTrace();
            return  new Result(false,e.getMessage());
        }
    }

    /**
     * 根据id查询会员
     *
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public Result findById(int id) {
        Member member= memberService.findById(id);
        return new Result(true, MessageConstant.GET_MEMBER_SUCCESS, member);
    }

    /**
     * 编辑
     *
     * @param member
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Member member) {
        memberService.update(member);
        return new Result(true, MessageConstant.EDIT_MEMBER_SUCCESS);
    }
}
