package com.best.hello.controller.RCE;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.exception.QLException;

public class MathExpressionService {
    private ExpressRunner runner;
    private IExpressContext<String, Object> context;

    public MathExpressionService() throws Exception {
        runner = new ExpressRunner();
        context = new DefaultContext<>();

        // 注册常见数学常量
        context.put("pi", Math.PI);
        context.put("e", Math.E);

        // 注册基础的数学函数
        runner.addFunction("sin", new SinFunction());
        runner.addFunction("cos", new CosFunction());
        runner.addFunction("sqrt", new SqrtFunction());

        // 注册自定义函数，如计算阶乘
        runner.addFunction("factorial", new FactorialFunction());
    }

    /**
     * 计算用户输入的数学表达式
     *
     * @param expression 用户输入的表达式
     * @param variables 动态变量上下文
     * @return 表达式计算结果
     * @throws Exception 表达式执行异常
     */
    public Object calculate(String expression, IExpressContext<String, Object> variables) throws Exception {
        // 安全校验：验证输入表达式的合法性
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("表达式不能为空");
        }
        if (variables == null) {
            throw new IllegalArgumentException("上下文变量不能为空");
        }

        // 使用正则表达式进行表达式过滤，防止注入风险
        if (!expression.matches("^[0-9+\\-*/().a-zA-Z_\\s]+$")) {
            throw new IllegalArgumentException("非法表达式输入");
        }

        // 执行表达式计算
        return runner.execute(expression, variables, null, false, false);
    }

    public static void main(String[] args) {
        try {
            MathExpressionService service = new MathExpressionService();

            // 用户输入的表达式
            String userInput = "factorial(5) + sqrt(16) + sin(pi / 2)";

            // 上下文变量
            IExpressContext<String, Object> userContext = new DefaultContext<>();
            userContext.put("x", 10); // 可根据需要加入其他变量

            // 计算表达式
            Object result = service.calculate(userInput, userContext);
            System.out.println("计算结果: " + result);

        } catch (Exception e) {
            System.err.println("表达式计算错误: " + e.getMessage());
        }
    }
}

/**
 * 自定义阶乘函数
 */
class FactorialFunction extends com.ql.util.express.instruction.op.OperatorBase {
    @Override
    public Object executeInner(Object[] args) throws Exception {
        if (args.length != 1 || !(args[0] instanceof Number)) {
            throw new QLException("factorial 函数需要一个数字参数");
        }
        int num = ((Number) args[0]).intValue();
        return factorial(num);
    }

    private int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}

/**
 * 自定义平方根函数
 */
class SqrtFunction extends com.ql.util.express.instruction.op.OperatorBase {
    @Override
    public Object executeInner(Object[] args) throws Exception {
        if (args.length != 1 || !(args[0] instanceof Number)) {
            throw new QLException("sqrt 函数需要一个数字参数");
        }
        double num = ((Number) args[0]).doubleValue();
        return Math.sqrt(num);
    }
}

/**
 * 自定义正弦函数
 */
class SinFunction extends com.ql.util.express.instruction.op.OperatorBase {
    @Override
    public Object executeInner(Object[] args) throws Exception {
        if (args.length != 1 || !(args[0] instanceof Number)) {
            throw new QLException("sin 函数需要一个数字参数");
        }
        double num = ((Number) args[0]).doubleValue();
        return Math.sin(num);
    }
}

/**
 * 自定义余弦函数
 */
class CosFunction extends com.ql.util.express.instruction.op.OperatorBase {
    @Override
    public Object executeInner(Object[] args) throws Exception {
        if (args.length != 1 || !(args[0] instanceof Number)) {
            throw new QLException("cos 函数需要一个数字参数");
        }
        double num = ((Number) args[0]).doubleValue();
        return Math.cos(num);
    }
}
