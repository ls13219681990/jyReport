package com.common;

//public  class AuthorityInterceptor extends AbstractInterceptor {
public class AuthorityInterceptor {
    private static final long serialVersionUID = 1358600090729208361L;
/*
    //拦截Action处理的拦截方法
    public String intercept(ActionInvocation invocation) throws Exception {
        // 取得请求相关的ActionContext实例
    	String result = "error";
		try {
			result = invocation.invoke();
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			throw new BusinessException("数据库操作失败！");
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			throw new BusinessException("调用了未经初始化的对象或者是不存在的对象！");
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new BusinessException("IO异常！");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			throw new BusinessException("指定的类不存在！");
		} catch (ArithmeticException ex) {
			ex.printStackTrace();
			throw new BusinessException("数学运算异常！");
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
			throw new BusinessException("数组下标越界!");
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			throw new BusinessException("方法的参数错误！");
		} catch (ClassCastException ex) {
			ex.printStackTrace();
			throw new BusinessException("类型强制转换错误！");
		} catch (SecurityException ex) {
			ex.printStackTrace();
			throw new BusinessException("违背安全原则异常！");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException("操作数据库异常！");
		} catch (NoSuchMethodError ex) {
			ex.printStackTrace();
			throw new BusinessException("方法末找到异常！");
		} catch (InternalError ex) {
			ex.printStackTrace();
			throw new BusinessException("Java虚拟机发生了内部错误");
		}catch (BusinessException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new BusinessException("程序内部错误，操作失败！");
		}
		return result;    
    }*/
}

