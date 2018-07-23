package com.common;

public class CheckExceptionInterceptor {
    private static final long serialVersionUID = 1L;

	/*@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String result = "error";
		try {
			result = invocation.invoke();
		} catch (DataAccessException ex) {
			throw new BusinessException("数据库操作失败！",ex);
		} catch (NullPointerException ex) {
			throw new BusinessException("调用了未经初始化的对象或者是不存在的对象！",ex);
		} catch (RemoteException ex){
			throw new BusinessException("接口调用失败！",ex);
		} catch (IOException ex) {
			throw new BusinessException("IO异常！",ex);
		} catch (ClassNotFoundException ex) {
			throw new BusinessException("指定的类不存在！",ex);
		} catch (ArithmeticException ex) {
			throw new BusinessException("数学运算异常！",ex);
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new BusinessException("数组下标越界!",ex);
		} catch (IllegalArgumentException ex) {
			throw new BusinessException("方法的参数错误！",ex);
		} catch (ClassCastException ex) {
			throw new BusinessException("类型强制转换错误！",ex);
		} catch (SecurityException ex) {
			throw new BusinessException("违背安全原则异常！",ex);
		} catch (SQLException ex) {
			throw new BusinessException("操作数据库异常！",ex);
		} catch (NoSuchMethodError ex) {
			throw new BusinessException("方法末找到异常！",ex);
		} catch (InternalError ex) {
			throw new BusinessException("Java虚拟机发生了内部错误",ex);
		} catch (Exception ex) {
			throw new BusinessException("程序内部错误，操作失败！",ex);
		}
		return result;
	}*/


}
