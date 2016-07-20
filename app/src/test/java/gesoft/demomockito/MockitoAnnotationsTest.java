package gesoft.demomockito;


import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by yhr on 2016/7/20.
 * Mockito 测试类
 * 在 Mockito 中你可以使用 mock() 方法来创建一个模拟对象，
 * 也可以使用注解的方式 @Mock 来创建 ，这里推荐使用注解。
 * 需要注意的是，如果是使用注解方式，需要在使用前进行初始化,
 * 这里有三种初始化方式：初始化1、初始化2、初始化3
 */

/**
 * 初始化2：使用 @RunWith(MockitoJUnitRunner.class) 方式
 * @RunWith(MockitoJUnitRunner.class)
 */
public class MockitoAnnotationsTest {

    @Mock
    AccountData accountData;

    /**
     * 初始化3：使用 MockitoRule 方式
     * @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
     */


    /**
     * 初始化1：使用 MockitoAnnotations.initMocks(this) 方式
     */
    @Before
    public void setupAccountData(){
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 希望 isLogin() 方法被调用时返回true
     */
    @Test
    public void testIsLogin(){
        when( accountData.isLogin() ).thenReturn(true);
        boolean isLogin = accountData.isLogin();
        assertTrue(isLogin);
    }

    /**
     * 希望对 setUserName(String userName) 方法中参数进行测试
     */
    @Test
    public void testWhenThenReturn(){
        when(accountData.getUserName()).thenReturn("Jack");
        assertEquals("Jack", accountData.getUserName());
    }

    /**
     * 统计 'isLogin()' 方法被调用的次数
     */
    @Test
    public void testTimes(){

        if( true ){
            accountData.isLogin();
            accountData.isLogin();
            accountData.log(anyString(), anyString());
            verify(accountData, times(2)).isLogin();
            //两个参数为任意字符串
            verify(accountData).log(anyString(), anyString());
        }

        //或者
        if( false ){
            verify(accountData, never()).isLogin();
            verify(accountData, atLeastOnce()).isLogin();
            verify(accountData, atLeast(2)).isLogin();
            verify(accountData, times(5)).isLogin();
            verify(accountData, atMost(3)).isLogin();
        }
    }

    /**
     *验证是否调用了mockedList.add方法
     * 验证是否调用乐mockedList.clear方法
     */
    @Test
    public void testVerify(){
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    /**
     * Spy对象的方法默认调用真实的逻辑
     * mock对象的方法默认什么都不做，或直接返回默认值。
     */
    @Test
    public void testSpy(){

        //Spy对象的方法默认调用真实的逻辑
        AccountData dataSpy = spy(AccountData.class);
        dataSpy.print("spy");
        //spy对象的方法也可以指定特定的行为
        //when(accountData.log("mock", "log")).thenReturn("this is a test for mock");

        //mock
        AccountData dataMock = mock(AccountData.class);
        when(dataMock.print("mock")).thenReturn("return mock");
        System.out.println(dataMock.print("mock"));


    }

}
