# VerifyCodeDemo
图形验证码校验功能Demo

## 一、验证码功能需求
1. 页面显示验证码图片，用户可以输入框输入验证码
2. 输入完毕后，点击验证
3. 验证成功，提示验证通过，失败提示验证不通过
4. 用户点击验证码图片刷新验证码

## 二、需求分析
1. 验证码用户页面（verify.jsp）
   1. 输入框
   2. 验证码图片
   3. 验证按钮
2. 随机生成验证码图片（VerifyCodeUtils，VerifyCodeGenServlet）
   1. 生成验证码字符内容
   2. 绘制字符到图片
   3. 绘制干扰线
   4. 返回验证码字符内容和图片
3. 验证程序（SubmitServlet）
   1. 验证用户输入和验证字符内容是否一致
   2. 返回验证信息
