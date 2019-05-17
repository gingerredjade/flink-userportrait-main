package com.jhy.reduce;

import com.jhy.entity.UserGroupInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户分群Reduce
 *		[整合用户的消费信息]
 *
 * Created by JHy on 2019/5/16.
 */
public class UserGroupInfoReduce implements ReduceFunction<UserGroupInfo>{

    @Override
    public UserGroupInfo reduce(UserGroupInfo userGroupInfo1, UserGroupInfo userGroupInfo2) throws Exception {
        String userid = userGroupInfo1.getUserid();
        List<UserGroupInfo> list1 = userGroupInfo1.getList();
        List<UserGroupInfo> list2 = userGroupInfo2.getList();

        UserGroupInfo userGroupInfofinal = new UserGroupInfo();
        List<UserGroupInfo> finallist = new ArrayList<UserGroupInfo>();
        finallist.addAll(list1);
        finallist.addAll(list2);
        userGroupInfofinal.setList(finallist);
        return userGroupInfofinal;
    }
}
