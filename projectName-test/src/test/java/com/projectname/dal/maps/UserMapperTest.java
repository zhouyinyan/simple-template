package com.projectname.dal.maps;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzt.test.base.BaseSpringContextTest;
import com.test.dal.mybatis.maps.UserMapper;
import com.test.dal.mybatis.model.User;
import com.test.dal.mybatis.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by zyinyan on 2015/6/25.
 */
public class UserMapperTest extends BaseSpringContextTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setName("zyinyan");
        user.setAge(18);
        userMapper.insertSelective(user);
    }


    @Test
    public void testSelect() throws Exception {
        UserExample userExample = new UserExample();
        userExample.or();
        RowBounds rowBounds = new RowBounds(1,10);
        userMapper.selectByExampleWithRowbounds(userExample,rowBounds);
    }

    @Test
    public void testSelect2() throws Exception {
        UserExample userExample = new UserExample();
        userExample.or();

        PageHelper.startPage(1,10);
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> page = new PageInfo<User>(users);

        //PageInfo包含了非常全面的分页属性
        assertEquals(1, page.getPageNum());
        assertEquals(10, page.getPageSize());
        assertEquals(1, page.getStartRow());
        assertEquals(2, page.getEndRow());
        assertEquals(2, page.getTotal());
        assertEquals(1, page.getPages());
        assertEquals(1, page.getFirstPage());
        assertEquals(1, page.getLastPage());
        assertEquals(true, page.isIsFirstPage());
        assertEquals(true, page.isIsLastPage());
        assertEquals(false, page.isHasPreviousPage());
        assertEquals(false, page.isHasNextPage());
    }
}
