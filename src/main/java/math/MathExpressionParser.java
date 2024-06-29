package math;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MathExpressionParser {

    public static double evaluateExpression(String expression) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        // 解析并计算表达式
        Object result = engine.eval(expression);

        // 将结果转换为double
        if (result instanceof Integer) {
            return (double) ((Integer) result);
        } else if (result instanceof Double) {
            return (double) result;
        } else {
            throw new ScriptException("无法解析表达式的结果");
        }
    }

    public static void main(String[] args) {
        try {
            String expression = "3 * ((5/6) * 2) + 4*5 + 6/3.1";
            double result = evaluateExpression(expression);
            System.out.println(expression + " 的计算结果是: " + result);
        } catch (ScriptException e) {
            System.err.println("表达式解析错误: " + e.getMessage());
        }
    }
}
