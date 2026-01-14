package win.ixuni.yonyoudatadict;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试类
 */
public class RegexTest {

    // 新的正则表达式
    private static final Pattern ITEM_PATTERN = Pattern.compile(
            "\\{\\s*id:\\s*[\"']?([^\"',}]+)[\"']?\\s*,.*?name:\\s*[\"']([^\"']+)[\"']",
            Pattern.DOTALL);

    public static void main(String[] args) {
        // 测试用例
        String[] testCases = {
                // 简单项（分类节点）
                "{id:\"199\",name:\"0020 riart 运行框架\"}",
                // 带pId的项（叶子节点）
                "{id:\"1\",pId:\"2\",name:\"aam_appasset 应用资产实体\",url:\"./ddc/1.html\",target:\"ddc\"}",
                // 字符串ID
                "{id:\"all\",name:\"all  所有表\"}",
                // 更复杂的例子
                "{id:\"3354\",name:\"1101 webrt 运行引擎\"}"
        };

        System.out.println("=== 正则表达式测试 ===\n");

        int passed = 0;
        int failed = 0;

        for (String testCase : testCases) {
            System.out.println("测试: " + testCase);
            Matcher matcher = ITEM_PATTERN.matcher(testCase);
            if (matcher.find()) {
                String id = matcher.group(1);
                String name = matcher.group(2);
                System.out.println("  ✓ 匹配成功: id=" + id + ", name=" + name);
                passed++;
            } else {
                System.out.println("  ✗ 匹配失败!");
                failed++;
            }
            System.out.println();
        }

        System.out.println("=== 测试完成 ===");
        System.out.println("通过: " + passed + ", 失败: " + failed);

        if (failed > 0) {
            System.exit(1);
        }
    }
}
