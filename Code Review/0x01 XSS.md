# XSS

## 0x01 JavaScript

### 1.1 .setAttribute() 设置为属性值
#### HTML标签  事件属性可控

1. 事件属性能够执行JavaScript  (标签以及属性清单 以及影响的对应浏览器 可参考 [Cross-Site Scripting (XSS) Cheat Sheet - PortSwigger](https://portswigger.net/web-security/cross-site-scripting/cheat-sheet))

2. attr 用户可控 或 属性可控
3. 防护措施：对于onXXX 事件属性 若使用 addEventListener() 第二个参数，必须为函数或者 EventListener借口的对象 （推荐）  无 XSS注入风险
4. attr 若对其 进行 JavaScript编码，则代码不会执行（不推荐），此时 无 XSS注入风险 （通用）

<span style="background:#fff88f">实质是允许用户输入任意可执行代码</span>

```
document.getElementById("").setAttribute("", attr);
```

```
document.getElementById("").addEventListener("", greetUser(attr));
```

```
// payload
alert(1)
```

特别地， autofocus 属性 无需用户交互

```
autofocus>
```

另外，无需用户交互的场景还有：


#### a 标签 href 属性

```
// payload
javascript:alert(1)
```

<span style="background:#fff88f">若对参数 进行  httpCheck ；判断用户输入 是否以 http/https 开头，如果不是则添加 http/https；则无 XSS风险</span>
#### iframe 标签 src/srcdoc 属性

<span style="background:#fff88f">若对参数 进行  httpCheck ；判断用户输入 是否以 http/https 开头，如果不是则添加 http/https；则无 XSS风险</span>
#### HTML button 标签 formaction 属性

```
document.getElementById("button").setAttribute("formaction", attr);
```

```
// payload
javascript:alert(1)
```

<span style="background:#fff88f">若对参数 进行  httpCheck ；判断用户输入 是否以 http/https 开头，如果不是则添加 http/https；则无 XSS风险</span>

##### \[SUM\]  对于a标签 iframe标签 HTML标签 href/src/formaction 属性 payload 均需 javascript:alert(1)  若限定伪协议仅为http(s) 则无XSS 风险
#### HTML 其他标签设置属性无 XSS风险


### 1.2 Script对象 src属性

```
<script id="myScript"></script>
document.getElementById("myScript").src = attr;
```

```
var htmlStr = '<script src="' + attr +  '"></script>';
```

```
// payload
src 指向 一个有 XSS注入的任意网址  比如 depend_js/test.js
```
### 1.3 Script对象 text/textContent/innerText 参数

```
<script id="myScript"></script>
document.getElementById("myScript").text = attr;
```

<span style="background:#fff88f">实质是允许用户输入任意可执行代码</span>

text/textContent/innerText 支持的浏览器有差异
### 1.4 Range.createContextualFragment()

```
// UserInput = <img src=1 onerror=alert(1)>
var attr = document.getElementById("UserInput").value;
var range = document.createRange();

// Make the parent of the first div in the document becomes the context node
range.selectNode(document.getElementById("div").item(0));

// attr  可以为 任意标签富文本
var documentFragment = range.createContextualFragment(attr);
// 添加到指定节点
document.body.appendChild(documentFragment);
```

```
// payload
<img src=1 onerror=alert(1)>
```

<span style="background:#b1ffff"> 实质是允许输入任意标签富文本</span>

添加 DOM 节点
### 1.5 eval()

```
// alert(1)");alert(2);//
var htmlStr = '<script>eval("' + attr +  '")</script>';
```

先闭合eval函数后执行 alert(2);

不推荐用 eval() 来解析 JSON字符串；
推荐使用 JSON.parse() 解析 JSON字符串；

低版本浏览器使用 安全的 Polyfill 封装；

<span style="background:#fff88f">实质是允许用户输入任意可执行代码</span>
### 1.6 execScript()

```
// alert(1)");alert("a  或 alert(1)
var htmlStr = '<script>execScript("' + attr +  '")</script>';
```

<span style="background:#fff88f">实质是允许用户输入任意可执行代码</span>
### 1.7 new Function()

```
// alert(1));alert(2  或 alert(1)
var htmlStr = "<script>execScript(" + attr +  ")</script>";
```

<span style="background:#fff88f">实质是允许用户输入任意可执行代码</span>

### 1.8 Location 对象  函数参数

```
location=
location.href=
location.assign()
location.replace()
```

```
#1  以上4种函数 若可控 为拼接，则 httpCheck 后 经过JS编码 才无 XSS注入风险

拼接时 , 只有 httpCheck 时  或无 check
// payload
https://www.baidu.com";alert(1)"  或 javascript:alert(1)
https://www.baidu.com');alert('123  或 javascript:alert(1)

#2  若是直接作为对象函数参数，则 httpCheck 后 无 XSS注入风险
// payload
javascript:alert('xss')  或 
data:text/html;base64,PHNjcmlwdD5hbGVydCgxKTwvc2NyaXB0Pg==
```

Location 对象存储在 Window 对象 Location 属性中，表示那个窗口中当前显示的文档的 Web 地址，它的 href 属性 存放的是文档的完整URL，其他属性分别描述了 URL 的各个部分
### 1.9 setTimeout() / setInterval() / setImmediate() 

```
// payload
alert(1)
```

<span style="background:#fff88f">实质是允许用户输入任意可执行代码</span>

拼接


### \[SUM\]  除了 src类(输入伪协议javascript:  data:) 以及 DOM节点  之外 实质上都是 允许用户输入任意可执行代码  alert(1)


### 1.10 可控参数渲染页面总结


#### HTML 标签

#### HTML 属性

1.1 即所有的 HTML属性

#### JavaScript

1.2 ~ 1.9 即 JavaScript

### 1.11 不常见的危险函数

## 0x02 JSP

## 0x03 Vue

